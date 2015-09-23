/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import comun.configuracion.ManejadorDeConfiguracion;
import comun.entidades.Movimientos;
import datos.FabricadorDao;
import datos.contratos.IPsManejadorDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miguelangel
 */
public class ControladorMovimientos {
          public static List<Movimientos> consultarTodos() throws SQLException {
        return (List<Movimientos>) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarConsulta((IPsManejadorDao manejadorDao) -> {
                    return manejadorDao.getDaoMovimientos().consultarTodos();
                });
    }
    public static List<Movimientos> consultarPorCodigo(Integer cuenta) throws SQLException {
        return (List<Movimientos>) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarConsulta((IPsManejadorDao manejadorDao) -> {
                    return manejadorDao.getDaoMovimientos().consultarPorCuenta(cuenta);
                });
    }

    public static boolean insertar(Movimientos entidadAInsertar) throws SQLException {
        return (boolean) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarTransaccion((IPsManejadorDao manejadorDao) -> {
                    return manejadorDao.getDaoMovimientos().insertar(entidadAInsertar);
                });
    }
       public static boolean insertarListado(List<Movimientos> listadoAIsertar)  throws SQLException{
        return (boolean) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarTransaccion((IPsManejadorDao manejadorDao) -> {
                    for (Movimientos nodo : listadoAIsertar) {
                        manejadorDao.getDaoMovimientos().insertar(nodo);
                    }
                    return true;
                });
    }

    public static boolean modificar(Movimientos entidadAModificar) throws SQLException {
        List<Movimientos> entidadMovimientoss = new ArrayList<>();
        entidadMovimientoss.add(entidadAModificar);
        return modificarListado(entidadMovimientoss);
    }

    public static boolean modificarListado(List<Movimientos> listadoAModificar) throws SQLException{
        return (boolean) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarTransaccion((IPsManejadorDao manejadorDao) -> {
                    for (Movimientos nodo : listadoAModificar) {
                        manejadorDao.getDaoMovimientos().modificar(nodo);
                    }
                    return true;
                });

    }

    public static boolean eliminar(Movimientos entidadAEliminar) throws SQLException{
        return (boolean) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarTransaccion((IPsManejadorDao manejadorDao) -> {
                    return manejadorDao.getDaoMovimientos().eliminar(entidadAEliminar);
                });
    }
}
