/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.daosBase;

import java.sql.SQLException;

/**
 *
 * @author Ana
 */
public interface IComandoDao {

    /**
     * Metodo donde se define la serie de operaciones a ser ejecutadas en
     * conjunto
     *
     * @param <T> un parametro que extiende de ManejadorDaoBase
     * @param manejadorDao el manejador dao
     * @return un objeto el cual debe castearse
     * @throws java.sql.SQLException
     */
    <T extends ManejadorDaoBaseBd> Object ejecutar(T manejadorDao) throws SQLException;

}
