package com.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class ChatBuffer {

	DataInputStream inBuffer;
	DataOutputStream outBuffer;
	
	public ChatBuffer(Socket socket) {
		// TODO Auto-generated constructor stub
		try {
			inBuffer=new DataInputStream(socket.getInputStream());
			outBuffer=new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	public DataInputStream getInBuffer() {
		return inBuffer;
	}
	public void setInBuffer(DataInputStream inBuffer) {
		this.inBuffer = inBuffer;
	}
	public DataOutputStream getOutBuffer() {
		return outBuffer;
	}
	public void setOutBuffer(DataOutputStream outBuffer) {
		this.outBuffer = outBuffer;
	}
	
	
}


