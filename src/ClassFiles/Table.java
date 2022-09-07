//package ClassFiles;

//public class Table <T extends Contact> {
    /**
     * First record in the table
     */
  //  private Node head;
    /**
     * Last record in the table
     */
  //  private Node tail;
    /**
     * Label for the table
     */
   // private String title;

   // private class Node {
   //     int data;
   //     Node next;

    //    public Node(int data) {
   //         this.data = data;
   //         this.next = null;
    //    }

  //  }
//}

import java.util.NoSuchElementException;

public class Table <T> {

    private class Node <T> {
        T value;
        private Table.Node next;

        public Node(T value) {
            this.value = value;
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

    //addFirst
    public void addFirst(T item){
        var node = new Table.Node(item);
        if(isEmpty())
            first =  last = node;
        else{
            node.next = first;
            first = node;
        }
    }
    private boolean isEmpty(){
        return first == null ;
    }
    //addLast
    public void addLast(T item) {
        var node = new Table.Node(item);
        if (isEmpty())
            first = last = node;
        else {
            last.next = node;
            last = node;
        }
    }
    //deleteFirts
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
    //deleteLast
    public void removeLast(){
        //[10 -> 20 -> 30]
        // last -> 30
        // lst -> 20
        // previous -> 20
        //last -> 20
        if(isEmpty())
            throw new NoSuchElementException();
        if(first == last){
            first = last = null;
            return;
        }

        var previous = getPreviouse(last);
        last = previous;
        last.next = null;

    }
    private Table.Node getPreviouse(Table.Node node){
        var current = first;
        while (current != null){
            if (current.next == node) return current;
            current = current.next;
        }
        return null;
    }
    //contaisns
    public boolean contains(T item){
        return indexOf(item) !=-1;
    }
    //indexOf
    public int indexOf(T item){
        int index = 0;
        var current = first;
        while (current != null){
            if(current.value == item) return index;
            current = current. next;
            index++;}
        return -1;
    }
}
