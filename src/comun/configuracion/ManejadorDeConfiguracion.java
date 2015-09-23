/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comun.configuracion;

/**
 *
 * @author miguelangel
 */
public class ManejadorDeConfiguracion {
    
    public enum TiposDeBaseDeDatos {

        Usuario, Auxiliar, Indiferente
    }

    private static TiposDeBaseDeDatos tipoBaseDeDatosActual = TiposDeBaseDeDatos.Usuario;
    private static final BdConfigBase configuracionUsuario = new BdConfigBase();
    private static final BdConfigBase configuracionAuxiliar = new BdConfigBase();
    private static final BdConfigBase configuracionIndiferente = new BdConfigBase();

    public static BdConfigBase getConfiguracionActual() {
        switch (getTipoBaseDeDatosActual()) {
            case Usuario:
                return getConfiguracionUsuario();
            case Auxiliar:
                return getConfiguracionAuxiliar();
            default:
                return getConfiguracionIndiferente();
        }
    }

    public static BdConfigBase getConfiguracionUsuario() {
        return configuracionUsuario;
    }

    public static BdConfigBase getConfiguracionAuxiliar() {
        return configuracionAuxiliar;
    }

    public static BdConfigBase getConfiguracionIndiferente() {
        return configuracionIndiferente;
    }

    public static TiposDeBaseDeDatos getTipoBaseDeDatosActual() {
        return tipoBaseDeDatosActual;
    }

    public static void setTipoBaseDeDatosActual(TiposDeBaseDeDatos tipoBaseDeDatosActual) {
        ManejadorDeConfiguracion.tipoBaseDeDatosActual = tipoBaseDeDatosActual;
    }
}
