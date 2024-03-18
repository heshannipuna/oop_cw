package com.iit.oopcw;

import java.util.Date;

public class Consultation {
    private Doctor doctor;
    private Patient patient;
    private Date appointmentDate;

    public Consultation(Doctor doctor, Patient patient,Date appointmentDate ){
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

}
