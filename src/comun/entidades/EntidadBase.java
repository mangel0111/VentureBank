/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comun.entidades;

import java.util.Objects;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author miguelangel
 */
public abstract class EntidadBase {
    
    private final LongProperty id = new SimpleLongProperty();

    /**
     *
     * @return el identificador unico
     */
    public long getId() {
        return id.get();
    }

    /**
     * Asigna el identificador unico
     *
     * @param id el identificador unico, si es invalido, asigna el valor vacio
     */
    public void setId(long id) {
        this.id.set(id <= 0 ? 0 : id);
    }

    public LongProperty idProperty() {
        return id;
    }

    public boolean isIdVacio() {
        return getId() <= 0;
    }
   private final StringProperty referencia = new SimpleStringProperty();
    private final StringProperty descripcion = new SimpleStringProperty();

    public String getReferencia() {
        return referencia.get();
    }

    public void setReferencia(String referencia) {
        this.referencia.set(referencia == null || referencia.isEmpty() ? null : referencia);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion == null || descripcion.isEmpty() ? null : descripcion);
    }

    public boolean isIdIgual(EntidadBase entidadBase) {
        if (entidadBase == null || this.isIdVacio() || entidadBase.isIdVacio()) {
            return false;
        } else {
            return this.getId() == entidadBase.getId();
        }
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (getClass() != object.getClass()) {
            return false;
        }

        return isIdIgual((EntidadBase) object);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id.get());
        return hash;
    }

    public double round(double value, int decimalPlaces) {

        if (decimalPlaces < 0) {
            throw new IllegalArgumentException();
        }

        double p = Math.pow(10d, decimalPlaces);
        return Math.round(value * p) / p;

    }

    
}
