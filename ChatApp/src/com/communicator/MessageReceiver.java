package com.communicator;

import java.io.DataInputStream;
import java.io.IOException;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import com.util.ClientInfo;
import com.util.UserCommunicator;

public class MessageReceiver implements Runnable {

	ClientInfo clientInfo;
	DataInputStream inBuffer=null;
	String uniqueUserId;
	JTextArea textArea;
	JRadioButton[]  selectedRadio;
	
	public MessageReceiver(DataInputStream inBuffer,String uniqueUserId,JTextArea textArea,JRadioButton[] selectedUser,ClientInfo clientInfo) {
		// TODO Auto-generated constructor stub
		this.inBuffer=inBuffer;
		this.uniqueUserId=uniqueUserId;
		this.textArea=textArea;		
		this.selectedRadio=selectedUser;
		this.clientInfo=clientInfo;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {			
			
			String chatMessage=inBuffer.readUTF();			
			
			while(true)
			{
			   UserCommunicator.setMessage(uniqueUserId, clientInfo.getName()+" : "+chatMessage);
			   for (int i = 0; selectedRadio !=null && i < selectedRadio.length; i++) {
				if(selectedRadio[i] !=null && selectedRadio[i].isSelected() && selectedRadio[i].getText().split(":")[0].trim().equals(uniqueUserId))
					textArea.setText(UserCommunicator.getMessage(uniqueUserId));
				
				}
			   //textArea.setText(UserCommunicator.getMessage(uniqueUserId));
			   chatMessage=inBuffer.readUTF();	
			}			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}




}
