/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venturebank;

import comun.entidades.Usuario;
import control.ControladorUsuario;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;
import view.controller.ControllerBorderPane;

/**
 *
 * @author miguelangel
 */
public class VenturePantalla extends ControllerBorderPane implements Initializable {

    @FXML
    private Button uxbtnEntrar, uxbtnCancelar;
    @FXML
    private TextField uxtxtUsuario;
    @FXML
    private PasswordField uxtxtPassword;
    @FXML
    private Label uxlbluser, uxlblpassword;
    @FXML
    private BorderPane uxPnMainPane;

    public VenturePantalla() {
        setRight(getView(VenturePantalla.class.getResource("FXMLVenturePrincipal.fxml")));

    }

    @Override
    public void eventoCargarData() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uxlbluser.setVisible(true);
        uxlblpassword.setVisible(true);
        uxbtnEntrar.setOnAction((ActionEvent event) -> {
            comprobarUsuario();
        });
        uxbtnCancelar.setOnAction((ActionEvent event) -> {
            eventoLimpiar();
        });
    }

    public void comprobarUsuario() {
        try {
            List<Usuario> listadoUsuarios = FXCollections.observableArrayList();
            listadoUsuarios = ControladorUsuario.consultarTodos();
            for (Usuario nodo : listadoUsuarios) {
                if (nodo.getReferencia().equals(uxtxtUsuario.getText())
                        && nodo.getPassword().equals(uxtxtPassword.getText())) {
                    eventoAutorizado();
                } else {
                    eventoLimpiar();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VenturePantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eventoLimpiar() {
        uxtxtUsuario.clear();
        uxtxtPassword.clear();
//        getMainView().cerrarVista();
    }

    public void eventoAutorizado() {
//       setCenter(getView(VenturePantalla.class.getResource("FXMLVentureMenu.fxml")));
        getMainView().transicion(new VentureMenu());
}

}
