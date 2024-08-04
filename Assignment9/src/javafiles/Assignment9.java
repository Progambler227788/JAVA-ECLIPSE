package javafiles;
import java.io.*;
import java.util.*;

/**
 * PsalmsReaderMain class - Template for Assignment-9
 * @author TMyatt
 *
 */
public class Assignment9 {
	static HashMap<String, Integer>wordsHash =
			new HashMap<String, Integer>();
	static TreeMap<String, Integer> wordsTree =
			new TreeMap<String, Integer>();
	
	/**
	 * addWordToHashMap - add word to list or increment counter
	 * @param inWord - the word to add to the list
	 * @return number of words in the list
	 */
	static int addWordToHashMap(String inWord) {
		// Part #1 goes here
		 // Check if the word already exists in the HashMap
        if (wordsHash.containsKey(inWord)) {
            // If word exists, increment its count
            wordsHash.put(inWord, wordsHash.get(inWord) + 1);
        } else {
            // If word doesn't exist, add it to the HashMap with count 1
            wordsHash.put(inWord, 1);
        }
        // Return the number of words in the HashMap
        return wordsHash.size();
	}
	
	/**
	 * addWordToTreeMap - add word to sorted list or increment counter
	 * @param inWord - the word to add to the list
	 * @return number of words in the list
	 */
	static int addWordToTreeMap(String inWord) {
		// Part #2 goes here
		 // Check if the word already exists in the TreeMap
        if (wordsTree.containsKey(inWord)) {
            // If word exists, increment its count
            wordsTree.put(inWord, wordsTree.get(inWord) + 1);
        } else {
            // If word doesn't exist, add it to the TreeMap with count 1
            wordsTree.put(inWord, 1);
        }
        // Return the number of words in the TreeMap
        return wordsTree.size();
	}
	
	/**
	 * printWordList - dump the list if count > inMinimum
	 */
	static void printWordList(int inMinimum) {
		if (wordsHash.size() > 0) {
		
			// Code to print hash list using an iterator
			Iterator<Map.Entry<String, Integer>> hashIterator = wordsHash.entrySet().iterator();
	        while (hashIterator.hasNext()) {
	                Map.Entry<String, Integer> entry = hashIterator.next();
	                System.out.println(entry.getKey() + ": " + entry.getValue());
	            }
	    	System.out.println("Total words: " + wordsHash.size());
		}
		if (wordsTree.size() > 0) {
			// Code to print tree list using an iterator
			 Iterator<Map.Entry<String, Integer>> treeIterator = wordsTree.entrySet().iterator();
	         while (treeIterator.hasNext()) {
	                Map.Entry<String, Integer> entry = treeIterator.next();
	                System.out.println(entry.getKey() + ": " + entry.getValue());
	            }
	     	 System.out.println("Total words: " + wordsTree.size());
		
		}
	}

	/**
	 * getVerse - Parse and return only the Psalms verse
	 * @param inLine - the line to parse on tab delimiter
	 * @return right half of tab delimited string
	 */
	static String getVerse(String inLine) {
		String[] ver = inLine.split("\t");
		return ver[1];
	}

	/**
	 * PsalmsReaderMain main() - Template for Assignment-9
	 * @param args
	 */
	public static void main(String[] args) {
		long microTime, microTimeSum = 0;
		try {
			// Open the required text file for sequential read
			Scanner inputFile =
					new Scanner (new File("bible-complete10.txt"));
			// Check for EOF, read the next line, and display it
			while (inputFile.hasNextLine()) {
				String inLine, verse;
				String[] verseParsed;
				
				inLine = inputFile.nextLine();
				verse = getVerse(inLine);
				verseParsed = verse.split("[ :;,.'!?()-]+");
				// We're going to do a microsecond execution calc
		        microTime = System.nanoTime()/1000;
				for (String s: verseParsed) {
					//addWordToHashMap(s.toLowerCase());
					addWordToTreeMap(s.toLowerCase());
				}
				microTimeSum += (System.nanoTime()/1000 - microTime);

			}	
			
			// Close the required file when EOF is reached
			inputFile.close();
			printWordList(10000);
	        System.out.println("Elapsed Time: " + microTimeSum);
	        
	        
		} // END try
		catch (Exception e) {
			// All Exceptions come here for graceful termination
			System.out.println("PsalmsReaderMain Error: " + e);
		} // END catch		
	} // END main
} // END class
