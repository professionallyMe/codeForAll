package com.listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.StringTokenizer;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import com.util.UserCommunicator;

public class UserNameFocussedListener implements ItemListener{

    private JTextArea textArea=null;
    
    public UserNameFocussedListener(JTextArea textArea) {
		// TODO Auto-generated constructor stub
    	this.textArea=textArea;
	}
   
    @Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
    	
    	JRadioButton button=(JRadioButton)(arg0.getSource());
    	if(UserCommunicator.getMessage(button.getText().split(":")[0].trim())==null)
		{
		    textArea.setText(null);
		    return;
		}
	
	String tokenizer=new String(UserCommunicator.getMessage(button.getText().split(":")[0].trim()));
	String message[]=tokenizer.split("\n");
	
	String allMessage="";	
	for (int i = 0; i < message.length; i++) {
		
       allMessage=allMessage+message[i]+"\n";			
	}	    
	textArea.setText(allMessage);
	}
    
	/*@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
		if(UserCommunicator.getMessage(((JRadioButton)arg0.getComponent()).getText().split(":")[0].trim())==null)
			{
			    textArea.setText(null);
			    return;
			}
		
		String tokenizer=new String(UserCommunicator.getMessage(((JRadioButton)arg0.getComponent()).getText().split(":")[0].trim()));
		String message[]=tokenizer.split("\n");
		
		String allMessage="";	
		for (int i = 0; i < message.length; i++) {
			
           allMessage=allMessage+message[i]+"\n";			
		}	    
		textArea.setText(allMessage);
	}*/

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	

		
}
