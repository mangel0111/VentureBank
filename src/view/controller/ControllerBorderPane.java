/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author miguelangel
 */
public abstract class ControllerBorderPane extends controllerBase{

   
    @FXML
    private final Label uxlblTitulo = new Label();
    private StringProperty tituloVentana = new SimpleStringProperty();
    private final BorderPane uxbpnBorderPane = new BorderPane();
    private final HBox uxhbxBotonera = new HBox();

    public ControllerBorderPane() {

        //Seteo de titulo
        AnchorPane.setTopAnchor(uxlblTitulo, 0.0);
        AnchorPane.setLeftAnchor(uxlblTitulo, 0.0);
        AnchorPane.setBottomAnchor(uxlblTitulo, 0.0);
        //    uxlblTitulo.setFont(Font.font(25));

        uxlblTitulo.textProperty().bind(tituloVentana);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);
        
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0f);
        
        Lighting l = new Lighting();
        l.setLight(light);
        l.setSurfaceScale(5.0f);
        
        
        Text t = new Text();
        t.setEffect(l);
        t.setCache(true);
        t.setX(10.0f);
        t.setY(50.0f);
        t.setFill(Color.STEELBLUE);
        t.setText("Venture Bank");
        t.setFont(Font.font(null, FontWeight.BOLD, 46));
        
         
//         uxlblTitulo.textProperty().bind();
        //AquaFx.createLabelStyler().style(uxlblTitulo);
        //uxlblTitulo.getStyleClass().add("label2");
        //     uxlblTitulo.setId("fancytext");
        //Seteo de botonera
        AnchorPane.setTopAnchor(uxhbxBotonera, 0.0);
        AnchorPane.setRightAnchor(uxhbxBotonera, 0.0);
        AnchorPane.setBottomAnchor(uxhbxBotonera, 0.0);
        uxhbxBotonera.setSpacing(10);
        uxhbxBotonera.setAlignment(Pos.CENTER);

        AnchorPane uxapnVistaTop = new AnchorPane();
        uxapnVistaTop.getChildren().addAll(t);

        uxapnVistaTop.setPadding(new Insets(10));
        uxapnVistaTop.getStyleClass().add("titlePane");
        uxbpnBorderPane.setTop(uxapnVistaTop);

    }

    @Override
    public Node getView() {
        return uxbpnBorderPane;
    }

    protected HBox getBotonera() {
        return uxhbxBotonera;
    }

    public BorderPane getBorderPane() {
        return uxbpnBorderPane;
    }

    public void setLeft(Node node) {
        uxbpnBorderPane.setLeft(node);
    }

    public void setRight(Node node) {
        uxbpnBorderPane.setRight(node);
    }

    public void setCenter(Node node) {

        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        uxbpnBorderPane.setCenter(node);
    }

    public void setBottom(Node node) {
        uxbpnBorderPane.setBottom(node);
    }

    public String getTituloVentana() {
        return tituloVentana.get();
    }

    public void setTituloVentana(String tituloVentana) {
        this.tituloVentana.set(tituloVentana);
    }

    @Override
    public void eventoSalir() {
        super.eventoSalir(); //To change body of generated methods, choose Tools | Templates.
        getMainView().cerrarVista();
    }
    
}
