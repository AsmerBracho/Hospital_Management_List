/**
 * Public Class DeletePatient
 *
 * Purpose: Display Menus that allow user to delete patient from the Queue as follow
 * - Delete First in the Queue
 * - Delete Last in the Queue
 * - Delete by PID
 * - Delete last N patients
 */
package ccthospital.viewcontroller;

import ccthospital.model.Model;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @authors: 
 * Asmer Bracho 2016328 
 * Miguelantonio Guerra 2016324
 */
public class DeletePatient extends SuperMenu {

    // Declare a Scanner 
    Scanner scn;

    /**
     * Constructor that takes the model as parameter
     *
     * @param model
     */
    public DeletePatient(Model model) {
        super(model);
        deletePatients();
    }

    /**
     * Method that displays a menu for deleting patients three options are
     * given: - Delete First in the Queue - Delete Last in the Queue - Delete by
     * PID - Delete last N patients
     *
     */
    public void deletePatients() {
        setHeader("Delete Patients");
        separator();

        System.out.println("[1] Delete First\n[2] Delete Last\n[3] Delete by PID\n[4] Delete last \"N\" patients\n\n[0] Go Back\n");

        String selection;
        do {
            /**
             * If the variable breakVar is true it means we are coming back from
             * performing and action of deletion (PID or Last N which contains
             * sub menus) for what we will break this loop and go back straight
             * to the main menu
             */
            if (isBreakVar()) {
                // change the breaker again so we can run the loop starting in line 67 when performming again a deletion 
                setBreakVar(false);
                break;
                /**
                 * Else It means we are just landing here from the main menu and
                 * we need to run the loop in order to perform a deletion by
                 * choosing an option
                 */
            } else {

                System.out.print("Insert selection: ");
                Scanner sc = new Scanner(System.in);
                selection = sc.nextLine();
                switch (selection) {
                    /**
                     * If selection is 0 set the header to "Main Menu" and go to
                     * Main screen
                     */
                    case "0":
                        setHeader("Main Menu");
                        separator();
                        break;
                    case "1":
                        // delete first patient in the list
                        this.getModel().getListOfPatients().removeFirstPatient();
                        redirect("Patient deleted succefully");
                        break;
                    case "2":
                        // remove last patient in the list 
                        this.getModel().getListOfPatients().removeLastPatient();
                        redirect("Patient deleted succefully");
                        break;
                    case "3":
                        deletebyPID();
                        break;
                    case "4":
                        deleteLastN();
                        break;
                    default:
                        System.out.println("The selection was invalid!");
                }
            }
        } while (!"0".equals(selection));
    }

    /**
     * This method is used to delete a N number of patient from the last
     * position of the Queue i.e.: If the Queue has 8 patients and your input is
     * 3, position 6, 7 and 8 will be remove from the Queue
     */
    public void deleteLastN() {
        int selection = -1;
        int breaker = -1;

        do {
            try {
                System.out.println("\nHow many patients Do you want to delete?\n");
                System.out.print("Insert selection: ");
                scn = new Scanner(System.in);
                selection = scn.nextInt();
                scn.nextLine();

                /**
                 * Verify the number in not 0 less than 0 or greater than the
                 * list size otherwise notify the user
                 */
                if (selection <= 0 || selection > this.getModel().getListOfPatients().getSizeOfListOfPatients()) {
                    System.out.println("\nthe number of patients to delete is invalid, please verify the input\n");
                } else {
                    // break the loop
                    breaker = 0;
                }
                /**
                 * If the input is other rather than numbers display an error
                 * message and keep running the loop till we have a valid option
                 */
            } catch (InputMismatchException e) {
                printError();
                System.out.println("\nOnly Numbers Allowed, please verify Input");
                breaker = -1;
                scn.nextLine();
            }
        } while (breaker == -1);

        /**
         * If the loop was broken then delete the last N patients
         */
        this.getModel().getListOfPatients().removeLastNPatients(selection);
        System.out.println("\n");
        redirect("The list of patient above have been deleted succefully");
    }

    /**
     * This method is called when a user wants to remove a patient by his/her
     * PID validation is applied in this method to be sure the PID is in list of
     * patients otherwise an error message is display to the console
     */
    public void deletebyPID() {
        int selection = -1;
        setHeader("Delete Patients By ID");
        separator();

        // print the list of patients so the user can see the PID to be deleted 
        this.getModel().getListOfPatients().shortListOfPatients();

        // define breaker variable that will help to break the loop if needed 
        int breaker = -1;
        do {
            try {
                System.out.println("\nSelect the Patient's ID you want to delete\n");

                System.out.print("Insert selection: ");
                scn = new Scanner(System.in);
                selection = scn.nextInt();
                scn.nextLine();

            } catch (InputMismatchException e) {
                printError();
                System.out.println("\nOnly Numbers Allowed, please verify Input");
                breaker = -1;
                scn.nextLine();
            }
            
            /**
             * verify if the PID is correct and correspond to a patient in the
             * list otherwise notify the user
             */
            if (this.getModel().getListOfPatients().searchPatientInListByPID(selection) != null) {
                breaker = 0;
            } else {
                System.out.println("\nWrong patient ID, please verify your input\n");
            }
        } while (breaker == -1);
        
        // finally delete the patient and redirect to the main screen 
        this.getModel().getListOfPatients().removePatientByPID(selection );
        redirect("Patient deleted succefully");
    }

}
