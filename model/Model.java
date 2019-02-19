/**
 * Class: Model
 * 
 * Purpose: Responsible for creating the instances of CustomLinkedList
 */

package ccthospital.model;

import ccthospital.model.hospitallinkedlist.HospitalLinkedList;
import ccthospital.model.patient.Patient;
import ccthospital.model.patient.PatientsWaitingList;

/**
 *
 * @author
 * Asmer Bracho 2016328
 * Miguelantonio Guerra 2016324
 */
public class Model {
    
    /**
     * Attributes of the class
     */
    private PatientsWaitingList listOfPatients;
    
    public Model() {
        listOfPatients = new PatientsWaitingList(); // Instance of the list of patients
    }

    /**
     * Method that retrieves the list of patients
     * 
     * @return List of patients
     */
    public PatientsWaitingList getListOfPatients() {
        return listOfPatients;
    }
    
}
