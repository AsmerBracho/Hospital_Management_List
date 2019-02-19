/*
 * Class Menu
 *
 * Purpose: Responsable for creating the menus and submenus 
 * for the Comand line interface
 *  
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

public class MainMenu extends SuperMenu {
    
    /**
     * This class creates a Instance of the Model which will be used to access 
     * the method from the linkList 
     */
    private Model model;
    
    /**
     * Getter & Setter for the Model
     * @return 
     */
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    
    /**
     * Constructor of MainMenu Class which takes as parameter the Model
     * @param model 
     */
    public MainMenu( Model model) {
        super(model);
        this.model = model; 
        printMenu(); 
    } 
    
    /**
     * Method printMenu which display the menu whit the main 
     * options for the Hospital System
     */
    public void printMenu() {
        // start by printing the header 
        header("Main Menu");
        System.out.println("Welcome to CCT Hospital Queueing System");
        
        String selection =null;
        
        // do till selection is diferent than EXIT
        do {
            System.out.println("Please Select an Option\n");
            System.out.println("[1] See List of Patients\n[2] Add new Patient\n[3] Delete Patient"
                    + "\n[4] Update Patient\n[5] Search Patient\n\n[0] EXIT\n");

            System.out.print("Insert selection: "); 
            Scanner sc = new Scanner(System.in);
            selection = sc.nextLine();
            
            switch (selection) {
                case "1": new SeePatients(model); break;
                case "2": new AddPatient(model); break;
                case "3": new DeletePatient(model); break;
                case "4": new UpdatePatient(model); break;
                case "5": new SearchPatient(model); break;
                case "0": System.out.println("Application has been shut down"); break;
                default: separator(); System.out.println("The selection was invaild!");
            }
        } while (!"0".equals(selection));
    }
    
}
