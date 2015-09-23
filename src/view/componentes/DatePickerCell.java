/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.beans.binding.Bindings;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TreeTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Ana
 */
public class DatePickerCell<T> extends TreeTableCell<T, Date>  {
   private final DateFormat formatter;
    private final DatePicker datePicker; 

public DatePickerCell() {

        formatter = new SimpleDateFormat("dd/MM/yyyy");
        datePicker = new DatePicker();

        // Commit edit on Enter and cancel on Escape.
        // Note that the default behavior consumes key events, so we must 
        // register this as an event filter to capture it.
        // Consequently, with Enter, the datePicker's value won't yet have been updated, 
        // so commit will sent the wrong value. So we must update it ourselves from the
        // editor's text value.
        datePicker.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
                datePicker.setValue(datePicker.getConverter().fromString(datePicker.getEditor().getText()));
                commitEdit(getValueOfLocalDate(datePicker.getValue()));
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });

        // Modify default mouse behavior on date picker:
        // Don't hide popup on single click, just set date
        // On double-click, hide popup and commit edit for editor
        // Must consume event to prevent default hiding behavior, so
        // must update date picker value ourselves.
        // Modify key behavior so that enter on a selected cell commits the edit
        // on that cell's date.
        datePicker.setDayCellFactory(picker -> {
            DateCell cell = new DateCell();
            cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                datePicker.setValue(cell.getItem());
                if (event.getClickCount() == 2) {
                    datePicker.hide();
                    commitEdit(getValueOfLocalDate(cell.getItem()));
                }
                event.consume();
            });
            cell.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    commitEdit(getValueOfLocalDate(datePicker.getValue()));
                }
            });
            return cell;
        });

        contentDisplayProperty().bind(Bindings.when(editingProperty())
                .then(ContentDisplay.GRAPHIC_ONLY)
                .otherwise(ContentDisplay.TEXT_ONLY));
    }

    @Override
    public void updateItem(Date selectedDate, boolean empty) {
        super.updateItem(selectedDate, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(selectedDate == null ? null : formatter.format(selectedDate));
            setGraphic(datePicker);
        }
    }

    @Override
    public void startEdit() {
        super.startEdit();
        if (!isEmpty()) {
            datePicker.setValue(getItem().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
    }

    private Date getValueOfLocalDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        } else {
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            return Date.from(instant);
        }
    }

}
