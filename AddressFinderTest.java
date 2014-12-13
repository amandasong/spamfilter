// ===================================================================
//  COMS1004 Fall 2013
//  Extra Credit
//
//  EmailAddressFinder - test class
//
//  This program takes the URL of a website, and searches
//  for email addresses on it.
// 
//  By Amanda Song (UNI: as4513), with html regex from mkyong
//  Source: http://www.mkyong.com/regular-expressions/how-to-extract
//  -html-links-with-regular-expression/). 
//  and email regex from: http://computrscience.blogspot.com/2012/12/
//  web-crawlers-in-javaextracting-email.html
// =====================================================================

import java.net.URL;
import java.util.Scanner;
import java.io.*;

public class AddressFinderTest {
	
	public static void main (String[] args){

	try{
		
		AddressFinder myFinder = new AddressFinder();
		
		System.out.println("Enter website URL to search: ");
		Scanner input = new Scanner(System.in);
		String url = input.nextLine();

		myFinder.urlBrancher(url);
		
		if (!(myFinder.getURLList().size()==0)){
			System.out.println("More URLs were found on this page:");
			System.out.println(myFinder.convertURLListToString());
		}
		else{
			System.out.println("This page did not contain more URLs");
		}
		
		if (!(myFinder.getEmailList().size()==0)){
			System.out.println("Email addresses were found.");
			System.out.println("Please see the textfile.");
			String allEmails = myFinder.convertEmailListToString();
			Scanner in = new Scanner(allEmails);
			String email;
			PrintWriter output = new PrintWriter(args[0]);	
			while(in.hasNext()){
				email = in.next();
				output.println(email);
			}
			output.close();
		}
		
		else{
			System.out.println("No email addresses found.");
		}
		
	}
		
		
		catch (IOException e){
			
			System.out.println("Please check your URL format.");
			
		}
		
			
	}

}
