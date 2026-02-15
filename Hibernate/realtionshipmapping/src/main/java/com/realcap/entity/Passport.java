package com.realcap.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passport_seq")
    @SequenceGenerator(name = "passport_seq", sequenceName = "passport_sequence", initialValue = 1, allocationSize = 1)
    private int passportId;
    private String passportNumber;
    private String country;
    
    
    @OneToOne(mappedBy="passport")
    private Person person;

    // Default Constructor
    public Passport() {}

    // Parameterized Constructor
    public Passport(String passportNumber, String country) {
        this.passportNumber = passportNumber;
        this.country = country;
    }

    // Getters and Setters
    public int getPassportId() {
        return passportId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}