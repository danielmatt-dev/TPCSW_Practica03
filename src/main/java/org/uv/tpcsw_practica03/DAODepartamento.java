package org.uv.tpcsw_practica03;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAODepartamento implements IDAOGeneral<Departamento, Long>{

    @Override
    public boolean save(Departamento pojo) {
        
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
        
        Departamento pojo = session.get(Departamento.class, id);
        
        if(pojo == null) {
            return false;
        }
        
        session.delete(pojo);
        t.commit();
        return true;
    }

    @Override
    public boolean update(Departamento pojo, Long id) {
        
        Session session = HibernateUtil.buildSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        session.save(pojo);
        t.commit();
        return true;
        
    }

    @Override
    public List<Departamento> findAll() {
                Session session = HibernateUtil.buildSessionFactory().getCurrentSession();
        Transaction transaction = null;
        List<Departamento> departamentos = null;

        try {
            // Start a transaction
            transaction = session.beginTransaction();

            // Use HQL or Criteria to fetch all Empleado entities
            departamentos = session.createQuery("FROM Departamento", Departamento.class).getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of exception
            }
            e.printStackTrace(); // Log the exception
        } finally {
            session.close(); // Ensure the session is closed
        }

        return departamentos; // Return the list of Empleado
    }

    @Override
    public Departamento findById(Long id) {
        
        Session session = HibernateUtil.buildSessionFactory().getCurrentSession();
        Transaction transaction = null;
        Departamento departamento = null;

        try {

            transaction = session.beginTransaction();
            departamento = session.get(Departamento.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); 
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return departamento;
        
    }

}
