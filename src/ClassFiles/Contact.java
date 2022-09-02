package ClassFiles;

import Interfaces.ContactIF;

public class Contact implements ContactIF, Cloneable{
    /** Personal information */
    private PersonalInfo person;
    /** Current address */
    private Address address;
    /** Phone number */
    private String phone;
    /** Email */
    private String email;

    public Contact(PersonalInfo person , Address address, String phone , String email){
        this.person = person;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    static class Address{
        private String streetAddress;
        private String city;
        private String state;
        private String zip;

        public Address(String address , String city , String state , String zip){
            this.streetAddress = address;
            this.city = city;
            this.state = state;
            this.zip = zip;
        }
    }

    @Override
    public boolean exists(String attribute , String value) {
       return false;
    }

    @Override
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        attribute = attribute.toLowerCase();
        switch(attribute){
            case "first":
                if(this.person.first == value){
                    return true;
                }

        }
        return false;
    }

    @Override
    public void setValue(String attribute, String value) throws IllegalArgumentException {

    }
}
