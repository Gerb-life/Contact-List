/**
 * This class is an extension of the contact class and implements a worklContact
 * Which contains the title of the contact the company the contact works for and
 * the department of the contact as well as the contact's personal info such as name
 * last name, phone number, and email.
 * @author Gabe Rodriguez, Alejandro Ordonez
 * @version 1.5
 */
package ClassFiles;




public class WorkContact extends Contact implements Cloneable{
    /** Job title */
    private String title;
    /** Company contact works for */
    private String company;
    /** ClassFiles.Contact’s department */
    private String department;


    /**
     * This Constructs a contact using the information from the contact.java file,
     * with the name, last name, marital status, address, phone number as well
     * as the contact's title, the company's name, and department
     * @param person the current contact's basic information such as name, last name, marital status
     * @param address the address fo the current contact
     * @param phone the phone number for the current contact
     * @param email the email for the current contact
     * @param title the job title of the current contact
     * @param company the company's name where the current contact works
     * @param department the company's department where the current contact works
     */
    public WorkContact(PersonalInfo person, Address address, String phone, String email,
                       String title,String company , String department) {
        super(person, address, phone, email);
        this.title = title;
        this.company = company;
        this.department = department;


    }
    
    
    /**
     * Constructs a string with the current contact information such as the person's name,
     * last name, marital status, and the person's address as well as the current contact's
     * work information such as job title, company's name, and the department where the
     * current contact works.
     * @return a string with a contact information.
     */
    public String toString(){
        return "Job Title: " + title + "\n" +
                "Company: " + company + "\n" +
                "Department: " + department +  "\n\t\t" +
                this.getPerson().last +","+ this.getPerson().first +"(" +this.getPerson().status+")"+"    Phone:" + this.getPhone()  + "\n\t\t" +
                this.getAddress().getStreetAddress() + "\n\t\t" +
                this.getAddress().getCity() + ", " + this.getAddress().getState() + " " + this.getAddress().getZip()+"\n\t\t"+
                this.getEmail() + "\n --------------------------------";
    }
}
