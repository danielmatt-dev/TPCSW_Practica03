package org.uv.tpcsw_practica03;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAOEmpleado implements IDAOGeneral<Empleado, Long>{

    @Override
    public boolean save(Empleado pojo) {
        
        Session session = HibernateUtil.buildSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        session.save(pojo);
        t.commit();
        return true;
    }

    @Override
    public boolean delete(Long id) {
        
        Session session = HibernateUtil.buildSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        
        try {
            Empleado pojo = session.get(Empleado.class, id);

            if(pojo == null) {
                t.rollback();
                return false;
            }

            session.delete(pojo);
            t.commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }
        
    }

    @Override
    public boolean update(Empleado pojo, Long id) {
        Session session = HibernateUtil.buildSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        session.save(pojo);
        t.commit();
        return true;
    }

    @Override
    public List<Empleado> findAll() {
        Session session = HibernateUtil.buildSessionFactory().getCurrentSession();
        Transaction transaction = null;
        List<Empleado> empleados = null;

        try {
            
            transaction = session.beginTransaction();

            empleados = session.createQuery("FROM Empleado", Empleado.class).getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return empleados;
    }

    @Override
    public Empleado findById(Long id) {
    
        Session session = HibernateUtil.buildSessionFactory().getCurrentSession();
        Transaction transaction = null;
        Empleado empleado = null;

        try {

            transaction = session.beginTransaction();
            empleado = session.get(Empleado.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); 
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return empleado;
    }
    
}
