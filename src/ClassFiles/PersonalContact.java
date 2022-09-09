package ClassFiles;

public class PersonalContact extends Contact implements Cloneable{
    /** Label for grouping personal contacts */
    private String label;

    //Total of 9 attributes

    public PersonalContact(PersonalInfo person, Address address, String phone, String email , String label) {
        super(person, address, phone, email);
        this.label = label;
    }
}
