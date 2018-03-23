package com.communicator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MessageSender implements Runnable{

	DataOutputStream writer;
	String uniqueUserId;
	
	public MessageSender(DataOutputStream writer, String uniqueUserId) {
		// TODO Auto-generated constructor stub
		this.writer=writer;
		this.uniqueUserId=uniqueUserId;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			String chatMessage=reader.readLine();
			
			while(true)
			{
				writer.writeUTF(chatMessage);
				writer.flush();
				
				chatMessage=reader.readLine();				
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
