
To use in the JavaScript of a project:

1. Initialize once, setting the IP address and port of the intended receiver:

	udptransmit.initialize("131.193.42.36", 4445);

   OR domain name (see NOTES below), and port of the intended receiver:

	udptransmit.initialize(“www.mydomain.com”, 4445);

2. Check the success and/or error callbacks to see if the transmitter was properly initialized:

	function UDPTransmitterInitializationSuccess(success) {
	  console.log(success);
	}

	function UDPTransmitterInitializationError(error) {
	  console.log(error);
	}

3. Then send messages, over and over:

	udptransmit.sendMessage("Hello from my mobile app.”);
	udptransmit.sendMessage(“Another message”);
	udptransmit.sendMessage(“Yet another message”);

4. Check the success and/or error callbacks to see that each message was transmitted:

	function UDPTransmissionSuccess(success) {
	  console.log(success);
	}

	function UDPTransmissionError(error) {
	  console.log(error);
	}


NOTES
—----

Note that initialization using the “www.mydomain.com” approach requires resolution of the named URL to a
ddd.ddd.ddd.ddd-type IP address, which requires Internet access to a DNS server. The plug-in will return 
an error if it does fail to resolve to an IP address, and in turn initialize the transmitter. 

Until the initialization returns success in creating a transmitter, it will not attempt to send packets 
when udptransmit.sendMessage() is called. Regardless of this safety feature, is recommended that you test 
that the transmitter was properly initialized via the success/error callbacks before sending packets. 
That way your code can try to initialize again before sending packets, if it failed the first time due to 
DNS resolution (i.e., due to being offline, generally) issues.

If you want the transmitter to safely and “always” initialize (provided you send it a valid address parameter) 
regardless of Internet connection status, use the  "131.193.42.36" approach, which requires no DNS lookup, 
but of course limits you to a fixed IP address instead of a URL.


