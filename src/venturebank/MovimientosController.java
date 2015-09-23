/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venturebank;

import comun.entidades.CuentaBancaria;
import comun.entidades.Movimientos;
import control.ControladorCuentaBancaria;
import control.ControladorMovimientos;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import view.controller.ControllerBorderPane;

/**
 * FXML Controller class
 *
 * @author miguelangel
 */
public class MovimientosController extends ControllerBorderPane implements Initializable {

    @FXML
    private ComboBox<String> uxtxtCuenta, UxcmbTIPO;
    @FXML
    private TextField uxtxtNombre, uxtxtxvalor;
    @FXML
    private DatePicker uxtxtFecha;
    @FXML
    private ListView<String> uxttcMovimiento;
    @FXML
    private Button uxbtnInsertar, uxbtnEliminar, uxbtnModificar, uxbtnGuardar;
    public List<CuentaBancaria> listadoCuenta = FXCollections.observableArrayList();

    public MovimientosController() {
        setCenter(getView(MovimientosController.class.getResource("FXMLMovimientos.fxml")));
        uxtxtCuenta.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            seteoCuenta();
        });

        uxbtnModificar.setOnAction((ActionEvent event) -> {
            eventoModificar();
        });

        uxbtnEliminar.setOnAction((ActionEvent event) -> {
            eventoEliminar();
        });

        uxbtnInsertar.setOnAction((ActionEvent event) -> {
            insertarMovimiento();
        });
        uxbtnGuardar.setOnAction((ActionEvent event) -> {
            guardarMovimiento();
        });
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UxcmbTIPO.setVisible(false);
        uxtxtxvalor.setVisible(false);
        uxbtnGuardar.setVisible(false);
    }

    @Override
    public void eventoCargarData() {
        UxcmbTIPO.setVisible(false);
        uxtxtxvalor.setVisible(false);
        uxbtnGuardar.setVisible(false);
        try {
            List<CuentaBancaria> listadoDeCuentas = FXCollections
                    .observableArrayList();
            ObservableList<String> listadoDeCuentas2 = FXCollections
                    .observableArrayList();
            listadoDeCuentas = ControladorCuentaBancaria.consultarTodos();
            listadoCuenta = listadoDeCuentas;
            for (CuentaBancaria nodo : listadoDeCuentas) {
                listadoDeCuentas2.add(nodo.getCuenta().toString());
            }
            uxtxtCuenta.setItems(listadoDeCuentas2);
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eventoModificar() {
        try {
            CuentaBancaria nueva = new CuentaBancaria();
            nueva.setCuenta(Integer.valueOf(uxtxtCuenta.getValue()));
            nueva.setReferencia(uxtxtNombre.getText());
            ControladorCuentaBancaria.modificar(nueva);
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eventoEliminar() {
        try {
            CuentaBancaria nueva = new CuentaBancaria();
            nueva.setCuenta(Integer.valueOf(uxtxtCuenta.getValue()));
            nueva.setReferencia(uxtxtNombre.getText());
            ControladorCuentaBancaria.eliminar(nueva);
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void seteoCuenta() {
        try {
            CuentaBancaria nueva = new CuentaBancaria();
            nueva.setCuenta(Integer.valueOf(uxtxtCuenta.getValue()));
            nueva = ControladorCuentaBancaria.consultarPorCodigo(nueva);
            uxtxtNombre.setText(nueva.getReferencia());
            uxtxtFecha.setValue(getLocalDateOfDate(nueva.getFecha()));
            List<Movimientos> listadoMov = FXCollections.observableArrayList();
            ObservableList<String> listadoMov2 = FXCollections.observableArrayList();
            listadoMov = ControladorMovimientos.consultarPorCodigo(nueva.getCuenta());
            for (Movimientos nodo : listadoMov) {
                listadoMov2.add(nodo.getReferencia() + " - " + nodo.getValor());
            }
            uxttcMovimiento.setItems(listadoMov2);
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertarMovimiento() {
        try {
            uxtxtxvalor.setVisible(true);
            List<Movimientos> listadoMov = ControladorMovimientos.consultarTodos();
            ObservableList<String> list2 = FXCollections.observableArrayList();
            for (Movimientos nodo : listadoMov) {
                list2.add(nodo.getReferencia());
            }
            UxcmbTIPO.setItems(list2);
            UxcmbTIPO.setVisible(true);
            uxbtnGuardar.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarMovimiento() {
        try {
            Movimientos nuevo = new Movimientos();
            nuevo.setCuenta(new CuentaBancaria());
            nuevo.setReferencia(UxcmbTIPO.getValue());
            nuevo.getCuenta().setCuenta(Integer.valueOf(uxtxtCuenta.getValue()));
            nuevo.setvalor(Integer.valueOf(uxtxtxvalor.getText()));

            ControladorMovimientos.insertar(nuevo);
            eventoCargarData();
            seteoCuenta();
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
