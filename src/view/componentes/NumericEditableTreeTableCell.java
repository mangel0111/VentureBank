/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import java.text.NumberFormat;
import java.util.Locale;
import javafx.scene.control.TextField;

/**
 *
 * @author Ana
 */
public class NumericEditableTreeTableCell<S extends Object> extends AbstractEditableTreeTableCell<S, Double> {

    private final NumberFormat formatter;
    private final int fractionDigits;

    public NumericEditableTreeTableCell(int fractionDigits) {

        this.fractionDigits = fractionDigits;

        formatter = NumberFormat.getInstance(Locale.getDefault(Locale.Category.FORMAT));
        formatter.setMaximumFractionDigits(fractionDigits);
        formatter.setMinimumFractionDigits(fractionDigits);
        formatter.setGroupingUsed(true);
    }

    @Override
    public TextField generateNewTextField() {

        NumberField textField = new NumberField();
        textField.setFractionDigits(fractionDigits);
        textField.setValue(getItem());
        return textField;
    }

    @Override
    protected void commitHelper(boolean losingFocus) {
        commitEdit(((NumberField) getTextField()).getValue());
    }

    @Override
    protected String getString() {
        return formatter.format(getItem());
    }
}
