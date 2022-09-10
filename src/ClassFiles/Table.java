package ClassFiles;



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

    //addFirst
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
    private boolean isEmpty(){
        return first == null ;
    }
    //addLast
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

        var previous = getPrevious(last);
        last = previous;
        last.next = null;

    }
    private Table.Node getPrevious(Table.Node node){
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



    public int getCount(){
        return this.count;
    }
    /**
     * Method used to get a node at a certain index.
     * */
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

    public void printTable(Table table){
        for(int i = 0 ; i < table.getCount(); i++){
            System.out.println(table.getNode(i).toString());
        }
    }

    Table<T>union(Table<T>tb1 , Table<T> tb2){
        Table<T> tb3 = new Table<T>();
        for(int i = 0 ; i< tb1.getCount(); i++){
            if(! tb3.contains(tb1.getNode(i))){
                tb3.addFirst(tb1.getNode(i));
            }
        for(int j = 0 ; j <tb2.getCount(); j++){
            if(! tb3.contains(tb2.getNode(j))){
                tb3.addFirst(tb2.getNode(j));
            }
        }
        }
        return tb3;
    }

}
