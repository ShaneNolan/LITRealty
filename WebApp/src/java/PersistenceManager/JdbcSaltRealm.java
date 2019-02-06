/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenceManager;

import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 *
 * @author shane
 */
public class JdbcSaltRealm extends JdbcRealm {
    public JdbcSaltRealm() {
        setSaltStyle(SaltStyle.COLUMN);
    }
}
