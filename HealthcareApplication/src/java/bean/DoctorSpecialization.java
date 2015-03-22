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
public class DoctorSpecialization {
    private int doctorId;
    private int specializationId;

    /**
     * @return the doctorId
     */
    public int getDoctorId() {
        return doctorId;
    }

    /**
     * @param doctorId the doctorId to set
     */
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * @return the specializationId
     */
    public int getSpecializationId() {
        return specializationId;
    }

    /**
     * @param specializationId the specializationId to set
     */
    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }
    
}
