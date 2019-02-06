/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistenceManager;

import Entities.Vendors;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Shane
 */
public class VendorsUtility {
    
    public static List<Vendors> getVendorsById(HashSet<Integer> vendorIds){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        List<Vendors> vendors = new ArrayList<>();
        try {
            vendorIds.forEach((i) -> {
                vendors.add(getVendorById(i));
            });
        }catch(Exception ex) {
            System.out.println(ex);
        }finally{
            em.close();
        }
        return vendors;
    }
    
    public static Vendors getVendorById(Integer id){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        Vendors vendor = null;
        try {
            vendor = em.createNamedQuery("Vendors.findById", 
                    Vendors.class).setParameter("id", id)
                    .getSingleResult();
        }catch(Exception ex) {
            System.out.println(ex);
        }finally{
            em.close();
        }
        return vendor;
    }
    
    public static void updateVendor(Vendors v){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.merge(v);
            transaction.commit();
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            em.close();
        }
    }
    
    public static List<Vendors> getAllVendors(){
        EntityManager em = DatabaseUtility.getEMF().createEntityManager();
        List<Vendors> vendors = null;
        try {
            vendors = em.createNamedQuery("Vendors.findAll", 
                    Vendors.class).getResultList();
        }catch(Exception ex) {
            System.out.println(ex);
        }finally{
            em.close();
        }
        return vendors;
    }
}
