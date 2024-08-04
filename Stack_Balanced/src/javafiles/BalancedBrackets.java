package javafiles;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class BalancedBrackets {
    // main function to run driver code
    public static void main(String[] args) {
    	// Check if given command line arguements are not given
        if (args.length != 1) {
            System.out.println("Usage: java BalancedBrackets <input_file>");
            return;
        }
        // Take filename and store it in string variable named filename
        String filename = args[0];
        // Try,catch exception handling
        try {
        	// Call readFile function to read contents
            String fileContent = readFile(filename);
            // Check parenthesis are balanced or not balanced
            String result = checkBalancedBracketsComment(fileContent);
            // Print fetched result
            System.out.println(result);
        } catch (IOException e) {
        	// Print error in case of reading file
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    // Function to read data from file
    private static String readFile(String filename) throws IOException {
    	// Make String Builder object to store content in it
        StringBuilder content = new StringBuilder();
        // Now use Buffer Reader class to read data line by line
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Read input until there is no line
            while ((line = br.readLine()) != null) {
                content.append(line).append('\n'); 
            }
        }
        // Convert Content to string 
        return content.toString();
    }
    // Function to check either brackets there are balanced or not 
    private static String checkBalancedBrackets(String code) {
    	// Use stack class for characters
        Stack<Character> stack = new Stack<>();
       // For Each Loop iteration over File Data String convert to Char Array
        for (char c : code.toCharArray()) {
        	// In case there is left bracket that is opening bracket
            if (c == '{' || c == '[' || c == '(') {
            	//Push opening bracket
                stack.push(c);
            } 
            // In case there is right bracket that is closing bracket
            else if (c == '}' || c == ']' || c == ')') {
            	// If Stack is Empty, it mean starting bracket was not there
                if (stack.isEmpty()) {
                    return "Not balanced: Stack is empty, found " + c + ".";
                }
                // Fetch top element from stack using pop operation
                char top = stack.pop();
                
                // Compare if Brackets are not equal to fetched top element
                if ((c == '}' && top != '{') || (c == ']' && top != '[') || (c == ')' && top != '(')) {
                    return "Not balanced: Expected " + getBracket(top) + ", found " + c + ".";
                }
            }
        }
        // In case stack is not empty, it means it is still unbalanced
        if (!stack.isEmpty()) {
            return "Not balanced: Stack is not empty on completion.";
        }
        // Balanced in case above condition passed successfully
        return "Balanced.";
    }

    private static char getBracket(char closingBracket) {
    	// Return opposite bracket of Opening Bracket using Switch Statment
        switch (closingBracket) {
            case '{':
                return '}';
            case '[':
                return ']';
            case '(':
                return ')';
            default:
                return ' ';
        }
    }
    private static String checkBalancedBracketsComment(String code) {
    	// Stack Object
        Stack<Character> stack = new Stack<>();
        // Boolean variables to check either single line comment or multiline comment
        boolean inSingleLineComment = false;
        boolean inMultiLineComment = false;
        
        // Iterate over lines
        for (int iteration = 0; iteration < code.length(); iteration++) {
            char c = code.charAt(iteration);
            // Check if Single Line Comment
            if (inSingleLineComment) {
                if (c == '\n') {
                    inSingleLineComment = false;
                }
                // Skip Single Line Comment
                continue;
            }
            // Check if Multi Line Comment
            if (inMultiLineComment) {
                if (c == '*' && iteration < code.length() - 1 && code.charAt(iteration + 1) == '/') {
                    inMultiLineComment = false;
                    iteration++; // Skip the '/'
                }
                // Skip MultiLine Comment
                continue;
            }

            if (c == '/' && iteration < code.length() - 1) {
                char nextChar = code.charAt(iteration + 1);
                if (nextChar == '/') {
                    inSingleLineComment = true;
                    continue;
                } else if (nextChar == '*') {
                    inMultiLineComment = true;
                    iteration++; // Skip the '*'
                    continue;
                }
            }
            // In case there is left bracket that is opening bracket
            if (c == '{' || c == '[' || c == '(') {
            	//Push opening bracket
                stack.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
            	// If Stack is Empty, it mean starting bracket was not there
                if (stack.isEmpty()) {
                    return "Not balanced: Stack is empty, found " + c + ".";
                }
                // Fetch top element from stack using pop operation
                char top = stack.pop();
                if ((c == '}' && top != '{') || (c == ']' && top != '[') || (c == ')' && top != '(')) {
                    return "Not balanced: Expected " + getBracket(top) + ", found " + c + ".";
                }
            }
        }

        if (!stack.isEmpty() || inSingleLineComment || inMultiLineComment) {
            return "Not balanced: Stack is not empty at the end";
        }

        return "Balanced.";
    }

}
// End of Code
