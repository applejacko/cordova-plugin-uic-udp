cordova.define("edu.uic.udptransmit", function(require, exports, module) {// require, exports, module are predefined
			   
			   var exec = cordova.require('cordova/exec');
			   
			   // Events: 'message', 'error'
			   function Socket(type) {
			   this._multicastSocket = type === 'multicast-udp4';
			   this._socketId = ++Socket.socketCount;
			   this._eventHandlers = { };
			   Socket.sockets[this._socketId] = this;
			   
			   exec(null, null, 'Datagram', 'create', [ this._socketId, this._multicastSocket ]);
			   }
			   
			   Socket.socketCount = 0;
			   Socket.sockets = { };
			   
			   Socket.prototype.on = function (event, callback) {
			   this._eventHandlers[event] = callback;
			   };
			   
			   Socket.prototype.bind = function (port, callback) {
			   callback = callback || function () { };
			   exec(callback.bind(null, null), callback.bind(null), 'Datagram', 'bind', [ this._socketId, port ]);
			   };
			   
			   Socket.prototype.close = function () {
			   exec(null, null, 'Datagram', 'close', [ this._socketId ]);
			   delete Socket.sockets[this._socketId];
			   this._socketId = 0;
			   };
			   
			   // sends utf-8
			   Socket.prototype.send = function (buffer, destAddress, destPort, callback) {
			   callback = callback || function () { };
			   exec(callback.bind(null, null), // success
					callback.bind(null), // failure
					'Datagram',
					'send',
					[ this._socketId, buffer, destAddress, destPort ]);
			   };
			   
			   Socket.prototype.address = function () {
			   };
			   
			   Socket.prototype.joinGroup = function (address, callback) {
			   callback = callback || function () { };
			   if (!this._multicastSocket) throw new Error('Invalid operation');
			   exec(callback.bind(null, null), callback.bind(null), 'Datagram', 'joinGroup', [ this._socketId, address ]);
			   };
			   
			   Socket.prototype.leaveGroup = function (address, callback) {
			   callback = callback || function () { };
			   if (!this._multicastSocket) throw new Error('Invalid operation');
			   exec(callback.bind(null, null), callback.bind(null), 'Datagram', 'leaveGroup', [ this._socketId, address ]);
			   };
			   
			   function createSocket(type) {
			   if (type !== 'udp4' && type !== 'multicast-udp4') {
			   throw new Error('Illegal Argument, only udp4 and multicast-udp4 supported');
			   }
			   return new Socket(type);
			   }
			   
			   function onMessage(id, msg, remoteAddress, remotePort) {
			   var socket = Socket.sockets[id];
			   if (socket && 'message' in socket._eventHandlers) {
			   socket._eventHandlers['message'].call(null, msg, { address: remoteAddress, port: remotePort });
			   }
			   }
			   
			   module.exports = {
			   createSocket: createSocket,
			   _onMessage: onMessage
			   }
			   
			   });