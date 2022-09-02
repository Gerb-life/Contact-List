package ClassFiles;

public class PersonalContact extends Contact implements Cloneable{
    /** Label for grouping personal contacts */
    private String label;


    public PersonalContact(PersonalInfo person, Address address, String phone, String email) {
        super(person, address, phone, email);
    }
}
