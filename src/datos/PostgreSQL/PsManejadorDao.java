/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.PostgreSQL;

import comun.configuracion.BdConfigBase;
import datos.contratos.IPsComandoDao;
import datos.contratos.IPsDaoCuenta;
import datos.contratos.IPsDaoMovimientos;
import datos.contratos.IPsDaoTipoDeMovimientos;
import datos.contratos.IPsDaoUsuario;
import datos.contratos.IPsManejadorDao;
import datos.daosBase.IComandoDao;
import datos.daosBase.ManejadorDaoBaseBd;
import java.sql.SQLException;

/**
 *
 * @author miguelangel
 */
public class PsManejadorDao extends ManejadorDaoBaseBd implements IPsManejadorDao {

    private final BdConfigBase configActualUsada = new BdConfigBase();
    private PsDaoUsuarios daoUsuario;
    private PsDaoCuenta daoCuenta;
    private PsDaoMovimientos daoMovimiento;
    private PsDaoTipoDeMovimientos daotipoMov;

    public PsManejadorDao(BdConfigBase bdConfigBase) {
        super(bdConfigBase);
        this.configActualUsada.cloneConfig(bdConfigBase);
    }

    @Override
    public Object ejecutarConsulta(IPsComandoDao comandoDao) throws SQLException {
        return ejecutarConsultaBase(new IComandoDao() {

            @Override
            public <T extends ManejadorDaoBaseBd> Object ejecutar(T manejadorDao) throws SQLException {
                return comandoDao.ejecutar(PsManejadorDao.this);
            }
        });
    }

    @Override
    public Object ejecutarTransaccion(IPsComandoDao comandoDao) throws SQLException {
        return ejecutarTransaccionBase(new IComandoDao() {

            @Override
            public <T extends ManejadorDaoBaseBd> Object ejecutar(T manejadorDao) throws SQLException {
                return comandoDao.ejecutar(PsManejadorDao.this);
            }
        });
    }

    @Override
    public IPsDaoUsuario getDaoUsuario() {
        if (daoUsuario == null) {
            daoUsuario = new PsDaoUsuarios(this);
        }
        return daoUsuario;
    }

    @Override
    public IPsDaoCuenta getDaoCuenta() {
        if (daoCuenta == null) {
            daoCuenta = new PsDaoCuenta(this);
        }
        return daoCuenta;
    }

    @Override
    public IPsDaoMovimientos getDaoMovimientos() {
        if (daoMovimiento == null) {
            daoMovimiento = new PsDaoMovimientos(this);
        }
        return daoMovimiento;
    }

    @Override
    public IPsDaoTipoDeMovimientos getDaoTipoMov() {
//        if (daotipoMov == null) {
//            daotipoMov = new PsDaoTipoDeMovimientos(this);
//        }
//        return daotipoMov;
        return null;
    }

}
