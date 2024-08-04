package javafiles;

import java.util.ArrayList;

/**
 * 
 * @author TMyatt
 * 
 * Description: The implementation of WordList
 * using the Java build-in ArrayList dynamic arrays.
 */
public class WordArrayList extends WordList {
	static ArrayList<String> words = new ArrayList<String>();
	static ArrayList<Integer> count = new ArrayList<Integer>();
	static int lastIndex = 0;
	
	/**
	 * Public constructor method WordArrayList
	 */
	public WordArrayList() {
		super();
		lastIndex = 0;
	}

	/**
	 * Protected method searchWord
	 * @param inWord String
	 * @return -1 if inWord not found, array index if found
	 */
	private int searchWord(String inWord) {
		int index = 0;
		for (index = 0; index < lastIndex; index++) {
			if (inWord.compareTo(words.get(index)) == 0) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * Public method getWord
	 * @param inWord String
	 * @return 0 if inWord not found, inWord count if found
	 */
	@Override
	public int getWord(String inWord) {
		int index = searchWord(inWord);
		if (index >= 0) {
			return count.get(index);
		} else {
			return 0;
		}
	}
			
	/**
	 * Public method existsWord
	 * @param inWord String
	 * @return True if inWord exists in WordList
	 */
	@Override
	public boolean existsWord(String inWord) {
		int index = searchWord(inWord);
		if (index >= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Public method addWord
	 * @param inWord String
	 * @return 1 if inWord not found, incremented inWord count if found
	 */
	@Override
	public int addWord(String inWord) {
		int index = searchWord(inWord);
		if (index >= 0) {
			super.incTotalAllWords();
			count.set(index, count.get(index)+1);
		} else {
			super.incNumUniqueWords();
			super.incTotalAllWords();
			index = lastIndex;
			words.add(index, String.valueOf(inWord));
			count.add(index, 1);
			lastIndex += 1;
		}
		return count.get(index);
	}
	
	/**
	 * Public method incWord
	 * @param inWord String
	 * @return 0 if inWord not found, incremented inWord count if found
	 */
	@Override
	public int incWord(String inWord) {
		int index = searchWord(inWord);
		if (index >= 0) {
			super.incTotalAllWords();
			count.set(index, count.get(index)+1);
			return count.get(index);
		} else {
			return 0;
		}
	}
	
	/**
	 * Public method printWordList
	 * @param inMinimum int
	 */
	@Override
	public void printWordList(int inMinimum) {
		System.out.println(super.toString());
		for (int i = 0; i < lastIndex; i++) {
			if (count.get(i) >= inMinimum) {
				System.out.println(words.get(i) + 
						":"+ count.get(i));
			}
		}
	}
	
	/**
	 * Public string method toString
	 * @return A string with Unique Words and Total Word Count
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}