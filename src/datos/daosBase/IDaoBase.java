/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.daosBase;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ana
 */
public interface IDaoBase<T> {

    /**
     * Metodo que consulta todas las entiades de una tabla
     *
     * @return Listado de entidades
     * @throws ExcepcionDatosBase si una excepcion de base de datos ocurre
     */
    List<T> consultarTodos() throws SQLException;

    /**
     * Metodo que consulta una entidad segun su identificador unico
     *
     * @param entidadAConsultar entidad que debe contener el identificador unico
     * para encontrar el resto de la informacion en la base de datos
     * @return <code>T</code> si existe una entidad con ese identificador unico
     * o <code>null</code> si no lo encuentra
     * @throws ExcepcionDatosBase si una excepcion de base de datos ocurre
     */
    T consultarPorId(T entidadAConsultar) throws SQLException;

    /**
     * Metodo que inserta una entidad en la base de datos, una vez insertada la
     * entidad, dicha entidad se le asigna el ID insertado en BD.
     *
     * @param entidadNueva entidad con los datos a insertar
     * @return <code>true</code> si inserta en bd o <code>false</code> si no
     * ocurrio la insercion
     * @throws ExcepcionDatosBase si una excepcion de base de datos ocurre
     */
    boolean insertar(T entidadNueva) throws SQLException;

    /**
     * Metodo que modifica una entidad en la base de datos.
     *
     * @param entidadAModificar entidad con los datos a modificar, debe tener el
     * identificador unico
     * @return <code>true</code> si modifica o <code>false</code> si no ocurre
     * la modificacion
     * @throws ExcepcionDatosBase si una excepcion de base de datos ocurre
     */
    boolean modificar(T entidadAModificar) throws SQLException;

    /**
     * Metodo que elimina una entidad en base de datos
     *
     * @param entidadAEliminar entidad a eliminar, debe tener el identificador
     * unico
     * @return <code>true</code> si se elimina o <code>false</code> si no ocurre
     * la eliminacion
     * @throws ExcepcionDatosBase si una excepcion de base de datos ocurre
     */
    boolean eliminar(T entidadAEliminar) throws SQLException;

}
