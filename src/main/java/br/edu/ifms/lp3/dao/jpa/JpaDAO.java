package br.edu.ifms.lp3.dao.jpa;

import br.edu.ifms.lp3.dao.DAO;
import br.edu.ifms.lp3.util.JPAUtil;
import br.edu.ifms.lp3.util.ReflectionUtil;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

/**
 * Classe com metodos basicos de crud
 *
 * @param <T> Classe a ser utilizada
 */
public class JpaDAO<T> implements DAO<T> {

    private static final Logger LOGGER = Logger.getLogger(JpaDAO.class);

    protected EntityManager em;

    /**
     * Método genérico para salvar
     *
     * @param object - Objeto que deverá ser salvo
     * @return retorna o objeto salvo ou nulo se não for possível
     */
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

    /**
     * Método genérico para salvar
     *
     * @param object - Objeto que devera ser atualizado
     * @return retorna o objeto atualizado ou nulo se não for possível
     */
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

    /**
     * Método genérico para remover
     *
     * @param object - Objeto a ser removido
     * @return Retorna true se foi possível salvar e false caso contrario
     */
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

    /**
     * Método genérico para recuperar
     *
     * @param id - Id do objeto a ser recuperado
     * @return Retorna o objeto recuperado ou nulo se não for possível
     */
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