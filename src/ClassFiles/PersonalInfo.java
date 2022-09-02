package ClassFiles;

import Enums.MaritalStatus;

public class PersonalInfo {
    /** First name */
    public String first;
    /** Last name */
    private String last;
    /** Marital Status */
    private MaritalStatus status;

    public PersonalInfo(String first , String last , MaritalStatus status){
        this.first = first;
        this.last = last;
        this.status = status;
    }
}
