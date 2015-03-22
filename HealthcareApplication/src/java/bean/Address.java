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
public class Address {
    private int id;
    private int regionId;
    private String street;
    private int streetNumber;
    private String postalCode;

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
     * @return the regionId
     */
    public int getRegionId() {
        return regionId;
    }

    /**
     * @param regionId the regionId to set
     */
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the streetNumber
     */
    public int getStreetNumber() {
        return streetNumber;
    }

    /**
     * @param streetNumber the streetNumber to set
     */
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
