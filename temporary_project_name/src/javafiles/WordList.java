package javafiles;

/**
 * 
 * @author TMyatt
 * 
 * Description: The superclass of WordArrayList,
 * WordLinkedList, and WordHashMap.  This superclass
 * maintains a list of words and their counts.
 */
public abstract class WordList {
	private int numUniqueWords;
	private int totalAllWords;
	
	/**
	 * Public constructor method WordList
	 */
	public WordList() {
		numUniqueWords = 0;
		totalAllWords = 0;
	}
	
	/**
	 * Public abstract method getWord
	 * @param inWord String
	 * @return 0 if inWord not found, inWord count if found
	 */
	public abstract int getWord(String inWord);
	
	/**
	 * Public abstract method existsWord
	 * @param inWord String
	 * @return true if inWord exists in WordList
	 */
	public abstract boolean existsWord(String inWord);

	/**
	 * Public abstract method addWord
	 * @param inWord String
	 * @return 1 if inWord not found, incremented inWord count if found
	 */
	public abstract int addWord(String inWord);
	
	/**
	 * Public abstract method incWord
	 * @param inWord String
	 * @return 0 if inWord not found, incremented inWord count if found
	 */
	public abstract int incWord(String inWord);
	
	/**
	 * Public abstract method printWordList
	 * @param inMinimum int
	 */
	public abstract void printWordList(int inMinimum);
	
	/**
	 * Protected accessor method getNumUniqueWords
	 * @return The count of unique words in WordList
	 */
	protected int getNumUniqueWords() {
		return numUniqueWords;
	}
	
	/**
	 * Protected accessor method getTotalAllWords
	 * @return The sum of the counts of all words in WordList
	 */
	protected int getTotalAllWords() {
		return totalAllWords;
	}
	
	/**
	 * Protected mutator method getNumUniqueWords
	 * @return The count of unique words in WordList
	 */
	protected int incNumUniqueWords() {
		numUniqueWords++;
		return numUniqueWords;
	}
	
	/**
	 * Protected mutator method getTotalAllWords
	 * @return The sum of the counts of all words in WordList
	 */
	protected int incTotalAllWords() {
		totalAllWords++;
		return totalAllWords;
	}
	
	/**
	 * Public string method toString
	 * @return A string with Unique Words and Total Word Count
	 */
	public String toString() {
		String str = "";
		str = "Unique Words: " + numUniqueWords +
				", Total Word Count: " + totalAllWords;
		return str;
	}
}

