package com.iit.oopcw;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Doctor extends Person{

    private int licenseNumber;
    private String specialization;
    ArrayList<Date> dateTimeList = new ArrayList<>();       //This list has dates where doctor is available for consultation

    public Doctor(String name, String surname, int mobileNumber, int licenseNumber, String specialization){
        super(name, surname, mobileNumber);
        this.licenseNumber = licenseNumber;
        this.specialization = specialization;

    }
    public String getName(){
        return super.name;
    }
    public String getSurname(){
        return super.surname;
    }
    public String getDob(){
        return super.dob;
    }
    public LocalDate getDateOfBirth(){
        return super.dateOfBirth;
    }
    public int getMobileNo(){
        return super.mobileNumber;
    }
    public int getlicenseNo(){
        return this.licenseNumber;
    }
    public String getSpecialization(){
        return this.specialization;
    }

    public void printall(){
        System.out.println("---------------------------------------------------");
        System.out.println("First Name: " + super.name);
        System.out.println("Surname: " + super.surname);
        System.out.println("Date of Birth: " + super.dob);
        System.out.println("");
        System.out.println("Mobile Number: " + super.mobileNumber);
        System.out.println("Medical License Number: " + this.licenseNumber);
        System.out.println("Specialization: " + this.specialization);
        System.out.println("---------------------------------------------------");
    }

}
