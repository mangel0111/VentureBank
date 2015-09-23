/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.daosBase;

import comun.configuracion.BdConfigBase;
import comun.entidades.EntidadBaseReferencia;
import datos.contratos.IPsManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class PsManejadorBaseDeDatos implements IPsManejadorBaseDeDatos {

    protected Connection connection = null;
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;
    protected final String prefijo;

    public PsManejadorBaseDeDatos(String prefijo, BdConfigBase bdConfig) {
        this.prefijo = prefijo;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql:" + bdConfig.getHost()
                    + "PostgreConfig", "MiguelUser", "qazwsxedcrfv");
        } catch (SQLException ex) {
            Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<EntidadBaseReferencia> consultarTodos() {
        ArrayList<EntidadBaseReferencia> listadoResultado = new ArrayList<>();
        try {
            String query
                    = "SELECT datname FROM pg_database "
                    + "WHERE datistemplate = false "
                    + "AND datname LIKE '" + prefijo + "%'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EntidadBaseReferencia nodo = new EntidadBaseReferencia();
                nodo.setReferencia(resultSet.getString(1));
                nodo.setDescripcion(resultSet.getString(1).replace(prefijo, ""));

                listadoResultado.add(nodo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return listadoResultado;
    }

    @Override
    public EntidadBaseReferencia consultarPorId(EntidadBaseReferencia entidadAConsultar) {
        try {
            String query
                    = "SELECT datname FROM pg_database "
                    + "WHERE datistemplate = false "
                    + "AND datname = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, entidadAConsultar.getDescripcion());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                 EntidadBaseReferencia nodo = new EntidadBaseReferencia();
               nodo.setDescripcion(resultSet.getString(1).replace(prefijo, ""));

                return nodo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException ex) {
                // Logger.getLogger(DlPsManejadorBaseDeDatos.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
                Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean insertar(EntidadBaseReferencia entidadNueva) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(EntidadBaseReferencia entidadAModificar) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(EntidadBaseReferencia entidadAEliminar) throws SQLException {
        try {
            String query = "DROP DATABASE IF EXISTS ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, entidadAEliminar.getDescripcion());
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException ex) {
            Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }

   

}
