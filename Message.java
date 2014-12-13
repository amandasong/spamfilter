// ==============================================================
//  COMS1004 Fall 2013
//  Programming Project 5
//
//  Spam Filter - Message class
//
//  By Amanda Song (UNI: as4513)
// ===============================================================

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Message{
	 
	 private String subject;
	 private String min;
	 private String emailAddress;
	 private String textBody;
	 private int msgNumber;
	
	public Message(String email, String sub, String m, String text, int num){

		emailAddress = email;
		subject = sub;
		min = m;
	    textBody = text;
	    msgNumber = num;
	    
	}
	
	
	public String getSubject(){
		return subject;
	}
	
	public String getMIN(){
		return min;
	}
	
	public String getEmail(){
		return emailAddress;
	}
	
	public String getTextBody(){
		return textBody;
	}
	
	public int getMsgNum(){
		return msgNumber;
	}

}
