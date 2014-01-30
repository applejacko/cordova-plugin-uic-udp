#import "UDPTransmit.h"

#import <Cordova/NSDictionary+Extensions.h>
#import <Cordova/NSArray+Comparisons.h>

#define DIALOG_TYPE_ALERT @"alert"
#define DIALOG_TYPE_PROMPT @"prompt"


@implementation UDPTransmit
	
	- (void)showDialogWithMessage:(NSString*)message title:(NSString*)title buttons:(NSArray*)buttons defaultText:(NSString*)defaultText callbackId:(NSString*)callbackId dialogType:(NSString*)dialogType
	{
		CDVAlertView* alertView = [[CDVAlertView alloc]
								   initWithTitle:title
								   message:message
								   delegate:self
								   cancelButtonTitle:nil
								   otherButtonTitles:nil];
		
		alertView.callbackId = callbackId;
		
		int count = [buttons count];
		
		for (int n = 0; n < count; n++) {
			[alertView addButtonWithTitle:[buttons objectAtIndex:n]];
		}
		
		if ([dialogType isEqualToString:DIALOG_TYPE_PROMPT]) {
			alertView.alertViewStyle = UIAlertViewStylePlainTextInput;
			UITextField* textField = [alertView textFieldAtIndex:0];
			textField.text = defaultText;
		}
		
		[alertView show];
	}
	
	- (void)alert:(CDVInvokedUrlCommand*)command
	{
		NSString* callbackId = command.callbackId;
		NSString* message = [command argumentAtIndex:0];
		NSString* title = [command argumentAtIndex:1];
		NSString* buttons = [command argumentAtIndex:2];
		
		[self showDialogWithMessage:message title:title buttons:@[buttons] defaultText:nil callbackId:callbackId dialogType:DIALOG_TYPE_ALERT];
	}
	
	
	@end

@implementation CDVAlertView
	
	@synthesize callbackId;
	
	@end

