package com.mkrupa.blankapp.backoffice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkrupa.blankapp.backoffice.domain.Profile;
import com.mkrupa.blankapp.backoffice.exception.BusinessException;
import com.mkrupa.blankapp.backoffice.repository.ProfileRepository;
import com.mkrupa.blankapp.backoffice.repository.UserRepository;

/**
 * Business Service on profile management 
 * @author mkrupa
 *
 */
@Service
public class ProfileService {

	/** The profile repository */
	@Autowired
	private ProfileRepository profileRepo;
	
	/** The user repository */
	@Autowired
	private UserRepository userRepo;
	
	/**
	 * Return all profiles
	 * @return List<Profile> : the profiles
	 */
	public List<Profile> listAllProfiles() {
		return profileRepo.findAll();
	}
	
	/**
	 * Store the profile in parameter in database
	 * @param pProfile Profile
	 * 		the profile to create
	 * @throws BusinessException
	 * 		if the profile libelle already exists
	 */
	public void createProfile(Profile pProfile)
		throws BusinessException {
		
		final Profile vProfile = profileRepo.findByLibelle(pProfile.getLibelle());
		
		if (vProfile != null) {
			throw new BusinessException("error.profile.create.same.libelle", 
					"A profile with same libelle already exist");
		} else {
			profileRepo.save(pProfile);
		}
	}
	
	/**
	 * Get the profile for the ID in parameter
	 * @param pIdProfile Long :
	 * 			the searched ID
	 * @return Profile : the found profile
	 * @throws BusinessException :
	 * 			if profile is not found in database
	 */
	public Profile getProfile(Long pIdProfile)
		throws BusinessException {
		final Profile vProfile = profileRepo.getOne(pIdProfile);
		if (vProfile != null) {
			return vProfile;
		} else {
			throw new BusinessException("error.profile.notfound", "Pas de profil trouvé en BDD pour l'ID - " + pIdProfile);
		}
	}
	
	/**
	 * Store the profile in parameter in database
	 * @param pProfile Profile
	 * 		the profile to save
	 */
	public void editProfile(Profile pProfile) {
		profileRepo.save(pProfile);
	}
	
	/**
	 * Delete the profile in database according to the ID in parameter.
	 * @param pIdProfile Long :
	 * 			the profil ID to delete
	 * @throws BusinessException :
	 * 			exception thrown if the profile have users
	 */
	
	public void deleteProfile(Long pIdProfile)
		throws BusinessException {
		// RG : if it exists some users that still have the profile,
		// we can't delete this profile
		final Long vNbUsers = userRepo.countQuery(userRepo
				.countUsersForProfileQuery(profileRepo.findOne(pIdProfile)));
		if (vNbUsers == 0) {
			profileRepo.delete(pIdProfile);
		} else {
			throw new BusinessException("error.profile.remove.still.users", 
					"Profile can't be deleted, some users still use it !");
		}
	}
}
