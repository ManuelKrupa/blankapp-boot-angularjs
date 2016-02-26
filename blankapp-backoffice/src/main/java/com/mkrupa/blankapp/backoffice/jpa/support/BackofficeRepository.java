package com.mkrupa.blankapp.backoffice.jpa.support;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Extend the JpaRepository interface of SpringDataJpa to add some methods to run JPQL queries
 * @author mkrupa
 *
 * @param <T> Type :
 * 		the domain type for the repository
 * @param <ID> Serializable :
 * 		the ID type of the domain
 */
public interface BackofficeRepository <T, ID extends Serializable>
	extends JpaRepository<T, ID> {

	/**
	 * Method definition for JPQL queries with many results expected
	 * @param pQuery String :
	 * 		the JPQL query
	 * @return List<T> : all matching results
	 */
	List<T> findAllQuery(String pQuery);

	/**
	 * Method definition for JPQL queries with only one result expected
	 * @param pQuery String :
	 * 		the JPQL query
	 * @return T : the only one matching result
	 */
	T findOneQuery(String pQuery);
	
	/**
	 * Method definition for JPQL queries of count type
	 * @param pQuery String :
	 * 		the JPQL query
	 * @return Long : the count number
	 */
	Long countQuery(String pQuery);
}