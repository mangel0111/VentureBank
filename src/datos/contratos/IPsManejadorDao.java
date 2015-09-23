/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.contratos;

import java.sql.SQLException;

/**
 *
 * @author miguelangel
 */
public interface IPsManejadorDao {
   Object ejecutarConsulta(IPsComandoDao comandoDao) throws SQLException;

    Object ejecutarTransaccion(IPsComandoDao comandoDao) throws SQLException;
  
    IPsDaoUsuario getDaoUsuario();   
    IPsDaoCuenta getDaoCuenta();   
    IPsDaoMovimientos getDaoMovimientos();   
    IPsDaoTipoDeMovimientos getDaoTipoMov();   
}
