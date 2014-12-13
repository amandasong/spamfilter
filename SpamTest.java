// ==============================================================
//  COMS1004 Fall 2013
//  Programming Project 5
//
//  Spam Filter - Main class
//
//  Takes command-line arguments of textfiles
//  and acts as a spam filter to identify
//  messages from blacklisted senders or that contain 
//  prohibited keywords, and updates the files
//  Also produces a textfile with the message identification
//  numbers of spam messages
//
//  By Amanda Song (UNI: as4513)
// ===============================================================

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;


public class SpamTest {
	
	public static void main(String[] args){
		
		// importing the command-line arguments
		
			try{
				
				Filter myFilter = new Filter();
				StringParser myParser = new StringParser();
				
				//File keywords = new File(args[0]);
				//File blacklist = new File(args[1]);
				//File messages = new File (args[2]);
				
				String keywordsString ="";
				String blacklistString = "";
				String messagesString = "";
				
				Scanner reader = new Scanner(new FileInputStream(args[0]));
				while(reader.hasNext()){
					keywordsString = keywordsString + reader.nextLine() + "\n";
				}
				
				
				Scanner reader2 = new Scanner(new FileInputStream(args[1]));
				while (reader2.hasNext()){
					blacklistString = blacklistString+reader2.nextLine()+"\n";
				}
				
				Scanner reader3 = new Scanner(new FileInputStream(args[2]));
				while (reader3.hasNext()){
					messagesString = messagesString + reader3.nextLine()+"\n";
				}
				
				myFilter.createKeywordList(keywordsString);
				myFilter.createBlacklist(blacklistString);
				
				myParser.createMessageList(messagesString);
			//	myParser.messageListToString();
				
				myFilter.scanMessages(myFilter.getKeywordList(), 
						myFilter.getBlacklist(), myParser.getMessageList());
				myFilter.convertMINListToString();
				String minListString = myFilter.getMINListString();
				System.out.println("MIN:" + minListString);	
				
				Scanner input = new Scanner(minListString);
				String newMIN;
				PrintWriter output = new PrintWriter(args[3]);	
				while (input.hasNext()){
					newMIN = input.next();
					output.println(newMIN);
				}
				output.close();
				
				Scanner in = new Scanner (System.in);
				System.out.println("Would you like to add any prohibited "
						+ "words?");
				System.out.println("Emails with subject lines containing these"
						+ " words will be labelled spam.");
				Boolean anotherWord = true;
				while (anotherWord){
					System.out.println("Enter your word: (0 to exit)");
					String word = in.nextLine();
					if(!(word.compareTo("0") == 0)){
						myFilter.addSpamKeyword(word);
					}
						else{
						anotherWord = false;
						}
					}
				
				
				String newKeywordList = myFilter.convertKeywordListToString();
				System.out.println("New KeywordList: " + newKeywordList);
				Scanner in2 = new Scanner(newKeywordList);
				String newKeyword;
				PrintWriter output2 = new PrintWriter(args[0]);	
				while (in2.hasNext()){
					newKeyword = in2.next();
					output2.println(newKeyword);
				}
				output2.close();
					
				String newBlacklist = myFilter.convertBlacklistToString();
				System.out.println("New blackList: " + newBlacklist);
				Scanner in3 = new Scanner(newBlacklist);
				String newEmail;
				PrintWriter output3 = new PrintWriter(args[1]);	
				while (in3.hasNext()){
					newEmail = in3.next();
					output3.println(newEmail);
				}
				output3.close();
				
			}
			
			
			
				catch (IOException e){
					System.out.println("Please try again with "
							+ "correct input file names.");
					Scanner in = new Scanner(System.in);   
					while (in.hasNext()){
				    	for (int i = 0; i < 4; ){
				    		args[i]=in.next();
				    	}
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {           
					System.out.println("Please specify a command-line "
							+ "argument.");
				}

	
	}	
}

