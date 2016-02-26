package com.mkrupa.blankapp.backoffice.jpa.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * Bean Factory for BackofficeRepository
 * @author mkrupa
 *
 * @param <R> Repository :
 * 		the repository to create
 * @param <T> Type :
 * 		the domain managed by the repository
 * @param <I> Serializable :
 * 		the ID type for the managed domain
 */
public class BackofficeRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
	extends JpaRepositoryFactoryBean<R, T, I> {

	/**
	 * Create the backoffice repository factory
	 */
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager pEntityManager) {
		return new BackofficeRepositoryFactory(pEntityManager);
	}
	
	/**
	 * Factory of backoffice repository
	 * @author mkrupa
	 *
	 * @param <T> Type :
	 * 		the domain managed by the repository
	 * @param <I> Serializable :
	 * 		the ID type for the managed domain
	 */
	private static class BackofficeRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {
	
		/** The JPA Entity Manager */
		private EntityManager entityManager;
	
		/**
		 * Specific constructor to get the EntityManager reference
		 * @param pEntityManager EntityManager :
		 * 		 the JPA EntityManager
		 */
		public BackofficeRepositoryFactory(EntityManager pEntityManager) {
			super(pEntityManager);
			this.entityManager = pEntityManager;
		}
	
		/**
		 * Create a repository for domain passed through the metadata parameter
		 */
		protected Object getTargetRepository(RepositoryMetadata pMetadata) {
			return new BackofficeRepositoryImpl<T, I>((Class<T>) pMetadata.getDomainType(), entityManager);
		}
	
		/**
		 * Return the repository base class to use which is Backoffice repository in our case
		 */
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata pMetadata) {
	
			// The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
			// to check for QueryDslJpaRepository's which is out of scope.
			return BackofficeRepository.class;
		}
	}
}