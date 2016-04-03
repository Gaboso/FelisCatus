package br.edu.ifms.lp3.dao;

import java.io.Serializable;

/**
 * Interface com assinaturas dos métodos para um CRUD básico
 *
 * @param <T> - Classe a ser implementada
 */
public interface Dao<T> {

    /**
     * Método para salvar
     *
     * @param object - Objeto a ser salvo
     * @return Retorna objeto da classe que implementa esta interface
     */
    T save(T object);

    /**
     * Método para atualizar
     *
     * @param object - Objeto a ser atualizado
     * @return Retorna objeto da classe que implementa esta interface
     */
    T update(T object);

    /**
     * Método para remover
     *
     * @param object - Objeto a ser removido
     * @return Retorna booleano
     */
    boolean remove(T object);

    /**
     * Método para busca a partir do ID
     *
     * @param id - ID do item a ser buscado
     * @return Retorna objeto da classe que implementa esta interface
     */
    T retrieve(Serializable id);
}
