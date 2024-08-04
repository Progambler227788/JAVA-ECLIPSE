package javafiles;

import java.util.LinkedList;

/**
 * Implementation of WordList using LinkedList data structure.
 * Maintains a list of words and their counts.
 */
public class WordLinkedList extends WordList {
    private LinkedList<String> words = new LinkedList<>();
    private LinkedList<Integer> count = new LinkedList<>();

    /**
     * Gets the count of a specific word in the list.
     * @param inWord The word to get the count for.
     * @return The count of the word, or 0 if the word is not found.
     */
    @Override
    public int getWord(String inWord) {
        int index = searchWord(inWord);
        if (index != -1) {
            return count.get(index);
        } else {
            return 0;
        }
    }

    /**
     * Checks if a word exists in the list.
     * @param inWord The word to check for existence.
     * @return True if the word exists, otherwise false.
     */
    @Override
    public boolean existsWord(String inWord) {
        return searchWord(inWord) != -1;
    }

    /**
     * Adds a word to the list.
     * @param inWord The word to add.
     * @return The count of the word after addition.
     */
    @Override
    public int addWord(String inWord) {
        int index = searchWord(inWord);
        if (index != -1) {
            return incWord(inWord);
        } else {
            incNumUniqueWords();
            incTotalAllWords();
            words.add(inWord);
            count.add(1);
            return 1;
        }
    }

    /**
     * Increments the count of a word in the list.
     * @param inWord The word to increment the count for.
     * @return The updated count of the word, or 0 if the word is not found.
     */
    @Override
    public int incWord(String inWord) {
        int index = searchWord(inWord);
        if (index != -1) {
            incTotalAllWords();
            count.set(index, count.get(index) + 1);
            return count.get(index);
        } else {
            return 0;
        }
    }

    /**
     * Prints the word list with counts, filtering by minimum count.
     * @param inMinimum The minimum count for words to be printed.
     */
    @Override
    public void printWordList(int inMinimum) {
        System.out.println(toString());
        for (int i = 0; i < words.size(); i++) {
            if (count.get(i) >= inMinimum) {
                System.out.println(words.get(i) + ": " + count.get(i));
            }
        }
    }

    /**
     * Searches for a word in the list.
     * @param inWord The word to search for.
     * @return The index of the word if found, otherwise -1.
     */
    private int searchWord(String inWord) {
        if (words.size() == 0)
            return -1;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals(inWord)) {
                return i;
            }
        }
        return -1;
    }
}
