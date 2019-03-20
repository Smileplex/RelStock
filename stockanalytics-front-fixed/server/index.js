/* eslint consistent-return:0 import/order:0 */

const express = require('express');
// const WebSocket = require('ws');
const SocketIo = require('socket.io');
const logger = require('./logger');

const argv = require('./argv');
const port = require('./port');
const setup = require('./middlewares/frontendMiddleware');
const isDev = process.env.NODE_ENV !== 'production';
const ngrok =
  (isDev && process.env.ENABLE_TUNNEL) || argv.tunnel
    ? require('ngrok')
    : false;
const { resolve } = require('path');
const app = express();

// If you need a backend, e.g. an API, add your custom backend-specific middleware here
// app.use('/api', myApi);

// In production we need to pass these values in instead of relying on webpack
setup(app, {
  outputPath: resolve(process.cwd(), 'build'),
  publicPath: '/',
});

// get the intended host and port number, use localhost and port 3000 if not provided
const customHost = argv.host || process.env.HOST;
const host = customHost || null; // Let http.Server use its default IPv6/4 host
const prettyHost = customHost || 'localhost';

// use the gzipped bundle
app.get('*.js', (req, res, next) => {
  req.url = req.url + '.gz'; // eslint-disable-line
  res.set('Content-Encoding', 'gzip');
  next();
});

// Start your app.
app.listen(port, host, async err => {
  if (err) {
    return logger.error(err.message);
  }

  // Connect to ngrok in dev mode
  if (ngrok) {
    let url;
    try {
      url = await ngrok.connect(port);
    } catch (e) {
      return logger.error(e);
    }
    logger.appStarted(port, prettyHost, url);
  } else {
    logger.appStarted(port, prettyHost);
  }
});

const io = new SocketIo(8989);
require('../server/socketEvents')(io);

// const wss = new WebSocket.Server({ port: 8989 });
// const users = [];
//
// const broadcast = (data, ws) => {
//   wss.clients.forEach(client => {
//     if (client.readyState === WebSocket.OPEN && client !== ws) {
//       client.send(JSON.stringify(data));
//     }
//   });
// };
//
// wss.on('connection', ws => {
//   let index;
//   ws.on('message', message => {
//     const data = JSON.parse(message);
//     switch (data.type) {
//       case 'chat/ADD_USER': {
//         index = users.length;
//         users.push({ name: data.name, id: index + 1 });
//         ws.send(
//           JSON.stringify({
//             type: 'chat/USERS_LIST',
//             users,
//           }),
//         );
//         broadcast(
//           {
//             type: 'chat/USERS_LIST',
//             users,
//           },
//           ws,
//         );
//         break;
//       }
//       case 'chat/ADD_MESSAGE':
//         console.log('message', data);
//         broadcast(
//           {
//             type: 'chat/ADD_MESSAGE',
//             message: data.message,
//             author: data.author,
//           },
//           ws,
//         );
//         break;
//       default:
//         break;
//     }
//   });
// });
