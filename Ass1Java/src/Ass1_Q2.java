import java.util.Scanner;


// Class to test brackets combination
public class Ass1_Q2 {
    public static boolean isPar(String x) {
    	// Create stack object on heap memory
    	StackUsingLinkedList obj = new StackUsingLinkedList();
    	// Function to store popped character from stack
        char a;
        // Loop until string length
        for (int i = 0; i <= x.length()-1; i++) {
        	// If there is left bracket, push it to stack
            if (x.charAt(i) == '{' || x.charAt(i) == '[' || x.charAt(i) == '(') {
                obj.push(x.charAt(i));
            }
            
            else {
            	// If stack is empty, it means there are only right brackets (no left)
                if (obj.empty()) {
                    return false;
                }
                // Check bracket pair
                switch (x.charAt(i)) {
                // If there is right curly bracket, then check top of stack bracket
                    case '}':
                        a = obj.pop();
                        // Opposite bracket so wrong combination
                        if (a == '[' || a == '(') {
                            return false;
                        }
                        break;
                // If there is right square bracket, then check top of stack bracket
                    case ']':
                        a = obj.pop();
                        // Opposite bracket so wrong combination
                        if (a == '{' || a == '(') {
                            return false;
                        }
                        break;
               // If there is right circular bracket, then check top of stack bracket
                    case ')':
                        a = obj.pop();
                        // Opposite bracket so wrong combination
                        if (a == '{' || a == '[') {
                            return false;
                        }
                        break;
                }
            }
        }
        // If stack is empty then it means combination of brackets is perfect otherwise wrong order
        return obj.empty();
    }
    
    // Main function to test driving code

    public static void main(String[] args) {
    	
    	// Manual Testing 
        String testString = "{[()]}";
        System.out.println(isPar(testString)); // Output: true

        String testString2 = "{[()]";
        System.out.println(isPar(testString2)); // Output: false
        
        
     // Automatic Testing using User Input
        
        // Creates scannerObj object
  	   Scanner scannerObj = new Scanner(System.in);
  
        // To store user menu choice
  	   
  	   
  	   
        
        String combination="()";
        
        System.out.println("Hi, welcome to Paranthesis Testing! (space or empty to exit)");
        
        while(true) {
        	// Display message to user
            System.out.println("Enter String:");
            
            // Get user choice
            combination = scannerObj.nextLine();
            
            if (combination==" "|| combination=="") {
            	break; 
            }
            
            System.out.println(isPar(combination));
        }
        
        
        
        // Clear memory leakages
        scannerObj.close();
        
        System.out.println("Thanks for using. Exited!! (:");
        
        
    
  }
}

