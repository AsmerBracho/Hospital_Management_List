/**
 * Class SuperMenu 
 * 
 * purpose: This is a father class which will be extended by the other classes 
 * and contains the common methods and properties to be used in the whole system 
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
public class SuperMenu {
    
    /**
     * Declaration of global variables to be used
     * 
     * These are the fills given by the user through 
     * interaction with a scanner  
     */
    private int pps = 0;
    private String firstName = null;
    private String lastName = null;
    private int mobileNumber = 0;
    private String email = null;
    private String city = null;
    
     /**
     * Global variables used for validation purposes and 
     * menus interaction 
     */
    private boolean validation = false;
    private boolean breakVar = false;
    
    // scanner that will be used during the application 
    private Scanner sc = new Scanner(System.in);
    
    private String header;
    
    //Create instance of the model so we can pase it in the SuperMenu constructor
    private Model model; 

    /**
     * Set the getter and setter for all the variables 
     * so we will be able to access the variables from other classes  
     */
    
    // Model
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    
    // Header
     public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    
    // PPS 
    public int getPps() {
        return pps;
    }

    public void setPps(int pps) {
        this.pps = pps;
    }
    
    // First Name 
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    // Last Name
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    // Mobile Number 
    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    // Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    // City 
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    // Validation 
    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }
    
    // Break Variable 
    public boolean isBreakVar() {
        return breakVar;
    }

    public void setBreakVar(boolean breakVar) {
        this.breakVar = breakVar;
    }

    public Scanner getSc() {
        return sc;
    }
    
    /**
     * Constructor of the SuperMenu which takes as parameter the model 
     * to be able to perform and use the methods from the linkList
     * 
     * @param model 
     */
    public SuperMenu(Model model) {
        this.model = model; 
    }
 
    /**
     * This is the Method that will display the header every time a different menu is called 
     * 
     * It takes as parameter a String which is the header were we are moving to
     * 
     * @param header 
     */
    public void header (String header) {
        this.header = header;
        //create a small formula to centrate the title in the header 
        int lenValue = header.length();
        StringBuffer varSpace = new StringBuffer();
        for (int i = 0; i < (72-lenValue)/2; i++) {
            varSpace.append(" ");
        }
        
        // print the header
        System.out.println("       _____________________________________________________________");
        System.out.println("       | ##### ##### #####  #   # #### #### #### ### ### #### #    |");
        System.out.println("       | #     #       #    #   # #  # #    #  #  #   #  #  # #    |");
        System.out.println("       | #     #       #    ##### #  # #### ####  #   #  #### #    |");
        System.out.println("       | #     #       #    #   # #  #    # #     #   #  #  # #    |");
        System.out.println("       | ##### #####   #    #   # #### #### #    ###  #  #  # #### |");
        System.out.println("       -------------------------------------------------------------");
        System.out.println();
        System.out.println("========================================================================");
        System.out.println(varSpace + "\033[35m" + header);
        System.out.println("========================================================================");
    }
    
    /**
     * Method used to give a space between menu
     * 
     * this version prints the header 
     */
    public void separator() {
        System.out.println("*************************************************************************");
        for (int i = 0; i< 10; i++) {
            System.out.println(); 
        }
        header(header);
    }
    
    /**
     * Method used to give a space between menu
     * 
     * this version DO NOT prints the header 
     */
    public void separator2() {
        System.out.println("*************************************************************************");
        for (int i = 0; i< 10; i++) {
            System.out.println(); 
        }  
    }
    
    /**
     * Method that print an error message to the console 
     */
    public void printError() {
        System.out.println("\n   ##### ##### #####  ##### ##### ");
        System.out.println("   #     #   # #   #  #   # #   # ");
        System.out.println("   ###   ##### #####  #   # ##### ");
        System.out.println("   #     #  #  #  #   #   # #  #  ");
        System.out.println("   ##### #   # #   #  ##### #   # ");
    }
    
    /**
     * Method used to go back to the previous screen 
     * 
     * @param where 
     */
    public void goBack(int where) {
        String selection = null;
        do {
            System.out.println("\n[0] Go Back\n");
            System.out.print("Insert selection: "); 
            Scanner sc = new Scanner(System.in);
            selection = sc.nextLine();
            switch (selection) {
                case "0":
                    if (where==0) {
                        separator2();
                        header("Main Menu");
                        break;
                       } else {
                        separator2();
                        header("");
                        break;
                       }              
                default: System.out.println("The selection was invalid!");
            }
        } while (!"0".equals(selection));
    }
    
      /**
     * this method will display a menu to be followed 
     * where information for the patient have to be provided. 
     * 
     * Simple validation is applied for each one of the fills
     */
    public void getData() {
        System.out.println("\nPlease provide the Patient Information\n");
      
        // validate the PPS Number 
        while (validation == false) {
            try {
                System.out.print("PPS Number: ");
                pps = sc.nextInt();
                sc.nextLine();
                validation = true;
            } catch (InputMismatchException e) {
                System.out.println("\nPPS Number Invalid Please Try again (Only Numbers)\n");
                sc.nextLine();
            }
        }

        // validate the Name 
        validation = false;
        while (validation == false) {
            System.out.print("First Name: ");
            firstName = sc.nextLine();
            validateString(firstName, "Name", 3);
        }

        // validate the Surname
        validation = false;
        while (validation == false) {
            System.out.print("Last Name: ");
            lastName = sc.nextLine();
            validateString(lastName, "Surname", 3);
        }

        // validate the Mobile Number
        validation = false;
        while (validation == false) {
            try {
                System.out.print("Mobile Number: ");
                mobileNumber = sc.nextInt();
                sc.nextLine();
                validation = true;
            } catch (InputMismatchException e) {
                System.out.println("\nMobile Number Invalid Please Try again (Only Numbers)\n");
                sc.nextLine();
            }
        }
        
        // validate email 
        validation = false;
        while (validation == false) {
            System.out.print("Email: ");
            email = sc.nextLine();
            if (email.contains("@") && email.contains(".")) {
                validation = true;
            } else {
                System.out.println("\nthe Patient email is NOT valid, Please enter a valid email\n");
            }
        }
        
        // validate city 
        validation = false;
        while (validation == false) {
            System.out.print("City: ");
            city = sc.nextLine();
            validateString(city, "City", 3);
        }
    }
    

    /**
     * This method is used to validate Strings in the application
     * 
     * It takes the parameters: 
     * 
     * @param s the fill for which the validation is going to be applied 
     * @param c which is the Name of the fill we want to see in the message 
     * @param num the min number of character It can has 
     */
     
    public void validateString(String s, String c, int num) {
        if (s.length() < num) {
            System.out.println("\nthe Patient " + c + " can NOT be less than " + num + "\n");
        } else {
            validation = true;
        }
    }
    
    /**
     * this method will be used to redirect the user to the main screen after having 
     * performed a specific task (Delete / Update)
     * 
     * It takes as parameter a String which will contains part of the message to be display 
     * on the console after performing the deletion.
     * @param p 
     */
    public void redirect(String p) {
        /**
         * We will set the break variable to true which means that when coming back to top loop 
         * will break and go to Main screen (see line 57 - 60)
         */
        setBreakVar(true);
        System.out.println("\n" + p + ", You will be redirected\nto the main screen in 3 seconds");
        // start a try-catch block that will delate the redirect for n seconds 
        try {
            TimeUnit.SECONDS.sleep(3);
            setHeader("Main Menu");
            separator();
        } catch (InterruptedException ex) {
            Logger.getLogger(AddPatient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    

