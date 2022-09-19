package ClassFiles;

/**
 * This class is our driver, it'll prompt the user for files to use then creates
 * multiple list and methods to read and populate the two tables, print results,
 * Then it will display all the information.
 * @author Gabe Rodriguz, Alejandro Ordonez
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
    /**
     * This function is used to create a table of contacts given a specific file
     * @param f- file to create contacts from
     * @return
     */
    public Table<Contact> createTable(File f){
        Table<Contact> tb = new Table<>();
        ContactList ctl = new ContactList();
        try {
            String[] str = ctl.readFile(f); // create string array from file
            Character pw = str[0].charAt(0);// first character in the file to determine work or personal contact
            str[0] = str[0].substring(1);//removing first character from first element in array
            String[][] contacts = ctl.separateContacts(str , pw);//2d array created from contacts

            //Adding contacts to table
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

                int option = ctl.queryInt(); //making sure that option is an integer

                if (option == 0) { //Exit display
                    System.out.println("Thank you have a nice day!");
                    screen = 0;
                } else if (option == 1) {// Perform intersect
                    //Gather information for intersect.
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter Group");
                    int groupNum = ctl.queryInt();// making sure group number is numeric
                    System.out.println("Enter attribute");
                    String attribute = sc.next();
                    System.out.println("Enter value");
                    String value = sc.next();

                    if (groupNum == 1) {
                        ctl.callIntersectSelectRemove(tb,tb2 , attribute,value,'I',groupNum);
                    } else if (groupNum == 2) {
                        ctl.callIntersectSelectRemove(tb2,tb , attribute , value ,'I', groupNum);
                    }
                    else{
                        System.out.println("Please enter valid group number (1 or 2)");
                    }

                } else if (option == 2) {// Perform difference
                    // Gather information needed for difference.
                    System.out.println("Enter Group");
                    int groupNum = ctl.queryInt();// making sure group number is numeric
                    if (groupNum == 1) {
                        ctl.callUnionDifference(tb,tb2,'D',1);

                    } else if (groupNum == 2) {
                        ctl.callUnionDifference(tb,tb2,'D',2);

                    } else {
                        System.out.println("Please enter valid group number (1 or 2)");
                    }
                } else if (option == 3) {// perform Union
                    //Gather info needed for union
                    System.out.println("Enter Group");
                    int groupNum = ctl.queryInt();// making sure group number is numeric
                    if (groupNum == 1) {
                        ctl.callUnionDifference(tb,tb2,'U',groupNum);
                    } else if (groupNum == 2) {
                          ctl.callUnionDifference(tb2,tb,'U',groupNum);
                    }
                    else{
                        System.out.println("Please enter valid group number (1 or 2)");
                    }


                } else if (option == 4) {//Perform Select
                    //Gather information for select
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter Group");
                    int groupNum = ctl.queryInt();// making sure group number is numeric
                    System.out.println("Enter attribute");
                    String attribute = sc.next();
                    System.out.println("Enter value");
                    String value = sc.next();

                    if (groupNum == 1) {
                        ctl.callIntersectSelectRemove(tb,tb2,attribute,value,'S',groupNum);
                    } else if (groupNum == 2) {
                        ctl.callIntersectSelectRemove(tb2,tb,attribute,value,'S',groupNum);
                    }
                    else{
                        System.out.println("Please enter valid group number (1 or 2)");
                    }

                } else if (option == 5) {//Perform remove
                   //Gather information for remove
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter attribute");
                    String attribute = sc.next();
                    System.out.println("Enter value");
                    String value = sc.next();
                    // removing attribute and value from both tables.
                    ctl.callIntersectSelectRemove(tb,tb2,attribute,value,'R',1);
                    ctl.callIntersectSelectRemove(tb2,tb,attribute,value,'R',2);

                } else if (option == 6) {//Print both

                    System.out.println("============== Group "+ 1 + " ==============");
                    tb.printTable(tb);
                    System.out.println("============== Group "+ 1 + " ==============");
                    System.out.println("============== Group "+ 2 + " ==============");
                    tb2.printTable(tb2);
                    System.out.println("============== Group "+ 2 + " ==============");
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
       clients = temp.split(","); //splitting string in temp using "," to separate
        //removing leading and trailing spaces
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
        //if the first letter of string arr is w then separate contcts based on number of attributes.
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
            //creating personal contact and sets marital status to NA
        if(pw == 'p' || pw == 'P'){
            PersonalInfo ps = new PersonalInfo(strings[0] , strings[1] ,MaritalStatus.NA);
            email = strings[2];
            phone = strings[3];
            Contact.Address address = new Contact.Address(strings[4] , strings[5] , strings[6] , strings[7]);
            label = strings[8];

            return new PersonalContact(ps , address , phone , email , label);
        }
        //creating work contact
        else if(pw == 'w' || pw == 'W'){
            PersonalInfo ps = new PersonalInfo(strings[0] , strings[1] , MaritalStatus.valueOf(strings[2].toUpperCase()));
            email = strings[3];
            phone = strings[4];
            Contact.Address address = new Contact.Address(strings[5] , strings[6] , strings[7] , strings[8]);

            return new WorkContact(ps , address , phone , email , strings[9] , strings[10] , strings[11]);
        }
        return null;
    }
    
    
    /**
     *This method checks to see whether the input from the user is an integer
     * if it is not then ask the user to try again.
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
     * Displays the result from Intersect Select or Remove based in the ISR character
     * This function was created in an attempt to remove redundant code.
     * @param tb the linked list
     * @param attribute the sting attribute of the list (i.e. first of last etc.)
     * @param value the string value of the list (i.e. name etc.)
     * @param ISR the character for the intersection
     * @param groupNum the number for the group
     */
    public void callIntersectSelectRemove(Table tb ,Table tb2, String attribute , String value , Character ISR, int groupNum){
        Table<Contact> tbNew;
        if(ISR == 'I'){//perform intersect
            System.out.println("============== Group "+ groupNum + " ==============");
            tbNew = tb.intersect(tb2 , attribute , value);
            tbNew.printTable(tbNew);
            System.out.println("============== Group "+ groupNum + " ==============");
        }
        else if(ISR == 'S'){//perform select
            System.out.println("============== Group "+ groupNum + " ==============");
            tbNew = tb.select(attribute , value);
            tbNew.printTable(tbNew);
            System.out.println("============== Group "+ groupNum + " ==============");
        }
        else if(ISR == 'R'){//perform remove
            tb.remove(attribute, value);
        }
    }
    
    
    /**
     * Displays the result of Union or Difference on 2 tables based on the UD character
     * This function was also created in an attempt to reduce code.
     * @param tb the fist linked list
     * @param tb2 the second linked list
     * @param UD the character for union
     * @param groupNum the number for the group
     */
    public void callUnionDifference(Table tb,Table tb2 , Character UD, int groupNum){
        Table<Contact> tbNew;
        if(UD == 'U'){//perform union based on group number
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
        else if(UD == 'D'){//perform difference based on group number
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
     * @param args Command line arguments
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
