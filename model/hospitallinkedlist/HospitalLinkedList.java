/**
 * Class: CustomLinkedList
 *
 * Purpose: Generate a Single Linked List
 * 
 * It was design as a abstract class so that every method could be defined
 * in a general way and then it was inherit for another class called PatientsWaitingList.
 * In the inheriting class the abstract methods are defined.
 *
 * This classes are based on online research and the lectures given by
 * the Professor Dr. Muhammad Iqbal
 */
package ccthospital.model.hospitallinkedlist;

/**
 * @author
 * Asmer Bracho 2016328
 * Miguelantonio Guerra 2016324
 * @param <C>
 */
public abstract class HospitalLinkedList<C> {

    private PatientNode<C> firstPatienteInList = null;
    private int sizeOfListOfPatients = 0;

    /**
     * This method adds a node at the beginning of the list. And then this node
     * is going to be the head of the list. This method is used to add high
     * emergency patients.
     *
     * This method takes in only one parameter:
     *
     * @param element element that is going to be added at the beginning of the
     * list
     */
    public void addPatientToFront(C patient) {
        this.firstPatienteInList = new PatientNode<C>(patient, this.firstPatienteInList);
        this.sizeOfListOfPatients++;
    }

    /**
     * This method adds a node at the end of the list. This method is used to
     * add normal cases patients
     *
     * This method takes in only one parameter:
     *
     * @param element element that is going to be added at the end of the list
     */
    public void addPatientToEnd(C patient) {
        PatientNode<C> oldLastPatient = getPatientAtPosition(this.sizeOfListOfPatients - 1);
        
        oldLastPatient.setNextPatientRef(new PatientNode<C>(patient, oldLastPatient.getNextPatientRef()));
        this.sizeOfListOfPatients++;
    }

    /**
     * This method adds a patient at a given position and moves the others
     * one position up. This method is to be used with patients of that
     * are not urgent to be attended but that require more attention than
     * normal case patients.
     * 
     * It takes the parameters:
     * 
     * @param patient a patient object
     * @param position the position at which you want to add the patient
     */
    public void addPatientAtPosition(C patient, int position) {
        /**
         * Here we can add at the beginning of the list or at the end, but, as
         * we already have those methods we just call them when position is
         * equal to 0 or size-1
         */
        if ((position < 0)||(position > this.sizeOfListOfPatients)) {
            // If someone tries to add a patient out of range they'll get an error message.
            System.out.println("Position to add cannot be outside the range from 0 to " + (this.sizeOfListOfPatients));
        } else if (position == 0) {
            // Here is where we add the patient to the first position
            this.addPatientToFront(patient);
        } else if (position == this.sizeOfListOfPatients) {
            // Here is where we add the patient to the last position
            this.addPatientToEnd(patient);
        } else {
            /**
             * Here is when we add the patient to any position that is
             * not the first or the last one
             */
            PatientNode<C> patientAtPreviouPosition = getPatientAtPosition(position - 1);
            patientAtPreviouPosition.setNextPatientRef(new PatientNode<C>(patient, patientAtPreviouPosition.getNextPatientRef()));
            this.sizeOfListOfPatients++;
        }
    }
    
    /**
     * This method removes the first patient in the list. This means
     * that the patient was already seen by a doctor.
     * 
     * It returns the data of the patient that was just deleted from the
     * list.
     * 
     * @return 
     */
    public C removeFirstPatient() {
        PatientNode<C> auxNode = this.firstPatienteInList;
        /**
         * If there is nothing in the list doe snot delete anything
         * and returns null
         */
        if (this.firstPatienteInList != null) {
            this.firstPatienteInList = this.firstPatienteInList.getNextPatientRef();
        }
        if (auxNode != null) {
            this.sizeOfListOfPatients--;
            return auxNode.getDataPatient();
        } else {
            return null;
        }
    }
    
    /**
     * This method is used to remove the last patient in the list.
     * 
     * It returns the patient that was deleted from the list.
     * 
     * @return 
     */
    public C removeLastPatient() {
        /**
         * If there is only one patient in the list we only delete the head.
         * For this we use the Method for removing the first patient in the list.
         */
        if (this.sizeOfListOfPatients == 1) {
            return this.removeFirstPatient();
        }
        PatientNode<C> auxNode = this.getPatientAtPosition(sizeOfListOfPatients - 1);
        this.getPatientAtPosition(sizeOfListOfPatients - 2).setNextPatientRef(null);
        this.sizeOfListOfPatients--;
        
        return auxNode.getDataPatient();
    }
    
    /**
     * This method deletes the last N patients in the list.
     * 
     * It takes the parameter the tells the program the number of patients to delete from the list.
     * @param N 
     */
    public void removeLastNPatients(int N) {
        int deletes = N;
        while (deletes != 0) {
            this.removeLastPatient();
            deletes--;
        }
    }
    
    /**
     * This method removes a patient at any position in the list.
     * 
     * It takes the parameter that indicates the position in the list of the patient to delete.
     * 
     * @param position
     * 
     * It returns the patient eliminated.
     * 
     * @return 
     */
    public C removePatientAtAnyPosition(int position) {
        if ((position < 0)||(position > this.sizeOfListOfPatients - 1)) {
            // If someone tries to remove a patient out of range they'll get an error message.
            System.out.println("Position to remove cannot be outside the range from 0 to " + (this.sizeOfListOfPatients - 1));
            return null;
        } else if (position == 0) {
            return this.removeFirstPatient();
        } else if (position == this.sizeOfListOfPatients - 1) {
            return this.removeLastPatient();
        } else {
            C patient = this.getPatientAtPosition(position).getDataPatient();
            this.getPatientAtPosition(position - 1).setNextPatientRef(this.getPatientAtPosition(position + 1));
            this.sizeOfListOfPatients--;
            return patient;            
        }
    }
    
    /**
     * This method deletes patients by PID
     * 
     * It takes the parameter PID of patient to remove
     * 
     * @param pid
     * 
     * It returns the eliminated patient.
     * 
     * @return 
     */
    public C removePatientByPID(int pid) {
        PatientNode<C> auxNode = this.searchPatientInListByPID(pid); // Node to remove
        /**
         * If auxNode is null the there is no node to remove. PID number inserted does not exit in the list.
         */
        if (auxNode == null) {
            return null; // PID inserted does not exist in the list. No patient was removed.
        }
        int position = this.getPatientNodePositionInList(auxNode); // Position in list of node to remove
        this.removePatientAtAnyPosition(position);
        auxNode.setNextPatientRef(null);
        
        return auxNode.getDataPatient();
    }
    
    
    /**
     * This method returns a integer that represents the node's position in the list.
     * 
     * It takes the parameter:
     * 
     * @param patientNode node to determine its position.
     * 
     * Returns:
     * 
     * @return node's position in the list. Return -1 if node is not in the list.
     */
    public int getPatientNodePositionInList(PatientNode<C> patientNode) {
        PatientNode<C> auxNode = this.firstPatienteInList;
        int position = 0;
        while (auxNode != null) {
            if (auxNode == patientNode) {
                return position;
            }
            position++;
            auxNode = auxNode.getNextPatientRef();
        }
        
        return -1;
    }
    
    /**
     * This method returns a Node at a given position in the list
     *
     * It takes in only one parameter:
     *
     * @param position position in the list of the item we want to return
     * 
     * It returns:
     * 
     * @return the PatientNode at the given position. Returns null if the position is invalid or if the Node is not found.
     */
    public PatientNode<C> getPatientAtPosition(int position) {
        PatientNode<C> auxNode = this.firstPatienteInList;
        
        if ((position < 0)||(position > this.sizeOfListOfPatients - 1)) {
            return null;
        }
        
        for (int i = 0; (i < position) && (auxNode != null); i++) {
            auxNode = auxNode.getNextPatientRef();
        }

        return auxNode;
    }

    /**
     * This method returns the first patient in the list.
     *
     * @return
     */
    public PatientNode<C> getFirstPatientInList() {
        return firstPatienteInList;
    }

    /**
     * This method returns how many patients are in the list.
     *
     * @return
     */
    public int getSizeOfListOfPatients() {
        return sizeOfListOfPatients;
    }

    /**
     * This is the To String method for our Hospital Linked List object.
     * 
     * It returns all the elements in the list formatted in a readable way.
     * 
     * @return formatted elements in the list.
     */
    @Override
    public String toString() {
        PatientNode<C> auxNode = this.firstPatienteInList;
        int position = 1; // Position in the queue starts from 1 (1 is the head of the linked list).
        String waitingList = "\n________________________________________\n\tPatients in the Queue: " + this.sizeOfListOfPatients + "\n________________________________________\n"; // Waiting list to return.
        while(auxNode != null) {
            waitingList  = waitingList + "\t\tPOSITION: " + position + "\n" + auxNode.getDataPatient().toString() + "\n";
            auxNode = auxNode.getNextPatientRef();
            position++;
        }
        return waitingList;
    }
    
    public void seeListOfPatients() {
        System.out.println(this);
    }
    
    /**
     * This method is to search a patient node by PID number. It was done as a abstract method
     * due to this whole class done generic. If could have cast the object to patient but then
     * this class would have lost its general form and then it could have not been used with another
     * kind of object.
     * 
     * There is another class that is going to override this method to make a particular case with the
     * patient objects and its pid number property
     * 
     * It takes the parameter:
     * 
     * @param pid that for our case is a PID number but if we wanted to search by phone number it can be used as well
     * 
     * It returns:
     * 
     * @return a patient node that matches that PID (or integer). It will return null if there is no match.
     */
    public abstract PatientNode<C> searchPatientInListByPID(int pid);
    
    /**
     * This method is to search a patient node by name (a combination of the first name and last name). It was done
     * as an abstract method due to this whole class done generic. In another class this class is going to be extended
     * to be a Patient object. Now this class can be used for other purposes because it is not going to loose
     * it general form (that is why casting was avoided by doing the research and in the class that extends this class
     * we can access the Patient methods).
     * 
     * It takes the parameter:
     * 
     * @param name first name and last name of the patient. It can be used to search by another kind of string within other types of object.
     * The abstract nature makes it a versatile method when overridden by a child class.
     * 
     * @return it return a Patient Node of general type C.
     */
    public abstract PatientNode<C> searchPatientInListByName(String name);
    
}
