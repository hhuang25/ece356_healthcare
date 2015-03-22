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
    private Review review;

    public DoctorReview(Doctor doctor, Review review) {
        this.doctor = doctor;
        this.review = review;
    }

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @return the review
     */
    public Review getReview() {
        return review;
    }
}
