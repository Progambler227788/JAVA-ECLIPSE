// Linked List class
public class LinkedList {
    // Data members
    public LinkedList nextPointer;
    public int data;

    // A constructor function to create empty node
    public LinkedList (int data){
        nextPointer = null; // assign null as by default it will be last node
        this.data = data;   // Assign data 
    }
      // Set data of a node
    public void setDataOfNode (int storageData){
    data = storageData;
    }

    // This function will return data for a node
    public int getDataOfNode(){
        return data;
    }

    // This piece of code will set next pointer for a node that will call this function
    public void setNextPointer (LinkedList node){
        nextPointer = node;
   }

    // Get next node for a node that calls this function
    public LinkedList getNextPointer(){
         return nextPointer;
    }
    
 
    // Sets the data stored in this node.
    public String toString (){
        return Integer.toString(data);
    } 
} 
