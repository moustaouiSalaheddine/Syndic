/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Habitant;
import dao.IDao;
import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Sinponzakra
 */
public class HabitantService implements IDao<Habitant> {

    @Override
    public boolean create(Habitant o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }
    }
    
    public Habitant created(Habitant o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            session.close();
            return o;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return null;
        }
    }
    
    public boolean createAll(List<Habitant> os) {
        HibernateUtil.getSessionFactory().openSession().setFlushMode(FlushMode.MANUAL);
      final  Session session = HibernateUtil.getSessionFactory().openSession();
      final  Transaction tx = session.beginTransaction();;
        try {
            os.forEach((e) -> {
                session.save(e);
            });
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            System.out.println("error message :"+ex.getMessage());
            if (tx != null) { tx.rollback();}
            session.close();
            return false;
        }
    }

    @Override
    public boolean update(Habitant o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }
    }

    @Override
    public boolean delete(Habitant o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }
    }

    @Override
    public Habitant findById(int id) {
        Habitant m = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            m = (Habitant) session.get(Habitant.class, id);
            tx.commit();
            session.close();
            return m;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return m;
        }
    }

    @Override
    public List<Habitant> findAll() {
        List<Habitant> Habitants = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Habitants = session.createQuery("from Habitant").list();
            tx.commit();
            session.close();
            return Habitants;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return Habitants;
        }
    }
    
    public List<Habitant> findAllSyndic() {
        List<Habitant> Habitants = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Habitants = session.createQuery("SELECT h from Habitant h where h.type = 'syndic' ").list();
            tx.commit();
            session.close();
            return Habitants;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return Habitants;
        }
    }
    

}


