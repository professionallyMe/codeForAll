package com.util;

import java.io.Serializable;

public class ClientCommunication implements Serializable {

	ChatBuffer chatBuffer;
	ClientInfo clientInfo;
	StringBuffer message;
	
	public StringBuffer getMessage() {
		return message;
	}
	public void setMessage(StringBuffer message) {
		this.message = message;
	}
	public ChatBuffer getChatBuffer() {
		return chatBuffer;
	}
	public void setChatBuffer(ChatBuffer chatBuffer) {
		this.chatBuffer = chatBuffer;
	}
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
	public void setClientInfo(ClientInfo clientInfor) {
		this.clientInfo = clientInfor;
	}
	
	
}
