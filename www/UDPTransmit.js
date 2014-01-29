window.udptransmit = function(str, callback) {
	cordova.exec(
				 
				 // success callback
				 callback,
				 
				 // success callback
				 function(err) {
				 /* callback('UDP transmit failed.'); */
				 },
				 
				 // the OS-level service (class)
				 "Datagram",
				 
				 // the OS-level action (method of the class)
				 "send",
				 
				 // arguments (of the method)
				 [str]
				 
				 );
};