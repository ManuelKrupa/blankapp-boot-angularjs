package com.mkrupa.blankapp.backoffice.repository;

import com.mkrupa.blankapp.backoffice.domain.Profile;

/**
 * Interface defining custom methods for UserRepository
 * @author mkrupa
 *
 */
public interface UserRepositoryCustom {

	/**
	 * Return the query that count the number of users having the profile in parameter
	 * @param pProfile Profile :
	 * 		the searched profile
	 * @return Long : the number of users
	 */
	public String countUsersForProfileQuery(final Profile pProfile);
	
}
