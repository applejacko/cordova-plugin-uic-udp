package edu.uic.udptransmit;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import android.util.SparseArray;

public class UDPTransmit extends CordovaPlugin {
	private static final String TAG = Datagram.class.getSimpleName();
	
    SparseArray<DatagramSocket> m_sockets;
    SparseArray<SocketListener> m_listeners;
	
    public UDPTransmit() {
    }
	
}