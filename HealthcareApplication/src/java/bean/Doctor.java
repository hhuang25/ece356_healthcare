/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author huangster
 */
public class Doctor {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private int yearLicenseObtained;
    private int userId;

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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        if (Character.isLowerCase(gender.charAt(0))) {
            gender = gender.substring(0, 1).toUpperCase() + gender.substring(1);
        }

        this.gender = gender;
    }

    /**
     * @return the yearLicenseObtained
     */
    public int getYearLicenseObtained() {
        return yearLicenseObtained;
    }

    /**
     * @param yearLicenseObtained the yearLicenseObtained to set
     */
    public void setYearLicenseObtained(int yearLicenseObtained) {
        this.yearLicenseObtained = yearLicenseObtained;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
