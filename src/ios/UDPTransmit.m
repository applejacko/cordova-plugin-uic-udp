/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at
 
 http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

// This plugin sends UDP packets.

#import "UDPTransmit.h"

@implementation UDPTransmit
{
	// Regular C implementation:
	char * messageToSend;
	struct sockaddr_in broadcastAddr;
	int DatagramSocketC;
	Boolean successInitializingTransmitter;
}

// Initializer for the packet and socket, takes a desination IP address and socket number
- (void) initialize:(CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
		CDVPluginResult* pluginResult = nil;
		successInitializingTransmitter = false;
		// Allocate the memory
		memset(&broadcastAddr, 0, sizeof broadcastAddr);
		broadcastAddr.sin_family = AF_INET;
		
		// Set the destination IP address
		const char * ip_address_or_url = ((NSString *)[command.arguments objectAtIndex:0]).cString;
		// First, assume it's a ddd.ddd.ddd.ddd address
		int result = inet_pton(AF_INET, ip_address_or_url, &broadcastAddr.sin_addr); // Set the broadcast IP address
		// If that failed, it might be in www.xxxyyyzzz.com domain name format
		if (result != 1) { // 1 = SUCCESS
			// Resolve host name to IP address
			struct hostent *host_entry = gethostbyname(ip_address_or_url);
			// If we were able to resolve the IP address from the host name, we're good to initialize
			if (host_entry != nil) {
				
				// Extract the ddd.ddd.ddd.ddd entry from the host_entry
				char *ip_address_from_url;
				ip_address_from_url = inet_ntoa(*((struct in_addr *)host_entry->h_addr_list[0]));
				
				// Convert ddd.ddd.ddd.ddd to binary form
				result = inet_pton(AF_INET, ip_address_from_url, &broadcastAddr.sin_addr);
			}
		}
		
		// If we could resolve it
		if (result == 1) {
			
			// Set the destination port #
			NSUInteger thePort = [[command.arguments objectAtIndex:1] integerValue];
			broadcastAddr.sin_port = htons(thePort); // Set port, e.g., 4445
			
			// 	Create the socket
			DatagramSocketC = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP);
			int broadcastEnable=1;
			setsockopt(DatagramSocketC, SOL_SOCKET, SO_BROADCAST, &broadcastEnable, sizeof(broadcastEnable));
			
			successInitializingTransmitter = true;
		}
		
		NSString* socket = [NSString stringWithFormat:@"%i", DatagramSocketC];
		if (DatagramSocketC != 0 && successInitializingTransmitter)
			pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:[@"Success initializing UDP transmitter using datagram socket: " stringByAppendingString:socket]];
		else
			pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:[@"Error initializing UDP transmitter using datagram socket: " stringByAppendingString:socket]];
		
		[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
	}];
}

// Sends a message to the IP and port set up in the initializer
- (void) sendMessage:(CDVInvokedUrlCommand*)command
{
	[self.commandDelegate runInBackground:^{
		
		ssize_t result = 0;
		CDVPluginResult* pluginResult = nil;
		Boolean messageSent = false;
		
		// Only attempt to send a packet if the transmitter initialization was successful
		if (successInitializingTransmitter) {
			messageToSend = ((NSString *)[command.arguments objectAtIndex:0]).cString;
			result = sendto(DatagramSocketC, messageToSend, strlen(messageToSend), 0, (struct sockaddr*)&broadcastAddr, sizeof broadcastAddr);
			messageSent = true;
		}
		
		if (messageSent)
			pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:[@"Success transmitting UDP packet: " stringByAppendingString:[command.arguments objectAtIndex:0]]];
		else
			pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:[@"Error transmitting UDP packet: " stringByAppendingString:[command.arguments objectAtIndex:0]]];
		
		[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
	}];
}

// Returns a ddd.ddd.ddd.ddd type IP address from a named URL host name of type whatever.mydomain.com
- (void) resolveHostName:(CDVInvokedUrlCommand*)command
{
	[self.commandDelegate runInBackground:^{
		
		CDVPluginResult* pluginResult = nil;
		Boolean nameResolved = false;
		
		char *ip_address_from_url = "";
		const char * url = ((NSString *)[command.arguments objectAtIndex:0]).cString;
		struct hostent *host_entry = gethostbyname(url);
		if (host_entry != nil) {
			ip_address_from_url = inet_ntoa(*((struct in_addr *)host_entry->h_addr_list[0]));
			nameResolved = true;
		}
		NSString* return_value = [NSString stringWithUTF8String:ip_address_from_url];
		
		if (nameResolved)
			pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:return_value];
		else
			pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:[@"Error resolving: " stringByAppendingString:[command.arguments objectAtIndex:0]]];
		
		[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
	}];
}

// Returns a ddd.ddd.ddd.ddd type IP address from a named URL host name of type whatever.mydomain.com, plus the custom string sent in as a single string, pipe-delimired
// e.g., "123.456.789.123|mystring" (this can be used to control waht your application does after the callback executes, since custom callbacks aren't possible with plugins)
- (void) resolveHostNameWithUserDefinedCallbackString:(CDVInvokedUrlCommand*)command
{
	[self.commandDelegate runInBackground:^{
		
		CDVPluginResult* pluginResult = nil;
		Boolean nameResolved = false;
		
		char *ip_address_from_url = "";
		const char * url = ((NSString *)[command.arguments objectAtIndex:0]).cString;
		struct hostent *host_entry = gethostbyname(url);
		if (host_entry != nil) {
			ip_address_from_url = inet_ntoa(*((struct in_addr *)host_entry->h_addr_list[0]));
			nameResolved = true;
		}
		NSString* return_value = [NSString stringWithUTF8String:ip_address_from_url];
		
		if (nameResolved)
			pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:[[return_value stringByAppendingString:@"|"]stringByAppendingString:[command.arguments objectAtIndex:1]]];
		else
			pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:[[return_value stringByAppendingString:@"|"]stringByAppendingString:[command.arguments objectAtIndex:1]]];
		
		[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
	}];
	
}


@end