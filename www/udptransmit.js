cordova.define("edu.uic.udptransmit.udptransmit", function(require, exports, module) {var exec = require('cordova/exec');
			   var platform = require('cordova/platform');
			   
			   module.exports = {
			   
			   // Order of parameters on all calls:
			   // - callback function
			   // - error function
			   // - native class name
			   // - native method name
			   // - arguments for method
			   
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
			   
			   
			   testmethodthatreturnsvalue: function(callback) {
			   cordova.exec(
							callback,
							function(error){alert("error calling testmethodthatreturnsvalue: " + error);},
							"UDPTransmit",
							"testmethodThatReturnsValue",
							[]);
			   return true;
			   },
			   
			   
			   
			   createDatagramPacket: function(data, length, host, port) {
			   cordova.exec(
							function(){alert("success creating datagram packet");},
							function(error){alert("error creating datagram packet: " + error);},
							"UDPTransmit",
							"createDatagramPacket",
							[data, length, host, port]);
			   return true;
			   },
			   setDatagramPacketAddress: function(host) {
			   cordova.exec(
							function(){alert("success setting host address");},
							function(error){alert("error setting host address: " + error);},
							"UDPTransmit",
							"setDatagramPacketAddress",
							[host]);
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
			   
			   });
