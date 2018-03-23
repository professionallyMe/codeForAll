package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesFileReader {

	 private static Properties properties=null;
	 
	public static void loadProperties()
	{
		properties=new Properties();
		 try {
			 properties.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\resources\\users.properties"));			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	public static Object getProperty(String key)
	{
		return properties.getProperty(key);
	}
	
	}
