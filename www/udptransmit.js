var exec = require('cordova/exec');
var platform = require('cordova/platform');

module.exports = {
	// This method has no interaction with the Native side
	whatever: function() {
		return 42;
	},
	// This method has interaction with the Native side
	testmethod: function() {
		cordova.exec(
					 function(){alert("success calling testmethod");},
					 function(error){alert("error calling testmethod: " + error);},
					 "UDPTransmit",
					 "testMethod",
					 []);
		return true;
	},
	// This method has interaction with the Native side and has parameters
	testmethodwithargs: function(x, y) {
		cordova.exec(
					 function(){alert("success calling testmethodwithargs");},
					 function(error){alert("error calling testmethodwithargs: " + error);},
					 "UDPTransmit",
					 "testMethodWithArgs",
					 [x, y]);
		return true;
	},
	createDatagramPacket: function(data, length, host, port) {
		// Order of parameters:
		// - callback function
		// - error function
		// - native class name
		// - native method name
		// - arguments for method
		//		return exec(null, null, "UDPTransmit", "createDatagramPacket", [data, length, host, port]);
		//		exec(null, null, "UDPTransmit", "createDatagramPacket", [data, length, host, port]);
		//		exec("successMakingDatagramPacket", "errorMakingDatagramPacket", "UDPTransmit", "createDatagramPacket", [data, length, host, port]);
		cordova.exec(
					 function(){alert("success creating datagram packet");},
					 function(error){alert("error creating datagram packet: " + error);},
					 "UDPTransmit",
					 "createDatagramPacket",
					 [data, length, host, port]);
		return true;
	}
};


/*
 function successMakingDatagramPacket() {
 alert("In successMakingDatagramPacket");
 }
 
 function errorMakingDatagramPacket() {
 alert("In errorMakingDatagramPacket");
 }
 */

