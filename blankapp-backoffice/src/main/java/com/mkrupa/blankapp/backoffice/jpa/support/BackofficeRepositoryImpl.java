package com.mkrupa.blankapp.backoffice.jpa.support;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * Implements BackofficeRepository interface to add some methods to run JPQL queries
 * @author mkrupa
 *
 * @param <T> Type :
 * 		the domain type for the repository
 * @param <ID> Serializable :
 * 		the ID type of the domain
 */
public class BackofficeRepositoryImpl <T, ID extends Serializable>
	extends SimpleJpaRepository<T, ID> implements BackofficeRepository<T, ID> {

	/** The JPA Entity Manager */
	private EntityManager entityManager;
	
	/** The domain entity managed by the repository */
	private Class<T> domainClass;

	/**
	 * Overload constructor
	 * @param pDomainClass Class<T> :
	 * 		the domain class to manage
	 * @param pEm EntityManager :
	 * 		the JPA Entity Manager
	 */
	public BackofficeRepositoryImpl(Class<T> pDomainClass, EntityManager pEm) {
		super(pDomainClass, pEm);
		entityManager = pEm;
		domainClass = pDomainClass;
	}

	@Override
	public List<T> findAllQuery(String pQuery) {
		final TypedQuery<T> vQuery = entityManager.createQuery(pQuery, domainClass);
		return vQuery.getResultList();
	}

	@Override
	public T findOneQuery(String pQuery) {
		final TypedQuery<T> vQuery = entityManager.createQuery(pQuery, domainClass);
		return vQuery.getSingleResult();
	}
	
	@Override
	public Long countQuery(String pQuery) {
		final TypedQuery<Long> vQuery = entityManager.createQuery(pQuery, Long.class);
		return vQuery.getSingleResult();
	}
}