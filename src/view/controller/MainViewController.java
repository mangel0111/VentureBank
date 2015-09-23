/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ana
 */
public class MainViewController implements Initializable {
    
    private final ArrayList<ControllerBorderPane> listadoVistas = new ArrayList<>();
    private Stage stage;
    private final VistaPrincipalController vistaInicialController;
    @FXML
    private BorderPane uxPnMainPane;
    
    public MainViewController(VistaPrincipalController vistaInicialController) {
        this.vistaInicialController = vistaInicialController;
    }
    
    void setView(Node view) {
        this.uxPnMainPane.centerProperty().set(view);
        
        FadeTransition ft = new FadeTransition(Duration.millis(550), view);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.play();
    }
    
    private Node getView() {
        return this.uxPnMainPane.centerProperty().get();
    }
    
    void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setFlowPane(FlowPane flowPane) {
        //     this.flowPane = flowPane;
    }
    
    public void cerrarVista() {
        this.uxPnMainPane.centerProperty().set(vistaInicialController.getView());
        listadoVistas.clear();
    }
    
    public void setVista(ControllerBorderPane controladorBase) {
//        cerrarVista();
        setView(controladorBase.getView());
        controladorBase.eventoCargarData();
        controladorBase.cargarLookAndFeel();
        listadoVistas.add(controladorBase);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.uxPnMainPane.centerProperty().set(vistaInicialController.getView());
       
        cargarData();
    }
    
    public void cargarData() {
        
    }
    
}
