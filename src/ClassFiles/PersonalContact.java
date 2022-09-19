/**
 * This class is an extension of the contact class and implements a personalContact
 * which contains the label of the contact. The contact's personal info such as name
 * las name, phone number, and email.
 * @author Gabe Rodriguez, Alejandro Ordonez
 * @version 1.5
 */
package ClassFiles;

public class PersonalContact extends Contact implements Cloneable{
    /** Label for grouping personal contacts */
    private String label;

    //Total of 9 attributes
    /**
     * This Constructs a contact using the information from the contact.java file,
     * with the name, last name, marital status, address, phone number and label of the contact
     * @param person the current contact's basic information such as name, last name, marital status
     * @param address the address fo the current contact
     * @param phone the phone number for the current  contact
     * @param email the email for the current contact
     * @param label the relation of the current contact
     */
    public PersonalContact(PersonalInfo person, Address address, String phone, String email , String label) {
        super(person, address, phone, email);
        this.label = label;
    }
    
    /**
     *  Constructs a string with the current contact information such as the person's name,
     *  last name, marital status and the person's address.
     * @return a string with a contact information.
     */
    public String toString() {
        return "Category: " + label + "\n\t\t" +
                this.getPerson().last +","+ this.getPerson().first + "  Phone: " + this.getPhone()  + "\n\t\t" +
                this.getAddress().getStreetAddress() + "\n\t\t" +
                this.getAddress().getCity() + "," + this.getAddress().getState() + " " + this.getAddress().getZip()+ "\n\t\t"+
                this.getEmail() + "\n --------------------------------";
    }
}
