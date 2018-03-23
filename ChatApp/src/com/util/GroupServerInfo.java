package com.util;

public class GroupServerInfo {

	private String ipAddress;
	private int port;

	public GroupServerInfo(String ipAddress, String port) {
		// TODO Auto-generated constructor stub
		this.ipAddress=ipAddress;
		this.port=Integer.parseInt(port);
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
