/**
 * Class Update Patient
 *
 * Purpose: Display Menu for Updating a patient with the different options
 * and functions that lead to other sub-menus
 */
package ccthospital.viewcontroller;

import ccthospital.model.Model;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @authors: 
 * Asmer Bracho 2016328 
 * Miguelantonio Guerra 2016324
 */
public class UpdatePatient extends SuperMenu {

    //
    private int patientSelected;

    // create a scanner 
    private Scanner scn;

    /**
     * Constructor of the class UpdatePatient It takes as parameters the model
     * that contains the methods for the linkList
     *
     * @param model
     */
    public UpdatePatient(Model model) {
        super(model);
        updatePatients();
    }

    /**
     * Method that contains and display the Update Patient Menu with the
     * different ways or options to do so.
     */
    public void updatePatients() {
        // Set the header and add a separator 
        setHeader("Update Patients");
        separator();

        // show a list of patients 
        this.getModel().getListOfPatients().shortListOfPatients();

        do {
            /**
             * If the variable breakVar is true it means we are coming back from
             * performing and Update for what we will break this loop and go
             * back straight to the main menu
             */
            if (isBreakVar()) {
                // change the breaker again so we can run the loop  
                setBreakVar(false);
                break;
            } else {
                try {
                    /**
                     * Else It means we are just landing here from the main menu
                     * and we need to run the loop in order to perform a
                     * deletion by choosing an option
                     */
                    System.out.println("\n*************************************************************************");
                    System.out.println("\n\n[0] Go Back");
                    System.out.println("\nSelect the patient ID you want to update\n");
                    // here loop troug the array an show the patients 

                    System.out.print("Insert selection: ");
                    scn = new Scanner(System.in);
                    patientSelected = scn.nextInt();

                    /**
                     * If the selection does Not get a patient back (NULL) It
                     * means there is not patient with the PID specified, then
                     * notify the user
                     */
                    if (this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected) == null && patientSelected != 0) {
                        printError();
                        System.out.println("\nWrong patient ID, please verify your input\n");
                        /**
                         * If the selection is 0 it means we are calling the Go
                         * back option for what we need to go to the Main Screen
                         */
                    } else if (patientSelected == 0) {
                        setHeader("Main Menu");
                        separator();
                        break;

                        /**
                         * Else we are chosen a patient so go to next screen and
                         * display the patient to be modified
                         */
                    } else {
                        setHeader("Update Patient");
                        separator();

                        // print the patient we are updating
                        printSelectedPatient(patientSelected);

                        // We will print the Sub Menu Update 
                        int selection2 = 0;

                        do {
                            /**
                             * If the variable breakVar is true it means we are
                             * coming back from performing and Update for what
                             * we will break this loop and go back straight to
                             * the main menu
                             */
                            if (isBreakVar()) {
                                break;
                            } else {
                                // try to run
                                try {
                                    // print the option for updating the patient 
                                    System.out.println("\nWhat do you want to Update\n");
                                    System.out.println("[1] Update PPS \n[2] Update First Name\n[3] Update Last Name\n[4] Update Mobile\n"
                                            + "[5] Update Email\n[6] Update City\n\n[7] Update All Fills\n[0] Go Back\n");

                                    System.out.print("Insert selection: ");
                                    selection2 = scn.nextInt();

                                    updateOptions(selection2);
                                    
                                    /**
                                     * If the input is other rather than numbers
                                     * display an error message and change the
                                     * input to -1 so the loop keep running till
                                     * we have a valid option
                                     */
                                } catch (InputMismatchException e) {
                                    printError();
                                    System.out.println("\nOnly Numbers Allowed, please verify Input");
                                    selection2 = -1;
                                    scn.nextLine();
                                }
                            }
                        } while (selection2 != 0);
                    }

                    /**
                     * If the input is other rather than numbers display an error
                     * message and change the input to -1 so the loop keep
                     * running till we have a valid option
                     */
                } catch (InputMismatchException e) {
                    printError();
                    System.out.println("\nOnly Numbers Allowed, please verify Input");
                    patientSelected = -1;
                    scn.nextLine();
                }
            }

        } while (patientSelected != 0);
    }

    /**
     * Method that contains the different option for updating the patients
     *
     * It takes the parameter i which is the option coming from the previous
     * menu (PPS number, first name, last name etc)
     *
     * @param i
     */
    public void updateOptions(int i) {
        switch (i) {
            case 0:
                /**
                 * It means we are coming back to Update screen so we will print
                 * the header for Update screen
                 */
                setHeader("Update Patients");
                separator();
                // show a list of patients 
                this.getModel().getListOfPatients().shortListOfPatients();
                break;

            case 1:
                /**
                 * Update the PPS
                 */
                // validate the PPS Number 
                while (isValidation() == false) {
                    try {
                        System.out.println("\n*************************************************************************");
                        System.out.print("PPS Number: ");
                        setPps(scn.nextInt());
                        scn.nextLine();
                        setValidation(true);

                        //Update Patient's PPS
                        this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setPpsNumber(getPps());
                        // redirect the user to the Main Screen 
                        redirect("Patient PPS updated succefully");
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nPPS Number Invalid Please Try again (Only Numbers)\n");
                        scn.nextLine();
                    }
                }
            case 2:
                /**
                 * Update patient's First Name
                 */
                // validate the Name
                while (isValidation() == false) {
                    System.out.println("\n*************************************************************************");
                    System.out.print("First Name: ");
                    setFirstName(getSc().nextLine());
                    validateString(getFirstName(), "Name", 3);
                    if (isValidation()) {
                        // Update patient's First Name
                        this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setFirstName(getFirstName());
                        // redirect the user to the Main Screen 
                        redirect("Patient first name updated succefully");
                        break;
                    }
                }
            case 3:
                /**
                 * Update Patient's Last Name
                 */
                // validate the Surname 
                while (isValidation() == false) {
                    System.out.println("\n*************************************************************************");
                    System.out.print("Last Name: ");
                    setLastName(getSc().nextLine());
                    validateString(getLastName(), "Surname", 3);
                    if (isValidation()) {
                        // Update patient's Last Name
                        this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setLastName(getLastName());
                        // redirect the user to the Main Screen 
                        redirect("Patient last name updated succefully");
                        break;
                    }
                }
            case 4:
                /**
                 * Update Patient's Mobile Number
                 */
                // validate the Mobile Number
                while (isValidation() == false) {
                    try {
                        System.out.println("\n*************************************************************************");
                        System.out.print("Mobile Number: ");
                        setMobileNumber(scn.nextInt());
                        scn.nextLine();
                        setValidation(true);
                        setBreakVar(true);

                        // Update Patient's Mobile Number
                        this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setMobileNumber(getMobileNumber());
                        // redirect the user to the Main Screen 
                        redirect("Patient mobile number updated succefully");
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\nMobile Number Invalid Please Try again (Only Numbers)\n");
                        scn.nextLine();
                    }
                }
            case 5:
                /**
                 * Update Patient's Email
                 */
                // validate email 
                while (isValidation() == false) {
                    System.out.println("\n*************************************************************************");
                    System.out.print("Email: ");
                    setEmail(getSc().nextLine());
                    if (getEmail().contains("@") && getEmail().contains(".")) {
                        setValidation(true);
                    } else {
                        System.out.println("\nthe Patient email is NOT valid, Please enter a valid email\n");
                    }
                    if (isValidation()) {
                        // Update patient's Last Name
                        this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setEmail(getEmail());
                        // redirect the user to the Main Screen 
                        redirect("Patient Email updated succefully");
                        break;
                    }
                }
            case 6:
                /**
                 * Update Patient's City
                 */
                // validate city 
                while (isValidation() == false) {
                    System.out.println("\n*************************************************************************");
                    System.out.print("City: ");
                    setCity(getSc().nextLine());
                    validateString(getCity(), "City", 3);

                    if (isValidation()) {
                        // Update patient's City
                        this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setCity(getCity());
                        // redirect the user to the Main Screen 
                        redirect("Patient City updated succefully");
                        break;
                    }
                }
            case 7:
                /**
                 * Update All Fields for the Patient
                 */
                if (isValidation() == false) {
                    // in which case we call the getData method from the super class
                    System.out.println("\n*************************************************************************");
                    getData();

                    /**
                     * Finally Update all the fields but the PID and position
                     */
                    //Update Patient's PPS
                    this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setPpsNumber(getPps());
                    // Update patient's First Name
                    this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setFirstName(getFirstName());
                    // Update patient's Last Name
                    this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setLastName(getLastName());
                    // Update Patient's Mobile Number
                    this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setMobileNumber(getMobileNumber());
                    // Update Email
                    this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setEmail(getEmail());
                    // Update City
                    this.getModel().getListOfPatients().searchPatientInListByPID(patientSelected).getDataPatient().setCity(getCity());

                    // redirect the user to the Main Screen
                    redirect("Patient Information updated succefully");
                    break;
                }
            default:
                separator();
                System.out.println("\nThe selection was invaild!\n");

        }
    }

    /**
     * This method takes a patient ID and print to the screen the patients First
     * & Surname
     *
     * @param pid
     */
    public void printSelectedPatient(int pid) {
        System.out.println((this.getModel().getListOfPatients().searchPatientInListByPID(pid)).getDataPatient().getFirstName() + " "
                + (this.getModel().getListOfPatients().searchPatientInListByPID(pid)).getDataPatient().getLastName());
    }
}
