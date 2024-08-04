package javafiles;
import java.io.*;
import java.util.*;

/**
 * WordCountNode class - The data portion of the linked list
 */
class WordCountNode {
	String word;
	int count;
	
	/**
	 * WordCountNode constructor
	 * @param inWord
	 */
	public WordCountNode(String inWord) {
		word = inWord;
		count = 1;
	}	
	
	/**
	 * @return the object word datum
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * @return the object count datum
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the object count auto-incremented
	 */
	public int incrementCount() {
		count++;
		return count;
	}
}

/**
 * PsalmsReaderMain class - Template for Assignment-7
 * @author TMyatt
 *
 */
public class Assignment7 {
	static LinkedList<WordCountNode> words =
			new LinkedList<WordCountNode>();
	
	/**
	 * addWordToLinkedList - add word to linked list with counter
	 * @param inWord - the word to add to the list
	 * @return number of words in the list
	 */
	static int addWordToLinkedList(String inWord) {
		// Part #1 goes here
		// Check if the word already exists in the linked list
        for (WordCountNode node : words) {
            if (node.getWord().equals(inWord)) {
                // If word exists, increment its count and return word size that is in integer
                node.incrementCount();
                return words.size();
            }
        }
        // If word doesn't exist, add it as a new node
        words.add(new WordCountNode(inWord));
        return words.size();
	}
	
	/**
	 * addWordToSortedLinked List - add word to sorted linked list
	 * @param inWord - the word to add to the list
	 * @return number of words in the list
	 */
	static int addWordToSortedLinkedList(String inWord) {
		// Part #2 goes here
		 // Create a new WordCountNode with the given word
	    WordCountNode newNode = new WordCountNode(inWord);

	    // If the list is empty or the word should be inserted at the beginning,
	    // add the new node at the start of the list
	    if (words.isEmpty() || inWord.compareTo(words.getFirst().getWord()) < 0) {
	        words.addFirst(newNode);
	        return words.size();
	    }
	    // Iterator is a class that helps us to iterate over collections sequentially

	    // Iterate through the list to find the correct position to insert the new node
	    ListIterator<WordCountNode> iterator = words.listIterator();
	    while (iterator.hasNext()) { // it means that next node is not null
	        WordCountNode currentNode = iterator.next();
	        int comparison = inWord.compareTo(currentNode.getWord());

	        // If the word is already in the list, increment its count and return
	        if (comparison == 0) {
	            currentNode.incrementCount();
	            return words.size();
	        }
	        // If the word should be inserted before the current node, insert it and return
	        else if (comparison < 0) {
	            iterator.previous(); // Move the iterator back one position
	            iterator.add(newNode); // Insert the new node before the current node
	            return words.size();
	        }
	    }

	    // If the word should be inserted at the end of the list, add it there
	    words.addLast(newNode);
	    return words.size();
		
	}
	
	/**
	 * printWordList - dump the list if count > inMinimum
	 */
	static void printWordList(int inMinimum) {
		// Code to print linked list
		 System.out.println("Total words: " + words.size());
		
		    for (WordCountNode node : words) {
		        if (node.getCount() > inMinimum) {
		            System.out.println(node.getWord() + ":" + node.getCount());
		        }
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
	 * PsalmsReaderMain main() - Template for Assignment-7
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
				 microTime = System.nanoTime()/1000;

				for (String s: verseParsed) {
					//addWordToLinkedList(s.toLowerCase());
					addWordToSortedLinkedList(s.toLowerCase());
				}
				microTimeSum += (System.nanoTime()/1000 - microTime);
			}
		

			// Convert microseconds to 1000th of a second
			double elapsedSeconds = (double) microTimeSum / 1_000_000; // Convert to seconds
			double elapsedMilliseconds = (double) microTimeSum / 1_000; // Convert to milliseconds

			// Print elapsed time in the ss.sss format (1000th of a second)
			System.out.printf("Elapsed Time: %.3f seconds%n", elapsedSeconds + elapsedMilliseconds);

			
			// Close the required file when EOF is reached
			inputFile.close();
			//printWordList(1000);
		} // END try
		catch (Exception e) {
			// All Exceptions come here for graceful termination
			System.out.println("PsalmsReaderMain Error: " + e);
		} // END catch
	} // END main
} // END class
