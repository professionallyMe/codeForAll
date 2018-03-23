package com.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import com.communicator.MessageReceiver;
import com.communicator.MessageSender;
import com.util.ChatBuffer;
import com.util.ClientCommunication;
import com.util.ClientInfo;
import com.util.PropertiesFileReader;
import com.util.UserRetriever;

public class ChatServer {

	private ServerSocket server=null;
	private ExecutorService service=null;
	private Map<String, ClientCommunication> users=null;
	JTextArea textArea;
	JRadioButton[] selectedRadio;
	
	public ChatServer(String myServerPort) {
		// TODO Auto-generated constructor stub
		try {
			server=new ServerSocket(Integer.parseInt(myServerPort));
		    service=Executors.newCachedThreadPool();				    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void acceptClients()
	{
		System.out.println("Server starting...............");
		
		try {
			while(true)
			{
			  Socket client=server.accept();
			  
			  System.out.println("Client Connected...........");
			 
			   MessageReceiver receiver=retrieveandPopulateClient(client);
	   	  
			   service.execute(receiver);

			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private MessageReceiver retrieveandPopulateClient(Socket client)
	{
		ObjectInputStream ois;
		ClientCommunication clientComm=null;
		ClientInfo clientInfo=null;
		ChatBuffer chatBuffer=null;
		String userUniqueId=null;
		
		try {
			ois = new ObjectInputStream(client.getInputStream());
			clientInfo= (ClientInfo) ois.readObject();
			
			userUniqueId=clientInfo.getId();
			
			clientComm=new ClientCommunication();
			chatBuffer=new ChatBuffer(client);
			clientComm.setChatBuffer(chatBuffer);
			clientComm.setClientInfo(clientInfo);			
			users.put(userUniqueId, clientComm);
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MessageReceiver receiver=new MessageReceiver(chatBuffer.getInBuffer(),userUniqueId,textArea,selectedRadio,clientInfo);
		return receiver;
	}
	
	public ExecutorService getService() {
		return service;
	}

	public void setService(ExecutorService service) {
		this.service = service;
	}

	public Map<String, ClientCommunication> getUsers() {
		return users;
	}

	public void setUsers(Map<String, ClientCommunication> users) {
		this.users = users;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JRadioButton[] getSelectedRadio() {
		return selectedRadio;
	}

	public void setSelectedRadio(JRadioButton[] selectedRadio) {
		this.selectedRadio = selectedRadio;
	}

}

	
	
