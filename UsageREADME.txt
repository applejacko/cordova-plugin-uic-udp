
To use in the JavaScript of a project:

// Simple usage, initialize once, setting the IP address and port of the intended receiver
udptransmit.initialize("131.193.42.36", 4445);

// Then send messages over and over
udptransmit.sendMessage("Hello from my mobile app.”);
udptransmit.sendMessage(“Another message”);
udptransmit.sendMessage(“Yet another message”);
