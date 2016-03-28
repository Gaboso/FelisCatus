package br.edu.ifms.lp3.dao.jpa;

import br.edu.ifms.lp3.model.Cliente;
import br.edu.ifms.lp3.util.JPAUtil;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends JpaDAO<Cliente> {

    /**
     * Método para recuperar todos os registros do banco
     *
     * @return retorna lista com os resultados
     */
    @SuppressWarnings("unchecked")
    public List<String[]> retrieveAll() {
        List<String[]> clients = new ArrayList<>();
        try {
            em = JPAUtil.getEntityManager();
            Query query = em.createNamedQuery(Cliente.RETRIEVE_ALL);
            clients = query.getResultList();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }


    /**
     * Método para buscar no BD por nome
     *
     * @param name - Nome digitado no filtro
     * @return retorna lista com os resultados
     */
    @SuppressWarnings("unchecked")
    public List<String[]> retrieveByName(String name) {
        List<String[]> clients = new ArrayList<>();
        try {
            em = JPAUtil.getEntityManager();
            Query query = em.createNamedQuery(Cliente.RETRIEVE_BY_NAME);
            query.setParameter("nameInFilter", "%" + name + "%");
            clients = query.getResultList();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }

}
