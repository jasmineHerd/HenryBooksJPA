
package business;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author jasmine
 */

public class StoreDB {
    
    public static List<Store> getStores(){
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        List<Store> stores = null;
        try{
            session = sessionFactory.openSession();
            String qS = "from Store order by storeName";
            Query q = session.createQuery(qS);
            stores = q.list();
        }catch(Exception e){
            stores = null;
        }finally{
            session.close();
        }
        return stores;
    }
    public static String persistStore(Store s){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        String msg = "";
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(s);
            session.getTransaction().commit();
            session.flush();
            session.refresh(s);
            msg = "Store updated!";
        }catch(Exception e){
            if(session != null){
                session.getTransaction().rollback();
            }
            msg = "Store update error: "+ e.getMessage();
        }finally{
            session.close();
        }
        return msg;
    }
}
