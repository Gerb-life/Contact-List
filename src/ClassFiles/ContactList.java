package ClassFiles;


import Enums.MaritalStatus;

import java.io.*;


import java.util.Scanner;

public class ContactList {
    public Table<Contact> createTable(File f){
        Table<Contact> tb = new Table<>();
        ContactList ctl = new ContactList();
        try {
            String[] str = ctl.readFile(f);
            Character pw = str[0].charAt(0);
            str[0] = str[0].substring(1);
            String[][] contacts = ctl.separateContacts(str , pw);

            for(int i = 0 ; i < contacts.length;i++){
                Contact ct = ctl.createContacts(contacts[i],pw);
                tb.addFirst(ct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tb;
    }


    public static void display()  {
        int screen = -1;
        ContactList ctl = new ContactList();
        File f =  new File("D:\\Users\\quibl\\IdeaProjects\\ContactList\\src\\TextFiles\\personalContacts.txt");
        File f2 =  new File("D:\\Users\\quibl\\IdeaProjects\\ContactList\\src\\TextFiles\\workContacts.txt");
        Table<Contact> tb = ctl.createTable(f); // personal contact
        Table<Contact> tb2 = ctl.createTable(f2);// work contact


        while (screen != 0) {
            System.out.println("Welcome to database display \n\nPlease make a choice:\n" +
                    "0) Quit\n" +
                    "1) Intersect\n" +
                    "2) Difference\n" +
                    "3) Union\n" +
                    "4) Select\n" +
                    "5) Remove\n" +
                    "6) Print both tables");

                int option = ctl.queryInt();

                if (option == 0) { //Exit display
                    System.out.println("Thank you have a nice day!");
                    screen = 0;
                } else if (option == 1) {
                    /** do intersect*/
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter Group");
                    int groupNum = ctl.queryInt();
                    System.out.println("Enter attribute");
                    String attribute = sc.next();
                    System.out.println("Enter value");
                    String value = sc.next();

                    Table<Contact> tb3 = new Table<>();
                    if (groupNum == 1) {

                        ctl.callIntersectSelectRemove(tb , attribute,value,'I',groupNum);
                    } else if (groupNum == 2) {

                        ctl.callIntersectSelectRemove(tb2 , attribute , value ,'I', groupNum);
                    }
                    else{
                        System.out.println("Please enter valid group number (1 or 2)");
                    }
                } else if (option == 2) {
                    /** do Difference*/

                    System.out.println("Enter Group");
                    int groupNum = ctl.queryInt();
                    Table<Contact> tb3 ;
                    if (groupNum == 1) {

                        ctl.callUnionDifference(tb,tb2,'D',1);
                    } else if (groupNum == 2) {

                        ctl.callUnionDifference(tb,tb2,'D',2);
                    } else {
                        System.out.println("Please enter valid group number (1 or 2)");
                    }
                } else if (option == 3) {
                    /** do Union*/

                    System.out.println("Enter Group");
                    int groupNum = ctl.queryInt();
                    Table<Contact> tb3;
                    if (groupNum == 1) {
                        ctl.callUnionDifference(tb,tb2,'U',groupNum);
                    } else if (groupNum == 2) {
                          ctl.callUnionDifference(tb2,tb,'U',groupNum);
                    }
                    else{
                        System.out.println("Please enter valid group number (1 or 2)");
                    }


                } else if (option == 4) {
                    /** do Select*/
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter Group");
                    int groupNum = ctl.queryInt();
                    System.out.println("Enter attribute");
                    String attribute = sc.next();
                    System.out.println("Enter value");
                    String value = sc.next();
                    Table<Contact> tb3;
                    if (groupNum == 1) {
                        ctl.callIntersectSelectRemove(tb,attribute,value,'S',groupNum);
                    } else if (groupNum == 2) {
                        ctl.callIntersectSelectRemove(tb2,attribute,value,'S',groupNum);
                    }

                } else if (option == 5) {
                    /** do Remove*/
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter Group");
                    int groupNum = ctl.queryInt();
                    System.out.println("Enter attribute");
                    String attribute = sc.next();
                    System.out.println("Enter value");
                    String value = sc.next();

                    if (groupNum == 1) {
                        ctl.callIntersectSelectRemove(tb,attribute,value,'R',groupNum);
                    } else if (groupNum == 2) {
                        ctl.callIntersectSelectRemove(tb2,attribute,value,'R',groupNum);
                    }

                } else if (option == 6) {
                    /** do Print Both*/
                    tb.printTable(tb);
                    tb2.printTable(tb2);
                } else {
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

    public int queryInt() {
        Scanner stdin = new Scanner(System.in);
        while (true) {
            try {
                return Integer.parseInt(stdin.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid entry, please try again");
            }
        }
    }

    public void callIntersectSelectRemove(Table tb , String attribute , String value , Character ISR, int groupNum){
        Table<Contact> tbNew;
        if(ISR == 'I'){
            System.out.println("============== Group "+ groupNum + " ==============");
            tbNew = tb.intersect(tb , attribute , value);
            tbNew.printTable(tbNew);
            System.out.println("============== Group "+ groupNum + " ==============");
        }
        else if(ISR == 'S'){
            System.out.println("============== Group "+ groupNum + " ==============");
            tbNew = tb.select(attribute , value);
            tbNew.printTable(tbNew);
            System.out.println("============== Group "+ groupNum + " ==============");
        }
        else if(ISR == 'R'){
            System.out.println("============== Group "+ groupNum + " ==============");
            tb.remove(attribute, value);
            tb.printTable(tb);
            System.out.println("============== Group "+ groupNum + " ==============");
        }
    }

    public void callUnionDifference(Table tb,Table tb2 , Character UD, int groupNum){
        Table<Contact> tbNew;
        if(UD == 'U'){
            if (groupNum == 1) {
                System.out.println("============== Group1,Group2 ==============");
                tbNew = tb.union(tb2);
                tbNew.printTable(tbNew);
                System.out.println("============== Group1,Group2 ==============");
            } else if (groupNum == 2) {
                System.out.println("============== Group2,Group1 ==============");
                tbNew = tb2.union(tb);
                tbNew.printTable(tbNew);
                System.out.println("============== Group2,Group1 ==============");
            }
        }
        else if(UD == 'D'){
            if(groupNum == 1){
                System.out.println("==============Group 1 ==============");
                tbNew = tb.difference(tb2);
                tbNew.printTable(tbNew);
                System.out.println("==============Group 1 ==============");
            }
            else if(groupNum ==2){
                System.out.println("==============Group 2 ==============");
                tbNew = tb2.difference(tb);
                tbNew.printTable(tbNew);
                System.out.println("==============Group 2 ==============");

            }
        }
    }



    public static void main(String[] args) throws Exception {
        display();






    }
}
