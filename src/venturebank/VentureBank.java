/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venturebank;

import comun.configuracion.BdConfigBase;
import comun.configuracion.ManejadorDeConfiguracion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.controller.MainViewController;
import views.controller.ResizableCanvas;

/**
 *
 * @author miguelangel
 */
public class VentureBank extends Application {

    private static VentureController instance;
    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        setConfiguracionSiNoExiste();
        VentureBank.stage = stage;
        FXMLLoader mainViewLoader = new FXMLLoader(VentureController.class.getResource("FXMLVenture.fxml"));
        Parent mainView = (Parent) mainViewLoader.load();

        mainView.getStyleClass().add("main-view-pane");
        instance = mainViewLoader.getController();
        instance.setStage(stage);
        final FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.getStyleClass().add("alert-background-pane");
        instance.setFlowPane(flowPane);

        ResizableCanvas canvas = new ResizableCanvas();
        final StackPane stackPane = new StackPane();
        stackPane.getChildren().add(canvas);
        stackPane.getChildren().add(flowPane);  // initially hide the alert
        stackPane.getChildren().add(mainView);
        scene = new Scene(stackPane);
        scene.getStylesheets().add(VentureBank.class.getResource("fxmlmainview.css").toExternalForm());
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        canvas.widthProperty().bind(stackPane.widthProperty());
        canvas.heightProperty().bind(stackPane.heightProperty());
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.show();
    }
    
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static Scene getScene() {
        return scene;
    }

    public static Stage getStage() {
        return stage;
    }

    public static VentureController getInstance() {
        return instance;
    }

    private void setConfiguracionSiNoExiste() {
        ManejadorDeConfiguracion.getConfiguracionActual().setDataBase("ventureBank");
        ManejadorDeConfiguracion.getConfiguracionActual().setHost("//localhost:5432/");
        ManejadorDeConfiguracion.getConfiguracionActual().setServidorBd(BdConfigBase.TiposDeServidorBd.Postgre9_3);

    }

}
