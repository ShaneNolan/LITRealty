/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenceManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author k00205031
 */
public class DatabaseUtility {
    
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("WebAppPU");
    
    public static EntityManagerFactory getEMF() { return emf; }
    
}
