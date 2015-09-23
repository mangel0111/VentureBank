/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import comun.configuracion.ManejadorDeConfiguracion;
import comun.entidades.CuentaBancaria;
import datos.FabricadorDao;
import datos.contratos.IPsManejadorDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miguelangel
 */
public class ControladorCuentaBancaria {

    public static List<CuentaBancaria> consultarTodos() throws SQLException {
        return (List<CuentaBancaria>) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarConsulta((IPsManejadorDao manejadorDao) -> {
                    return manejadorDao.getDaoCuenta().consultarTodos();
                });
    }

    public static CuentaBancaria consultarPorCodigo(CuentaBancaria entidad) throws SQLException {
        return (CuentaBancaria) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarConsulta((IPsManejadorDao manejadorDao) -> {
                    return manejadorDao.getDaoCuenta().consultarPorId(entidad);
                });
    }
    
    public static boolean insertar(CuentaBancaria entidadAInsertar) throws SQLException {
        return (boolean) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarTransaccion((IPsManejadorDao manejadorDao) -> {
                    return manejadorDao.getDaoCuenta().insertar(entidadAInsertar);
                });
    }

    public static boolean insertarListado(List<CuentaBancaria> listadoAIsertar) throws SQLException {
        return (boolean) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarTransaccion((IPsManejadorDao manejadorDao) -> {
                    for (CuentaBancaria nodo : listadoAIsertar) {
                        manejadorDao.getDaoCuenta().insertar(nodo);
                    }
                    return true;
                });
    }
    
    public static boolean modificar(CuentaBancaria entidadAModificar) throws SQLException {
        List<CuentaBancaria> entidadCuentaBancarias = new ArrayList<>();
        entidadCuentaBancarias.add(entidadAModificar);
        return modificarListado(entidadCuentaBancarias);
    }
    
    public static boolean modificarListado(List<CuentaBancaria> listadoAModificar) throws SQLException {
        return (boolean) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarTransaccion((IPsManejadorDao manejadorDao) -> {
                    for (CuentaBancaria nodo : listadoAModificar) {
                        manejadorDao.getDaoCuenta().modificar(nodo);
                    }
                    return true;
                });
        
    }
    
    public static boolean eliminar(CuentaBancaria entidadAEliminar) throws SQLException {
        return (boolean) FabricadorDao.construirManejadorDao(ManejadorDeConfiguracion.getConfiguracionActual())
                .ejecutarTransaccion((IPsManejadorDao manejadorDao) -> {
                    return manejadorDao.getDaoCuenta().eliminar(entidadAEliminar);
                });
    }
}
