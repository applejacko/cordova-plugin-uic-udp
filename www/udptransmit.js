var exec = require('cordova/exec');
var platform = require('cordova/platform');

// Start of cut and paste area (to put back in Git repo version of this file)
module.exports = {
	
	// Order of parameters on all calls:
	// - callback function
	// - error function
	// - native class name
	// - native method name
	// - arguments for method
	
initialize: function(host, port) {
	cordova.exec(
				 function(){console.log("success initializing UDP transmitter");},
				 function(error){console.log("error initializing UDP transmitter: " + error);},
				 "UDPTransmit",
				 "initialize",
				 [host, port]);
	return true;
},

sendMessage: function(message) {
	cordova.exec(
				 function(){console.log("success sending message via UDP");},
				 function(error){console.log("error sending message via UDP: " + error);},
				 "UDPTransmit",
				 "sendMessage",
				 [message]);
	return true;
}
	
};
// End of cut and paste area
