/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venturebank;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import view.controller.ControllerBorderPane;

/**
 *
 * @author miguelangel
 */
public class VentureMenu extends ControllerBorderPane implements Initializable {
    
    @FXML
    private Button uxbtnMovimientos, uxbtnDetalles;
    
    public VentureMenu() {
        setCenter(getView(VentureMenu.class.getResource("FXMLVentureMenu.fxml")));
        
    }
    
    @Override
    public void eventoCargarData() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uxbtnMovimientos.setOnAction((ActionEvent event) -> {
            eventoMovimiento();
        });
        uxbtnDetalles.setOnAction((ActionEvent event) -> {
            eventoDetalles();
        });
    }
    
    public void eventoMovimiento() {
        getMainView().transicion(new MovimientosController());
    }
    
    public void eventoDetalles() {
           getMainView().transicion(new DetallesController());
    
    }
}
