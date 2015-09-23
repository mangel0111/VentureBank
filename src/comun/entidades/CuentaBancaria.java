/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comun.entidades;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author miguelangel
 */
public class CuentaBancaria extends EntidadBaseReferencia{
    
     private final IntegerProperty cuenta = new SimpleIntegerProperty();
   
    private final ObjectProperty<Date> Fecha= new SimpleObjectProperty<>();
    
     public Date getFecha() {
        return Fecha.get();
    }

    public void setFecha(Date fecha) {
        this.Fecha.set(fecha);
    }
    
     public Integer getCuenta() {
        return cuenta.get();
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta.set(cuenta);
    }
    
    public IntegerProperty cuentaProperty() {
        return cuenta;
    }
    
     public ObjectProperty<Date> fechaProperty() {
        return Fecha;
    }
    
}
