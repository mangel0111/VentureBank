/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.contratos;

import java.sql.SQLException;


/**
 *
 * @author user
 */
public interface IPsComandoDao {

    public Object ejecutar(IPsManejadorDao manejadorDao) throws SQLException;
}
