package com.iit.oopcw;

import java.time.LocalDate;


public class Person {
    protected String name;
    protected String surname;
    protected String dob;
    protected int mobileNumber;
    protected LocalDate dateOfBirth;

    public Person(String name, String surname, LocalDate dateOfBirth, int mobileNumber){

        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
    }
         //This constructor is for Doctor Class
    public Person(String name, String surname, int mobileNumber){

        this.name = name;
        this.surname = surname;
        this.mobileNumber = mobileNumber;
    }

}
