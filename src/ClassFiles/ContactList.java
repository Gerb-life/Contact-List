package ClassFiles;


import Enums.MaritalStatus;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class ContactList {
    /**This is the driver for the project**/

    public static void display() {
        int screen = -1;


        while (screen != 0) {
            System.out.println("Welcome to database display \n\nPlease make a choice:\n" +
                    "0) Quit\n" +
                    "1) Intersect\n" +
                    "2) Difference\n" +
                    "3) Union\n" +
                    "4) Select\n" +
                    "5) Remove\n" +
                    "6) Print both tables");
            Scanner stdin = new Scanner(System.in);
            int option = stdin.nextInt();
            if(option == 0){
                screen = 0;
            }
            else if(option ==1){
                /** do intersect*/
            }
            else if(option ==2){
                /** do intersect*/
            }
            else if(option ==3){
                /** do intersect*/
            }
            else if(option ==4){
                /** do intersect*/
            }
            else if(option ==5){
                /** do intersect*/
            }
            else if(option ==6){
                /** do intersect*/
            }
            else{
                System.out.println("Invalid option");
            }

        }

    }
    public String[] readFile(File f) throws Exception{

        BufferedReader br = new BufferedReader(new FileReader(f));
        String st;
        String temp = "";
        String[] clients;
        while((st = br.readLine()) != null){
            temp += st;
        }
       clients = temp.split(",");
        for(int i = 0 ; i < clients.length; i++){
            clients[i] = clients[i].trim();

        }
        return clients;
    }
    public String[][] separateContacts(String[] people){
        //if the first letter of string arr is p then separate contacts based on number of attributes
        if(people[0].toLowerCase().charAt(0) == 'p'){
            String[][] contactsP = new String[people.length/9][9];
            for(int i = 0 ; i < people.length; i++){
                contactsP[i/9][i%9] = people[i];
            }
            return contactsP;

        }



        return null;
    }

    public Contact createContacts(String[] strings , Character pw){

        if(pw == 'p' || pw == 'P'){
            PersonalInfo ps = new PersonalInfo(strings[0] , strings[1] );
            String email = strings[2];
            String phone = strings[3];
            Contact.Address address = new Contact.Address(strings[4] , strings[5] , strings[6] , strings[7]);
            String label = strings[8];

            Contact personal = new PersonalContact(ps , address , phone , email , label);

            return personal;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        //display();
        PersonalInfo ps = new PersonalInfo("Steve" , "Jobs" , MaritalStatus.MARRIED);
        Contact.Address ca = new Contact.Address("Fairview" , "Sylva" , "NC" ,"28779" );
        Contact steve = new Contact(ps , ca , "8284766872" , "chumbo@yahoo.comm");

        PersonalInfo ps1 = new PersonalInfo("Grechen" , "Smith" , MaritalStatus.DIVORCED);
        Contact.Address ca1 = new Contact.Address("Smokey" , "Waynesville" , "NC" ,"28788" );
        Contact grechen = new Contact(ps1 , ca1 , "1232132" , "yehaw@gamil.com");


        Table<Contact> tb = new Table<>();
        tb.addFirst(steve);
        tb.addFirst(grechen);
        File f =  new File("D:\\Users\\quibl\\IdeaProjects\\ContactList\\src\\TextFiles\\personalContacts.txt");
        File f2 =  new File("D:\\Users\\quibl\\IdeaProjects\\ContactList\\src\\TextFiles\\workContacts.txt");

        System.out.println(tb.getNode(0).toString());

        ContactList ctl = new ContactList();
        String[] str = ctl.readFile(f);

        String[][] contacts = ctl.separateContacts(str);
        for(int i = 0 ; i < contacts.length;i++){
            Contact ct = ctl.createContacts(contacts[i],'p');
            tb.addFirst(ct);
        }
        System.out.println(tb.getCount());
        System.out.println(tb.getNode(0).toString());
        System.out.println(tb.getNode(1).toString());
        System.out.println(tb.getNode(2).toString());
        System.out.println(tb.getNode(3).toString());
        System.out.println(tb.getNode(4).toString());

    }
}
