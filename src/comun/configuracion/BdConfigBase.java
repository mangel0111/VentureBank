/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comun.configuracion;

import java.util.Objects;
import java.util.prefs.Preferences;

/**
 *
 * @author Ana
 */
public class BdConfigBase {

    private final Preferences preferences;
    private final String keyHost;
    private final String keyDataBase;
    private final String keyServidorBd;

    private TiposDeServidorBd servidorBd;
    private String host;
    private String dataBase;

    private final boolean isImplementada;

    public BdConfigBase() {
        preferences = null;
        this.keyHost = null;
        this.keyDataBase = null;
        this.keyServidorBd = null;
        isImplementada = false;
    }

    public BdConfigBase(Preferences preferences, String keyCode) {
        this.preferences = preferences;
        this.keyHost = keyCode + "KeyHost";
        this.keyDataBase = keyCode + "KeyDataBase";
        this.keyServidorBd = keyCode + "ServidorBd";
        isImplementada = true;
    }

    public enum TiposDeServidorBd {

        Postgre9_3(0), OldMaprex(1);
        private final int value;

        private TiposDeServidorBd(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public String getHost() {
        if (isImplementada) {
            return preferences.get(keyHost, null);
        } else {
            return host;
        }
    }

    public void setHost(String host) {
        if (isImplementada) {
            preferences.put(keyHost, host);
        } else {
            this.host = host;
        }
    }

    public String getDataBase() {
        if (isImplementada) {
            return preferences.get(keyDataBase, null);
        } else {
            return dataBase;
        }
    }

    public void setDataBase(String dataBase) {
        if (isImplementada) {
            preferences.put(keyDataBase, dataBase);
        } else {
            this.dataBase = dataBase;
        }
    }

    public TiposDeServidorBd getServidorBd() {
        if (isImplementada) {
            switch (preferences.getInt(keyServidorBd, 0)) {
                case 0:
                    return BdConfigBase.TiposDeServidorBd.Postgre9_3;
                case 1:
                    return BdConfigBase.TiposDeServidorBd.OldMaprex;
                default:
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        } else {
            return servidorBd;
        }
    }

    public void setServidorBd(TiposDeServidorBd dialect) {
        if (isImplementada) {
            preferences.putInt(keyServidorBd, dialect.getValue());
        } else {
            this.servidorBd = dialect;
        }
    }

    public void cloneConfig(BdConfigBase config) {
        setHost(config.getHost());
        setDataBase(config.getDataBase());
        setServidorBd(config.getServidorBd());
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BdConfigBase other = (BdConfigBase) obj;

        return (other.getHost() == null ? this.getHost() == null : other.getHost().equals(this.getHost()))
                && (other.getDataBase() == null ? this.getDataBase() == null : other.getDataBase().equals(this.getDataBase()))
                && (other.getServidorBd() == null ? this.getServidorBd() == null : other.getServidorBd().equals(this.getServidorBd()));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.getHost());
        hash = 79 * hash + Objects.hashCode(this.getDataBase());
        hash = 79 * hash + Objects.hashCode(this.getServidorBd());
        return hash;
    }

}
