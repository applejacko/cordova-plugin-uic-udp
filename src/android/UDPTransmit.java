package edu.uic.udptransmit;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.json.JSONArray;
import org.json.JSONException;

public class UDPTransmit extends CordovaPlugin {
	
	DatagramPacket datagramPacket;
	DatagramSocket datagramSocket;
	
	// Constructor
	public UDPTransmit() {
	}
	
	// Handles and dispatches "exec" calls from the JS interface piece (udptransmit.js)
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if("testMethod".equals(action)) {
			this.testMethod();
			callbackContext.success();
			return true;
		}
		else if("testMethodWithArgs".equals(action)) {
			this.testMethodWithArgs(args.getInt(0), args.getInt(1));
			callbackContext.success();
			return true;
		}
		// Hmmmm...how do we marshal InetAddress over...
		//		else if("createDatagramPacket".equals(action)) {
		//			this.createDatagramPacket(args.getString(0), args.getInt(1), args.getClass(2), args.getInt(3));
		//		}
		
		return false;
	}
	
    public void testMethod() {
    	System.out.println("In testMethod");
    }
	
    public void testMethodWithArgs(int a, int b) {
    	System.out.println("In testMethodWithArgs " + a + ", " + b);
    }
    
    
	
	public boolean createDatagramPacket(byte[] data, int length, InetAddress host, int port) {
		//System.out.println("About to make DatagramPacket");
		//datagramPacket = new DatagramPacket(data, length, host, port);
		//		if (datagramPacket instanceof DatagramPacket)
		return true;
		//		else
		//			return false;
	}
	
	//	public void successMakingDatagramPacket() {
	//		System.out.println("Success making DatagramPacket");
	//
	//	}
	//
	//	public void errorMakingDatagramPacket() {
	//		System.out.println("Error making DatagramPacket");
	//	
	//	}
}