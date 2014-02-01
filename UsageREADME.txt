To use in the JavaScript of a project:

alert("Result of udptransmit.whatever(): " + udptransmit.whatever());
alert("Result of udptransmit.testmethod(): " + udptransmit.testmethod());
alert("Result of udptransmit.testmethodwithargs(42, 69): " + udptransmit.testmethodwithargs(42, 69));
		
// This is result of the udptransmit.js:
var madeDatagramPacket = udptransmit.createDatagramPacket("xyz", 3, "www.travelmidwest.com", 555);
// alert(madeDatagramPacket);
// successMakingDatagramPacket();
