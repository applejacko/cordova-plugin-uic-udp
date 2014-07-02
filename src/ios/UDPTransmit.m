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
}

// Initializer for the packet and socket, takes a desination IP address and socket number
- (void) initialize:(CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
		CDVPluginResult* pluginResult = nil;
		// Allocate the memory
		memset(&broadcastAddr, 0, sizeof broadcastAddr);
		broadcastAddr.sin_family = AF_INET;
		
		// Set the destination IP address
		const char * ip_address = ((NSString *)[command.arguments objectAtIndex:0]).cString;
		inet_pton(AF_INET, ip_address, &broadcastAddr.sin_addr); // Set the broadcast IP address
		
		// Set the destination port #
		NSUInteger thePort = [[command.arguments objectAtIndex:1] integerValue];
		broadcastAddr.sin_port = htons(thePort); // Set port, e.g., 4445
		
		// 	Create the socket
		DatagramSocketC = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP);
		int broadcastEnable=1;
		setsockopt(DatagramSocketC, SOL_SOCKET, SO_BROADCAST, &broadcastEnable, sizeof(broadcastEnable));
		
		NSString* socket = [NSString stringWithFormat:@"%i", DatagramSocketC];
		
		if (DatagramSocketC != 0)
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
		CDVPluginResult* pluginResult = nil;
		messageToSend = ((NSString *)[command.arguments objectAtIndex:0]).cString;
		ssize_t result = sendto(DatagramSocketC, messageToSend, strlen(messageToSend), 0, (struct sockaddr*)&broadcastAddr, sizeof broadcastAddr);
		
		if (result != 0)
		pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:[@"Success transmitting UDP packet: " stringByAppendingString:[command.arguments objectAtIndex:0]]];
		else
		pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:[@"Error transmitting UDP packet: " stringByAppendingString:[command.arguments objectAtIndex:0]]];
		
		[self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
	}];
}

@end