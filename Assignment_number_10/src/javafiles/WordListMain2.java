package javafiles;


import java.io.*;
import java.util.*;

/**
 * 
 * @author TMyatt
 * 
 */
public class WordListMain2 {
	static WordList wordList;

	/**
	 * getVerse
	 * @param inLine String
	 * @return verse
	 */
	static String getVerse(String inLine) {
		String[] ver = inLine.split("\t");
		return ver[1];
	}
	
	/**
	 * getCommand
	 * @param type
	 */
	static void getCommand(String cmd) {
		
		switch (cmd) {
		case "A":
		case "a":
			wordList = new WordArrayList();
			break;
		case "L":
		case "l":
			wordList = new WordLinkedList();
			break;
		case "H":
		case "h":
			wordList = new WordHashMap();
			break;
		}
	}
	
	/**
	 * WordList main()
	 * @param args String[]
	 */
	public static void main(String[] args) {
		try {
			// Open the required text file for sequential read
			Scanner inputFile = new Scanner (new File(args[0]));
			getCommand(args[1]);

			// Check for EOF, read the next line, and display it
			while (inputFile.hasNextLine()) {
				String inLine, verse;
				String[] verseParsed;
				
				inLine = inputFile.nextLine();
				verse = getVerse(inLine);
				verseParsed = verse.split("[ :;,.'!?()-]+");
				
				for (String s: verseParsed) {
					wordList.addWord(s.toLowerCase());
				}
			}		
			// Close the required file when EOF is reached
			inputFile.close();
			wordList.printWordList(Integer.parseInt(args[2]));
		} // END try
		catch (Exception e) {
			// All Exceptions come here for graceful termination
			System.out.println("PsalmsReaderMain Error: " + e);
		} // END catch
	} // END main
} // END class


