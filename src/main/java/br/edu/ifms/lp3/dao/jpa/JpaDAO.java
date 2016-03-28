package br.edu.ifms.lp3.dao.jpa;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.edu.ifms.lp3.dao.Dao;
import br.edu.ifms.lp3.util.JPAUtil;
import br.edu.ifms.lp3.util.ReflectionUtil;

public class JpaDAO<T> implements Dao<T> {

	EntityManager em;

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
			e.printStackTrace();
		}
		em.close();
		return null;
	}

	public T update(T object) {
		try {
			em = JPAUtil.getEntityManager();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			object = em.merge(object);
			transaction.commit();
			em.close();
			return object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return null;
	}

	public boolean remove(T object) {
		try {
			em = JPAUtil.getEntityManager();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			object = em.merge(object);
			em.remove(object);
			transaction.commit();
			em.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	public T retrieve(Serializable id) {
		if (id != null) {
			try {
				em = JPAUtil.getEntityManager();
				Class<?> clazz = ReflectionUtil.getGenericClass(this, 0);
				T object = (T) em.find(clazz, id);
				em.close();
				return object;
			} catch (Exception e) {
				e.printStackTrace();
			}
			em.close();
		}
		return null;
	}
}
