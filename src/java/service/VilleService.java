/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Ville;
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
public class VilleService implements IDao<Ville> {

    @Override
    public boolean create(Ville o) {
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
    
    public boolean createAll(List<Ville> os) {
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
    public boolean update(Ville o) {
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
    public boolean delete(Ville o) {
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
    public Ville findById(int id) {
        Ville m = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            m = (Ville) session.get(Ville.class, id);
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
    public List<Ville> findAll() {
        List<Ville> Profils = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Profils = session.createQuery("from Ville").list();
            tx.commit();
            session.close();
            return Profils;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return Profils;
        }
    }
    

}

