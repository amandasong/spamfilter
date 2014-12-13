// ==============================================================
//  COMS1004 Fall 2013
//  Programming Project 5
//
//  Spam Filter - Filter class
//
//  By Amanda Song (UNI: as4513)
// ===============================================================

import java.util.ArrayList;
import java.util.Scanner;

public class Filter {
	
	private ArrayList<String> keywordList = new ArrayList<String>();
	private ArrayList<String> blacklist = new ArrayList<String>();
	private ArrayList<String> minList = new ArrayList<String>();
	private String minListString="";
	private ArrayList<Message> myMessageList = new ArrayList<Message>();
	private Boolean minAbsent = null;

	public void createKeywordList(String keywords){
		
		String allKeywords = keywords;
		Scanner input = new Scanner(allKeywords);
		String oneKeyword;
		while (input.hasNext()){
			oneKeyword = input.nextLine();
			keywordList.add(oneKeyword);
		}
		input.close();
	}
	
	public ArrayList getKeywordList(){
		
		return keywordList;	
	}
	
	public String convertKeywordListToString(){
		
		String keywordString = "";
		
		for(int i = 0; i < keywordList.size(); i++){
			String word = keywordList.get(i);
			keywordString = keywordString + word + "\n";
		}
		return keywordString;
	}
	
	public void createBlacklist(String addresses){
		
		String allAddresses = addresses;
		Scanner input = new Scanner (allAddresses);
		String oneAddress;
		while (input.hasNext()){
			oneAddress = input.next();
			blacklist.add(oneAddress);
		}
	}
	
	public void addBlacklistEmail(String email){

		String newEmail = email;
		blacklist.add(newEmail);
	}
	
	public ArrayList getBlacklist(){
		return blacklist;
	}

	public String convertBlacklistToString(){
	
	String blacklistString = "";
	
	for(int i = 0; i < blacklist.size(); i++){
		String email = blacklist.get(i);
		blacklistString = blacklistString + email + "\n";	
	}
	return blacklistString;

}

	public void scanMessages(ArrayList k, ArrayList b, ArrayList m){
		keywordList = k;
		blacklist = b;
		myMessageList = m;
		
		this.scanForKeywords();
		this.scanForBlacklistEmails();	
	}
		
	public void scanForKeywords(){ // necessary?
	 
		// scans each message for each of the keywords
		for (int i = 0; i < myMessageList.size(); i++){
			Message msg = myMessageList.get(i);
			String min = msg.getMIN();
			int msgNum = msg.getMsgNum();
			String subject = msg.getSubject();
			String text = msg.getSubject() + " " + msg.getTextBody();
			
			for (int j = 0; j < keywordList.size(); j++){
				String singleKeyword = keywordList.get(j);
				if (text.contains(singleKeyword)){
					if (this.checkMINList(min)){
						this.recordMIN(min);
					}	
					this.checkSubjectKeywords(subject);
					break;
				}		
			} 
		}
	}

	
	public void scanForBlacklistEmails(){
		
		String singleBlacklistEmail;
		Boolean emailFound = false;
		
		for (int i = 0; i < myMessageList.size(); i++){
		
			Message msg = myMessageList.get(i);
			String email = msg.getEmail();
			String min = msg.getMIN();
			String subject = msg.getSubject();
			
			for (int j = 0; j < blacklist.size(); j++){
				singleBlacklistEmail = blacklist.get(j); 
				if (email.compareTo(singleBlacklistEmail)==0){
					if (this.checkMINList(min)){
						this.recordMIN(min);
					}
					this.checkSubjectKeywords(subject);
						
				}
			}
		}
	}
	
	public void checkSubjectKeywords(String s){
		
		// for spam messages, finds words in subject with 6 or more characters
		// then checks if these words are already in the keywords list
		// if not, another method will add them

		String subjectLine = s;

		Scanner input = new Scanner(subjectLine);
		String oneWord;
		while (input.hasNext()){
			oneWord = input.next();
			if (oneWord.length() > 5){
				if (this.checkSubjectKeyWordRepeat(oneWord)){
					this.addSpamKeyword(oneWord);
					System.out.println("Added: " + oneWord);
				}
			}
		}
	}
	
	public Boolean checkSubjectKeyWordRepeat(String s){
		
		Boolean subjectWordAbsent = true;
		
		String word = s;
		String keyword;
	
		for(int j = 0; j < keywordList.size(); j++){
			keyword = keywordList.get(j);
			System.out.println("KeywordList to check: " + keyword);
			if((word.compareToIgnoreCase(keyword)==0)){
				subjectWordAbsent = false;
				System.out.println("already there");
				break;
			}
		}
		
		return subjectWordAbsent;
	}
	
	public void addSpamKeyword(String s){
		String keyword = s;
		keywordList.add(keyword);
	}	
	
	public void recordMIN(String min){
		
		String singleMIN = min;
		minList.add(singleMIN);
	}
	
	public ArrayList getMINList(){
		return minList;
	}
	
	public Boolean checkMINList(String m){
		
		Boolean minAbsent = false;
		
		String minToCheck = m;
		if(minList.size()==0){
			minAbsent = true;
		}
		
		else{
			for(int i = 0; i < minList.size(); i++){
				String min = minList.get(i);
				System.out.println(min);
				if(!(minToCheck.compareToIgnoreCase(min)==0)){
					minAbsent = true;
				}
			}
		}	
		return minAbsent;
	}
	
	public void convertMINListToString(){
		
		for(int i = 0; i < minList.size(); i++){
			String min = minList.get(i);
			minListString = minListString + min + "\n";
		}
		
	}
	
	public String getMINListString(){
		
		return minListString;
	}
}