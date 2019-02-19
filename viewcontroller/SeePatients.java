/**
 * Class SeePatients
 * 
 * Purpose: Show a list of Patient contain in the List with the specific information
 * for each of then 
 */
package ccthospital.viewcontroller;

import ccthospital.model.Model;
import java.util.Scanner;

/**
 *
 * @authors: 
 * Asmer Bracho 2016328 
 * Miguelantonio Guerra 2016324
 */

public class SeePatients extends SuperMenu {
    
    /**
     * Constructor for SeePatient
     * 
     * Takes as parameter the model 
     * 
     * @param model 
     */
    public SeePatients(Model model) {
        super(model);
        seePatients();
    }
    
    /**
     * Method that print the list of patients and call the super method
     * goBack which handle the interaction of the menu moving backwards to the main screen
     */
    public void seePatients() {
        setHeader("List of Patients");
        separator();
        
        // here loop troug the array an show the patients
        this.getModel().getListOfPatients().seeListOfPatients();
         
        goBack(1);
    } 
    
    
}
