/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.contratos;

import comun.entidades.Movimientos;
import datos.daosBase.IDaoBase;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author miguelangel
 */
public interface IPsDaoMovimientos extends IDaoBase<Movimientos>{
    
    List<Movimientos> consultarPorCuenta(Integer cuenta) throws SQLException;
    
}
