/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.daosBase;

import comun.configuracion.BdConfigBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public abstract class ManejadorDaoBaseBd {

    /**
     * La <code>BdConfigBase</code> que contiene los datos necesarios para
     * establecer una conexion
     */
    private final BdConfigBase bdConfigBase;
    /**
     * El objeto donde se manipula la conexion
     */
    private Connection objetoConexion;

    /**
     * Constructor
     *
     * @param bdConfigBase configuracion que contiene los datos necesarios para
     * establecer una conexion
     */
    public ManejadorDaoBaseBd(BdConfigBase bdConfigBase) {
        this.bdConfigBase = bdConfigBase;
        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ManejadorDaoBaseBd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo que retorna un objeto de conexion con la base de datos, este
     * objeto es unico y se crea uno por cada Manejador de BD creado,
     * dependiendo de la configuracion, retorna la donexion al manejador
     * especificado
     *
     * @return <code>Connection</code> a la BD especificada en el constructor
     * @throws ExcepcionFuenteInaccesible si la base de datos no es accesible
     */
    public Connection getConnection() {
        if (objetoConexion == null) {
            try {
                objetoConexion = DriverManager.getConnection("jdbc:postgresql:" + 
                        bdConfigBase.getHost() + 
                        bdConfigBase.getDataBase(),
                        "MiguelUser", "qazwsxedcrfv");
            } catch (SQLException ex) {
                Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return objetoConexion;
    }

    /**
     * Ejecuta una consulta a la base de datos
     *
     * @param command serie de operaciones a ejecutar
     * @return el objeto de la consulta
     * @throws ExcepcionDatosBase si ocurre un error
     */
    protected Object ejecutarConsultaBase(IComandoDao command) {
        try {
            Object resultado = command.ejecutar(this);
            return resultado;
        } catch (Exception ex) {

            Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
    }

    /**
     * Ejecuta una transaccion en base de datos, la cual realiza un rollback si
     * ocurre alguna excepcion al ejecutar
     *
     * @param command serie de operaciones a ejecutar
     * @return el objeto de resultado de la operacion
     * @throws ExcepcionDatosBase si ocurre un error al ejecutar la operacion
     */
    protected Object ejecutarTransaccionBase(IComandoDao command) {
        return ejecutarConsultaBase(new IComandoDao() {

            @Override
            public <T extends ManejadorDaoBaseBd> Object ejecutar(T manejadorDao) {
                try {
                    getConnection().setAutoCommit(false);
                    Object retorno = command.ejecutar(ManejadorDaoBaseBd.this);
                    getConnection().commit();
                    return retorno;
                } catch (SQLException ex) {

                    try {
                        getConnection().rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                    return null;
                } finally {
                    try {
                        getConnection().setAutoCommit(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(PsManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                }
            }
        });
    }
}
