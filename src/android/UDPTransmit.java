package edu.uic.udptransmit;

import org.apache.cordova.CordovaPlugin;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class UDPTransmit extends CordovaPlugin {
	
	DatagramPacket datagramPacket;
	DatagramSocket datagramSocket;
	
	// Constructor
	public UDPTransmit() {
	}
	
    public int whatever() {
		return 42;
    }
	
	public boolean createDatagramPacket(byte[] data, int length, InetAddress host, int port) {
		datagramPacket = new DatagramPacket(data, length, host, port);
		if (datagramPacket instanceof DatagramPacket)
			return true;
		else
			return false;
	}
}