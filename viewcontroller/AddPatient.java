/**
 * Class: AddPatient
 *
 * Purpose: handle the views for the add Patient Menu with the different
 * inputs as well as validations and interactions in between the menus
 *
 * In this class we will see the Model being use too, specifically for adding
 * patients in the queue at specific positions.
 *
 *
 */
package ccthospital.viewcontroller;

import ccthospital.model.Model;
import ccthospital.model.patient.Patient;
import ccthospital.model.patient.PatientsWaitingList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors: 
 * Asmer Bracho 2016328 
 * Miguelantonio Guerra 2016324
 */
public class AddPatient extends SuperMenu {

    // Declare scanner 
    private Scanner scn;

    /**
     * Constructor AddPatient Takes the parameters
     *
     * @param model
     */
    public AddPatient(Model model) {
        super(model);

        /**
         * This method will call the getData method define in the super class
         * That will handle the data for the patient and add it to the LinkList
         */
        setHeader("Add Patient"); // set the header of the menu 
        separator();
        getData();

        // call de priority method
        priority();
    }

    /**
     * After introducing all the fills we need to determinate the patients
     * priority in the list
     *
     * In order to do that we will display 3 option First in the line Any
     * position Last in the line
     *
     */
    public void priority() {
        int selection = -1;

        /// loop till a condition is met
        do {
            /**
             * The breakVar will determinate if an action comes back to this
             * menu but is not supposed to, then will break the loop that could
             * have been opened from before and goes to theMain Menu Instead
             */
            if (isBreakVar()) {
                setBreakVar(false);
                break;
            } else {
                try {
                    System.out.println("\n*************************************************************************");
                    System.out.println("Select the Patient's Priority\n");
                    System.out.println("[1] Emergency (First in the Line) \n[2] Urgent(Any Position)\n[3] Last in the Line\n\n[0] Go Back\n");

                    System.out.print("Insert selection: ");
                    scn = new Scanner(System.in);
                    selection = scn.nextInt();
                    scn.nextLine();

                    switch (selection) {
                        case 1: // if selection es 1 it means goes first on the line so selection will be 1
                            addPatient(selection, 0);
                            break;
                        /**
                         * if selection is 2 then launch another menu to show
                         * the different options for adding patient in the queue
                         * (see atPosition method below)
                         */
                        case 2:
                            atPosition();
                            break;
                        case 3:
                            addPatient(selection, 0); // Goes Last in the queue 
                            break;
                        default:
                            separator();
                            System.out.println("\nThe selection was invaild!\n");
                    }
                    /**
                     * If the input is other rather than numbers display an error
                     * message and change the input to -1 so the loop keep
                     * running till we have a valid option
                     */
                } catch (InputMismatchException e) {
                    printError();
                    System.out.println("\nOnly Numbers Allowed, please verify Input");
                    selection = -1;
                    scn.nextLine();
                }
            }

        } while (selection != 0);
    }

    /**
     * This method will be called any time the patient is given the option to be
     * added in a different position of the queue (given the condition of the
     * patient)
     *
     */
    public void atPosition() {
        /**
         * since this method can be call even though the list is empty we need
         * to make sure if so, that we add the patient at position 1 without
         * even showing any other option since there will be no other place to
         * add at
         */
        if (this.getModel().getListOfPatients().getSizeOfListOfPatients() == 0) {
            addPatient(1, 0);
            setBreakVar(true);
        } else {
            // if there are patients in the list the show a short version of the list 
            System.out.println("========================================================================");
            System.out.println("                          List of Patients");
            System.out.println("========================================================================");

            this.getModel().getListOfPatients().shortListOfPatients();

            /**
             * then loop till you get the position the patient will be add to
             */
            int position = -1;
            do {
                Scanner scp = new Scanner(System.in);
                System.out.println("\nWhat Position Do you want to add the Patient at\n");
                System.out.print("Insert selection: ");
                position = scp.nextInt();

                // this is to make sure it jumps to the next line and it doesn't skip 
                scp.nextLine();

                /**
                 * If the input is less than 1 or bigger than the size of the
                 * list, it means there is not such position. then keep looping
                 * till you get a valid one
                 */
                if (position < 1 || position > this.getModel().getListOfPatients().getSizeOfListOfPatients()) {
                    System.out.println("The selection was invaild! There is not such Position in the list");

                    // change the position value to make sure it keeps looping 
                    position = -1;
                } else {
                    break;
                }
            } while (position == -1);

            /**
             * the method will take two parameter which are the option (or case)
             * that will follow when hit the addPatientMethod (see addPatient
             * Method below) and the position where to add the patient
             */
            addPatient(2, position - 1);
        }
    }

    /**
     * This method is in charged of creating a patient whit the values stored in
     * global variables and add this patient to the linklist, according to the
     * criteria applied
     *
     * It takes the parameters
     *
     * @param selection which is the case it is going to be followed
     * @param position which is the position to be add to (when is different
     * than 0)
     */
    public void addPatient(int selection, int position) {

        Patient createPatient = new Patient(getPps(), getFirstName(), getLastName(), getMobileNumber(), getEmail(), getCity());

        switch (selection) {
            case 1:
                this.getModel().getListOfPatients().addPatientToFront(createPatient);
                break;
            case 2:
                this.getModel().getListOfPatients().addPatientAtPosition(createPatient, position);

                /**
                 * change breakVar to true so when gets back to previous menu
                 * will break and go to main Menu
                 */
                setBreakVar(true);
                break;
            case 3:
                /**
                 * if the list is empty add to patient to the first position
                 */
                if (this.getModel().getListOfPatients().getSizeOfListOfPatients() == 0) {
                    this.getModel().getListOfPatients().addPatientToFront(createPatient);
                } else {
                    // add the patient to the last position 
                    this.getModel().getListOfPatients().addPatientToEnd(createPatient);
                }
                setBreakVar(true);
                break;
        }

        // redirect the user to the Main Menu 
        System.out.println("\nThe patient has been add succesfully, You will be redirected\nto the main screen in 3 seconds");
        try {
            TimeUnit.SECONDS.sleep(3);
            setHeader("Main Menu");
            separator();
        } catch (InterruptedException ex) {
            Logger.getLogger(AddPatient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
