/**
 * Class: PatientsWaitingList
 * 
 * Purpose:
 * 
 * To create a Patients Waiting List by extending the Parent Class HospitalLinkedList and defining its type
 * as Patient.
 * 
 */

package ccthospital.model.patient;

import ccthospital.model.hospitallinkedlist.HospitalLinkedList;
import ccthospital.model.hospitallinkedlist.PatientNode;

/**
 * @authors: 
 * Asmer Bracho 2016328 
 * Miguelantonio Guerra 2016324
 */
public class PatientsWaitingList extends HospitalLinkedList<Patient> {

    /**
     * Method to update a patient given a PID
     * 
     * It takes the parameters:
     * 
     * @param pidn Patient ID
     * @param ppsn PPS Number
     * @param firstName First Name
     * @param lastName Last name
     * @param mobileNumber Mobile Number
     * @param email Email address
     * @param city City of Patient
     */
    public void updatePatientByPID(int pidn, int ppsn, String firstName, String lastName, int mobileNumber, String email, String city) {
        PatientNode<Patient> patient = this.searchPatientInListByPID(pidn);
        patient.getDataPatient().setPpsNumber(ppsn);
        patient.getDataPatient().setFirstName(firstName);
        patient.getDataPatient().setLastName(lastName);
        patient.getDataPatient().setMobileNumber(mobileNumber);
        patient.getDataPatient().setEmail(email);
        patient.getDataPatient().setCity(city);        
    }
    
    /**
     * This Method is to show a resumed list of the patients in the waiting list
     */
    public void shortListOfPatients() {
        PatientNode<Patient> auxNode = this.getFirstPatientInList();
        int position = 1;
        while(auxNode != null) {
            System.out.println("________________________________________________________");
            System.out.print("POSITION: " + position + " - ");
            auxNode.getDataPatient().resumedPatientData();
            auxNode = auxNode.getNextPatientRef();
            position++;
        }
    }
    
    /**
     * This method was override from the parent class HospitalLinkedList because here we can
     * extend the parent list as a Patient and then when writing the override method we can
     * obtained the functionality of the class Patient, in this case to get the pid number.
     * 
     * It takes the parameter:
     * 
     * @param pid pid number
     * 
     * It returns:
     * 
     * @return a Patient Node or null if the pid is not in the list.
     */
    @Override
    public PatientNode<Patient> searchPatientInListByPID(int pid) {
        PatientNode<Patient> foundPatient = this.getFirstPatientInList();
        
        while (foundPatient != null) {
            
            if (foundPatient.getDataPatient().getPidNumber() == pid) {
                return foundPatient;
               
            }
            foundPatient = foundPatient.getNextPatientRef();   
        }
        
        return null;
    }

    /**
     * This method was override from the parent class HospitalLinkedList because here we can
     * extend the parent list as a Patient and then when writing the override method we can
     * obtained the functionality of the class Patient, in this case to get the first name
     * and last name.
     * 
     * It takes the parameter:
     * 
     * @param name First Name + Last Name
     * 
     * It returns:
     * 
     * @return a Patient Node or null if the (First Name + Last Name) is not in the list.
     */
    @Override
    public PatientNode<Patient> searchPatientInListByName(String name) {
        PatientNode<Patient> foundPatient = this.getFirstPatientInList();
        String patientName = null;
        while (foundPatient != null) {
            patientName = foundPatient.getDataPatient().getFirstName() + " " + foundPatient.getDataPatient().getLastName();
            if (name.equalsIgnoreCase(patientName)) {
                return foundPatient;
            }
            foundPatient = foundPatient.getNextPatientRef();
            
        }
        
        return null;
    }
    
}
