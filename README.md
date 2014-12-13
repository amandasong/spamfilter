This is a project I wrote in Fall 2013 for COMS1004: Introduction to Computer Science at Columbia University.

COMS1004 Fall 2013
Programming Project 5
November 27th, 2013

Amanda Song (UNI: as4513)
------------------------------------------------------------------------

CONTENTS: 1. SPAM FILTER 
	   2. ADDRESS FINDER (extra credit)
		  
		  
=========================================================================
PROGRAM: SPAM FILTER
=========================================================================

---- How to run the program: --------------------------------------------

Command-line arguments:

	args[0]: keywords (text file)
	args[1]: blacklist emails (text file)
	args[2]: messages (text file)
	args[3]: output (text file)
	
	
----1. List of classes------------------------------------------------------

Main class: SpamTest
Supporting classes: Message, Filter, StringParser

----2. Design choices ------------------------------------------------------

I decided to create another class, StringParser, to handle all the main 
parsing of the email messages, to identify where each message started,
and the different parts of each message (sender email, subject line, MIN,
text body). 

This class also contains a useful "findSubstring" static method, which has
two versions.

The first version receives the arguments of a String, and a subString,
and finds the index of the String at the point then this String contains
the subString. 

The second version receives arguments of a String, index where to start
looking, and the substring to locate, and similarly, finds the index
of the String where this subString ends. 

This method can be used to return the beginning and ending indices
of a target substring, so this substring can be saved as the appropriate
variable, when parsing each message for the different parts.
This static method helped to simplify my code, since it could be called
whenever this task needed to be carried out.

I also decided to maintain arraylists for the keywords, blacklisted
emails, and rawMessages, to separate them, and so that I could
easily update these lists. Also, I can easily compare each message
against the lists of the keywords and blacklist emails, by retrieving
each element in each list.


----3. Description of how the program works  --------------------------------

The main class, SpamTest, creates a Filter object and StringParser object,
and then reads the command-line arguments, and passes the first two arguments
(keywords and blacklist) to the Filter class, where they are stored as
two separate ArrayLists. 

SpamTest also passes the third command-line argument (messages) into the 
StringParser class, which parses the entire messages String to find where 
each individual message starts and ends,and stores each message as a separate 
element in the rawMessageList.

Then, the StringParser goes through the rawMessageList and parses
each element to find its sender email, subject line, MIN, and main
body text, and creates a new Message object, storing those values
into the appropriate fields of the Message object.

After that, SpamTest calls the "scanMessages" method from the Filter class,
which scans each Message object, checking its email against the list
of blacklisted emails, and its subject and body text against each
keyword. If a match is found, we check if the MIN of the message
is already in MINlist. If not, we add this MIN to MINList.
We also check if the sender email is part of the blacklist,
and if not, we add it. Finally, we check if the subject line
of the email contains any words with 6 or more characters.
If so, we check if those words are already in the keywords list,
and if not, we add them.

Now, back in SpamTest, we use a PrintWriter to write all the
MINs into the textfile of the args[3] in the command-line.

Then, we give the user the option of manually adding other
prohibited keywords.These words are added to the arraylist
keywords.

Finally, we use two more PrintWriters to write the 
keywordsList and blacklistEmails arraylists into the first
two textfiles, args[0], args[1], effectively updating
their contents.

=========================================================================
PROGRAM: ADDRESS FINDER
=========================================================================


---- How to run the program: ----------------------------------------------

Command-line arguments:

args[0]: output of emails (textfile)

----1. List of classes------------------------------------------------------

Main class: AddressFinderTest
Supporting class: AddressFinder

----2. Design choices ------------------------------------------------------

To find email addresses, I matched the content of a webpage against the
regular expression for email (I used the regex from the site
http://computrscience.blogspot.com/2012/12/web-crawlers-in-javaextracting-
email.html). 

To find links, I matched the webpage against html tags, and I used info
from (http://www.mkyong.com/regular-expressions/how-to-extract-html-links-
with-regular-expression/). 

To keep track the email addresses found, I created an ArrayList of strings,
which is updated with new addresses.

----3. Description of program ----------------------------------------------

The main class takes the URL of a website, and passes it to the ArrayFinder
class to search for email addresses, as well as additional hyperlinks. 
The ArrayFinder saves addresses and URLs in two array lists. 
If more links are found, those sites are also searched for email addresses. 
The sites found are printed in the console.