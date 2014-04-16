#import <Cordova/CDVPlugin.h>

#import <CoreFoundation/CFSocket.h>
#include <CFNetwork/CFNetwork.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

@interface UDPTransmit : CDVPlugin

- (void)initialize:(CDVInvokedUrlCommand*)command;
- (void)sendMessage:(CDVInvokedUrlCommand*)command;

@end
