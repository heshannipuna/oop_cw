package com.iit.oopcw;

import javax.swing.*;

public class InteractivePage {

    InteractivePage() {

        JLabel background = new JLabel(new ImageIcon("UseCase.png"));
        background.setBounds(0, 0, 0, 0);

        InfoPage frame = new InfoPage();
        frame.setLayout(null);
        frame.setTitle("Westminster Skin Consultation Center");

        JButton button1 = new JButton("View all Doctors");
        button1.setBounds(300, 540, 250, 50);
        button1.addActionListener(e -> {
            DocList dTable = new DocList();
        });

        JButton button2 = new JButton("Add Consultation");
        button2.setBounds(900, 540, 250, 50);
        button2.addActionListener(e -> {
            InteractiveMenu consultMenu = new InteractiveMenu();
        });

        frame.add(button1);
        frame.add(button2);
        frame.setVisible(true);
    }
}

