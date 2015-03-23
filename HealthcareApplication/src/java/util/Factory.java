/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import bean.Doctor;
import bean.Patient;
import bean.User;
import composite.DoctorReview;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Tommy
 */
public class Factory {
    public static User CreateUser(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getInt("id"));
            user.setAlias(rs.getString("alias"));
            user.setEmail(rs.getString("email"));
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        
        return user;
    }
    
    public static Doctor CreateDoctor(ResultSet rs) {
        Doctor doctor = new Doctor();
        try {
            doctor.setId(rs.getInt("id"));
            doctor.setFirstName(rs.getString("first_name"));
            doctor.setMiddleName(rs.getString("middle_name"));
            doctor.setLastName(rs.getString("last_name"));
            doctor.setGender(rs.getString("gender"));
            doctor.setYearLicenseObtained(rs.getInt("year_license_obtained"));
            doctor.setUserId(rs.getInt("user_id"));
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        
        return doctor;
    }
    
    public static DoctorReview CreateDoctorReview(ResultSet rs) {
        DoctorReview doctorReview = new DoctorReview();
        try {
            doctorReview.getDoctor().setId(rs.getInt("id"));
            doctorReview.getDoctor().setFirstName(rs.getString("first_name"));
            doctorReview.getDoctor().setMiddleName(rs.getString("middle_name"));
            doctorReview.getDoctor().setLastName(rs.getString("last_name"));
            doctorReview.getDoctor().setGender(rs.getString("gender"));
            doctorReview.setAverageRating(rs.getDouble("average_rating"));
            doctorReview.setNumberOfReviews(rs.getInt("num_of_reviews"));
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        
        return doctorReview;
    }
    
    public static Patient CreatePatient(ResultSet rs) {
        Patient patient = new Patient();
        try {
            patient.setId(rs.getInt("id"));
            patient.setFirstName(rs.getString("first_name"));
            patient.setMiddleName(rs.getString("middle_name"));
            patient.setLastName(rs.getString("last_name"));
            patient.setGender(rs.getString("gender"));
            patient.setUserId(rs.getInt("user_id"));
            patient.setRegionId(rs.getInt("region_id"));
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        
        return patient;
    }
}
