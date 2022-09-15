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

    private Table.Node getPrevious(Table.Node node){
        var current = first;
        while (current != null){
            if (current.next == node) return current;
            current = current.next;
        }
        return null;
    }
    //contains
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
        for(int i = 0 ; i < this.getCount(); i++){
            System.out.println(this.getNode(i).toString());
        }
    }

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
    
    public Table<Contact >intersect(Table<Contact> tb1, String attribute , String value){
        Table<Contact> newTable = new Table<>();

        for (int i = 0; i < tb1.getCount(); i++) {
            if (tb1.getNode(i).hasValue(attribute, value)) {
                newTable.addFirst(tb1.getNode(i));
            }
        }

        return newTable;
    }

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
    public Table<Contact> select(String attribute , String value){
        Table <Contact> tb3 = new Table<>();

        for(int i = 0 ; i < this.getCount(); i++){
            Contact ct = (Contact) this.getNode(i);
            if(ct.hasValue(attribute , value)){
                 tb3.addFirst(ct);
            }
        }
        return tb3;
    }

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
