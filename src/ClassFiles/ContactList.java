package ClassFiles;

import Enums.MaritalStatus;

public class ContactList {
    /**This is the driver for the project**/
    public static void main(String[] args){
        PersonalInfo ps = new PersonalInfo("Steve" , "Jobs" , MaritalStatus.MARRIED);
        Contact.Address ca = new Contact.Address("Fairview" , "Sylva" , "NC" ,"28779" );
        Contact steve = new Contact(ps , ca , "8284766872" , "chumbo@yahoo.comm");

        System.out.println(steve.toString());
        steve.setValue("first" , "john");

        System.out.println(steve.toString());



    }
}
