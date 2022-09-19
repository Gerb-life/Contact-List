package ClassFiles;

/**
 * This class implements a Table a genetics linked list that contains the contact,s
 * information and various methods that interact with the linked lists.
 * @author Gabe Rodriguez, Alejandro Ordonez
 * @version 2.5
 */

import java.util.NoSuchElementException;

public class Table <T> {

    private class Node <T> {
        T value;
        private Table.Node next;

        public Node(T value) {
            this.value = value;
        }
        public T getValue(){
            return this.value;
        }
    }

    /**
     * First record in the table
     */
    private Table.Node first;
    /**
     * Last record in the table
     */
    private Table.Node last;
    private int count;

    /**
     * Adds a new node to the beginning of the list
     * @param item the new node
     */
    public void addFirst(T item){
        var node = new Table.Node(item);
        if(isEmpty()) {
            first = last = node;
            count++;
        }
        else{
            node.next = first;
            first = node;
            count++;
        }
    }
    
    /**
     * Returns null if the first node of the list is null which means that the
     * linked list is empty
     * @return null if the list is empty
     */
    private boolean isEmpty(){
        return first == null ;
    }
    
    /**
     * Adds a new node to the end of the linked list
     * @param item new node to add to the linked list
     */
    public void addLast(T item) {
        var node = new Table.Node(item);
        if (isEmpty()) {
            first = last = node;
            count++;
        }
        else {
            last.next = node;
            last = node;
            count++;
        }
    }
    
    
    /**
     * Removes the first node of the current linked list if specified.
     */
    public void removeFirst(){
        //[10 -> 20 -> 30]
        //first -> 20
        if(isEmpty())
            throw new NoSuchElementException();
        if(first == last){
            first = last = null;
            return;
        }
        var second = first.next;
        first.next = null;
        first = second;
    }
    
    /**
     * Removes the last node of the current linked list if specified.
     */
    public void removeLast(){
        
        if(isEmpty())
            throw new NoSuchElementException();
        if(first == last){
            first = last = null;
            return;
        }

        var previous = getPrevious(last);
        last = previous;
        last.next = null;

    }


    /**
     * Removes a node from the linked list in a specific index
     * @param the index of node to be removed
     */
    public void removeNode(int index){
        if(first == null){
             return;
        }
        Table.Node temp = first;

        if(index == 0){
            first = temp.next;
            return;
        }

        for (int i = 0; temp != null && i < index - 1; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            return;
        }
        Table.Node next = temp.next.next;
        temp.next = next;
        count--;
    }
        
        
    /**
     * Returns the node that follows the first node if the node matches
     * a specific node then sets the current node to that specific node.
     * @param node the specif node to test the current node
     * @return the current node
     */    
    private Table.Node getPrevious(Table.Node node){
        var current = first;
        while (current != null){
            if (current.next == node) return current;
            current = current.next;
        }
        return null;
    }
    
    
    /**
     * Returns the index of a specific node if the node exist in the list.
     * @param item the node the user is looking for
     * @return  the index of the node
     */
    public boolean contains(T item){
        return indexOf(item) !=-1;
    }
    
    
    /**
     * Returns the index of a specif node in the list.
     * @param item the node which index the user is looking for
     * @return the index of node.
     */
    public int indexOf(T item){
        int index = 0;
        var current = first;
        while (current != null){
            if(current.value == item) return index;
            current = current. next;
            index++;}
        return -1;
    }


    /**
     * Returns the current count of the linked list
     * @return current count of list
     */
    public int getCount(){
        return this.count;
    }
    
    
    /**
     * Method used to get a node at a certain index.
     * @param index the index of node
     * @return a node of the list
     */
    public T getNode(int index){
        Node current = first;
        int count = 0;
        while(current != null){
            if(count == index){
                return (T) current.value;
            }
            count++;
            current = current.next;
        }
        return null;
    }
    
    
    /**
     * Prints the values of a specific linked list
     * @param table the linked list
     */
    public void printTable(Table table){
        for(int i = 0 ; i < this.getCount(); i++){
            System.out.println(this.getNode(i).toString());
        }
    }
    
    
    /**
     * Returns a linked list with the combine values of two linked lists
     * @param tb1 a linked lists
     * @return a new list with the values of the two linked lists
     */
    public Table<T>union(Table<T>tb1){
        Table<T> tb3 = new Table<T>();
        for(int i = 0; i < this.getCount(); i++){
            tb3.addFirst(this.getNode(i));
        }

       for(int i = 0 ; i < tb1.getCount(); i++){
           tb3.addFirst(tb1.getNode(i));
       }

       for(int i = 0 ; i < this.getCount();i++){
           for(int j = 0 ; j < tb1.getCount(); j++){
               Contact ct1 = (Contact) this.getNode(i);
               Contact ct2 = (Contact) tb1.getNode(j);
               if(ct1.equalsContact(ct2)){
                   if(tb3.contains(this.getNode(i)) && tb3.contains(tb1.getNode(j))){
                       tb3.remove("first" , ((Contact) tb1.getNode(j)).getPerson().first);
                       tb3.count--;
                   }
               }

           }
       }


        return tb3;
    }
    
    
    /**
     * Returns a linked lis of commune values shared by two linked lists
     * @param tb1 the linked list that intersects the second list
     * @param attribute the attribute of the list
     * @param value the value that the user is looking for
     * @return a linked list the with the commune values
     */
    public Table<Contact >intersect(Table<Contact> tb1, String attribute , String value){
        Table<Contact> newTable = new Table<>();

        for (int i = 0; i < tb1.getCount(); i++) {
            if (tb1.getNode(i).hasValue(attribute, value)) {
                newTable.addFirst(tb1.getNode(i));
            }
        }

        return newTable;
    }
    
    
    /**
     * Returns a linked list with the different values of two linked lists
     * @param tb1 a linked list
     * @return a new linked list with the different values
     */
    public Table<Contact> difference(Table<Contact> tb1){
        Table <Contact> tb3 = new Table<>();
        for(int i = 0 ; i < this.getCount(); i++){
            for(int j = 0; j < tb1.getCount(); j++){
                if(! this.getNode(i).equals(tb1.getNode(j))){
                    if(tb3.contains((Contact) this.getNode(i)) == false){
                        tb3.addFirst((Contact) this.getNode(i));
                    }
                }
            }
        }
        return tb3;
    }
    
    
    /**
     * Returns a linked list with a specific values the user might be looking for
     * @param attribute the attribute of the list
     * @param value the specific value the user is looking fore
     * @return linked list with a specific value.
     */
    public Table<Contact> select(String attribute , String value){
        Table <Contact> tb3 = new Table<>();

        for(int i = 0 ; i < this.getCount(); i++){
            Contact ct = (Contact) this.getNode(i);
            if(ct.hasValue(attribute , value.toUpperCase())){
                 tb3.addFirst(ct);
            }
        }
        return tb3;
    }


    /**
     * Remove a specific value from a linked list
     * @param attribute the attribute  of the list
     * @param value the user wants to remove
     */
    public void remove(String attribute , String value){
        for(int i = 0 ; i < this.getCount(); i++){
            Contact ct = (Contact) this.getNode(i);
            if(ct.hasValue(attribute,value)){
                this.removeNode(i);

                return;
            }
        }
    }


}
