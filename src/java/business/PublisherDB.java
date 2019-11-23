/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author jasmine
 */
public class PublisherDB {
        public static List<Publisher> getPublishers(){
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        List<Publisher> publishers = null;
        try{
            session = sessionFactory.openSession();
            String qS = "from Publisher order by Publisher_Name";
            Query q = session.createQuery(qS);
            publishers = q.list();
        }catch(Exception e){
            publishers = null;
        }finally{
            session.close();
        }
        return publishers;
    }
}
