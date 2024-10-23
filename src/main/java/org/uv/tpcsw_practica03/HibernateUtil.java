package org.uv.tpcsw_practica03;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
 
    private static final SessionFactory sessionFactory = buildSessionFactory();
 
    public static SessionFactory buildSessionFactory() {
        
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch(Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }  
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown(){
        getSessionFactory().close();
    }
    
}
