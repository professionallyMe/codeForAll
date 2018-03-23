package com.server;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.util.ClientCommunication;
import com.util.ClientInfo;
import com.util.GroupServerWindow;

public class GroupServer implements Runnable{

	private Map<String,ClientCommunication> users;
	
	private ExecutorService service;
	
	private static ServerSocket server;
	
	public GroupServer()
		{
		users=new HashMap<String, ClientCommunication>();
		Properties properties=new Properties();
		
		ClientInfo clientInfo=null;
				
		try {			
			server=new ServerSocket(2000);
			
			properties.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\resources\\users.properties"));
			ClientCommunication clientComm=null;
			for (final String name: properties.stringPropertyNames())
			{
				System.out.println(name);
				
				clientInfo=new ClientInfo();
				String[] info=name.split(",");
						
				clientInfo.setIpAddress(info[0]);
				clientInfo.setPort(info[1]);
				
				info=properties.getProperty(name).split(",");
				
				clientInfo.setId(info[0]);
				clientInfo.setName(info[1]);	
				
				clientComm=new ClientCommunication();
				clientComm.setClientInfo(clientInfo);
				
				users.put(clientInfo.getId(),clientComm);
			}
						
			System.out.println(users);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
			service=Executors.newCachedThreadPool();
		
	}
	public void run()
	{
		try {
			Socket client=server.accept();		
			do{
				
				System.out.println("Client Connected");
			     ObjectOutputStream writer=new ObjectOutputStream(client.getOutputStream());
		         writer.writeObject(users);
		         writer.flush();
		         client=server.accept();
		     }while(!server.isClosed());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	
	public void stopServer()
	{
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
