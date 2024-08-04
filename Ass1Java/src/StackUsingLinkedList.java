public class StackUsingLinkedList {
	
    private LinkedListNode top; // Create top node
  
    // Constructor to create empty stack
    public StackUsingLinkedList() {
        top = null;
    }
    // Check either stack is null or not
    public boolean empty() {
        return top == null;
    }

    // Function to push elements into stack
    public void push(char newitem) {
        
    	// Create node 
        LinkedListNode newNode = new LinkedListNode(newitem);
        // Assign top node to new node next pointer
        newNode.next = top;
        top = newNode;
    }

    // Function to pop element from stack
    public char pop() {
    	// Check if stack is empty return space
        if (empty()) {
            System.out.println("STACK EMPTY");
            return ' '; // Return a default value for char
        }
        // Fetch top of stack element data
        char data = top.data;
        top = top.next;
        return data;
    }

    // Function to print all stack elements
    public void print() {
        System.out.print("Stack: ");
        LinkedListNode current = top;
        // Iterate until current is not null
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
   
    // Fetch top element of stack
    public char peek() {
    	// Check if stack is not empty then return top data else return space
        if (!empty())
            return top.data;
        return ' '; // Return a default value for char
    }
    
    // Linked List class to deal with characters 
    private class LinkedListNode {
    	// Data Members
        char data;
        LinkedListNode next;
        // Constructor to assign data and next pointer as null
        LinkedListNode(char data) {
            this.data = data;
            this.next = null;
        }
    }

}
