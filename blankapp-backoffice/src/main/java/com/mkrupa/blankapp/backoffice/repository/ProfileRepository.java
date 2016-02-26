package com.mkrupa.blankapp.backoffice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mkrupa.blankapp.backoffice.domain.Profile;
import com.mkrupa.blankapp.backoffice.jpa.support.BackofficeRepository;

/**
 * Repository class for profile management
 * @author mkrupa
 *
 */
public interface ProfileRepository extends BackofficeRepository<Profile, Long> {

	@Query("select p from Profile p where p.libelle = :libelle")
	Profile findByLibelle(@Param("libelle") String pLibelle);
}
