
/**
 * This class implements a contact which consist of the contact's name, last name,
 * marital status, address, phone number and email.
 * @author Gabe Rodriguez
 * @version 2.5
 */
package ClassFiles;

import Enums.MaritalStatus;
import Interfaces.ContactIF;

import java.util.Locale;

public class Contact implements ContactIF, Cloneable{
    /** Personal information */
    private final PersonalInfo person;
    /** Current address */
    private final Address address;
    /** Phone number */
    private String phone;
    /** Email */
    private String email;
    
    
    /**
     * This constructs a contact with a specific person, the persons address, phone number
     * and email
     * @param person the name, last name and marital status of a Contact
     * @param address the address of the contact
     * @param phone the phone number of the contact
     * @param email the email address of the contact
     */
    public Contact(PersonalInfo person , Address address, String phone , String email){
        this.person = person;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }




    /**
     * This class implements an address of contact, the address contains the street address,
     * city, state and a 5 dijit code
     * @author Gabriel Rodriguez
     */
    static class Address{
        private String streetAddress;
        private String city;
        private String state;
        private String zip;


        /**
         * This constructs an address with a specific StreetAddress, city, state and
         * zip
         * @param address the street address of the address
         * @param city the city where the address is located
         * @param state the state where the city of the address is located
         * @param zip the zip code of the address
         */
        public Address(String address , String city , String state , String zip){
            this.streetAddress = address;
            this.city = city;
            this.state = state;
            this.zip = zip;
        }
        
        
        /**
         * This returns the current street address of this Contact
         * @return this contact's street address
         */
        public String getStreetAddress(){
            return this.streetAddress;
        }
        
        
        /**
         * This returns the current city of this Contact
         * @return this contact's city
         */
        public String getCity(){
            return this.city;
        }
        
        /**
         * This returns the current state of this Contact
         * @return this contact's state
         */
        public String getState(){
            return this.state;
        }
        
        
        /**
         * This returns the current zip code of this Contact
         * @return this contact's zip code
         */
        public String getZip(){
            return this.zip;
        }
    }

    @Override
    public boolean exists(String attribute , String value) {
       return false;
    }
    
    
    /**
     *  Returns true if the value of the current person's contact contains a valid value of the
     *  contact's information. It'll return false if the attribute doesn't match the current values of the
     *  contact info throw an illegal argument exception.
     * @param attribute attribute of the current value of contact
     * @param value value of the current contact info
     * @return true if the cases are met and false otherwise
     * @throws IllegalArgumentException throws and exception when the values of the
     *                                   current value is not a string value.
     */
    @Override
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        attribute = attribute.toLowerCase();
        value = value.toLowerCase();
        boolean result = false;
        switch(attribute){
            case "first":
                if(this.person.first.toLowerCase().equals(value)){
                    result = true;
                    break;
                }

            case "last":
                if(this.person.last.toLowerCase().equals(value)){
                    result = true;
                    break;
                }

            case "status":
                if(this.person.status.name().equals(value.toUpperCase())){
                    result = true;
                    break;
                }

            case "address":
                if(this.address.streetAddress.toLowerCase().equals(value)){
                    result = true;
                    break;
                }
            case "city":
                if(this.address.city.toLowerCase().equals(value)){
                    result = true;
                    break;
                }

            case "state":
                if(this.address.state.toLowerCase().equals(value)){
                    result = true;
                    break;
                }

            case "zip":
                if(this.address.zip.toLowerCase().equals(value)) {
                    result = true;
                    break;
                }
            case "phone":
                if(this.phone.toLowerCase().equals(value)){
                    result = true;
                    break;
                }

            case "email":
                if(this.email.toLowerCase().equals(value)){
                    result = true;
                    break;
                }
        }


        return result;
    }
    
    
    /**
     * sets the values for the contact info
     * @param attribute attribute of the current value of contact
     * @param value value of the current contact info
     * @throws IllegalArgumentException
     */
    @Override
    public void setValue(String attribute, String value) throws IllegalArgumentException {
        switch(attribute){
            case "first":
                this.person.first = value;
                break;
            case "last":
                this.person.last = value;
                break;
            case "status":
                this.person.status = MaritalStatus.valueOf(value.toUpperCase());
                break;
            case "address":
                this.address.streetAddress = value;
                break;
            case "city":
                this.address.city = value;
                break;

            case "state":
                this.address.state = value;
                break;

            case "zip":
               this.address.zip = value;
                break;
            case "phone":
                this.phone = value;
                break;

            case "email":
                this.email = value;
                break;
                }



    }
    
    
    /**
     *  Constructs a string with the current contact information such as the person's name,
     *  last name, marital status and the person's address.
     * @return a string with a contact information.
     */
    public String toString(){
       return this.person.first + " " + this.person.last + " " + this.person.status + " " + this.email + " "+ this.phone
               + " " + this.address.streetAddress + " " + this.address.state + " " + this.address.city + " " +
               this.address.zip;

    }
    
    /**
     * Will check if the current contact's information match and return a true
     * otherwise it will return a false
     * @param ct1 current contact's info
     * @return true if the information id matches otherwise false.
     */
    public boolean equalsContact(Contact ct1) {
        if(this.address.streetAddress.replaceAll("\\s","").equals(ct1.address.streetAddress.replaceAll("\\s",""))&&
                this.address.city.equals(ct1.address.city)&& this.address.state.equals(ct1.address.state)&&
                this.address.zip.equals(ct1.address.zip) && this.email.equals(ct1.email) &&
                this.person.first.equals(ct1.person.first) && this.person.last.equals(ct1.person.last)&&
                this.phone.equals(ct1.phone)){

            return true;
        }
        else{

            return false;
        }
    }
    
    
    /**
     * This returns the current person's name, last name and marital
     * status of the  Contact
     * @return this contact's personal information
     */
    public PersonalInfo getPerson(){
        return this.person;
    }
    
    
    /**
     * This returns the current address of this contact
     * @return this contact's address
     */
    public Address getAddress(){
        return this.address;
    }
    
    
    /**
     * This returns the current phone number of this person
     * @return this contact's phone number
     */
    public String getPhone(){
        return this.phone;
    }
    
    
    /**
     * This returns the current email of this person
     * @return this contact's email
     */
    public String getEmail(){
        return this.email;
    }
}

