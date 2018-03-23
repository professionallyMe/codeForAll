package com.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class UserRetriever {

    public static  Map<String,ClientCommunication> retrieveUser(GroupServerInfo gpServerInfo)
    {
    	Map<String, ClientCommunication> users=null;
    	Socket socket=null;
    	
    	try {
			 socket=new Socket(gpServerInfo.getIpAddress(),gpServerInfo.getPort());
			 
			 ObjectInputStream ois =new ObjectInputStream(socket.getInputStream());
			 users= (Map<String, ClientCommunication>) ois.readObject();
			  
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	return users;
    }

}
