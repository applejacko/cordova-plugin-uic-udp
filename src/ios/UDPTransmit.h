#import <Cordova/CDVPlugin.h>

#import <CoreFoundation/CFSocket.h>
#include <CFNetwork/CFNetwork.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

@interface UDPTransmit : CDVPlugin

- (id)init;
- (void)initialize:(CDVInvokedUrlCommand*)command;
- (void)sendMessage:(CDVInvokedUrlCommand*)command;
- (void)createDatagramPacket:(CDVInvokedUrlCommand*)command;
- (void)setDatagramPacketAddress:(CDVInvokedUrlCommand*)command;
- (void)getDatagramPacketAddress:(CDVInvokedUrlCommand*)command;
- (void)setDatagramPacketPort:(CDVInvokedUrlCommand*)command;
- (void)getDatagramPacketPort:(CDVInvokedUrlCommand*)command;
- (void)setDatagramPacketData:(CDVInvokedUrlCommand*)command;
- (void)getDatagramPacketData:(CDVInvokedUrlCommand*)command;
- (void)createDatagramSocket:(CDVInvokedUrlCommand*)command;
- (void)sendDatagramPacket:(CDVInvokedUrlCommand*)command;

	@end
