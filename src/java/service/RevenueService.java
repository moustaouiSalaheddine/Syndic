/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import beans.Immeuble;
import beans.Revenue;
import dao.IDao;
import java.util.Date;
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
public class RevenueService implements IDao<Revenue> {

    @Override
    public boolean create(Revenue o) {
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
    
    public boolean createAll(List<Revenue> os) {
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
    public boolean update(Revenue o) {
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
    public boolean delete(Revenue o) {
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
    public Revenue findById(int id) {
        Revenue m = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            m = (Revenue) session.get(Revenue.class, id);
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
    public List<Revenue> findAll() {
        List<Revenue> Revenus = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Revenus = session.createQuery("from Revenue").list();
            tx.commit();
            session.close();
            return Revenus;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return Revenus;
        }
    }
    
    public List<Revenue> findByAnneeAndImmeuble(Date date, Immeuble immeuble) {
        List<Revenue> Revenus = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Revenus = session.createQuery("SELECT r from Revenue r WHERE r.appartement.immeubleId.id = ? AND YEAR(r.date) = YEAR(?) ")
                    .setParameter(0, immeuble.getId()).setParameter(1, date).list();
            tx.commit();
            session.close();
            return Revenus;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return Revenus;
        }
    }
    

}

