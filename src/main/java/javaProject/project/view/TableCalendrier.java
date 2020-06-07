/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.view;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Oscar
 */
class TableCalendrier extends AbstractTableModel {

    private Object[][] data;
    private String[] title;

    //Constructeur
    public TableCalendrier(Object[][] data, String[] title) {
        this.data = data;
        this.title = title;
    }

    public int getColumnCount() {
        return this.title.length;
    }

    public int getRowCount() {
        return this.data.length;
    }

    public Object getValueAt(int row, int col) {
        return this.data[row][col];
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public String getColumnName(int col) {
        return this.title[col];
    }
}
