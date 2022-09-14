package ClassFiles;



public class WorkContact extends Contact implements Cloneable{
    /** Job title */
    private String title;
    /** Company contact works for */
    private String company;
    /** ClassFiles.Contact’s department */
    private String department;

    public WorkContact(PersonalInfo person, Address address, String phone, String email,String title,String company , String department) {
        super(person, address, phone, email);
        this.title = title;
        this.company = company;
        this.department = department;

    }
    public String toString(){
        return "Job Title: " + title + "\n" + "Company: " + company + "\n" + "Department: " + department +  "\n" +
                person + "  phone: " + phone  + "\n" + address + "\n" + email ;
    }
}
