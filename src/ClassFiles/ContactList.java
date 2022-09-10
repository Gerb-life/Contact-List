package ClassFiles;


import Enums.MaritalStatus;

import java.io.*;

import java.util.Locale;
import java.util.Scanner;

public class ContactList {
    public static void fileEntry(){

    }

    public static void display() throws Exception {
        int screen = -1;

        Table<Contact> tb = new Table<>();
        Table<Contact> tb2 = new Table<>();

        File f =  new File("D:\\Users\\quibl\\IdeaProjects\\ContactList\\src\\TextFiles\\personalContacts.txt");
        File f2 =  new File("D:\\Users\\quibl\\IdeaProjects\\ContactList\\src\\TextFiles\\workContacts.txt");
        ContactList ctl = new ContactList();

        String[] str = ctl.readFile(f2);
        String[] str2 = ctl.readFile(f);

        String[][] contacts = ctl.separateContacts(str);
        for(int i = 0 ; i < contacts.length;i++){
            Contact ct = ctl.createContacts(contacts[i],'w');
            tb.addFirst(ct);
        }

        String[][] contacts1 = ctl.separateContacts(str2);
        for(int i = 0 ; i < contacts1.length;i++){
            Contact ct = ctl.createContacts(contacts1[i],'p');
            tb2.addFirst(ct);
        }


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
                System.out.println("Thank you have a nice day!");
                screen = 0;
            }
            else if(option ==1){
                /** do intersect*/


            }
            else if(option ==2){
                /** do Difference*/
            }
            else if(option ==3){
                /** do Union*/
                Table<Contact> tb3 = new Table<>();
                tb3 = tb3.union(tb , tb2);
                tb3.printTable(tb3);
            }
            else if(option ==4){
                /** do Select*/
            }
            else if(option ==5){
                /** do Remove*/
            }
            else if(option ==6){
                /** do Print Both*/
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
        else if(people[0].toLowerCase().charAt(0) == 'w'){
            String[][] contactsW = new String[people.length/12][12];
            for(int i = 0 ; i < people.length; i++){
                contactsW[i/12][i%12] = people[i];
            }
            return contactsW;
        }



        return null;
    }

    public Contact createContacts(String[] strings , Character pw){
            String email ;
            String phone ;
            String label ;
        if(pw == 'p' || pw == 'P'){
            PersonalInfo ps = new PersonalInfo(strings[0] , strings[1] );
            email = strings[2];
            phone = strings[3];
            Contact.Address address = new Contact.Address(strings[4] , strings[5] , strings[6] , strings[7]);
            label = strings[8];

            Contact personal = new PersonalContact(ps , address , phone , email , label);

            return personal;
        }
        else if(pw == 'w' || pw == 'W'){
            PersonalInfo ps = new PersonalInfo(strings[0] , strings[1] , MaritalStatus.valueOf(strings[2].toUpperCase()));
            email = strings[3];
            phone = strings[4];
            Contact.Address address = new Contact.Address(strings[5] , strings[6] , strings[7] , strings[8]);

            Contact work = new WorkContact(ps , address , phone , email , strings[9] , strings[10] , strings[11]);

            return work;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        display();






    }
}
