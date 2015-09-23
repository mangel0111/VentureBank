/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/**
 *
 * @author Ana
 */
public class NumberField extends TextField {

    private final DoubleProperty doubleProperty = new SimpleDoubleProperty();
    private final IntegerProperty fractionProperty = new SimpleIntegerProperty();
    private final DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault(Locale.Category.FORMAT));
    private final DecimalFormat formatterEdited = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault(Locale.Category.FORMAT));
    public boolean noMasComas = true;

    private boolean requiereCambioDouble = true;

    public NumberField() {
        super();
        this.setAlignment(Pos.TOP_RIGHT);

        formatter.setMaximumFractionDigits(0);
        formatter.setMinimumFractionDigits(0);
        formatter.setGroupingUsed(true);

        formatterEdited.setMaximumFractionDigits(0);
        formatterEdited.setMinimumFractionDigits(0);
        formatterEdited.setGroupingUsed(false);

        /**
         * Add a ChangeListener to this fields textProperty so that the
         * associated DoubleProperty is changed whenever this fields text
         * changes.
         */
        textProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    try {
                        if (requiereCambioDouble) {
                            valueProperty().set(getFormatter().parse(newValue).doubleValue());
                        }
                    } catch (NumberFormatException | ParseException ex) {
                        Logger.getLogger(NumberField.class.getName()).log(Level.SEVERE, null, ex);
                        /**
                         * Do nothing. newValue must not be able to be parsed as
                         * a double.
                         *
                         * There's a "better" way to check if newValue is
                         * parsable by Double.valueOf that is outlined at
                         * http://download.java.net/jdk8/docs/api/java/lang/Double.html#valueOf-java.lang.String-
                         * but it's simpler to catch the exception unless
                         * serious drawbacks to this approach are found.
                         */
                    }
                });

        /**
         * Add a ChangeListener to this fields associated DoubleProperty to
         * change this fields text whenever the associated DoubleProperty is
         * changed.
         */
        valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            requiereCambioDouble = false;
            setText(getFormatter().format(newValue));
            requiereCambioDouble = true;
        });

        focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            requiereCambioDouble = false;
            actualizarTexto();
            requiereCambioDouble = true;
        });

        fractionProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (oldValue != newValue) {
                requiereCambioDouble = true;
            }
            actualizarTexto();
            requiereCambioDouble = false;
        });

        fractionProperty().set(0);
        setText("0");
    }

    /**
     * Returns the DoubleProperty that is linked with this field.
     *
     * @return this fields DoubleProperty
     *
     *
     */
    public final DoubleProperty valueProperty() {
        return doubleProperty;
    }

    @Override
    public void replaceText(int i, int il, String string) {

        if (string.matches("[0-9]") || string.isEmpty() || string.matches(",")) {
            super.replaceText(i, il, string);
        }
//        else if (string.matches(",")) {
//            actualizarTexto();
//         
//        }
    }

    /**
     *
     * @return
     */
    public final Double getValue() {
        return doubleProperty.get();
    }

    /**
     *
     * @param value
     */
    public void setValue(Double value) {

        doubleProperty.set(value);
    }

    /**
     * Returns the DoubleProperty that is linked with this field.
     *
     * @return this fields DoubleProperty
     */
    public final IntegerProperty fractionProperty() {
        return fractionProperty;
    }

    /**
     *
     * @param fractionDigits
     */
    public void setFractionDigits(int fractionDigits) {
        formatter.setMaximumFractionDigits(fractionDigits);
        formatter.setMinimumFractionDigits(fractionDigits);

        formatterEdited.setMaximumFractionDigits(fractionDigits);
        formatterEdited.setMinimumFractionDigits(fractionDigits);

        fractionProperty.set(fractionDigits);

    }

    public int getFractionDigits() {
        return fractionProperty.get();
    }

    private DecimalFormat getFormatter() {
        if (focusedProperty().get()) {
            return formatterEdited;
        } else {
            return formatter;
        }
    }

    private void actualizarTexto() {

        setText(getFormatter().format(getValue()));

    }
}
