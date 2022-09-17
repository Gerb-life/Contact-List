package ClassFiles;

/**
 * This class is our driver, it'll prompts the user for files to use then creates
 * multiple list and methods to read and populate the two tables, print results,
 * Then it will display all the information.
 * @author Gabe Rodriguz
 * @version 1.5
 */
import Enums.MaritalStatus;

import java.io.*;


import java.util.Scanner;

/**
 * Creates multiple list  and methods to initialize, read
 * and populate the two tables, print results, and will prompt the user for input.
 * Then it will display all the information.
 */
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
            System.out.println("File not found");
        }
        return tb;
    }
    
    
    /**
     * Prompts the user to chose an number out of  6 differ options. 
     * Certain options will prompt the users for more specif  information
     * that will change the content of the current list and or display the content
     * of the list.
     * @file1 the file of group one
     * @file2 the file of group two
     */
    public static void display(String file1 , String file2)  {
        int screen = -1;
        ContactList ctl = new ContactList();
        File f =  new File(file1);
        File f2 =  new File(file2);
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
    
    /**
     * will read each file entered by the user line by line and will add the information
     * to an empty string then add it to a string array and return the array.
     * @param f the file entered by the user
     * @return   a string array with the files information
     * @throws Exception if the file does not contain any information or is an invalid
     *                     file it will throw an Exception.
     */
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
    
    /**
     * Separates the information from the string arrays and adds the values in to a
     * different list and returns a new list.
     * @param people the string array
     * @param pw the character which helps decide which list is the work list and the personal list
     * @return  a strings list
     */
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
    
    
    /**
     * Creates the contact's information such as email, phone number, label from the string
     * array and return an element of contact with personal info.
     * @param strings the string array with
     * @param pw the character which helps decided if the list is either work or personal info
     * @return return an element of contact with personal info
     */
    public Contact createContacts(String[] strings , Character pw){
            String email ;
            String phone ;
            String label ;
        if(pw == 'p' || pw == 'P'){
            PersonalInfo ps = new PersonalInfo(strings[0] , strings[1] ,MaritalStatus.NA);
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
    
    
    /**
     * If the value of the group is correct it will return the value entered by the user
     * otherwise it will print a  Invalid entry, please try again.
     * @return returns the correct value of group
     */
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
    
    
    /**
     * Displays the result of the intersection between two linked list
     * @param tb the linked list
     * @param attribute the sting attribute of the list (i.e. first of last etc.)
     * @param value the string value of the list (i.e. name etc.)
     * @param ISR the character for the intersection
     * @param groupNum the number for the group
     */
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
    
    
    /**
     * Displays the result of the Union between two linked list
     * @param tb the fist linked list
     * @param tb2 the second linked list
     * @param UD the character for union
     * @param groupNum the number for the group
     */
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


    /**
     * Prompts the user for two different files and then Calls the display method
     * @param args The source file path
     */
    public static void main(String[] args) {
        System.out.println("Please enter file for group 1");
        Scanner sc = new Scanner(System.in);
        String f1 = sc.nextLine();
        System.out.println("Please enter file for group 2");
        String f2 = sc.nextLine();
        display(f1 , f2);






    }
}
