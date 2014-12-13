// ==============================================================
//  COMS1004 Fall 2013
//  Extra Credit
//
//  EmailAddressFinder - AddressFinder class
//
//  By Amanda Song (UNI: as4513)
// ===============================================================

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.URL;
import java.io.*;

public class AddressFinder {
	
	private Boolean moreURLs;
	private ArrayList<String> emailList = new ArrayList<String>();
	private ArrayList<String> urlList = new ArrayList<String>();
	
	public void urlBrancher(String s) throws IOException {
	
		// searches a site for email addresses and
		// inspects it to see if it contains
		// additional URLS
		
		String web = s;
		this.findEmail(this.getSiteContent(web));
		
		this.findAllURLs(web);
		
		String site = "";
		if(this.moreURLs){
			for(int i = 0; i < urlList.size(); i++){
				site = urlList.get(i);
				this.findEmail(site);
			}
		}
		
	}
	
	public ArrayList getURLList(){
		return urlList;
	}
	
	public String convertURLListToString(){
		String urlListString = "";
		
		for (String u: urlList){
			urlListString = urlListString + u + "\n";
		}
		
		return urlListString;
	}
	
	public String getSiteContent(String s) throws IOException{
		
		// takes URL and returns all of its contents
		
		String web = s;
		URL u = new URL(web);
		Scanner input = new Scanner(u.openStream());
		
		String URLcontent = "";
		String content = "";
		
		while(input.hasNext()){
			content = input.next();
			URLcontent = URLcontent + content;
		}
			
		return URLcontent;
	}
	
	public void findAllURLs(String s) throws IOException {
		
		String web = s;
		String content = this.getSiteContent(web);
		
		int urlStart = 0;
		int urlEnd = 0;
		String oneURL = "";
		
		// searches website content for URLs
		
		String HTML_A_TAG_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
		String HTML_A_HREF_TAG_PATTERN = 
			"\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
	 
		Pattern pattern1 = Pattern.compile(HTML_A_TAG_PATTERN);
		Pattern pattern2 = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
	    Matcher matcher1 = pattern1.matcher(content);
	    Matcher matcher2 = pattern2.matcher(content);
	    
	    Boolean again = true;
	    int startHere = 0;
	    
	    while (again){
	    	
	    	if (matcher1.find(startHere)){
	    		if(matcher2.find(startHere)){
	    			urlStart = matcher2.start() + 6; // to get rid of href="
	    			urlEnd = matcher2.end() - 2;
	    			oneURL = content.substring(urlStart,urlEnd);
	    			urlList.add(oneURL);
	    			startHere = matcher2.end();
	    			moreURLs = true;
	    		}
	    	}
	    	
	    	else{
	    		moreURLs = false;
	    		again = false;
	    	}
	    	
	    }
	    
	}
	
	public Boolean moreURLs(){
		return moreURLs;
	}
	
	public void findEmail(String s){
		
		String content = s;
		int emailStart = 0;
		int emailEnd = 0;
		String oneEmail = "";
		
		// Matches URL content against regular expression for email
		Pattern pattern = Pattern.compile("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*"
				+ "@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");
	    Matcher matcher = pattern.matcher(content);
	    
	    Boolean again = true;
	    int startHere = 0;
	    
	    while (again){
	    	if (matcher.find(startHere)){
	    		emailStart = matcher.start();
	    		emailEnd = matcher.end();
	    		oneEmail = content.substring(matcher.start(),matcher.end());
	    		emailList.add(oneEmail);
	    		startHere = matcher.end();
	    	}
	    	
	    	else{
	    		again = false;
	    	}
	    	
	    }
	    
	}
	
	public ArrayList getEmailList(){
		return emailList;
	}
	
	public String convertEmailListToString(){
		String emailListString = "";
		
		for (String e: emailList){
			emailListString = emailListString + e + "\n";
		}
		
		return emailListString;
	}

}
