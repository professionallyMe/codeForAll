package com.chatapp;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.TextField;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.listener.ChatMessageSendListener;
import com.server.ChatServer;
import com.server.GroupServer;
import com.util.ChatBuffer;
import com.util.ClientCommunication;
import com.util.ClientInfo;
import com.util.GroupServerInfo;
import com.util.UserRetriever;

public class ChatApp extends JFrame {

	protected JTextArea output;
	protected JTextArea userList;
	protected JTextField input;
	protected Thread listener;
	private GroupServerInfo gpServerInfo;
	private ChatServer chatServer;
	private Map<String,ClientCommunication> users;
	private ExecutorService threadPollService=null;
	private ClientInfo myDetails;
	
	public ChatApp(GroupServerInfo gpServerInfo,String userId,String myServerPort) {
		// TODO Auto-generated constructor stub
		threadPollService=Executors.newCachedThreadPool();
		
		this.gpServerInfo=gpServerInfo;
		users=UserRetriever.retrieveUser(getGpServerInfo());
		chatServer=new ChatServer(myServerPort);		
        chatServer.setService(getThreadPollService());
        chatServer.setUsers(users);
        myDetails=users.get(userId).getClientInfo();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GroupServerInfo gpServerInfo= new GroupServerInfo(args[1],args[2]);
		
		final ChatApp chatApp=new ChatApp(gpServerInfo,args[3],args[4]);
		
		chatApp.setName(args[0]);			
		
		  NewJFrame jFrame=new NewJFrame(chatApp.getUsers(),chatApp.getMyDetails(),chatApp.getThreadPollService());		
		  jFrame.setVisible(true);
	      jFrame.getMessageArea();
	      chatApp.getChatServer().setTextArea(jFrame.getMessageArea());
	      chatApp.getChatServer().setSelectedRadio(jFrame.getjRadio());
	      chatApp.getChatServer().acceptClients();
		
		/*chatApp.setLayout (new BorderLayout ());
		
		chatApp.add ("West", chatApp.output = new JTextArea ());
		chatApp.output.setEditable (false);
		
	    chatApp.userList = new JTextArea ();
	    chatApp.add ("East",chatApp.userList);
    
		
	    chatApp.add ("South", chatApp.input = new JTextField ());
		chatApp.setSize(500,500);
		
		chatApp.input.requestFocus ();
		
		ChatMessageSendListener messageSendListener=new ChatMessageSendListener();
		messageSendListener.setOutputField(chatApp.output);
		chatApp.input.addKeyListener(messageSendListener);
		
		chatApp.setVisible(true);
*/
		
	}

	@Override
	@Deprecated
	public boolean handleEvent(Event evt) {
		// TODO Auto-generated method stub
		return super.handleEvent(evt);
	}
	
	public JTextArea getOutput() {
		return output;
	}

	public void setOutput(JTextArea output) {
		this.output = output;
	}

	public JTextField getInput() {
		return input;
	}

	public void setInput(JTextField input) {
		this.input = input;
	}

	public GroupServerInfo getGpServerInfo() {
		return gpServerInfo;
	}

	public void setGpServerInfo(GroupServerInfo gpServerInfo) {
		this.gpServerInfo = gpServerInfo;
	}

	public ChatServer getChatServer() {
		return chatServer;
	}

	public void setChatServer(ChatServer chatServer) {
		this.chatServer = chatServer;
	}

	public Map<String, ClientCommunication> getUsers() {
		return users;
	}

	public void setUsers(Map<String, ClientCommunication> users) {
		this.users = users;
	}

	/**
	 * @return the threadPollService
	 */
	public ExecutorService getThreadPollService() {
		return threadPollService;
	}

	/**
	 * @param threadPollService the threadPollService to set
	 */
	public void setThreadPollService(ExecutorService threadPollService) {
		this.threadPollService = threadPollService;
	}
	
	public ClientInfo getMyDetails() {
		return myDetails;
	}

	public void setMyDetails(ClientInfo myDetails) {
		this.myDetails = myDetails;
	}

	
}
