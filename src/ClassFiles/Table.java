package ClassFiles;

import ClassFiles.Contact;
import ClassFiles.Node;

public class Table <T extends Contact> {
    /** First record in the table */
    private Node head;
    /** Last record in the table */
    private Node tail;
    /** Label for the table */
    private String title;

}
