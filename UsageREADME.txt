
NOTE: This file will eventually contain meaningful usage notes. For now, this is test usage.

To use in the JavaScript of a project:

alert("Result of udptransmit.whatever(): " + udptransmit.whatever());
alert("Result of udptransmit.testmethod(): " + udptransmit.testmethod());
alert("Result of udptransmit.testmethodwithargs(42, 69): " + udptransmit.testmethodwithargs(42, 69));
alert("Result of udptransmit.createDatagramPacket(etc...): " + udptransmit.createDatagramPacket("xyz", 3, "131.193.42.200", 555));
alert("After setting address again with udptransmit.setDatagramPacketAddress('131.193.42.201'): " +  udptransmit.setDatagramPacketAddress("131.193.42.201"));
alert("Gotten value " + udptransmit.testmethodthatreturnsvalue(function(value){alert(value)}));

function eddie(message) {
	alert("eddie " + message);
}

 