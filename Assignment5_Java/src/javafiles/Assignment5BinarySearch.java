package javafiles;
import java.io.*;
import java.util.*;

/**
 * PsalmsReaderMain class - Template for Assignment-5
 * @author TMyatt
 *
 */
public class Assignment5BinarySearch {
	static String words[] = new String[13000];
	static int count[] = new int[13000];
	static int lastIndex = 0;
	static long compareStringCount = 0;
	static long moveStringCount = 0;
	
	/**
	 * addWordToList - add word to list or increment counter
	 * @param inWord - the word to add to the list
	 * @return number of words in the list
	 */
	static int addWordToList(String inWord) {

		// Include fully operational code from the last assignment
		 int index = -1; // as index is given -2 for comparison as it cannot be negative
         for (int currentIndex = 0; currentIndex  < lastIndex; ++currentIndex) {
             if (words[currentIndex].equals(inWord)) {
                 index = currentIndex; // give value to index in case word is repeated
                  break;
             }
         }
         if (index == -1) { // In case word is new in file, add to array
             words[lastIndex] = inWord; // assign word to array of words
             count[lastIndex] = 1; // assign count as 1, first time counted
             lastIndex++; // update last visited index 
         } else { // in case of positive index
             count[index]++; // increment in case word again come
         }
         return lastIndex; // return last index to know how many words are there and their counts
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
	        int compareResult = words[middle].compareTo(inWord);

	        if (compareResult == 0) {  // equals strings case
	            count[middle]++; // Increment count if word found  to avoid duplicate counting
	            return lastIndex;
	        } else if (compareResult < 0) {
	            left = middle + 1; //  increment ( in case like b is less than a)
	        } else {
	            insertIndex = middle;
	            right = middle - 1; // decrement ( in case like b is greater than a )
	        }
	        compareStringCount++; // Increment comparison count
	    }
	    
				// In part #1, build an algorithm to maintain sorted order
				// Be sure to count comparisons and moves
				int iteration;
				// Dynamically sorting things, making space for elements and shifting them to right
			    // Like c++,java,python (0,1,2) are words, new word is basic to be insert at 0 
			    // then, it will shift words c++,java,python to 1,2,3 and will add basic at 0 after loop.
			    for (iteration = lastIndex; iteration > insertIndex; iteration--) {
			        words[iteration] = words[iteration - 1];
			        count[iteration] = count[iteration - 1];
			        moveStringCount++; // Increment move count
			    }

			    // Insert new word at correct position
			    words[insertIndex] = inWord;
			    count[insertIndex] = 1;
			    lastIndex++; // Increment index
			    moveStringCount++; // Increment move count
			

		    

		    return lastIndex;

		
	
	}
	
	/**
	 * printWordList - dump the list if count > inMinimum
	 */
	static void printWordList(int inMinimum) {
		System.out.println("Total words: " + lastIndex);
		System.out.println("Compare count: " + compareStringCount);
		System.out.println("Move count: " + moveStringCount);
		for (int i = 0; i < lastIndex; i++) {
			if (count[i] >= inMinimum) {
				System.out.println(words[i] + ":" + count[i]);
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
			//printWordList(1000);
		} // END try
		catch (Exception e) {
			// All Exceptions come here for graceful termination
			System.out.println("PsalmsReaderMain Error: " + e);
		} // END catch
	} // END main
} // END class