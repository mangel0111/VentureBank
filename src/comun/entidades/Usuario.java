/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comun.entidades;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author miguelangel
 */
public class Usuario extends EntidadBaseReferencia{
    
   private final StringProperty password = new SimpleStringProperty();
       
    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordFProperty() {
        return password;
    }
    
    
}
