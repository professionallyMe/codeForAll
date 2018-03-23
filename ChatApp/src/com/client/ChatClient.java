package com.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.communicator.MessageReceiver;
import com.communicator.MessageSender;
import com.util.UserRetriever;

public class ChatClient {

	/**
	 * @param args
	 */
	private ExecutorService service=null;
	private static Map<String,String> users=null;
	
	public ChatClient() {
		// TODO Auto-generated constructor stub
		service=Executors.newCachedThreadPool();
		users=UserRetriever.retrieveUser();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		try {
			
			  ChatClient chatClient=new ChatClient();
			  
			  Socket client=new Socket("127.0.0.1",1000);
		
			  MessageSender sender=new MessageSender();
			  sender.setSocket(client);
			
			  MessageReceiver receiver=new MessageReceiver(client);			
			  			  
			  String senderName=users.get(client.getInetAddress().toString()+"1000");
			  
			  System.out.println(senderName);
			  
			  receiver.setSenderName(senderName);
			
			  System.out.println("Client started communication");
			
			  chatClient.service.execute(receiver);
			  chatClient.service.execute(sender);
			  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ExecutorService getService() {
		return service;
	}

	public void setService(ExecutorService service) {
		this.service = service;
	}
  
}
