package com.listener;

import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.communicator.MessageReceiver;
import com.util.ChatBuffer;
import com.util.ClientCommunication;
import com.util.ClientInfo;
import com.util.UserCommunicator;

public class ChatMessageSendListener implements KeyListener {

	private JTextArea outputField;
	private JRadioButton[] bGroup;
	private Map<String,ClientCommunication> users=null;
	private ClientInfo myDetials;
	private ExecutorService threadPoolSerivce;
	private JTextArea textArea;

	
	public ChatMessageSendListener(JTextArea outField,JRadioButton[] bGroup , Map<String,ClientCommunication> users,ClientInfo mydetails,ExecutorService threadPoolService,JTextArea textArea) {
		// TODO Auto-generated constructor stub
		this.outputField=outField;
		this.bGroup=bGroup;
		this.users=users;
		this.myDetials=mydetails;
		this.threadPoolSerivce=threadPoolService;
		this.textArea=textArea;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub
	
		JTextField inputField=(JTextField) key.getComponent();
	
		if(key.getKeyCode()== KeyEvent.VK_ENTER){
			JRadioButton user=null;
			for (int i = 0; i < bGroup.length; i++) {
				if(bGroup[i]!=null && bGroup[i].isSelected())
					user=bGroup[i];
			}
			
			
			String id=user.getText().split(":")[0].trim();
		
			ClientCommunication userComm=users.get(id);
		
			Socket socket=null;
			ChatBuffer chatBuffer=null;
				
				try {
					
					if(userComm.getChatBuffer()==null)
					{
						socket=new Socket(userComm.getClientInfo().getIpAddress(),Integer.parseInt(userComm.getClientInfo().getPort()));
						chatBuffer=new ChatBuffer(socket);					
						userComm.setChatBuffer(chatBuffer);
						ObjectOutputStream oos=new ObjectOutputStream(userComm.getChatBuffer().getOutBuffer());
						 oos.writeObject(getMyDetials());
						 MessageReceiver reciever=new MessageReceiver(chatBuffer.getInBuffer(),userComm.getClientInfo().getId(),textArea,bGroup,userComm.getClientInfo());
						 getThreadPoolSerivce().execute(reciever);
					}					
					
					userComm.getChatBuffer().getOutBuffer().writeUTF(inputField.getText());
					userComm.getChatBuffer().getOutBuffer().flush();
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     			
			
			//UserCommunicator.setMessage(user.getText(),inputField.getText());
			//outputField.setText(outputField.getText()+inputField.getText()+"\n");
			
			UserCommunicator.setMessage(userComm.getClientInfo().getId(), myDetials.getName()+" : "+inputField.getText());
			outputField.setText(UserCommunicator.getMessage(userComm.getClientInfo().getId()));
			inputField.setText(null);			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public JTextArea getOutputField() {
		return outputField;
	}

	public void setOutputField(JTextArea outputField) {
		this.outputField = outputField;
	}

	public ClientInfo getMyDetials() {
		return myDetials;
	}

	public void setMyDetials(ClientInfo myDetials) {
		this.myDetials = myDetials;
	}

	public ExecutorService getThreadPoolSerivce() {
		return threadPoolSerivce;
	}

	public void setThreadPoolSerivce(ExecutorService threadPoolSerivce) {
		this.threadPoolSerivce = threadPoolSerivce;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}


}
