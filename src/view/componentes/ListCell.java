/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeTableCell;

/**
 *
 * @author Ana
 */
public class ListCell<S extends Object, T extends String> extends TreeTableCell<S, T> {
    private final List<String> listado;

    public ListCell(List<String> listado) {
        this.listado = listado;
    }
    
  //   @Override
    protected void updateItem(ObservableList<T> item, boolean empty) {

        if (item == null) {
            setText(null);
            setGraphic(null);
        } else {
            
            ComboBox combobox = new ComboBox();
            combobox.setMaxSize(50,50);
            combobox.setItems(item);
//            File imagenNueva = new File(rutaAccesoImagenes + "/" + item);
            
            //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
            setGraphic(combobox);
        }

     //   super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
    }
}
