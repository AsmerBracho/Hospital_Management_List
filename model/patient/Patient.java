/**
 * Class: Patient
 * 
 * Purpose: To create an Object of type Patient that is structured by:
 * 
 *             Patient Identification number
 *             PPS number
 *             First and Last name
 *             Mobile number
 *             Email address
 *             and City.
 * 
 */

package ccthospital.model.patient;

/**
 * @author guerr
 * Asmer Bracho 2016328
 * Miguelantonio Guerra 2016328
 */
public class Patient {
    
    /**
     * Class Attributes
     */    
    private static int auxID = 1; // To create an Auto incremented ID
    private final int pidNumber; // Patient Identification Number
    private int ppsNumber; // Patient's PPS Number
    private String firstName; // Patient's First Name
    private String lastName; // Patient's Last Name
    private int mobileNumber; // Patient's mobile number
    private String email; // Patient's email address
    private String city; // Patient's city

    /**
     * This is the Constructor of the class.
     * 
     * It take the parameters:
     * 
     * @param ppsNumber Patient's PPS Number
     * @param firstName Patient's First Name
     * @param lastName Patient's Last Name
     * @param mobileNumber Patient's mobile number
     * @param email Patient's email address
     * @param city Patient's city
     */
    public Patient(int ppsNumber, String firstName, String lastName, int mobileNumber, String email, String city) {
        this.pidNumber = auxID;
        this.ppsNumber = ppsNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.city = city;
        
        auxID++;
    }

    /**
     * Method to get the patient's id number
     * @return 
     */
    public int getPidNumber() {
        return pidNumber;
    }

    /**
     * Method to get patient's pps number
     * @return 
     */
    public int getPpsNumber() {
        return ppsNumber;
    }

    /**
     * Method to get patient's first name
     * @return 
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * MEthod to get patient's last name
     * @return 
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method to get patient's mobile number
     * @return 
     */
    public int getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Method to get the patient's email
     * @return 
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to get the patient's city
     * @return 
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Method to update patient's pps number.
     * 
     * It takes the parameter:
     * 
     * @param ppsNumber patient's new pps number
     */
    public void setPpsNumber(int ppsNumber) {
        this.ppsNumber = ppsNumber;
    }

    /**
     * Method to update patient's first name.
     * 
     * It takes the parameter:
     * 
     * @param firstName patient's new fist name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method to update patient's first name.
     * 
     * It takes the parameter:
     * 
     * @param lastName patient's new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method to update patient's mobile number.
     * 
     * It takes the parameter:
     * 
     * @param mobileNumber patient's new mobile number.
     */
    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Method to update patient's email.
     * 
     * It takes the parameter:
     * 
     * @param email patient's new email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to update patient's city
     * 
     * It takes the parameter:
     * 
     * @param city  patient's new city
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    public void resumedPatientData() {
        System.out.println("Patient ID: " + pidNumber + " Full Name: " + firstName + " " + lastName);
    }

    /**
     * This is the toString method for the Patient class
     * 
     * It is used to return the attributes of the Object Patient in a formatted way.
     * It is used for console printing purposes.
     * 
     * @return Attributes formatted for printing purposes.
     */
    @Override
    public String toString() {
        return "Patient ID: " + pidNumber + 
                "\n\tPPS Number: " + ppsNumber +
                "\n\tFirst Name: " + firstName +
                "\n\tLast Name: " + lastName +
                "\n\tMobile Number: " + mobileNumber +
                "\n\tEmail: " + email +
                "\n\tCity: " + city +
                "\n________________________________________";
    }

    /**
     * Equals method to compare objects of type patient using the Unique PID as comparison.
     * There won't be two different patients with the same PID.
     * 
     * It takes in the parameter:
     * 
     * @param obj object to compare.
     * @return whether is equal or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Patient other = (Patient) obj;
        if (this.pidNumber != other.pidNumber) {
            return false;
        }
        return true;
    }
    
}
