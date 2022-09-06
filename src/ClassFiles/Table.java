package ClassFiles;

public class Table <T extends Contact> {
    /**
     * First record in the table
     */
    private Node head;
    /**
     * Last record in the table
     */
    private Node tail;
    /**
     * Label for the table
     */
    private String title;

    private class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

    }
}
