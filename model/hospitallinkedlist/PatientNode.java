/**
 * Class: CustomNode
 * 
 * Purpose: To create nodes and what they are referencing to be used
 * in a linked list.
 * 
 * This Class can be used only within the same package.
 * 
 * This classes are based on online research and the lectures given by
 * the Professor Dr. Muhammad Iqbal
 */

package ccthospital.model.hospitallinkedlist;

import java.util.Objects;

/**
 *
 * @author
 * Asmer Bracho 2016328
 * Miguelantonio Guerra 2016324
 */
public class PatientNode<C> {
    
    /**
     * Attributes of the class
     */
    private C dataPatient; // data element
    private PatientNode<C> nextPatientRef; // The link to the next value
    
    /**
     * This is the first constructor of the Class CustomNode
     * 
     * This constructor takes in the following parameters:
     * 
     * @param dataElement the element of type C that is going to be the data within the node
     * @param nextElementRef the reference to the next element.
     */
    public PatientNode(C dataElement, PatientNode<C> nextElementRef) {
        this.dataPatient = dataElement;
        this.nextPatientRef = nextElementRef;
    }
    
    /**
     * This is the other constructor of the Class CustomNode
     * 
     * This constructor takes in only one parameter:
     * 
     * @param dataElement the element of type C that is going to be the data within the node
     */
    public PatientNode(C dataElement) {
        this.dataPatient = dataElement;
        this.nextPatientRef = null;
    }

    /**
     * This method returns the Data of the object patient
     * 
     * @return 
     */
    public C getDataPatient() {
        return dataPatient;
    }

    /**
     * This method return the next patient reference
     * 
     * @return 
     */
    public PatientNode<C> getNextPatientRef() {
        return nextPatientRef;
    }

    /**
     * This method sets the data of a determined patient.
     * 
     * It takes in the following parameter:
     * 
     * @param dataPatient data of the patient
     */
    public void setDataPatient(C dataPatient) {
        this.dataPatient = dataPatient;
    }

    /**
     * This method sets the reference to the next Patient
     * 
     * It takes in the following parameter:
     * 
     * @param nextPatientRef reference to the next patient.
     */
    public void setNextPatientRef(PatientNode<C> nextPatientRef) {
        this.nextPatientRef = nextPatientRef;
    }

    /**
     * This is the rewritten equals method for the patient node.
     * 
     * It was overridden just in case we needed to compare the data of nodes and
     * not the next element reference.
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PatientNode<?> other = (PatientNode<?>) obj;
        if (!Objects.equals(this.dataPatient, other.dataPatient)) {
            return false;
        }
        return true;
    }

    /**
     * To string method to show only the data in the node but not the next reference.
     * 
     * It was done this way to adjust to the need of our program.s
     * @return 
     */
    @Override
    public String toString() {
        return dataPatient.toString();
    }

}
