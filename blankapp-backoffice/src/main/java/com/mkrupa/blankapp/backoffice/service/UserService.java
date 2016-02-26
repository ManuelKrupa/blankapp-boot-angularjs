package com.mkrupa.blankapp.backoffice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkrupa.blankapp.backoffice.domain.Profile;
import com.mkrupa.blankapp.backoffice.domain.User;
import com.mkrupa.blankapp.backoffice.exception.BusinessException;
import com.mkrupa.blankapp.backoffice.repository.UserRepository;

/**
 * Business Service on user management 
 * @author mkrupa
 *
 */
@Service
public class UserService {

	/** Logger */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** The profile repository */
	@Autowired
	private UserRepository userRepo;
	
	/**
	 * Return all users
	 * @return List<User> : the users
	 */
	public List<User> listAllUsers() {
		return userRepo.findAll();
	}
	
	/**
	 * Get the user for the ID in parameter
	 * @param pIdUser Long :
	 * 			the searched ID
	 * @return User : the found user
	 * @throws BusinessException :
	 * 			if the user is not found with the ID in parameter
	 */
	public User getUser(Long pIdUser)
		throws BusinessException {
		final User vUser = userRepo.getOne(pIdUser);
		if (vUser != null) {
			return vUser;
		} else {
			logger.error("No user found for ID : " + pIdUser);
			throw new BusinessException("error.user.notfound", "No user found for ID : " + pIdUser);
		}
	}
	
	/**
	 * Return the user for login and password in parameter
	 * @param pLogin String :
	 * 		the user login
	 * @param pPassword String :
	 * 		the user password
	 * @return User : the found user
	 */
	public User logUser(String pLogin, String pPassword) {
		return userRepo.findByLoginAndPassword(pLogin, pPassword);
	}
	
	/**
	 * Store the user in parameter in database.
	 * @param pUser User :
	 * 			the user to create
	 * @throws BusinessException :
	 * 		    if the login is already used
	 */
	public void createUser(User pUser)
		throws BusinessException {
		final User vUser = userRepo.findByLogin(pUser.getLogin());
		if (vUser != null) {
			logger.error("User with same login already exists");
			throw new BusinessException("error.user.login.exist", "User with same login already exists");
		} else {
			userRepo.save(pUser);
		}
	}
	
	/**
	 * Count the number of users for the profile in parameter.
	 * @param pProfile Profile :
	 * 			the profile
	 * @return Long : the numer of users
	 */
	public Long countUsersForProfile(Profile pProfile) {
		return userRepo.countQuery(userRepo.countUsersForProfileQuery(pProfile));
	}
	
	/**
	 * Delete user for ID in parameter.
	 * @param pUserId Long :
	 * 			the user ID
	 */
	public void deleteUser(Long pUserId) {
		userRepo.delete(pUserId);
	}
	
	/**
	 * Update the user in parameter in database
	 * @param pUser User :
	 * 			the user to update
	 */
	public void updateUser(User pUser) {
		userRepo.save(pUser);
	}
	
	/**
	 * Return the user for token in parameter
	 * @param pToken String :
	 * 		the token
	 * @return User : the found user
	 */
	public User tokenExists(String pToken) {
		return userRepo.findByToken(pToken);
	}
}
