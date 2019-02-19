/**
 * Class Search Patient
 *
 * Purpose: Look for patients in the LinkList
 * and show then to the console
 */
package ccthospital.viewcontroller;

import ccthospital.model.Model;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @authors: 
 * Asmer Bracho 2016328 
 * Miguelantonio Guerra 2016324
 */
public class SearchPatient extends SuperMenu {

    // create an Scanner 
    Scanner scn;

    /**
     * Constructor for the class SearchPatient
     *
     * It takes the model as parameters so we can access to the linkList methods
     *
     * @param model
     */
    public SearchPatient(Model model) {
        super(model);
        searchPatients();
    }

    /**
     * Method that print the search patients menu with the 2 option in it -
     * Search by Name - Search by PID
     */
    public void searchPatients() {
        setHeader("Search Patients");
        separator();

        String selection;
        do {
            // print the menu with the options
            System.out.println("[1] Search Patient by Name\n[2] Search Patient by PID\n\n[0] Go Back\n");
            /**
             * If the variable breakVar is true it means we are coming back from
             * performing a search for what we will break this loop and go back
             * straight to the main menu
             */
            if (isBreakVar()) {
                // change the breaker and leave 
                setBreakVar(false);
                break;
                /**
                 * Else It means we are just landing here from the main menu and
                 * we need to run the loop in order to perform a searching by
                 * choosing an option
                 */
            } else {
                System.out.print("Insert selection: ");
                Scanner sc = new Scanner(System.in);
                selection = sc.nextLine();
                switch (selection) {
                    /**
                     * If selection is 0 set the header to "Main Menu" and break
                     * (go to Main screen)
                     */
                    case "0":
                        setHeader("Main Menu");
                        separator();
                        break;
                    case "1":
                        // Search patient by Name
                        searchByName();
                        break;
                    case "2":
                        // Search patien by PID
                        searchByPID();
                        break;
                    default:
                        System.out.println("\n*************************************************************************");
                        System.out.println("\nThe selection was invalid!\n");
                }
            }
        } while (!"0".equals(selection));
    }

    /**
     * Method that prints the subMenu for searching patient by name
     */
    public void searchByName() {
        String selection;
        int breaker = -1;

        /**
         * start the looping process by running the menu
         */
        do {
            
            System.out.println("\n*************************************************************************");
            System.out.println("\n[0] Go Back");
            System.out.println("\nIntroduce the Patients Name and Surname\n");
            System.out.print("Insert selection: ");
            scn = new Scanner(System.in);
            selection = scn.nextLine();

            if ("0".equals(selection)) {
                breaker = 0;
                setHeader("Search Patients");
                separator();
            } else if (this.getModel().getListOfPatients().searchPatientInListByName(selection) == null) {
                System.out.println("\nThe Patient Could NOT been found in the List\n");
            } else {
                System.out.println(this.getModel().getListOfPatients().searchPatientInListByName(selection).getDataPatient().toString());
            }
        } while (breaker == -1);
    }

    /**
     * Method that prints the subMenu for searching patient by Name
     */
    public void searchByPID() {
        int selection;
        int breaker = -1;

        /**
         * start the looping process by running the menu
         */
        do {
            try {
                System.out.println("\n*************************************************************************");
                System.out.println("\n[0] Go Back");
                System.out.println("\nIntroduce the Patients ID\n");
                System.out.print("Insert selection: ");
                scn = new Scanner(System.in);
                selection = scn.nextInt();
                scn.nextLine();

                if (selection == 0) {
                    breaker = 0;
                    setHeader("Search Patients");
                    separator();
                } else if (this.getModel().getListOfPatients().searchPatientInListByPID(selection) == null) {
                    System.out.println("\nThe Patient ID does NOT exits, please verify your input\n");

                } else {
                    System.out.println(this.getModel().getListOfPatients().searchPatientInListByPID(selection).getDataPatient().toString());
                }
            } catch (InputMismatchException e) {
                printError();
                System.out.println("\nOnly Numbers Allowed, please verify Input");
                breaker = -1;
                scn.nextLine();
            }
        } while (breaker == -1);
    }

}
