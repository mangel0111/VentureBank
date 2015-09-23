/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.PostgreSQL;

import comun.entidades.CuentaBancaria;
import comun.entidades.EntidadBaseReferencia;
import comun.entidades.Movimientos;
import comun.entidades.TipoDeMovimiento;
import datos.contratos.IPsDaoMovimientos;
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
public class PsDaoMovimientos extends PsDaoBase<Movimientos> implements IPsDaoMovimientos {

    public PsDaoMovimientos(PsManejadorDao manejadorDao) {
        super(manejadorDao, Movimientos.class);
    }

    @Override
    protected Movimientos traducirRegistro(ResultSet resultSet) throws SQLException {
        Movimientos movimiento = new Movimientos();
        movimiento.setReferencia(resultSet.getString("referencia"));
        movimiento.setvalor(resultSet.getDouble("valor"));
        if (resultSet.getInt("cuenta") != 0) {
            movimiento.setCuenta(new CuentaBancaria());
            movimiento.getCuenta().setCuenta(resultSet.getInt("cuenta"));
        }
        return movimiento;
    }

    @Override
    protected String getNombreTabla() {
        return "movimientos";
    }

    @Override
    public Movimientos consultarPorId(Movimientos entidadAConsultar) throws SQLException {
        String query = String.format(
                "SELECT movimientos.*, "
                + " tipo_de_movimeintos.referencia AS tipo_referencia , "
                + " tipo_de_movimeintos.descripcion AS tipo_descripcion, "
                + " tipo_de_movimeintos.codigo AS tipo_codigo "
                + " FROM movimientos "
                + "LEFT JOIN movimientos ON (movimientos.tipodemovimiento = tipo.codigo) "
                + "WHERE cuenta = ?", getNombreTabla());
        PreparedStatement preparedStatement = manejadorDao.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, entidadAConsultar.getCuenta().getCuenta());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return traducirRegistro(resultSet);
        }

        return null;
    }

    @Override
    public boolean insertar(Movimientos entidadNueva) throws SQLException {
        try {
            String query = String.format(
                    "INSERT INTO  movimientos "
                    + "(referencia, valor, cuenta) "
                    + "VALUES (?, ?, ?) RETURNING id", getNombreTabla());
            PreparedStatement preparedStatement = manejadorDao.getConnection().prepareStatement(query);

            preparedStatement.setString(1, entidadNueva.getReferencia());
            preparedStatement.setDouble(2, entidadNueva.getValor());
            preparedStatement.setInt(3, entidadNueva.getCuenta().getCuenta());

            return insertarConId(entidadNueva, preparedStatement);
        } catch (SQLException ex) {
            Logger.getLogger(PsDaoCuenta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean modificar(Movimientos entidadAModificar) throws SQLException {
        try {
            String query = String.format(
                    "UPDATE movimiento "
                    + "SET valor=?, tipodemovimiento=?, referencia=? "
                    + "WHERE cuenta=?", getNombreTabla());
            PreparedStatement preparedStatement = manejadorDao.getConnection().prepareStatement(query);

            preparedStatement.setDouble(1, entidadAModificar.getValor());
            preparedStatement.setInt(2, entidadAModificar.gettipoDeMovimiento().getCodigo());
          
            preparedStatement.setString(3, entidadAModificar.getReferencia());
  preparedStatement.setInt(4, entidadAModificar.getCuenta().getCuenta());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException ex) {
            Logger.getLogger(PsDaoCuenta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Movimientos> consultarPorCuenta(Integer cuenta) throws SQLException {
        try {

            String query = String.format("SELECT * FROM %s WHERE cuenta = ?", getNombreTabla());

            PreparedStatement preparedStatement = manejadorDao.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, cuenta);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Movimientos> listadoRetorno = new ArrayList<>();
            while (resultSet.next()) {
                listadoRetorno.add(traducirRegistro(resultSet));
            }
            return listadoRetorno;
        } catch (SQLException ex) {
            Logger.getLogger(PsDaoBase.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            //  throw new ExcepcionDatosBase(null, ex);
        }
    }

}
