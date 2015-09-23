/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.PostgreSQL;

import comun.entidades.Usuario;
import datos.contratos.IPsDaoUsuario;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author miguelangel
 */
public class PsDaoUsuarios extends PsDaoBase<Usuario> implements IPsDaoUsuario {

    public PsDaoUsuarios(PsManejadorDao manejadorDao) {
        super(manejadorDao, Usuario.class);
    }

    @Override
    protected Usuario traducirRegistro(ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setReferencia(resultSet.getString("referencia"));
        usuario.setPassword(resultSet.getString("password"));
        return usuario;
    }

    @Override
    protected String getNombreTabla() {
        return "usuario";
    }

    @Override
    public Usuario consultarPorId(Usuario entidadAConsultar) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertar(Usuario entidadNueva) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(Usuario entidadAModificar) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
