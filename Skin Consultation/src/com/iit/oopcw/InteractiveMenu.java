package com.iit.oopcw;


import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class InteractiveMenu {

    static ArrayList<Patient> patientsList = new ArrayList<>();
    static ArrayList<Consultation> consultationList = new ArrayList<>();

    InfoPage frame = new InfoPage();
    JPanel detailsPanel = new JPanel();


    JLabel nameLabel = new JLabel("First Name");
    JLabel surnameLabel = new JLabel("Surname");
    JLabel dobLabel = new JLabel("Date Of Birth");
    JLabel mobileLabel = new JLabel("Mobile No");
    JTextField nameField = new JTextField();
    JTextField surnameField= new JTextField();
    JTextField dobField = new JTextField();
    JTextField mobField = new JTextField();

    JLabel idLabel = new JLabel("Enter the doctor's License Number");
    JTextField idField = new JTextField();
    JLabel dateLabel = new JLabel("Enter appointment date");
    JTextField dateField = new JTextField();

    JButton backBtn = new JButton("Back");
    JButton submitBtn = new JButton("Submit");

    public InteractiveMenu(){

        frame.setLayout(new BorderLayout());

        detailsPanel.setLayout(null);

        nameLabel.setBounds(250, 80, 110, 30);
        surnameLabel.setBounds(250, 140, 110, 30);
        dobLabel.setBounds(250, 200, 110, 30);
        mobileLabel.setBounds(250, 260, 110, 30);

        nameField.setBounds(350, 80, 200, 30);
        surnameField.setBounds(350, 140, 200, 30);
        dobField.setBounds(350, 200, 200, 30);
        mobField.setBounds(350, 260, 200, 30);


        detailsPanel.add(nameLabel);
        detailsPanel.add(nameField);

        detailsPanel.add(surnameLabel);
        detailsPanel.add(surnameField)
        ;
        detailsPanel.add(dobLabel);
        detailsPanel.add(dobField);

        detailsPanel.add(mobileLabel);
        detailsPanel.add(mobField);


        idLabel.setBounds(200, 400, 200, 30);
        idField.setBounds(500,400, 200, 30);
        dateLabel.setBounds(200, 440, 200, 30);
        dateField.setBounds(500, 440, 200, 30);

        backBtn.setBounds(680, 600, 100, 40);
        backBtn.addActionListener(e -> {
            frame.dispose();
        });


        submitBtn.setBounds(800, 600, 100, 40);
        submitBtn.addActionListener(e ->{
            String name = nameField.getText();
            String surname = surnameField.getText();
            String dob = dobField.getText();
            String mob = mobField.getText();
            LocalDate patientBirthDate;
            int mobileNo;
            Date appointmentdate;

//======================================      System        =============================================================
//======================================  Input validation  =============================================================

            if(name.isEmpty()){
                JOptionPane.showMessageDialog(detailsPanel,
                        "Name cannot be empty", "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(surname.isEmpty()){
                JOptionPane.showMessageDialog(detailsPanel,
                        "Surname cannot be empty", "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }

            //validates and converts String patient birth date to LocalDate format
            if (WestminsterSkinConsultationManager.dateValidater(dob)==null){
                JOptionPane.showMessageDialog(detailsPanel,
                        "Invalid date of birth", "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            patientBirthDate = WestminsterSkinConsultationManager.dateValidater(dob);

            //Validates the mobile number
            try{
                mobileNo = Integer.parseInt(mob);
            }catch(Exception x){
                JOptionPane.showMessageDialog(detailsPanel,
                        "Mobile should be integer", "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
//===================================================================================================================
            //validates appointment date, If suitable doctor found
           //  he is assigned to the respective patient
            SimpleDateFormat sf =  new SimpleDateFormat("yyyy-MM-dd;HH:mm");

            try{
                appointmentdate = sf.parse(dateField.getText());
                System.out.println(appointmentdate);
            }catch(ParseException s){
                JOptionPane.showMessageDialog(detailsPanel,
                        "Invalid date time format for appointment", "Error",JOptionPane.ERROR_MESSAGE);
                appointmentdate = null;
                return ;
            }

            //Checks to see if user's doctor of choice is available, if available returns the respective doctor,
            // otherwise returns a random doctor
            Doctor doc = checkDoctorAvailability(idField.getText(),appointmentdate);

            //Creates a new patient and a consultation
            if(doc != null){
                patientsList.add(new Patient(name, surname, patientBirthDate, mobileNo,doc));
                consultationList.add(new Consultation(doc, patientsList.get(patientsList.size() -1),appointmentdate));
            }
            if(doc==null){
                System.out.println("Error occured");
            }

            for(Consultation p : consultationList){
                System.out.println(p.getDoctor().getName());
                System.out.println(p.getPatient().getName());
                System.out.println(p.getAppointmentDate());
            }

            clearTextFields();
        });

        detailsPanel.add(idLabel);
        detailsPanel.add(idField);
        detailsPanel.add(dateLabel);
        detailsPanel.add(dateField);
        detailsPanel.add(backBtn);
        detailsPanel.add(submitBtn);

        frame.add(detailsPanel,BorderLayout.CENTER);
        frame.setVisible(true);
    }


    public void clearTextFields(){
        nameField.setText("");
        surnameField.setText("");
        dobField.setText("");
        mobField.setText("");
        dateField.setText("");
        idField.setText("");
    }

    public Doctor checkDoctorAvailability(String licenceId, Date appointmentdate){

        Doctor randomDoctor;

        int licenceNo = Integer.parseInt(licenceId);


        int i=0;

        //Checks all doctors to see if the selected doctor is available, If doctor is available
         //that respective doctor will be called otherwise getRandomDoctor() will be called which
         //in turn will return a random doctor
        for (Doctor d : WestminsterSkinConsultationManager.doctorsList){
            if (d.getlicenseNo() == licenceNo){
                if(d.dateTimeList.isEmpty()){
                    d.dateTimeList.add(appointmentdate);
                    JOptionPane.showMessageDialog(detailsPanel,
                            "The doctor has been booked", "Confirmation",JOptionPane.INFORMATION_MESSAGE);
                    return d;
                }
                for (Date ld : d.dateTimeList){

                    if (ld.compareTo(appointmentdate) ==0){  //Localdates compareTo method returns 0 if dates are equal
                        randomDoctor = getRandomDoctor(appointmentdate);
                        System.out.println(randomDoctor);
                        if (randomDoctor==null){
                            JOptionPane.showMessageDialog(detailsPanel,
                                    "No doctors available", "Alert",JOptionPane.ERROR_MESSAGE);
                            return null;
                        }

                        JOptionPane.showMessageDialog(detailsPanel,
                                "The doctor chosen is not available at that date, another doctor has been assigned", "Confirmation",JOptionPane.INFORMATION_MESSAGE);


                        randomDoctor.dateTimeList.add(appointmentdate);
                        return randomDoctor;

                    }

                }
                JOptionPane.showMessageDialog(detailsPanel,
                        "The doctor has been booked", "Confirmation",JOptionPane.INFORMATION_MESSAGE);
                d.dateTimeList.add(appointmentdate);
                return d;
            }else{
                i=1;
            }
        }
        if(i==1){
            JOptionPane.showMessageDialog(detailsPanel,
                    "Invalid License No", "Error",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    //Returns a random doctor if user's choice oo doctor is not available
    public Doctor getRandomDoctor(Date appointmentdate){

        Random random = new Random();

        Doctor doc;
        ArrayList<Doctor> availableDocList = new ArrayList<>();

        for(Doctor d : WestminsterSkinConsultationManager.doctorsList){
            doc= checkDoctorEligibility(d,appointmentdate);
            if(doc==null){
                continue;
            }else{
                availableDocList.add(doc);
            }
        }

        if (availableDocList.isEmpty()){
            return null;
        }

        int randomNum = random.nextInt(availableDocList.size());
        return availableDocList.get(randomNum);
    }

    //Checks whether the doctor is available
    public Doctor checkDoctorEligibility(Doctor doc, Date appDate){
        if(doc.dateTimeList.isEmpty()){
            return doc;
        }
        for (Date date : doc.dateTimeList){
            if (date.compareTo(appDate)==0){
                return null;
            }
        }
        return doc;
    }
}
