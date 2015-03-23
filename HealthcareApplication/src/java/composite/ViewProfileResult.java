/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import bean.Address;
import bean.Doctor;
import bean.Specialization;
import java.util.ArrayList;

/**
 *
 * @author Tommy
 */
public class ViewProfileResult {
    private Doctor doctor;
    private ArrayList<Address> addresses;
    private ArrayList<Specialization> specializations;
    private String email;
    private int yearsLicensed;
    private double averageRating;
    private int numOfReviews;
    
    public ViewProfileResult() {
        addresses = new ArrayList<>();
        specializations = new ArrayList<>();
    }

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * @return the addresses
     */
    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    /**
     * @param addresses the addresses to set
     */
    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * @return the specializations
     */
    public ArrayList<Specialization> getSpecializations() {
        return specializations;
    }

    /**
     * @param specializations the specializations to set
     */
    public void setSpecializations(ArrayList<Specialization> specializations) {
        this.specializations = specializations;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the yearsLicensed
     */
    public int getYearsLicensed() {
        return yearsLicensed;
    }

    /**
     * @param yearsLicensed the yearsLicensed to set
     */
    public void setYearsLicensed(int yearsLicensed) {
        this.yearsLicensed = yearsLicensed;
    }

    /**
     * @return the averageRating
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * @param averageRating the averageRating to set
     */
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * @return the numOfReviews
     */
    public int getNumOfReviews() {
        return numOfReviews;
    }

    /**
     * @param numOfReviews the numOfReviews to set
     */
    public void setNumOfReviews(int numOfReviews) {
        this.numOfReviews = numOfReviews;
    }
    
    

}
