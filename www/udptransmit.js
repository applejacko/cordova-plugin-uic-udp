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
				 function(){alert("success initializing");},
				 function(error){alert("error initializing: " + error);},
				 "UDPTransmit",
				 "initialize",
				 [host, port]);
	return true;
},

sendMessage: function(message) {
	cordova.exec(
				 function(){alert("success sending message");},
				 function(error){alert("error sending message: " + error);},
				 "UDPTransmit",
				 "sendMessage",
				 [message]);
	return true;
}
	
};
// End of cut and paste area
