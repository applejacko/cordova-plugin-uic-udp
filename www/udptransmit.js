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
				 // To access the success and error callbacks for initialization, these two functions should be in your project:
				 // UDPTransmitterInitializationSuccess(success)
				 // UDPTransmitterInitializationError(error)
				 function(success){UDPTransmitterInitializationSuccess(success);},
				 function(error){UDPTransmitterInitializationError(error);},
				 "UDPTransmit",
				 "initialize",
				 [host, port]);
	return true;
},

sendMessage: function(message) {
	cordova.exec(
				 // To access the success and error callbacks for packet transmission, these two functions should be in your project:
				 // UDPTransmissionSuccess(success)
				 // UDPTransmissionError(error)
				 function(success){UDPTransmissionSuccess(success);},
				 function(error){UDPTransmissionError(error);},
				 "UDPTransmit",
				 "sendMessage",
				 [message]);
	return true;
}
	
};
// End of cut and paste area
