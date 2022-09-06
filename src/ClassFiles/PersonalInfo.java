package ClassFiles;

import Enums.MaritalStatus;

public class PersonalInfo {
    /** First name */
    public String first;
    /** Last name */
    public String last;
    /** Marital Status */
    public MaritalStatus status;

    public PersonalInfo(String first , String last , MaritalStatus status){
        this.first = first;
        this.last = last;
        this.status = status;
    }
}
