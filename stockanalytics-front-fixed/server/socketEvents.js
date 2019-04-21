module.exports = io => {
  const rooms = new Map();
  io.on('connection', socket => {
    let currentRoomName;

    function leaveRoom(roomName) {
      const targetRoom = rooms.get(roomName);
      const count = targetRoom ? targetRoom && targetRoom.size - 1 : -1;

      rooms.set(roomName, {
        type: targetRoom && targetRoom.type,
        size: count,
      });

      if (count <= 0) {
        rooms.delete(roomName);
      }

      socket.leave(roomName);
      currentRoomName = '';
    }

    socket.on('chat mounted', () => {
      // TODO: Does the server need to know the user?
      io.sockets.emit('update rooms', JSON.stringify([...rooms]));
    });
    socket.on('leave room', data => {
      const room = JSON.parse(data);
      leaveRoom(room.name);
      io.sockets.emit('update rooms', JSON.stringify([...rooms]));
    });
    socket.on('disconnect', () => {
      leaveRoom(currentRoomName);
      io.sockets.emit('update rooms', JSON.stringify([...rooms]));
    });

    socket.on('join room', data => {
      console.log('join-room-data', data);
      const room = JSON.parse(data);
      if (room.name !== currentRoomName) {
        leaveRoom(currentRoomName);

        const count = rooms.get(room.name)
          ? rooms.get(room.name) && rooms.get(room.name).size + 1
          : 1;
        rooms.set(room.name, {
          type: room.type,
          size: count,
        });
        currentRoomName = room.name;
        socket.join(room.name);
      }

      console.log('socketEvent', JSON.stringify([...rooms]));
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
