/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comun.entidades;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author miguelangel
 */
public class Movimientos extends EntidadBaseReferencia {

    private final ObjectProperty<TipoDeMovimiento> tipoDeMovimiento = new SimpleObjectProperty<>();
    private final ObjectProperty<CuentaBancaria> cuenta = new SimpleObjectProperty<>();

    private final DoubleProperty valor = new SimpleDoubleProperty();

    public TipoDeMovimiento gettipoDeMovimiento() {
        return tipoDeMovimiento.get();
    }

    public void settipoDeMovimiento(TipoDeMovimiento tipoDeMovimiento) {
        this.tipoDeMovimiento.set(tipoDeMovimiento);
    }

    public CuentaBancaria getCuenta() {
        return cuenta.get();
    }

    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta.set(cuenta);
    }

    public double getValor() {
        return valor.get();
    }

    public void setvalor(double valor) {
        this.valor.set(valor);
    }

    public ObjectProperty<TipoDeMovimiento> tipodeMovimientoProperty() {
        return tipoDeMovimiento;
    }

    public ObjectProperty<CuentaBancaria> cuentaProperty() {
        return cuenta;
    }

    public DoubleProperty valorProperty() {
        return valor;
    }
}
