package com.iit.oopcw;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class TableStructure extends AbstractTableModel {


    private ArrayList<Doctor> list ;
    private String[] coloumnNames = {"First name","Surname","Mobile Number","Licence Number","Specialization"};


    public TableStructure(ArrayList<Doctor> doctorList){
        this.list = doctorList;
    }

    @Override
    public int getRowCount() {
        return list.size();

    }

    @Override
    public int getColumnCount() {
        return coloumnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if(columnIndex == 0){
            temp = list.get(rowIndex).getName();
        }else if(columnIndex == 1){
            temp = list.get(rowIndex).getSurname();
        }else if(columnIndex == 2){
            temp = list.get(rowIndex).getMobileNo();
        }else if(columnIndex == 3){
            temp = list.get(rowIndex).getlicenseNo();
        }else if(columnIndex == 4){
            temp = list.get(rowIndex).getSpecialization();
        }
        return temp;

    }

    @Override
    public String getColumnName(int col){
        return coloumnNames[col];
    }

}
