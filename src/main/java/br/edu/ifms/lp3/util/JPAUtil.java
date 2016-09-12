package br.edu.ifms.lp3.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe com itens úteis para JPA
 */
public class JPAUtil {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    /**
     * Construtor privado
     */
    private JPAUtil() {
    }

    /**
     * Método para pegar o EntityManager
     *
     * @return Retorna EntityManager
     */
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}