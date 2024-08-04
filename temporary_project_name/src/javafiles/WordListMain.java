package javafiles;

/**
 * 
 * @author TMyatt
 */
public class WordListMain {
	/**
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		
		WordHashMap wl;
		
		wl = new WordHashMap();

		wl.addWord("Myatt");
		wl.addWord("Myatt");
		wl.addWord("Timothy");
		wl.addWord("Esquire");
		wl.addWord("Myatt");
		wl.addWord("Myatt");
		wl.addWord("Timothy");

		System.out.println("get: " + wl.getWord("Timothy"));
		System.out.println("get: " + wl.getWord("Myatt"));
		System.out.println("get: " + wl.getWord("HelpMeICan"));
		System.out.println("exists: " + wl.existsWord("Timothy"));
		System.out.println("exists: " + wl.existsWord("Myatt"));
		System.out.println("exists: " + wl.existsWord("HelpMeICan"));
		wl.printWordList(1);
		
       WordArrayList w2;
		
		w2 = new WordArrayList();

		w2.addWord("Myatt");
		w2.addWord("Myatt");
		w2.addWord("Timothy");
		w2.addWord("Esquire");
		w2.addWord("Myatt");
		w2.addWord("Myatt");
		w2.addWord("Timothy");

		System.out.println("get: " + w2.getWord("Timothy"));
		System.out.println("get: " + w2.getWord("Myatt"));
		System.out.println("get: " + w2.getWord("HelpMeICan"));
		System.out.println("exists: " + w2.existsWord("Timothy"));
		System.out.println("exists: " + w2.existsWord("Myatt"));
		System.out.println("exists: " + w2.existsWord("HelpMeICan"));
		w2.printWordList(1);
		
		
	}
}
