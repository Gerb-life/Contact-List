package ClassFiles;

import Enums.MaritalStatus;

public class PersonalInfo {
    /** First name */
    public String first;
    /** Last name */
    public String last;
    /** Marital Status */
    public MaritalStatus status;

    public PersonalInfo(String last , String first , MaritalStatus status){
        this.first = first;
        this.last = last;
        this.status = status;
    }

    public PersonalInfo(String last , String first ){
        this.first = first;
        this.last = last;

    }
}
