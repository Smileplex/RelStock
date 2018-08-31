package com.ssmm.stockcrawler.service;

public interface Service<T> {
	Iterable<T> findAll();
	T find(Long id);
	void delete(Long id);
	<S extends T> S save(S entity);
}
