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

package edu.uic.udptransmit;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.json.JSONArray;
import org.json.JSONException;

public class UDPTransmit extends CordovaPlugin {
	
	DatagramPacket datagramPacket;
	DatagramSocket datagramSocket;
	boolean successInitializingTransmitter = false;
	
	// Constructor
	public UDPTransmit() {
	}
	
	// Handles and dispatches "exec" calls from the JS interface (udptransmit.js)
	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
		if("initialize".equals(action)) {
			final String host = args.getString(0);
			final int port = args.getInt(1);
			// Run the UDP transmitter initialization on its own thread (just in case, see sendMessage comment)
			cordova.getThreadPool().execute(new Runnable() {
            	public void run() {
            		this.initialize(host, port, callbackContext);
            	}
            	private void initialize(String host, int port, CallbackContext callbackContext) {
            		boolean successResolvingIPAddress = false;
            		// create packet
            		InetAddress address = null;
            		try {
            			// 'host' can be a ddd.ddd.ddd.ddd or named URL, so doesn't always resolve
            			address = InetAddress.getByName(host);
            			successResolvingIPAddress = true;
            		} catch (UnknownHostException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}            		
            		// If we were able to resolve the IP address from the host name, we're good to try to initialize
            		if (successResolvingIPAddress) {
            			byte[] bytes= new byte[0];
                		datagramPacket = new DatagramPacket(bytes, 0, address, port);
                		// create socket
                		try {
                			datagramSocket = new DatagramSocket();
                			successInitializingTransmitter = true;
    						
                		} catch (SocketException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}      
            		}  
            		if (successInitializingTransmitter)
            			callbackContext.success("Success initializing UDP transmitter using datagram socket: " + datagramSocket);
            		else
            			callbackContext.error("Error initializing UDP transmitter using datagram socket: " + datagramSocket);           			
            	}
            });
 			return true;
		}
		else if("sendMessage".equals(action)) {
			final String message = args.getString(0);
			// Run the UDP transmission on its own thread (it fails on some Android environments if run on the same thread)
			cordova.getThreadPool().execute(new Runnable() {
            	public void run() {
            		this.sendMessage(message, callbackContext);
            	}
 				private void sendMessage(String data, CallbackContext callbackContext) {
	 				boolean messageSent = false;
	 				// Only attempt to send a packet if the transmitter initialization was successful
	 				if (successInitializingTransmitter) {
 						byte[] bytes = data.getBytes();
 						datagramPacket.setData(bytes);
 						try {
 							datagramSocket.send(datagramPacket);
 							messageSent = true;
 						} catch (IOException e) {
 							// TODO Auto-generated catch block
 							e.printStackTrace();
 						}
 					}
					if (messageSent)
						callbackContext.success("Success transmitting UDP packet: " + datagramPacket);
					else
						callbackContext.error("Error transmitting UDP packet: " + datagramPacket);												
				}
			});
			return true;
		}
		else if("resolveHostName".equals(action)) {
			final String url = args.getString(0);
			// Run the UDP transmission on its own thread (it fails on some Android environments if run on the same thread)
			cordova.getThreadPool().execute(new Runnable() {
            	public void run() {
            		this.resolveHostName(url, callbackContext);
            	}
 				private void resolveHostName(String url, CallbackContext callbackContext) {
	 				boolean hostNameResolved = false;
            		InetAddress address = null;
            		try {
            			// 'host' can be a ddd.ddd.ddd.ddd or named URL, so doesn't always resolve
            			address = InetAddress.getByName(url);
            			hostNameResolved = true;
            		} catch (UnknownHostException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}            		
					if (hostNameResolved)
						callbackContext.success(address.getHostAddress());
					else
						callbackContext.error("Error resolving host name: " + url);												
				}
			});
			return true;
		}
		else if("resolveHostNameWithUserDefinedCallbackString".equals(action)) {
			final String url = args.getString(0);
			final String userString = args.getString(1);
			// Run the UDP transmission on its own thread (it fails on some Android environments if run on the same thread)
			cordova.getThreadPool().execute(new Runnable() {
            	public void run() {
            		this.resolveHostNameWithUserDefinedCallbackString(url, userString, callbackContext);
            	}
 				private void resolveHostNameWithUserDefinedCallbackString(String url, String userString, CallbackContext callbackContext) {
	 				boolean hostNameResolved = false;
            		InetAddress address = null;
            		try {
            			// 'host' can be a ddd.ddd.ddd.ddd or named URL, so doesn't always resolve
            			address = InetAddress.getByName(url);
            			hostNameResolved = true;
            		} catch (UnknownHostException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}            		
					if (hostNameResolved)
						callbackContext.success(address.getHostAddress() + "|" + userString);
					else
						callbackContext.error("|" + userString);												
				}
			});
			return true;
		}
		return false;
	}
}
