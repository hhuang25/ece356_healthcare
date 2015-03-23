/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import bean.Review;

/**
 *
 * @author Ajanthan Asogamoorth
 */
public class ReviewInfo {
    private Review review;
    private String doctor_name;
    
    public void setReview(Review r) {
        this.review = r;
    }
    
    public Review getReview() {
        return this.review;
    }
    
    public void setDoctorName(String s) {
        this.doctor_name = s;
    }
    
    public String getDoctorName() {
        return this.doctor_name;
    }
}
