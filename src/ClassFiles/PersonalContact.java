package ClassFiles;

public class PersonalContact extends Contact implements Cloneable{
    /** Label for grouping personal contacts */
    private String label;

    //Total of 9 attributes

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
