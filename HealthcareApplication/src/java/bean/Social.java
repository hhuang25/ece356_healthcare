/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author huang
 */
public class Social {
    private int id;
    private int requestingPatientId;
    private int receivingPatientId;
    private int statusId;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the requestingPatientId
     */
    public int getRequestingPatientId() {
        return requestingPatientId;
    }

    /**
     * @param requestingPatientId the requestingPatientId to set
     */
    public void setRequestingPatientId(int requestingPatientId) {
        this.requestingPatientId = requestingPatientId;
    }

    /**
     * @return the receivingPatientId
     */
    public int getReceivingPatientId() {
        return receivingPatientId;
    }

    /**
     * @param receivingPatientId the receivingPatientId to set
     */
    public void setReceivingPatientId(int receivingPatientId) {
        this.receivingPatientId = receivingPatientId;
    }

    /**
     * @return the statusId
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * @param statusId the statusId to set
     */
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
}
