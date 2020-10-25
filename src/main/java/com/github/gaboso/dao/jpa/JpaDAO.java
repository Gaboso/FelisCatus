package com.github.gaboso.dao.jpa;

import com.github.gaboso.dao.DAO;
import com.github.gaboso.util.JPAUtil;
import com.github.gaboso.util.ReflectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

public class JpaDAO<T> implements DAO<T> {

    private static final Logger LOGGER = LogManager.getLogger(JpaDAO.class);

    protected EntityManager em;

    @Override
    public T save(T object) {
        try {
            em = JPAUtil.getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(object);
            transaction.commit();
            em.close();
            return object;
        } catch (Exception e) {
            LOGGER.error(e);
        }

        em.close();
        return null;
    }

    @Override
    public T update(T object) {
        try {
            em = JPAUtil.getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            T objectMerged = em.merge(object);
            transaction.commit();
            em.close();
            return objectMerged;
        } catch (Exception e) {
            LOGGER.error(e);
        }

        em.close();
        return null;
    }

    @Override
    public boolean remove(T object) {
        try {
            em = JPAUtil.getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            T objectMerged = em.merge(object);
            em.remove(objectMerged);
            transaction.commit();
            em.close();
            return true;
        } catch (Exception e) {
            LOGGER.error(e);
        }

        em.close();
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T retrieve(Serializable id) {
        if (id != null) {
            try {
                em = JPAUtil.getEntityManager();
                Class<?> clazz = ReflectionUtil.getGenericClass(this, 0);
                T object = (T) em.find(clazz, id);
                em.close();
                return object;
            } catch (Exception e) {
                LOGGER.error(e);
            }

            em.close();
        }

        return null;
    }

}