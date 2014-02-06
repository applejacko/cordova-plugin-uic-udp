
NOTE: This file will eventually contain meaningful usage notes. For now, this is test usage.

To use in the JavaScript of a project:

//        alert("Result of udptransmit.createDatagramPacket(etc...): " + udptransmit.createDatagramPacket("Hello From UDP Land!", 20, "131.193.42.201", 4445));
//		alert("After getting address with udptransmit.getDatagramPacketAddress(etc...): " +  udptransmit.getDatagramPacketAddress(function(value){alert("Callback returned: " + value)}));
//		alert("After resetting address with udptransmit.setDatagramPacketAddress('10.0.1.25'): " +  udptransmit.setDatagramPacketAddress("10.0.1.25"));
//		alert("After getting address again with udptransmit.getDatagramPacketAddress(etc...): " +  udptransmit.getDatagramPacketAddress(function(value){alert("Callback returned: " + value)}));
// 		alert("Creating socket: " + udptransmit.createDatagramSocket());
// 		alert("Sending packet: " + udptransmit.sendDatagramPacket());
 		
         	// Create a brand new packet and socket, and send the packet (with incorrect address and port)
        	udptransmit.createDatagramPacket("This message will fail because I have the wrong IP address and port", "10.0.1.27", 4269);
 		udptransmit.createDatagramSocket();
 		udptransmit.sendDatagramPacket();
 		
        	// Test the getter and setter for the address, setting it to the correct value this time
 		udptransmit.getDatagramPacketAddress(function(value){alert("Callback returned: " + value)});
 		udptransmit.setDatagramPacketAddress("10.0.1.25");
  		udptransmit.getDatagramPacketAddress(function(value){alert("Callback returned: " + value)});
  		
        	// Test the getter and setter for the port, setting it to the correct value this time
 		udptransmit.getDatagramPacketPort(function(value){alert("Callback returned: " + value)});
 		udptransmit.setDatagramPacketPort(4445);
  		udptransmit.getDatagramPacketPort(function(value){alert("Callback returned: " + value)});
  		
 		// Test the getter and setter for the data (payload), setting it to a new value;
 		udptransmit.getDatagramPacketData(function(value){alert("Callback returned: " + value)});
 		udptransmit.setDatagramPacketData("This message will go through because I have the correct IP address and port");
  		udptransmit.getDatagramPacketData(function(value){alert("Callback returned: " + value)});
  		
  		// Try send it again
 		udptransmit.sendDatagramPacket();
 		
 		// Now just send a bunch of messages in a row
 		udptransmit.setDatagramPacketData("A long, long time ago");
 		udptransmit.sendDatagramPacket();
 		udptransmit.setDatagramPacketData("I can still remember");
 		udptransmit.sendDatagramPacket();
 		udptransmit.setDatagramPacketData("How the music used to make me smile");
 		udptransmit.sendDatagramPacket();
		udptransmit.setDatagramPacketData("Da da da da da");
 		udptransmit.sendDatagramPacket();
		// This will probably be JSON in the mobile app, transmitting a short-lived ID (to maintain anonymity), 
		// a timestamp, latitude, and longitude);
		udptransmit.setDatagramPacketData("AK275S90HV220717", 12761276712676712, 41.8819, -87.6278");
 		udptransmit.sendDatagramPacket();
 
// ========== temp junk ======================================
 
//      alert("Result of udptransmit.whatever(): " + udptransmit.whatever());
//	alert("Result of udptransmit.testmethod(): " + udptransmit.testmethod());
//	alert("Result of udptransmit.testmethodwithargs(42, 69): " + udptransmit.testmethodwithargs(42, 69));
// 	alert("Gotten value " + udptransmit.testmethodthatreturnsvalue(function(value){alert("Callback returned: " + value)}));
 
// ========== end temp junk =================================
