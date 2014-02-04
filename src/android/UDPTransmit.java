package edu.uic.udptransmit;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONException;

public class UDPTransmit extends CordovaPlugin {
	
	DatagramPacket datagramPacket;
	DatagramSocket datagramSocket;
	
	// Constructor
	public UDPTransmit() {
	}
	
	public void setDatagramPacketAddress(String address) {
		InetAddress addr = null;
		try {
			addr = InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		datagramPacket.setAddress(addr);
		System.out.println("datagramPacket = " + datagramPacket + ", address = " + datagramPacket.getAddress() + ":" + datagramPacket.getPort());
		
		
	}
	//
	//	public String getDatagramPacketAddress() {
	//		return datagramPacket.getAddress().toString();
	//	}
	
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
		else if("createDatagramPacket".equals(action)) {
			// Call the function to create the Datagram packet
			this.createDatagramPacket(args.getString(0), args.getInt(1), args.getString(2), args.getInt(3));
			callbackContext.success();
			return true;
		}
		
		
		
		
		
		
		else if("setDatagramPacketAddress".equals(action)) {
			this.setDatagramPacketAddress(args.getString(0));
			return true;
		}
		//		else if("getDatagramPacketAddress".equals(action)) {
		//		}
		
		return false;
	}
	
    public void testMethod() {
    	System.out.println("In testMethod");
    }
	
    public void testMethodWithArgs(int a, int b) {
    	System.out.println("In testMethodWithArgs " + a + ", " + b);
    }
    
    
	public boolean createDatagramPacket(String data, int length, String host, int port) {
		
		//System.out.println("About to make DatagramPacket");
		
		// convert String to bytes[] for arg 0
		byte[] bytes = data.getBytes();
		
		// create InetAddress for arg 2
		InetAddress address = null;
		try {
			address = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		datagramPacket = new DatagramPacket(bytes, length, address, port);
		
		System.out.println("datagramPacket = " + datagramPacket + ", address = " + datagramPacket.getAddress() + ":" + datagramPacket.getPort());
		
		if (datagramPacket != null)
			return true;
		else
			return false;
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