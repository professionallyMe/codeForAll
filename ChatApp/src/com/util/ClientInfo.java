package com.util;

import java.io.Serializable;

public class ClientInfo implements Serializable{

	String name;
	
	String id;
	
	String ipAddress;
	
	String port;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (ipAddress+port+id+name).hashCode();
	}

	
}
