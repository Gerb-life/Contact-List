package ClassFiles;


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
    public static void main(String[] args){
        display();
       /** PersonalInfo ps = new PersonalInfo("Steve" , "Jobs" , MaritalStatus.MARRIED);
        Contact.Address ca = new Contact.Address("Fairview" , "Sylva" , "NC" ,"28779" );
        Contact steve = new Contact(ps , ca , "8284766872" , "chumbo@yahoo.comm");
    */




    }
}
