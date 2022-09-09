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
    public void readFile() throws Exception{
        File f =  new File("D:\\Users\\quibl\\IdeaProjects\\ContactList\\src\\TextFiles\\personalContacts.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        String st;

        while((st = br.readLine()) != null){
            String tokens = String.valueOf(st.split(","));
            for(int i = 0; i < tokens.length(); i++) {
                //System.out.println(tokens[i]);
            }
        }

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

        //System.out.println(tb.getNode(0).toString());
        //System.out.println(tb.getNode(1).toString());
        ContactList ct = new ContactList();
        ct.readFile();


    }
}
