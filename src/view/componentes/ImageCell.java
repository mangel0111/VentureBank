/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import java.io.File;
import javafx.scene.control.TreeTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Ana
 */
public class ImageCell<S extends Object, T extends String> extends TreeTableCell<S, T> {

    private final String rutaAccesoImagenes;

    public ImageCell(String rutaAccesoImagenes) {
        this.rutaAccesoImagenes = rutaAccesoImagenes;
    }

    @Override
    protected void updateItem(T item, boolean empty) {

        if (item == null) {
            setText(null);
            setGraphic(null);
        } else {
            ImageView imageview = new ImageView();
            imageview.setFitHeight(50);
            imageview.setFitWidth(50);
//            File imagenNueva = new File(rutaAccesoImagenes + "/" + item);
            File imagenNueva = new File(item);
            if (imagenNueva.exists()) {
                imageview.setImage(new Image(imagenNueva.toURI().toString()));
            }
            //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
            setGraphic(imageview);
        }

        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
    }

}
