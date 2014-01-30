package edu.uic.udptransmit;

import org.apache.cordova.CordovaPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class UDPTransmit extends CordovaPlugin {
	
	public UDPTransmit() {
    }
	
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("alert")) {
            this.alert(args.getString(0), args.getString(1), args.getString(2), callbackContext);
            return true;
        }
        else {
            return false;
        }
    }
	
    public synchronized void alert(final String message, final String title, final String buttonLabel, final CallbackContext callbackContext) {
		
        final CordovaInterface cordova = this.cordova;
		
        Runnable runnable = new Runnable() {
            
        	public void run() {
				
                AlertDialog.Builder dlg = new AlertDialog.Builder(cordova.getActivity());
                dlg.setMessage(message);
                dlg.setTitle(title);
                dlg.setCancelable(true);
                dlg.setPositiveButton(buttonLabel,
									  new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
					}
				});
                dlg.setOnCancelListener(new AlertDialog.OnCancelListener() {
                    public void onCancel(DialogInterface dialog)
                    {
                        dialog.dismiss();
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, 0));
                    }
                });
				
                dlg.create();
                dlg.show();
            };
        };
        this.cordova.getActivity().runOnUiThread(runnable);
    }
}