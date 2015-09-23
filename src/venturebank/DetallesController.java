/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venturebank;

import comun.entidades.Movimientos;
import control.ControladorMovimientos;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import view.controller.ControllerBorderPane;

/**
 * FXML Controller class
 *
 * @author miguelangel
 */
public class DetallesController extends ControllerBorderPane implements Initializable {

    @FXML
    private ListView<String> uxlsvReporte;

    public DetallesController() {

        setCenter(getView(VentureMenu.class.getResource("FXMLDetalles.fxml")));

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void eventoCargarData() {
        try {
            List<Movimientos> listado1 = ControladorMovimientos.consultarTodos();
            ObservableList<String> listadoString = FXCollections.observableArrayList();

            int menor1 = 0;
            int menor2 = 0;
            int menor3 = 0;

            for (Movimientos nodo : listado1) {
                if (nodo.getValor() < 1000) {
                    menor1++;
                }
                if (nodo.getValor() > 1001 && nodo.getValor() < 5000) {
                    menor2++;
                }
                if (nodo.getValor() > 5001) {
                    menor3++;
                }
            }
            listadoString.add("Numero de Movimientos 0 - 1000:   " + String.valueOf(menor1));
            listadoString.add("Numero de Movimientos 1001 - 5000:  " + String.valueOf(menor2));
            listadoString.add("Numero de Movimientos >5001:   " + String.valueOf(menor3));
            uxlsvReporte.setItems(listadoString);

        } catch (SQLException ex) {
            Logger.getLogger(DetallesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
