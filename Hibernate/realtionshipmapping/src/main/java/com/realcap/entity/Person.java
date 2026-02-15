package com.realcap.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "person_sequence", initialValue = 100, allocationSize = 1)
    private int personId;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Passport passport;

    // Default Constructor
    public Person() {}

    // Parameterized Constructor
    public Person(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    // Getters and Setters
    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}