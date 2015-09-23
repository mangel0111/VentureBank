/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import comun.configuracion.BdConfigBase;
import datos.PostgreSQL.PsManejadorDao;
import datos.contratos.IPsManejadorDao;
import datos.daosBase.IDaoBase;
import datos.daosBase.PsManejadorBaseDeDatos;
import java.sql.SQLException;

/**
 *
 * @author miguelangel
 */
public class FabricadorDao {

    public static IPsManejadorDao construirManejadorDao(BdConfigBase config) throws SQLException {
        return new PsManejadorDao(config);
    }

    public static IDaoBase construirManejadorBaseDeDatos(BdConfigBase bdConfig) throws SQLException {

        return new PsManejadorBaseDeDatos(" ",bdConfig);

    }

}
