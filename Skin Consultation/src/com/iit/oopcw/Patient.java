package com.iit.oopcw;

import java.time.LocalDate;

public class Patient extends Person {

    Doctor assignDoctors;

    public Patient(String name, String surname, LocalDate dateOfBirth, int mobileNo, Doctor assignDoctors) {
        super(name, surname, dateOfBirth, mobileNo);
        this.assignDoctors = assignDoctors;
    }


    public String getName() {
        return super.name;
    }

    public String getSurname() {
        return super.surname;
    }

    public String getDob() {
        return super.dob;
    }

    public int getMobileNo() {
        return super.mobileNumber;
    }

    public LocalDate getDateOfBirth() {
        return super.dateOfBirth;
    }

}

