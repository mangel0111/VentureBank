/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author miguelangel
 */
public abstract class controllerBase extends AnchorPane {

    public venturebank.VentureController getMainView() {
        return venturebank.VentureBank.getInstance();
    }

    public Scene getMainScene() {
        return venturebank.VentureBank.getScene();
    }

    public Scene rechargeScene(Scene scene) {
        return scene;
    }

    public abstract Node getView();

    public Node getView(URL url) {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setResources(null);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {

            return fxmlLoader.load(url.openStream());
        } catch (IOException ex) {
            Logger.getLogger(controllerBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void cargarLookAndFeel() {

    }

    protected void ManejarExcepcion(Exception ex) {
    //    getMainView().manejarExcepcion(ex);
    }

    public void eventoSalir() {
        getMainView().cerrarVista();
    }

    public static void recordWithTimestamp(String mensaje) {
        System.out.println(new java.sql.Timestamp(new java.util.Date().getTime()) + " : " + mensaje);
    }

    protected boolean preguntarSiDeseaBorrar() {
        return true;
    }

    public abstract void eventoCargarData();

    public void eventoRecargarData() {
        eventoCargarData();
    }

    public static <S> void agregarAlPrincipioNull(List<S> listado) {
        if (listado != null && (listado.isEmpty() || listado.get(0) != null)) {
            listado.add(0, null);
        }
    }

    protected Date getValueOfLocalDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        } else {
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            return Date.from(instant);
        }
    }

    protected LocalDate getLocalDateOfDate(Date date) {
        if (date == null) {
            return null;
        } else {
            if (date instanceof java.sql.Date) {
                return ((java.sql.Date) date).toLocalDate();
            } else {
                return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }
        }
    }
    
}
