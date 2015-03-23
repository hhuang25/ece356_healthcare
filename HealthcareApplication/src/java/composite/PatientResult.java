/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;
import bean.*;
import java.sql.Timestamp;

/**
 *
 * @author Ajanthan Asogamoorth
 */
public class PatientResult {
    private Patient patient;
    private User user;
    private Region region;
    private int num_review;
    private Timestamp last_review_date;
    private int status;
    
    public void setPatient(Patient p) {
        this.patient = p;
    }
    
    public Patient getPatient() {
        return this.patient;
    }
     
    public void setUser(User u){
        this.user = u;
    }
    
    public User getUser() {
           return this.user;
    }
    
    public void setRegion(Region r){
        this.region = r;
    }
    
    public Region getRegion() {
        return this.region;
    }

    public void setNumReview(int num_review) {
        this.num_review = num_review;
    }
    
    public int getNumReview() {
        return this.num_review;
    }
    
    public void setTimeReview(Timestamp time_review) {
        this.last_review_date = time_review;
    }
    
    public Timestamp getTimeReview() {
        return this.last_review_date;
    }
    
    public void setStatus(int incoming_status, int outgoing_status) {
        if (incoming_status == 0 && outgoing_status == 0) {
            this.status = 0; // no social relationship
        } else if (incoming_status == 0 && outgoing_status == 1) {
            this.status = 1; // youve already sent a friend request
        } else if (incoming_status == 1 && outgoing_status == 0) {
            this.status = 2; // you must confirm friendship
        } else {
            this.status = 3;
        }
    }
    
    public int getStatus() {
            return this.status;
    }
    
}