/**
 * Asmer Bracho (2016328) and Miguelantonio Guerra (2016324)
 * Data Structures and Algorithms - Group B
 * Application Development Project - Hospital Patient Management List
 * Last Modified 17/12/2018
 * 
 *
 * Purpose:
 *
 * We have been tasked to create a Hospital Patient management List System.
 *
 * In the system The user can:
 * 
 * See the list of the patients. This list shows all the details of the Patient
 * Add a new patient
 * Delete Patient
 *     - First in the list
 *     - Last in the list
 *     - By PID
 *     - Last N patients
 * Update Patient. Here a resumed list of the patients will be shown
 * And, Search Patient
 *     - By PID
 *     - By Name
 * 
 * An command line application was build to help the user to perform all this activities
 * This Command Line App was build using the IDE NetBeans
 * 
 * Extra:
 * 
 * Any bugs that are find on this beta version of the system please inform the creators to sort them out.
 *
 */
package ccthospital;

import ccthospital.model.Model;
import ccthospital.model.patient.Patient;
import ccthospital.viewcontroller.MainMenu;

/**
 *
 * @authors: 
 * Asmer Bracho 2016328 
 * Miguelantonio Guerra 2016324
 */

public class Ccthospital {
    /**
     * @param args the command line arguments
     */
    public static Model model; 
    
    public static void main(String[] args) {
        new CCTHospitalSystem();        
    }
    
}
