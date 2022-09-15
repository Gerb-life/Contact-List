package ClassFiles;


import Enums.MaritalStatus;

import java.io.*;

import java.util.Locale;
import java.util.Scanner;

public class ContactList {



    public static void display() throws Exception {
        int screen = -1;

        Table<Contact> tb = new Table<>(); // work contact
        Table<Contact> tb2 = new Table<>();// personal contact

        File f =  new File("D:\\Users\\quibl\\IdeaProjects\\ContactList\\src\\TextFiles\\personalContacts.txt");
        File f2 =  new File("D:\\Users\\quibl\\IdeaProjects\\ContactList\\src\\TextFiles\\workContacts.txt");
        ContactList ctl = new ContactList();

        // result from reading files
        String[] str = ctl.readFile(f2);
        String[] str2 = ctl.readFile(f);

        Character pw = str[0].charAt(0);
        str[0] = str[0].substring(1);

        Character pw2 = str2[0].charAt(0);
        str2[0] = str2[0].substring(1);

        String[][] contacts = ctl.separateContacts(str , pw);

        for(int i = 0 ; i < contacts.length;i++){
            Contact ct = ctl.createContacts(contacts[i],pw);
            tb.addFirst(ct);
        }


        String[][] contacts1 = ctl.separateContacts(str2, pw2);
        for(int i = 0 ; i < contacts1.length;i++){
            Contact ct = ctl.createContacts(contacts1[i],pw2);
            tb2.addFirst(ct);
        }

        System.out.println(tb2.getNode(2).equalsContact(tb.getNode(3)));
        Contact ct = tb2.getNode(2);
        Contact ct2 = tb.getNode(3);
        System.out.println(ct.equalsContact(ct2));

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
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Group");
                int groupNum = sc.nextInt();
                System.out.println("Enter attribute");
                String attribute = sc.next();
                System.out.println("Enter value");
                String value = sc.next();

                Table<Contact> tb3 = new Table<>();
                if(groupNum ==1){
                    System.out.println("==============Group 1 ==============");
                    tb3 = tb3.intersect(tb , attribute , value);
                    tb3.printTable(tb3);
                    System.out.println("==============Group 1 ==============");
                }
                else if(groupNum ==2) {
                    System.out.println("==============Group 2 ==============");
                    tb3 = tb3.intersect(tb2 , attribute , value);
                    tb3.printTable(tb3);
                    System.out.println("==============Group 2 ==============");
                }
            }
            else if(option ==2){
                /** do Difference*/
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Group");
                int groupNum = sc.nextInt();
                Table<Contact> tb3 = new Table<>();
                if(groupNum == 1){
                    System.out.println("==============Group 1 ==============");
                    tb3 = tb2.difference(tb);
                    tb3.printTable(tb3);
                    System.out.println("==============Group 1 ==============");
                }
                else if(groupNum == 2){
                    System.out.println("==============Group 2 ==============");
                    tb3 = tb.difference(tb2);
                    tb3.printTable(tb3);
                    System.out.println("==============Group 2 ==============");
                }
                else{
                    System.out.println("Please enter a valid group number");
                }
            }
            else if(option ==3){
                /** do Union*/
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Group");
                int groupNum = sc.nextInt();
                Table<Contact> tb3 ;
                if(groupNum == 1) {
                    System.out.println("============== Group1,Group2 ==============");
                    tb3 = tb.union(tb2);
                    tb3.printTable(tb3);
                    System.out.println("============== Group1,Group2 ==============");
                }
                else if(groupNum == 2) {
                    System.out.println("============== Group2,Group1 ==============");
                    tb3 = tb2.union(tb);
                    tb3.printTable(tb3);
                    System.out.println("============== Group2,Group1 ==============");
                }


            }
            else if(option ==4){
                /** do Select*/
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Group");
                int groupNum = sc.nextInt();
                System.out.println("Enter attribute");
                String attribute = sc.next();
                System.out.println("Enter value");
                String value = sc.next();
                Table<Contact> tb3 ;
                if(groupNum == 1 ){
                    tb3 = tb.select(attribute , value);
                    tb3.printTable(tb3);
                }
                else if(groupNum == 2 ){
                    tb3 = tb2.select(attribute , value);
                    tb3.printTable(tb3);
                }

            }
            else if(option ==5){
                /** do Remove*/
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Group");
                int groupNum = sc.nextInt();
                System.out.println("Enter attribute");
                String attribute = sc.next();
                System.out.println("Enter value");
                String value = sc.next();


                if(groupNum == 1 ){
                    tb.remove(attribute , value);
                    tb.printTable(tb);
                }
                else if(groupNum == 2 ){
                    tb2.remove(attribute , value);
                    tb2.printTable(tb2);
                }

            }
            else if(option ==6){
                /** do Print Both*/
                tb.printTable(tb);
                tb2.printTable(tb2);
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
        br.close();
        return clients;
    }
    public String[][] separateContacts(String[] people, Character pw){
        //if the first letter of string arr is p then separate contacts based on number of attributes
        if(pw == 'p' || pw == 'P'){
            String[][] contactsP = new String[people.length/9][9];
            for(int i = 0 ; i < people.length; i++){
                contactsP[i/9][i%9] = people[i];
            }
            return contactsP;

        }
        else if(pw == 'w' || pw == 'W'){
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
