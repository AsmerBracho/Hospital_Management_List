/**
 * Class CCTHospitalSystem 
 * 
 * Purpose: Create an instance of the Model and Instance of the View, 
 * instantiate them and add some patient to the list 
 * 
 */
package ccthospital;

import ccthospital.model.Model;
import ccthospital.model.patient.Patient;
import ccthospital.viewcontroller.MainMenu;

/**
 * @authors: 
 * Asmer Bracho 2016328 
 * Miguelantonio Guerra 2016324
 */
public class CCTHospitalSystem {
    
    private Model theModel; 
    private MainMenu menu;
    
    /**
     * Constructor 
     */
    public CCTHospitalSystem() {
        this.theModel = new Model();
        
        // Create a list of Patient to have Information at the begining of the Program
        /**
         * Serialization or a database can be implemented but given that this is only a
         * test data set it is enough to create a few patients to test the functionality
         * of our program
         */
        Patient p1 = new Patient(1, "Jose", "Jose", 46564568, "email", "city");
        Patient p2 = new Patient(2, "Paolo", "Name", 46564568, "email", "city");
        Patient p3 = new Patient(3, "Guil", "Pato", 46564568, "email", "city");
        Patient p4 = new Patient(4, "Debora", "Meltroso", 46564568, "email", "city");
        Patient p5 = new Patient(5, "Rosa", "Meltroso", 46564568, "email", "city");
        Patient p6 = new Patient(6, "Gabo", "Petro", 46564568, "email", "city");
        Patient p7 = new Patient(7, "Pargo", "Lazo", 46564568, "email", "city");
        Patient p8 = new Patient(8, "Ricardo", "Monater", 46564568, "email", "city");
        
        // Add Some Patients to the List 
        this.theModel.getListOfPatients().addPatientToFront(p1);
        this.theModel.getListOfPatients().addPatientToEnd(p2);
        this.theModel.getListOfPatients().addPatientToEnd(p3);
        this.theModel.getListOfPatients().addPatientAtPosition(p4, 2);
        this.theModel.getListOfPatients().addPatientToFront(p5);
        this.theModel.getListOfPatients().addPatientAtPosition(p6, 3);
        this.theModel.getListOfPatients().addPatientToFront(p7);
        this.theModel.getListOfPatients().addPatientToEnd(p8);
        
        //Create a new Menu an Start the Program
        this.menu = new MainMenu(theModel);
                
    }    
    
}
