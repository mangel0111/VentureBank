/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import comun.entidades.EntidadBase;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;

/**
 *
 * @author Ana
 * @param <T>
 */
public class ControllerTreeTable<T extends EntidadBase> extends TreeTableView<T> {

    protected TreeItem<T> root;
    private ObservableList<T> listado;
    private Set<T> listadoModificado = new HashSet<>();

    public ControllerTreeTable() {

        this.editableProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            listadoModificado = new HashSet<>();
        });
    }

    public Set<T> getListadoModificado() {
        return listadoModificado;
    }

    public void setListadoModificado(Set<T> listadoModificado) {
        this.listadoModificado = listadoModificado;
    }

    protected T getEntidadDeEvento(TreeTableColumn.CellEditEvent<T, ?> evento) {
        return evento.getTreeTableView().getSelectionModel().getSelectedItem().getValue();
    }

    public ObservableList<T> getListado() {
        return listado;
    }

    public void setListado(ObservableList<T> listado) {
        this.listado = listado;
    }

    protected Callback<TreeTableColumn<T, Date>, TreeTableCell<T, Date>> generarDateCallBack() {
        return new Callback<TreeTableColumn<T, Date>, TreeTableCell<T, Date>>() {
            @Override
            public TreeTableCell call(TreeTableColumn p) {
                DatePickerCell textField = new DatePickerCell();
                textField.setAlignment(Pos.TOP_CENTER);
                return textField;
            }
        };
    }

    protected Callback<TreeTableColumn<T, String>, TreeTableCell<T, String>> generarImageCell(String rutaImagenes) {
        return new Callback<TreeTableColumn<T, String>, TreeTableCell<T, String>>() {
            @Override
            public TreeTableCell<T, String> call(TreeTableColumn<T, String> param) {
                ImageCell editableCell = new ImageCell(rutaImagenes);
                editableCell.setAlignment(Pos.CENTER);
                return editableCell;
            }
        };
    }
    
    protected Callback<TreeTableColumn<T, String>, TreeTableCell<T, String>> GenerarListado(List<String> listado) {
        return new Callback<TreeTableColumn<T, String>, TreeTableCell<T, String>>() {
            @Override
            public TreeTableCell<T, String> call(TreeTableColumn<T, String> param) {
                ListCell editableCell = new ListCell(listado);
                editableCell.setAlignment(Pos.CENTER);
                return editableCell;
            }
        };
    }

   protected Callback<TreeTableColumn<T, Integer>, TreeTableCell<T, Integer>> generarIntegerCenterCallBack() {
        return new Callback<TreeTableColumn<T, Integer>, TreeTableCell<T, Integer>>() {
            @Override
            public TreeTableCell<T, Integer> call(TreeTableColumn<T, Integer> param) {
             TreeTableCell cell = new TreeTableCell<T, Integer>() {

                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        if (item == null) {
                            setText(null);
                        } else {
                            setText(String.valueOf(item));
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        };
    }
    
    
    protected Callback<TreeTableColumn<T, Double>, TreeTableCell<T, Double>> generarNumericCallBack(int numeroDeDecimales) {
        return (TreeTableColumn<T, Double> param) -> {
            NumericEditableTreeTableCell textField = new NumericEditableTreeTableCell(numeroDeDecimales);
            textField.setAlignment(Pos.TOP_RIGHT);
            return textField;
        };
    }
}
