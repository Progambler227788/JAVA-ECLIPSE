package javafiles;

import java.util.HashMap;

/**
 * Implementation of WordList using HashMap data structure.
 * Maintains a map of words and their counts.
 */
public class WordHashMap extends WordList {
    private HashMap<String, Integer> wordMap = new HashMap<>();

    /**
     * Gets the count of a specific word in the map.
     * @param inWord The word to get the count for.
     * @return The count of the word, or 0 if the word is not found.
     */
    @Override
    public int getWord(String inWord) {
        return wordMap.getOrDefault(inWord, 0);
    }

    /**
     * Checks if a word exists in the map.
     * @param inWord The word to check for existence.
     * @return True if the word exists, otherwise false.
     */
    @Override
    public boolean existsWord(String inWord) {
        return wordMap.containsKey(inWord);
    }

    /**
     * Adds a word to the map.
     * @param inWord The word to add.
     * @return The count of the word after addition.
     */
    @Override
    public int addWord(String inWord) {
        int count = wordMap.getOrDefault(inWord, 0);
        wordMap.put(inWord, count + 1);
        incTotalAllWords();
        if (count == 0) {
            incNumUniqueWords();
        }
        return count + 1;
    }

    /**
     * Increments the count of a word in the map.
     * @param inWord The word to increment the count for.
     * @return The updated count of the word, or 0 if the word is not found.
     */
    @Override
    public int incWord(String inWord) {
        int count = wordMap.getOrDefault(inWord, 0);
        if (count > 0) {
            wordMap.put(inWord, count + 1);
            incTotalAllWords();
        }
        return count + 1;
    }

    /**
     * Prints the word map with counts, filtering by minimum count.
     * @param inMinimum The minimum count for words to be printed.
     */
    @Override
    public void printWordList(int inMinimum) {
        System.out.println(toString());
        for (String word : wordMap.keySet()) {
            int count = wordMap.get(word);
            if (count >= inMinimum) {
                System.out.println(word + ": " + count);
            }
        }
    }
}
