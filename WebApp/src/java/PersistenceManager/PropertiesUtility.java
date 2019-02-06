/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenceManager;

import Business.HelperFunctions;
import Entities.Garagetypes;
import Entities.Properties;
import Entities.Propertytypes;
import Entities.Styles;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author shane
 */
public class PropertiesUtility {
    
    public static List<Properties> searchProperties(String place, String[] prices){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        List<Properties> searched_properties = new ArrayList<>();
        try {
            if(prices.length == 2){
                    searched_properties = em.createNamedQuery("Properties.findByPlaceAndPrice", Properties.class)
                            .setParameter("place", "%" + place + "%")
                            .setParameter("priceMin", Double.parseDouble(prices[0]))
                            .setParameter("priceMax", Double.parseDouble(prices[1])).getResultList();
            }else{
                searched_properties = em.createNamedQuery("Properties.findExpensivePlaces", Properties.class)
                        .setParameter("place", "%" + place + "%")
                        .setParameter("price", Double.parseDouble(prices[0])).getResultList();
            }
        }catch(Exception ex) {
        }finally{
            em.close();
        }
        return searched_properties;
    }
    
    public static List<Properties> getRecentProperties(){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        List<Properties> recent_properties = null;
        try {
            recent_properties = em.createNamedQuery("Properties.recentProperties",
                    Properties.class).setParameter("dateAdded", 
                            HelperFunctions.convertLocalDateToDate(LocalDate.now().minusDays(7)))
                    .setParameter("currentDate", 
                            HelperFunctions.convertLocalDateToDate(LocalDate.now()))
                    .getResultList();
        }catch(Exception ex){
            //System.out.println(ex.toString());
        }finally{
            em.close();
        } 
        return recent_properties;
    }
    
    public static Properties getProperty(Integer id){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        Properties requested_property = null;
        try {
            requested_property = em.createNamedQuery("Properties.findById", 
                    Properties.class).setParameter("id", id).getSingleResult();
        }catch(Exception ex) {
            //System.out.println(ex.toString());
        }finally{
            em.close();
        }
        return requested_property;
    }
    
    public static HashMap<Integer, Propertytypes> getPropertyTypes(){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        HashMap<Integer, Propertytypes> map = new HashMap<>();
        try {
            List<Propertytypes> property_types = em.createNamedQuery("Propertytypes.findAll", 
                    Propertytypes.class).getResultList();
            
            property_types.forEach((p) -> {
                map.put(p.getTypeId(), p);
            });
        }catch(Exception ex) {
            System.out.println(ex);
        }finally{
            em.close();
        }
        return map;
    }
    
    public static HashMap<Integer, Garagetypes> getGarageTypes(){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        HashMap<Integer, Garagetypes> map = new HashMap<>();
        try {
            List<Garagetypes> garage_types = em.createNamedQuery("Garagetypes.findAll", 
                    Garagetypes.class).getResultList();
            
            garage_types.forEach((g) -> {
               map.put(g.getGarageId(), g); 
            });
        }catch(Exception ex) {
            System.out.println(ex);
        }finally{
            em.close();
        }
        return map;
    }
    
    public static HashMap<Integer, Styles> getStyleTypes(){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        HashMap<Integer, Styles> map = new HashMap<>();
        try {
            List<Styles> style_types = em.createNamedQuery("Styles.findAll", 
                    Styles.class).getResultList();
            
            style_types.forEach((s) -> {
               map.put(s.getStyleId(), s); 
            });
        }catch(Exception ex) {
            System.out.println(ex);
        }finally{
            em.close();
        }
        return map;
    }
    
    public static List<Properties> getAgentProperties(Integer agentId){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        List<Properties> properties = new ArrayList<>();
        try {
            properties = em.createNamedQuery("Properties.findPropertiesByAgentId",
                    Properties.class).setParameter("agentId", agentId)
                    .getResultList();
        }catch(Exception ex) {
            System.out.println(ex);
        }finally{
            em.close();
        }
        return properties;
    }
    
    public static void deleteProperty(Properties p){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.remove(em.merge(p));
            transaction.commit();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            em.close();
        }
    }
    
    public static void insertProperty(Properties p){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.persist(p);
            transaction.commit();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            em.close();
        }
    }
    
    public static void updateProperty(Properties p){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.merge(p);
            transaction.commit();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            em.close();
        }
    }
}
