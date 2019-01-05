/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Appartement;
import beans.Immeuble;
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
public class AppartementService implements IDao<Appartement> {

    @Override
    public boolean create(Appartement o) {
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
    
    public boolean createAll(List<Appartement> os) {
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
    public boolean update(Appartement o) {
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
    public boolean delete(Appartement o) {
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
    public Appartement findById(int id) {
        Appartement m = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            m = (Appartement) session.get(Appartement.class, id);
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
    public List<Appartement> findAll() {
        List<Appartement> Profils = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Profils = session.createQuery("from Appartement").list();
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
    
    public List<Appartement> findAllByImmeuble(Immeuble o) {
        List<Appartement> Profils = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Profils = session.createQuery("SELECT a from Appartement a WHERE a.immeubleId.id =  ?").setParameter(0, o.getId()).list();
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
