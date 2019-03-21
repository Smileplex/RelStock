module.exports = io => {
  const rooms = new Map();
  io.on('connection', socket => {
    let currentRoomName;
    // socket.join('Lobby');
    socket.on('chat mounted', () => {
      // TODO: Does the server need to know the user?
      socket.emit('load rooms', rooms);
    });
    socket.on('leave room', data => {
      console.log('leaving room ', data);
      const room = JSON.parse(data);

      rooms.set(room.name, rooms.get(room.name) - 1);
      if (rooms.get(room.name) <= 0) rooms.delete(room.name);

      socket.leave(room.name);
      io.sockets.emit('update rooms', JSON.stringify([...rooms]));
    });
    socket.on('disconnect', () => {
      rooms.set(currentRoomName, rooms.get(currentRoomName) - 1);
      if (rooms.get(currentRoomName) <= 0) rooms.delete(currentRoomName);
      io.sockets.emit('update rooms', JSON.stringify([...rooms]));
    });
    socket.on('join room', data => {
      const room = JSON.parse(data);
      const count = rooms.get(room.name) ? rooms.get(room.name) + 1 : 1;
      rooms.set(room.name, count);
      currentRoomName = room.name;
      socket.join(room.name);
      io.sockets.emit('update rooms', JSON.stringify([...rooms]));
    });
    socket.on('new message', data => {
      const { targetRoom } = JSON.parse(data);
      socket.broadcast.to(targetRoom).emit('new bc message', data);
    });
    socket.on('new channel', channel => {
      socket.broadcast.emit('new channel', channel);
    });
    socket.on('typing', data => {
      socket.broadcast.to(data.channel).emit('typing bc', data.user);
      socket.on('stop typing', () => {
        socket.broadcast.to(data.channel).emit('stop typing bc', data.user);
      });
      socket.on('new private channel', (socketID, channel) => {
        socket.broadcast.to(socketID).emit('receive private channel', channel);
      });
    });
  });
};
