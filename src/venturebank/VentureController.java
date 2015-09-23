/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venturebank;

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
import view.controller.ControllerBorderPane;
import view.controller.VistaPrincipalController;

/**
 *
 * @author miguelangel
 */
public class VentureController implements Initializable {

    private final ArrayList<ControllerBorderPane> listadoVistas = new ArrayList<>();
    public Stage stage;
    @FXML
    private BorderPane uxPnMainPane;
    private final VistaPrincipalController vistaInicialController;

    private FlowPane flowPane;

    public VentureController() {
        vistaInicialController = new VistaPrincipalController();
    }

    public void transicion(ControllerBorderPane controladorBase) {
        setView(controladorBase.getView());
        controladorBase.eventoCargarData();
        controladorBase.cargarLookAndFeel();
        listadoVistas.add(controladorBase);
    }

    private Node getView() {
        return this.uxPnMainPane.centerProperty().get();
    }

    void setView(Node view) {
        this.uxPnMainPane.centerProperty().set(view);

        FadeTransition ft = new FadeTransition(Duration.millis(550), view);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.play();
    }

    public void cerrarVista() {
        this.uxPnMainPane.centerProperty().set(vistaInicialController.getView());
        listadoVistas.clear();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.uxPnMainPane.centerProperty().set(vistaInicialController.getView());

        eventoVenture();

// TODO
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setVista(ControllerBorderPane controladorBase) {
        setView(controladorBase.getView());
        controladorBase.eventoCargarData();
        controladorBase.cargarLookAndFeel();
        listadoVistas.add(controladorBase);
    }

    public void setFlowPane(FlowPane flowPane) {
        this.flowPane = flowPane;
    }

    public void devolverTransicion() {
        listadoVistas.remove(listadoVistas.size() - 1);
        setView(listadoVistas.get(listadoVistas.size() - 1).getView());

    }

    public void eventoVenture() {
        setVista(new venturebank.VenturePantalla());
    }

}
