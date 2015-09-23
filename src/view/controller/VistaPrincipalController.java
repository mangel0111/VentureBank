/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Node;

/**
 *
 * @author Ana
 */
public class VistaPrincipalController extends controllerBase implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        super.initialize(location, resources); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eventoCargarData() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cargarLookAndFeel() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getView() {
        return getView(VistaPrincipalController.class.getResource("FXMLVistaPrincipal.fxml"));
    }
    
}
