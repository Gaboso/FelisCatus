package com.github.gaboso.dao;

import com.github.gaboso.dao.jpa.JpaDAO;
import com.github.gaboso.model.Cliente;
import com.github.gaboso.util.JPAUtil;
import org.apache.log4j.Logger;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends JpaDAO<Cliente> {

    private static final Logger LOGGER = Logger.getLogger(JpaDAO.class);

    public List<String[]> findAll() {
        List<String[]> clients = new ArrayList<>();

        try {
            em = JPAUtil.getEntityManager();
            Query query = em.createNamedQuery(Cliente.FIND_ALL);
            clients = query.getResultList();
            em.close();
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return clients;
    }

    public List<String[]> findByName(String name) {
        List<String[]> clients = new ArrayList<>();

        try {
            em = JPAUtil.getEntityManager();
            Query query = em.createNamedQuery(Cliente.FIND_BY_NAME);
            query.setParameter("nameInFilter", "%" + name + "%");
            clients = query.getResultList();
            em.close();
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return clients;
    }

}