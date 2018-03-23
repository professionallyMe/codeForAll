package com.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserCommunicator implements Serializable {

	private static Map<String, ClientCommunication> userCommunication=new HashMap<String, ClientCommunication>();

	
	public static void setMessage(String key,String message)
	{
		ClientCommunication comm=null;
		if(userCommunication.get(key)==null)
		{
			comm=new ClientCommunication();
			comm.setMessage(new StringBuffer(message+System.getProperty("line.separator")));
			userCommunication.put(key,comm);
		}
		else userCommunication.get(key).getMessage().append(message+System.getProperty("line.separator"));
	}
	
	public static String getMessage(String key)
	{
		if(userCommunication.get(key)==null)
			return null;
		return userCommunication.get(key).getMessage().toString();
	}
	
}
