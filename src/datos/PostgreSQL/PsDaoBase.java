/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.PostgreSQL;

import comun.entidades.EntidadBaseReferencia;
import datos.contratos.IPsDaoBase;
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
 * @param <T>
 */
public abstract class PsDaoBase<T extends EntidadBaseReferencia> implements IPsDaoBase<T> {

    protected final PsManejadorDao manejadorDao;
    protected final Class<T> daoClass;

    public PsDaoBase(PsManejadorDao manejadorDao, Class<T> daoClass) {
        this.manejadorDao = manejadorDao;
        this.daoClass = daoClass;
    }

    protected abstract T traducirRegistro(ResultSet resultSet) throws SQLException;

    protected abstract String getNombreTabla();

    @Override
    public List<T> consultarTodos() throws SQLException {
        try {
            String query;
            if (daoClass.getClass().equals(EntidadBaseReferencia.class)) {
                query = String.format("SELECT * FROM %s ORDER BY Referencia", getNombreTabla());
            } else {
                query = String.format("SELECT * FROM %s", getNombreTabla());
            }
            PreparedStatement preparedStatement = manejadorDao.getConnection().prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> listadoRetorno = new ArrayList<>();
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

    @Override
    public boolean eliminar(T entidadAEliminar) throws SQLException {
        try {
            String query = String.format("DELETE FROM %s  WHERE referencia=?", getNombreTabla());
            PreparedStatement preparedStatement = manejadorDao.getConnection().prepareStatement(query);
            preparedStatement.setString(1, entidadAEliminar.getReferencia());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException ex) {
            Logger.getLogger(PsDaoBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    protected void setDateOnPreparedStatement(int parameterIndex, PreparedStatement preparedStatement, java.util.Date date) throws SQLException {
        if (date != null) {
            preparedStatement.setDate(parameterIndex, new java.sql.Date(date.getTime()));
        } else {
            preparedStatement.setNull(parameterIndex, java.sql.Types.DATE);
        }
    }
    
     protected boolean insertarConId(EntidadBaseReferencia entidadNueva, PreparedStatement preparedStatement) throws SQLException {
        try {
            ResultSet generatedKeys = preparedStatement.executeQuery();
            if (generatedKeys.next()) {
                entidadNueva.setId(generatedKeys.getLong(1));
                return true;
            }
            //    Logger.getLogger(EmPsDaoEmpresa.class.getName()).log(Level.SEVERE, null, ex);
            return false;
            // throw new ExcepcionDatosBase(null, null);
        } catch (SQLException ex) {
            Logger.getLogger(PsDaoBase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
            //  throw FabricaExcepcionDatosBase.fabricarExcepcion(ex);
        }
    }
}
