package javafiles;
import java.io.*;
import java.util.*;

/**
 * PsalmsReaderMain class - Template for Assignment-5
 * @author TMyatt
 *
 */
public class Assignment6BinarySearch {
	static ArrayList<String> words = new ArrayList<>();
	static ArrayList<Integer> count = new ArrayList<>();
	static int lastIndex = 0;
	static long compareStringCount = 0;
	static long moveStringCount = 0;
	

	/**
	 * addWordToList - add word to list or increment counter
	 * @param inWord - the word to add to the list
	 * @return number of words in the list
	 */
	static int addWordToList(String inWord) {
	    int index = -1; // Initialize index

	    // Loop through the ArrayList to find the word
	    for (int currentIndex = 0; currentIndex < words.size(); ++currentIndex) {
	        if (words.get(currentIndex).equals(inWord)) {
	            index = currentIndex; // Set index if word is found
	            break;
	        }
	    }

	    // If word is not found, add it to the ArrayList
	    if (index == -1) {
	        words.add(inWord); // Add word to the ArrayList
	        count.add(1); // Set count as 1, first time counted
	        lastIndex++; // Update last visited index
	    } else {
	        // If word is found, increment its count
	        count.set(index, count.get(index) + 1);
	    }

	    // Return the updated size of the ArrayList
	    return words.size();
	}
	
	/**
	 * addWordToSortedList - add word to sorted list or increment counter
	 * @param inWord - the word to add to the list
	 * @return number of words in the list
	 */
	static int addWordToSortedList(String inWord) {

		
		// Search for word, set boolean and increment count if found
		// In Part #1 a linear search, in Part #2 a binary search
		// Be sure to count comparisons and moves
		 int left = 0;
		    int middle = 0;
		    int right = lastIndex - 1;
		    int insertIndex = lastIndex; // Default insertion point at the end

		    // Binary search for insertion point
		    while (left <= right) {
		        middle = (left + right) / 2;
		        int compareResult = words.get(middle).compareTo(inWord);

		        if (compareResult == 0) {  // equals strings case
		            count.set(middle, count.get(middle) + 1); // Increment count if word found to avoid duplicate counting
		            return lastIndex;
		        } else if (compareResult < 0) {
		            left = middle + 1; // increment (in case like b is less than a)
		        } else {
		            insertIndex = middle;
		            right = middle - 1; // decrement (in case like b is greater than a)
		        }
		        compareStringCount++; // Increment comparison count
		    }
				// In part #1, build an algorithm to maintain sorted order
				// Be sure to count comparisons and moves

		 
		        words.add(insertIndex, inWord); // Insert word at correct position
		        count.add(insertIndex, 1); // Add count for the new word
		     
		    


			    lastIndex++; // Increment index
			    moveStringCount++; // Increment move count
			

		    

		    return words.size();

		
	
	}
	
	/**
	 * printWordList - dump the list if count > inMinimum
	 */
	static void printWordList(int inMinimum) {
	    System.out.println("Total words: " + words.size());
	    System.out.println("Compare count: " + compareStringCount);
	    System.out.println("Move count: " + moveStringCount);
	    for (int i = 0; i < words.size(); i++) {
	        if (count.get(i) >= inMinimum) {
	            System.out.println(words.get(i) + ":" + count.get(i));
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
	 * PsalmsReaderMain main() - Template for Assignment-5
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Open the required text file for sequential read
			Scanner inputFile =
					new Scanner (new File("bible-complete10.txt"));
			// Starting time in microseconds
			long microTime = System.nanoTime() / 1000;
			// Check for EOF, read the next line, and display it
			while (inputFile.hasNextLine()) {
				String inLine, verse;
				String[] verseParsed;
				
				inLine = inputFile.nextLine();
				verse = getVerse(inLine);
				verseParsed = verse.split("[ :;,.'!?()-]+");

				for (String s: verseParsed) {
					//addWordToList(s.toLowerCase());
					addWordToSortedList(s.toLowerCase());
				}
			}
			// Elapsed time in microseconds
						microTime = System.nanoTime() / 1000 - microTime;

						// Convert microseconds to seconds and milliseconds
						double seconds = (double) microTime / 1_000_000; // Convert to seconds
						long milliseconds = microTime % 1_000_000; // Remainder is milliseconds

						// Print elapsed time in the ss.sss format
						System.out.printf("Elapsed time: %.3f seconds%n", seconds + (double) milliseconds / 1_000);
			// Close the required file when EOF is reached
			inputFile.close();
			printWordList(1000);
		} // END try
		catch (Exception e) {
			// All Exceptions come here for graceful termination
			System.out.println("PsalmsReaderMain Error: " + e);
		} // END catch
	} // END main
} // END class