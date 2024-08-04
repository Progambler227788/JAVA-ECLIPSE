
public class Stack {
    private char[] items;
    private int top;
    private final int MAX;

    public Stack(int capacity) {
        MAX = capacity;
        items = new char[MAX];
        top = -1;
    }

    public boolean full() {
        return top == MAX - 1;
    }

    public boolean empty() {
        return top == -1;
    }
    
    // Function to push elements into stack
    public void push(char newitem) {
        if (full()) {
            System.out.println("STACK IS FULL");
            return;
        }
        items[++top] = newitem;
    }
    
    // Function to pop element from stack 
    public char pop() {
        if (empty()) {
            System.out.println("STACK EMPTY");
            return (char) 0; // Return a default value for char
        }
        return items[top--];
    }
   
    // Function to print all stack elements
    public void print() {
        System.out.print("Stack: ");
        for (int i = 0; i <= top; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public char peek() {
        if (!empty())
            return items[top];
        return (char) 0; // Return a default value for char
    }

   
}

