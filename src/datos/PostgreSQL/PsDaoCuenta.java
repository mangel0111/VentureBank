/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.PostgreSQL;

import comun.entidades.CuentaBancaria;
import comun.entidades.EntidadBaseReferencia;
import datos.contratos.IPsDaoCuenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miguelangel
 */
public class PsDaoCuenta extends PsDaoBase<CuentaBancaria> implements IPsDaoCuenta {

    public PsDaoCuenta(PsManejadorDao manejadorDao) {
        super(manejadorDao, CuentaBancaria.class);
    }

    @Override
    protected CuentaBancaria traducirRegistro(ResultSet resultSet) throws SQLException {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setReferencia(resultSet.getString("referencia"));
        cuenta.setCuenta(resultSet.getInt("cuenta"));
        cuenta.setFecha(resultSet.getTimestamp("fecha"));
        return cuenta;
    }

    @Override
    protected String getNombreTabla() {
        return "Cuenta";
    }

    @Override
    public CuentaBancaria consultarPorId(CuentaBancaria entidadAConsultar) throws SQLException {
        try {
            String query = String.format("SELECT * FROM %s WHERE cuenta=?", getNombreTabla());
            PreparedStatement preparedStatement = manejadorDao.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, entidadAConsultar.getCuenta());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return traducirRegistro(resultSet);
            } else {
                return null;
            }
        } catch (SQLException ex) {     Logger.getLogger(PsDaoCuenta.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean insertar(CuentaBancaria entidadNueva) throws SQLException {
        try {
            String query = String.format(
                    "INSERT INTO  cuenta "
                    + "(referencia, fecha, cuenta) "
                    + "VALUES (?, ?, ?) RETURNING id", getNombreTabla());
            PreparedStatement preparedStatement = manejadorDao.getConnection().prepareStatement(query);

            preparedStatement.setString(1, entidadNueva.getReferencia());
            preparedStatement.setInt(2, entidadNueva.getCuenta());
            setDateOnPreparedStatement(3, preparedStatement, entidadNueva.getFecha());

            return insertarConId(entidadNueva, preparedStatement);
        } catch (SQLException ex) {
            Logger.getLogger(PsDaoCuenta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean modificar(CuentaBancaria entidadAModificar) throws SQLException {
        try {
            String query = String.format(
                    "UPDATE cuenta "
                    + "SET referencia=?, fecha=? "
                    + "WHERE cuenta=?", getNombreTabla());
            PreparedStatement preparedStatement = manejadorDao.getConnection().prepareStatement(query);
            preparedStatement.setString(1, entidadAModificar.getReferencia());
            setDateOnPreparedStatement(2, preparedStatement, entidadAModificar.getFecha());
            preparedStatement.setInt(3, entidadAModificar.getCuenta());

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException ex) {
            Logger.getLogger(PsDaoCuenta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
