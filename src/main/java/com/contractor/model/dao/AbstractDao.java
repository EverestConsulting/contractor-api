package com.contractor.model.dao;

import com.contractor.db.HibernateUtil;
import com.contractor.enums.DBProperty;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDao<T> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class.getSimpleName());

    private Class<T> clazz;
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked cast")
    public List<T> findAll() {
        List<T> entities = new LinkedList<>();
        Session session = null;

        String FETCH_ALL = String.format("FROM %s e", this.clazz.getSimpleName());

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            Query query = session.createQuery(FETCH_ALL);
            entities = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception exception) {
            LOG.error(this.clazz.getSimpleName(), exception);
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return entities;
    }

    public T get(Integer id) {
        return getByParam(DBProperty.id, id);
    }

    @SuppressWarnings("unchecked cast")
    public T getByParam(DBProperty param, Object id) {
        T entity = null;
        Session session = null;

        String FETCH_BY_PARAM = String.format("FROM %s e where e.%s = :ID", this.clazz.getSimpleName(), param.name());

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery(FETCH_BY_PARAM);
            query.setParameter("ID", id);
            entity = (T) query.getSingleResult();
            Hibernate.initialize(entity);
            session.getTransaction().commit();
        } catch (Exception exception) {
            LOG.error(this.clazz.getSimpleName(), exception);
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return entity;
    }

    public Integer create(T entity) {
        Session session = null;
        Integer ID = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            ID = (Integer) session.save(entity);
            session.getTransaction().commit();
        } catch (Exception exception) {
            LOG.error(this.clazz.getSimpleName(), exception);
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return ID;
    }

    public boolean update(T entity) {
        Boolean success = false;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
            success = true;
        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return success;
    }

    public boolean delete(T entity) {
        Boolean success = false;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            success = true;
        } catch (Exception exception) {
            LOG.error(this.clazz.getSimpleName(), exception);
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return success;
    }

}
