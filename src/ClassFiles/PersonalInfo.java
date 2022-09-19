package ClassFiles;
/**
 * This class implements a PersonalInfo which consist of the contact's name, last name,
 * marital status.
 * @author Gabe Rodriguez, Alejandro Ordonez
 * @version 1.0
 */
import Enums.MaritalStatus;

public class PersonalInfo {
    /** First name */
    public String first;
    /** Last name */
    public String last;
    /** Marital Status */
    public MaritalStatus status;
    
    
    /**
     * This constructs a PersonalInfo with the persons name, last name.
     * @param last the last name of the person
     * @param first the first name of the person
     * @param status the marital status of the person
     */
    public PersonalInfo(String last , String first , MaritalStatus status){
        this.first = first;
        this.last = last;
        this.status = status;
    }

    /**
     * This constructs a PersonalInfo with the persons name, last name and marital
     * status.
     * @param last the last name of the person
     * @param first the first name of the person
     */
    public PersonalInfo(String last , String first ){
        this.first = first;
        this.last = last;

    }
}
