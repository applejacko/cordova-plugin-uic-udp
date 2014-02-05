
NOTE: This file will eventually contain meaningful usage notes. For now, this is test usage.

To use in the JavaScript of a project:

alert("Result of udptransmit.createDatagramPacket(etc...): " + udptransmit.createDatagramPacket("xyz", 3, "131.193.42.200", 555));
alert("After getting address with udptransmit.getDatagramPacketAddress(etc...): " +  udptransmit.getDatagramPacketAddress(function(value){alert("Callback returned: " + value)}));
alert("After resetting address with udptransmit.setDatagramPacketAddress('131.193.42.201'): " +  udptransmit.setDatagramPacketAddress("131.193.42.201"));
alert("After getting address again with udptransmit.getDatagramPacketAddress(etc...): " +  udptransmit.getDatagramPacketAddress(function(value){alert("Callback returned: " + value)}));
 
// ========== temp junk ======================================
 
//      alert("Result of udptransmit.whatever(): " + udptransmit.whatever());
//	alert("Result of udptransmit.testmethod(): " + udptransmit.testmethod());
//	alert("Result of udptransmit.testmethodwithargs(42, 69): " + udptransmit.testmethodwithargs(42, 69));
// 	alert("Gotten value " + udptransmit.testmethodthatreturnsvalue(function(value){alert("Callback returned: " + value)}));
 
// ========== end temp junk =================================
