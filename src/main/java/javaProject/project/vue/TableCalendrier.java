/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.vue;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Oscar
 */
 class TableCalendrier extends AbstractTableModel{
    private Object[][] data;
    private String[] title;

    //Constructeur
    public TableCalendrier(Object[][] data, String[] title){
      this.data = data;
      this.title = title;
    }

    //Retourne le nombre de colonnes
    public int getColumnCount() {
      return this.title.length;
    }

    //Retourne le nombre de lignes
    public int getRowCount() {
      return this.data.length;
    }

    //Retourne la valeur à l'emplacement spécifié
    public Object getValueAt(int row, int col) {
      return this.data[row][col];
    }
    
    /**
* Retourne le titre de la colonne à l'indice spécifié
*/
public String getColumnName(int col) {
  return this.title[col];
}
  }
