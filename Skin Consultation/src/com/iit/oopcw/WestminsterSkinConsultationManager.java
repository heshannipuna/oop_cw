package com.iit.oopcw;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    static ArrayList<Doctor> doctorsList = new ArrayList<Doctor>(10);

    public static void main(String[] args) {

        int choice = 0;
        int count = 0;
        while (choice != 6) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("---------------------------------------------------");
            System.out.println("Welcome to Westminster Skin Consultation Management");
            System.out.println("---------------------------------------------------");
            System.out.println("1. Add a doctor \n"
                    + "2. Delete a Doctor \n"
                    + "3. View all Doctors \n"
                    + "4. Open GUI \n"
                    + "5. Save to a file \n"
                    + "6. Quit");
            System.out.println("---------------------------------------------------");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    deleteDoctor(count);
                    break;
                case 3:
                    viewDoctors();
                    break;
                case 4:
                    InteractivePage InteractivePage = new InteractivePage();
                    break;
                case 5:
                    saveFile();
                    break;
            }
        }
    }

             //Add doctors to the system
    public static void addDoctor() {
        String name;
        String surname;
        String dob;
        int mobileNumber;
        int lisenceNumber;
        String specialization;

        Scanner scanner = new Scanner(System.in);

        if (doctorsList.size() < 10) {
            int j = 1;
            ArrayList<LocalDate> dateList = new ArrayList<>();
            while (j != 0) {                            //While loop needed in order to add breaks for validation

                System.out.println("Enter doctor's name");
                name = scanner.next();

                System.out.println("Enter doctor's surname");
                surname = scanner.next();

                try {
                    System.out.println("Enter mobile number");
                    mobileNumber = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Mobile number should be an integer");
                    break;
                }

                try {
                    System.out.println("Enter license number");
                    lisenceNumber = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("License number should be integer");
                    break;
                }
                System.out.println("Enter specialization");
                specialization = scanner.next();

                doctorsList.add(new Doctor(name, surname, mobileNumber, lisenceNumber, specialization));
                j = 0;
            }

        } else {
            System.out.println("-------------------------------------------");
            System.out.println("Only a maximum of 10 doctors can be added");
            System.out.println("-------------------------------------------");
        }

    }

                //This Method converts string date into LocalDate format and validates
                //the date entered by the user
    public static LocalDate dateValidater(String dob) {

                //Displays error message if user tries to continue without
                 //adding any dates
        if ("q".equals(dob)) {
            System.out.println("You should enter at least one date to continue");
            return null;
        }

        try {
            LocalDate dateOfBirth = LocalDate.parse(dob, DateTimeFormatter.ISO_DATE);
            return dateOfBirth;

        } catch (IllegalArgumentException e) {
            System.out.println("Wrong date format, please enter in correct format");
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format, please enter in correct format");
        }
        return null;

    }
                //Delete doctors from system
    public static void deleteDoctor(int count) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter license No ");
        int license = scanner.nextInt();

        for (int i = 0; i < doctorsList.size(); i++) {
            Doctor d = doctorsList.get(i);
            if (d.getlicenseNo() == license) {
                System.out.println("The following doctor has been removed");
                doctorsList.get(i).printall();
                doctorsList.remove(i);
                System.out.println("Remaining amount of doctors: " + doctorsList.size());
                System.out.println("");
            }
        }
    }

         //For view all the doctors from the system
    public static void viewDoctors() {

        sort(doctorsList);

        try {
            for (Doctor i : doctorsList) {
                i.printall();
            }
        } catch (Exception e) {
        }
    }

    public static void sort(ArrayList<Doctor> list) {
        list.sort((o1, o2)
                -> o1.getSurname().compareTo(
                o2.getSurname()));
    }

           //Save information to the file
    public static void saveFile() {
        try {
            FileOutputStream file = new FileOutputStream("Saved Information.txt");
            ObjectOutputStream object = new ObjectOutputStream(file);
            for (Doctor addDoctor : doctorsList) {
                object.writeObject(addDoctor);
            }
            file.close();
            object.close();
            System.out.println("Something went wrong");
            }
            catch (Exception e){
                System.out.println("Successfully saved file");
            }
    }
}

