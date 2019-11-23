/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author jasmine
 */
public class UserDB {
    public static User getUser(int uid){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        User u = null;
        try{
            session = sessionFactory.openSession();
            //String qs = "from User where UserID = :uid";
            //Query q = session.createQuery(qs);
            Query q = session.getNamedQuery("dbget_User");
            q.setInteger("uid", uid);
            u = (User) q.uniqueResult();
        }catch(Exception e){
            u = null;
        }finally{
            session.close();
        }
        return u;
    }
}
