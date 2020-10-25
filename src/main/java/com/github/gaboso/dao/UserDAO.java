package com.github.gaboso.dao;

import com.github.gaboso.dao.jpa.JpaDAO;
import com.github.gaboso.model.User;
import com.github.gaboso.util.JPAUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends JpaDAO<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    @SuppressWarnings("unchecked")
    public List<String[]> findAll() {
        List<String[]> clients = new ArrayList<>();

        try {
            em = JPAUtil.getEntityManager();
            Query query = em.createNamedQuery(User.FIND_ALL);
            clients = query.getResultList();
            em.close();
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return clients;
    }

    @SuppressWarnings("unchecked")
    public List<String[]> findByName(String name) {
        List<String[]> clients = new ArrayList<>();

        try {
            em = JPAUtil.getEntityManager();
            Query query = em.createNamedQuery(User.FIND_BY_NAME);
            query.setParameter("nameInFilter", "%" + name + "%");
            clients = query.getResultList();
            em.close();
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return clients;
    }

}