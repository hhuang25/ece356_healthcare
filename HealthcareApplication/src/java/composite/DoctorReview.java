/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import bean.*;

/**
 *
 * @author huang
 */
public class DoctorReview {
    private Doctor doctor;
    private int numberOfReviews;
    private double averageRating;

    public DoctorReview(Doctor doctor, int numberOfReviews, double averageRating) {
        this.doctor = doctor;
        this.numberOfReviews = numberOfReviews;
        this.averageRating = averageRating;
    }
    
    public DoctorReview(){}

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @return the numberOfReviews
     */
    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    /**
     * @return the averageRating
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * @param numberOfReviews the numberOfReviews to set
     */
    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    /**
     * @param averageRating the averageRating to set
     */
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
