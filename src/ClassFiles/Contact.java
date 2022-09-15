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
        public String getStreetAddress(){
            return this.streetAddress;
        }
        public String getCity(){
            return this.city;
        }
        public String getState(){
            return this.state;
        }
        public String getZip(){
            return this.zip;
        }
    }

    @Override
    public boolean exists(String attribute , String value) {
       return false;
    }

    @Override
    public boolean hasValue(String attribute, String value) throws IllegalArgumentException {
        attribute = attribute.toLowerCase();
        value = value.toLowerCase();
        switch(attribute){
            case "first":
                if(this.person.first.toLowerCase().equals(value)){
                    return true;
                }

            case "last":
                if(this.person.last.toLowerCase().equals(value)){
                    return true;
                }

            //case "status":
              //  if(this.person.status.toString().toLowerCase().equals(value)){
               //     return true;
              //  }

            case "address":
                if(this.address.streetAddress.toLowerCase().equals(value)){
                    return true;
                }
            case "city":
                if(this.address.city.toLowerCase().equals(value)){
                    return true;
                }

            case "state":
                if(this.address.state.toLowerCase().equals(value)){
                    return true;
                }

            case "zip":
                if(this.address.zip.toLowerCase().equals(value)) {
                    return true;
                }
            case "phone":
                if(this.phone.toLowerCase().equals(value)){
                    return true;
                }

            case "email":
                if(this.email.toLowerCase().equals(value)){
                    return true;
                }
        }


        return false;
    }

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
                //this.person.status = MaritalStatus.valueOf(value);
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

    public String toString(){
       return this.person.first + " " + this.person.last + " " + this.person.status + " " + this.email + " "+ this.phone
               + " " + this.address.streetAddress + " " + this.address.state + " " + this.address.city + " " +
               this.address.zip;

    }
    public boolean equalsContact(Contact ct1) {
        if(this.address.streetAddress.replaceAll("\\s","").equals(ct1.address.streetAddress.replaceAll("\\s","")) &&
                this.address.state.equals(ct1.address.state) &&
                this.address.city.equals(ct1.address.city) && this.address.zip.equals(ct1.address.zip) &&
                this.email.equals(ct1.email) &&
                this.person.first.equals(ct1.person.first) && this.person.last.equals(ct1.person.last) &&
                this.phone.equals(ct1.phone)){
            return true;
        }
        else{
            return false;
        }
    }
    public PersonalInfo getPerson(){
        return this.person;
    }
    public Address getAddress(){
        return this.address;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getEmail(){
        return this.email;
    }
}

