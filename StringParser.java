// ==============================================================
//  COMS1004 Fall 2013
//  Programming Project 5
//
//  Spam Filter - StringParser class
//
//  By Amanda Song (UNI: as4513)
// ===============================================================


import java.util.ArrayList;
import java.util.Scanner;


public class StringParser {
	
	private static int substringEndIndex;
	private ArrayList<String> rawMessageList = new ArrayList<String>();
	private String minListString = "";
	private ArrayList<String> minList = new ArrayList<String>();
	private ArrayList<Message> myMessageList= new ArrayList<Message>();
	
	public void createMessageList(String msg){
		
		String allMessages = msg;
		this.createRawList(allMessages);
		this.separateElements();
	}
	
	public void createRawList(String msg){
		
		// takes String of all messages, stores each message
		// as string in an ArrayList
		
		String allmsg = msg;
		String oneMessage;
		String begin = "<BEGIN>";
		String end = "<END>";
		int startSearchIndex = 0;
		int msgCounter=0;
		
		Scanner input = new Scanner(allmsg);
		
		// counts the number of messages
		while(input.hasNextLine()){
			String one = input.nextLine();
			if (one.contains(begin)){
				msgCounter++;
			}
		}
		
		// adds each message into arraylist of Strings
		for (int j = 0; j < msgCounter; j++){
			findSubstring(allmsg, startSearchIndex, begin);
			int msgBeginIndex = getSubstringEndIndex();	
			findSubstring(allmsg, startSearchIndex, end);
			int msgEndIndex = getSubstringEndIndex();
		
			oneMessage = allmsg.substring(msgBeginIndex, msgEndIndex);
			rawMessageList.add(oneMessage);
			
			//begins search at end of previous message
			startSearchIndex = msgEndIndex; 
		}
	}
			
	
	public ArrayList getRawList(){
		return rawMessageList;
	}
	
	public void separateElements(){
		
		// go through each raw message, identify the different parts 
		// and store them in the appropriate fields
		// of new Message objects
		
		int emailStart;
		int emailEnd;
		int subjectStart;
		int subjectEnd;
		int minStart;
		int minEnd;
		int bodyStart;
		int bodyEnd;
		int msgNumber;
		String emailAddress;
		String subject;
		String min;
		String textBody;
		
		for(int i = 0; i < rawMessageList.size();i++){
			
			String message = rawMessageList.get(i);
			Scanner in = new Scanner(message);
		
			// getting the email address of sender
			findSubstring(message, "<");
			emailStart = getSubstringEndIndex();
			findSubstring(message, ">");
			emailEnd = getSubstringEndIndex()-1;
			emailAddress = message.substring(emailStart,emailEnd);
			
			// getting subject
			findSubstring(message, "Subject: ");
			subjectStart = getSubstringEndIndex();
			findSubstring(message, "MIN");
			
			// subtracted by 3 to remove "MIN"
			subjectEnd = getSubstringEndIndex() - 3;
			subject = message.substring(subjectStart,subjectEnd);
			
			// getting MIN
			findSubstring(message, "MIN: <");
			minStart = getSubstringEndIndex();
			findSubstring(message, minStart, ">");
			// subtracted by 1 to remove ">"
			minEnd = getSubstringEndIndex() - 1;
			min = message.substring(minStart, minEnd);
			
			// getting text body
			findSubstring(message, "Message Body:");
			bodyStart = getSubstringEndIndex();
			findSubstring(message, "<END>");
			bodyEnd = getSubstringEndIndex();
			textBody = message.substring(bodyStart,bodyEnd);
					
			msgNumber = i;
			
			// constructs new Message object
			Message msg = new Message(emailAddress, 
					subject, min, textBody, msgNumber);
			// adds this object to the ArrayList of objects
			myMessageList.add(msg);
			}
	
		}

	public ArrayList getMessageList(){
		return myMessageList;
	}
	
	public void messageListToString(){
		
		String messageListString = "";
		System.out.println("myMessageList Size: " + myMessageList.size());
		
		for(int i = 0; i < myMessageList.size(); i++){
			Message msg = myMessageList.get(i);
			String email = msg.getEmail();
			String subject = msg.getSubject();
			String min = msg.getMIN();
			String textBody = msg.getTextBody();
			int msgNum = msg.getMsgNum();
			
			System.out.println("Sender email: " + email);
			System.out.println("Subject: " + subject);
			System.out.println("MIN: " + min);
			System.out.println("Text body: " + textBody);
			System.out.println("Message Number: " + msgNum);
		}
		
	}
	

	public static void findSubstring(String msg, String sub){
		
		String message = msg;
		String targetSubstring = sub;
	
		String growingMessage = "";
		Boolean targetFound = false;
		
		while (!targetFound){
				for(int i = 0; i < message.length(); i++){
					growingMessage = growingMessage + message.substring(i,i+1);
					if (growingMessage.contains(targetSubstring)){
						substringEndIndex = i+1;
						targetFound = true;
						break;
					}
				}
			}		
	}
	
	public static void findSubstring(String msg, int s, String sub){
		
		String message = msg;
		int i = s;
		String targetSubstring = sub;
	
		String growingMessage = "";
		Boolean targetFound = false;
		
		while (!targetFound){
				for(i = s; i < message.length(); i++){
					growingMessage = growingMessage + message.substring(i,i+1);
					if (growingMessage.contains(targetSubstring)){
						substringEndIndex = i+1;
						targetFound = true;
						break;
					}
				}
		}
	}
	
	public static int getSubstringEndIndex(){
		return substringEndIndex;
	}

}
