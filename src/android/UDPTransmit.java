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
            		// create packet
            		InetAddress address = null;
            		try {
            			address = InetAddress.getByName(host);
            		} catch (UnknownHostException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
            		
            		byte[] bytes= new byte[0];
            		datagramPacket = new DatagramPacket(bytes, 0, address, port);
					
            		// create socket
            		try {
            			datagramSocket = new DatagramSocket();
            			callbackContext.success("Success initializing UDP transmitter using datagram socket: " + datagramSocket);
						
            		} catch (SocketException e) {
            			callbackContext.error("Error initializing UDP transmitter using datagram socket: " + datagramSocket);
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
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
					byte[] bytes = data.getBytes();
					datagramPacket.setData(bytes);
					try {
						datagramSocket.send(datagramPacket);
						callbackContext.success("Success transmitting UDP packet: " + datagramPacket);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						callbackContext.error("Error transmitting UDP packet: " + datagramPacket);
						e.printStackTrace();
					}
				}
			});
			return true;
		}
		return false;
	}
}