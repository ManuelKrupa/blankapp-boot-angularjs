package com.mkrupa.blankapp.backoffice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mkrupa.blankapp.backoffice.domain.User;
import com.mkrupa.blankapp.backoffice.jpa.support.BackofficeRepository;

/**
 * Repository class for user management
 * @author mkrupa
 *
 */
public interface UserRepository extends BackofficeRepository<User, Long>, UserRepositoryCustom {

	@Query("select u from User u where u.login = :login and u.password = :password")
	User findByLoginAndPassword(@Param("login") String pLogin,@Param("password") String pPassword);
	
	@Query("select u from User u where u.login = :login")
	User findByLogin(@Param("login") String pLogin);
	
	@Query("select u from User u where u.jwt = :token")
	User findByToken(@Param("token") String pToken);
}
