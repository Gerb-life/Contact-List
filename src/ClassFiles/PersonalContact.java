package ClassFiles;

public class PersonalContact extends Contact implements Cloneable{
    /** Label for grouping personal contacts */
    private String label;

    //Total of 9 attributes

    public PersonalContact(PersonalInfo person, Address address, String phone, String email , String label) {
        super(person, address, phone, email);
        this.label = label;
    }
    public String toString() {
        return "Category: " + label + "\n" + person + "phone: " + phone  + "\n" + address + "\n" + email ;
    }
}
