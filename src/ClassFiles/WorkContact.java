package ClassFiles;

import ClassFiles.Contact;

public class WorkContact extends Contact implements Cloneable{
    /** Job title */
    private String title;
    /** Company contact works for */
    private String company;
    /** ClassFiles.Contact’s department */
    private String department;

    public WorkContact(PersonalInfo person, Contact.Address address, String phone, String email) {
        super(person, address, phone, email);
    }
}
