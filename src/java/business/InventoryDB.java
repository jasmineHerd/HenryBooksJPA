/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author jasmine
 */
public class InventoryDB {
    public static boolean addBookInv(Inventory inv){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        boolean dbstat = false;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(inv);
            session.getTransaction().commit();
            dbstat = true;
        }catch(Exception e){
            if(session != null){
                session.getTransaction().rollback();
            }
        }finally{
            session.close();
            
        }
        return dbstat;
    }
        
    
}
