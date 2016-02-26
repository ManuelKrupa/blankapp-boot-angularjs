package com.mkrupa.blankapp.backoffice.repository;

import com.mkrupa.blankapp.backoffice.domain.Profile;

/**
 * Implementation of custom methods for UserRepository
 * @author mkrupa
 *
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

	@Override
	public String countUsersForProfileQuery(final Profile pProfile) {
		final StringBuffer vSb = new StringBuffer("select count(u)"); 
		vSb.append(" from com.mkrupa.blankapp.backoffice.domain.User u");
		vSb.append(" where u.profil.id = ");
		vSb.append(String.valueOf(pProfile.getId()));
		return vSb.toString();
	}
	
	
}
