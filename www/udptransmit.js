var exec = require('cordova/exec');
var platform = require('cordova/platform');

module.exports = {
	whatever: function() {
		return 42;
	},
	createDatagramPacket: function(data, length, host, port) {
		// Order of parameters:
		// - callback function
		// - error function
		// - native class name
		// - native method name
		// - arguments for method
		exec(null, null, "UDPTransmit", "createDatagramPacket", [data, length, host, port]);
		return true;
	}
};
