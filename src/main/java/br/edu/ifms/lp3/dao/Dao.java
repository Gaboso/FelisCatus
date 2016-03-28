package br.edu.ifms.lp3.dao;

import java.io.Serializable;

public interface Dao<T> {

	T save(T object);

	T update(T object);

	boolean remove(T object);

	T retrieve(Serializable id);
}
