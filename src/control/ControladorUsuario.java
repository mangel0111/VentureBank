/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import comun.configuracion.ManejadorDeConfiguracion;
import comun.entidades.Usuario;
import datos.FabricadorDao;
import datos.contratos.IPsManejadorDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author miguelangel
 */
public class ControladorUsuario {
     public static List<Usuario> consultarTodos() throws SQLException {
        return (List<Usuario>) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarConsulta((IPsManejadorDao manejadorDao) -> {
                    return manejadorDao.getDaoUsuario().consultarTodos();
                });
    }

    
}
