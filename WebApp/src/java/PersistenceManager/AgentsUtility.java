/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenceManager;

import Entities.Agents;
import javax.persistence.EntityManager;

/**
 *
 * @author Shane
 */
public class AgentsUtility {
    
    public static Agents getAgentDetails(String username){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        Agents agent = null;
        try {
            agent = em.createNamedQuery("Agents.findByEmail", Agents.class)
                    .setParameter("email", username).getSingleResult();
        }catch(Exception ex){
            //
        }finally{
            em.close();
        }
        return agent;
    }
}
