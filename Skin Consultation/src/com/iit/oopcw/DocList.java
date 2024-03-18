package com.iit.oopcw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;


public class DocList {

    InfoPage frame = new InfoPage();
    TableStructure tModel = new TableStructure(WestminsterSkinConsultationManager.doctorsList);
    JTable table = new JTable(tModel);
    JButton backBtn = new JButton("Back");

    JScrollPane scrollPane = new JScrollPane();

    public DocList(){

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        p2.setLayout(null);
        p2.setPreferredSize(new Dimension(400,800));

        backBtn.setBounds(50, 600, 100, 50);
        backBtn.addActionListener(e->{
            frame.dispose();
        });

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        table.setPreferredScrollableViewportSize(new Dimension(600,600));


        scrollPane.add(table);
        scrollPane.setViewportView(table);

        p1.add(scrollPane);
        p2.add(backBtn);

        frame.add(p1,BorderLayout.EAST);
        frame.add(p2,BorderLayout.WEST);

        frame.setVisible(true);


    }

}

