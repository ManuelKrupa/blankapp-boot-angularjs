package com.mkrupa.blankapp.backoffice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mkrupa.blankapp.backoffice.domain.User;
import com.mkrupa.blankapp.backoffice.dto.UserDTO;
import com.mkrupa.blankapp.backoffice.exception.BusinessException;
import com.mkrupa.blankapp.backoffice.exception.TokenException;
import com.mkrupa.blankapp.backoffice.service.ProfileService;
import com.mkrupa.blankapp.backoffice.service.UserService;

/**
 * Controller for user operations
 * @author mkrupa
 *
 */
@RestController
public class UserController extends ApiController {

	/** Logger */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** The user business service */
	@Autowired
	private UserService userService;
	
	/** The profile business service */
	@Autowired
	private ProfileService profileService;
	
	/**
	 * Return all users
	 * @param pToken String :
	 * 			the security token
	 * @return List<UserDTO> : profiles list 
	 * @throws TokenException :
	 * 			if the token in parameter is invalid 
	 */
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<UserDTO> listAllUsers(@RequestHeader("x-auth-token") String pToken)
		throws TokenException {
		
		// verify token validity
		isAccessGranted(pToken);
		
		final List<User> vUsers = userService.listAllUsers();
		List<UserDTO> vDtos = new ArrayList<UserDTO>();
		if (vUsers !=null && !vUsers.isEmpty()) {
			for (User vUser : vUsers) {
				vDtos.add(transformUserDomainObjectInDTO(vUser));
			}
		}
		return vDtos;
	}
	
	
	/**
	 * Return the user for the ID in parameter
	 * @param pUserId Long :
	 * 			the user ID
	 * @return User : the found user
	 */
	@RequestMapping(value="/users/{pUserId}", method=RequestMethod.GET,
			produces={MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
	public UserDTO getUser(@PathVariable Long pUserId)
		throws BusinessException {
		final User vUser = userService.getUser(pUserId);
		return transformUserDomainObjectInDTO(vUser);
	}
	
	/**
	 * Create a new user
	 * @param pToken String :
	 * 			the security token
	 * @param User pUser :
	 * 			the new user 
	 * @throws BusinessException
	 * 			if the user login already exists
	 * @throws TokenException :
	 * 			if the token in parameter is invalid 
	 */
	@RequestMapping(value="/users", method=RequestMethod.POST,
			produces=MediaType.TEXT_PLAIN_VALUE)
	@Transactional(rollbackFor = Exception.class)
	public void createUser(@RequestHeader("x-auth-token") String pToken, 
			@RequestBody UserDTO pUser)
		throws BusinessException, TokenException {
		try {
			logger.info("createUser - START");
			
			// verify token validity
			isAccessGranted(pToken);
			
			// transform UserDTO in User domain object
			User vUser = transformUserDTOInDomainObject(pUser);
			// call creation service
			userService.createUser(vUser);
			logger.info("createUser - END");
		} catch (BusinessException vBe) {
			logger.error("Error while creating user", vBe);
			throw vBe;
		}
	}
	
	/**
	 * Delete the user for ID in parameter.
	 * @param pToken String :
	 * 			the security token
	 * @param pUserId Long :
	 * 		the user ID to delete
	 * @throws TokenException :
	 * 			if the token in parameter is invalid 
	 */
	@RequestMapping(value="/users/{pUserId}", method=RequestMethod.DELETE, 
			produces=MediaType.TEXT_PLAIN_VALUE)
	@Transactional(rollbackFor = Exception.class)
	public void deleteUser(@RequestHeader("x-auth-token") String pToken, 
			@PathVariable Long pUserId)
		throws TokenException {
		logger.info("deleteUser - START");
		
		// verify token validity
		isAccessGranted(pToken);
		
		userService.deleteUser(pUserId);
		
		logger.info("deleteUser - END");
	}
	
	/**
	 * Edit the user
	 * @param pToken String :
	 * 			the security token
	 * @param Long pUserId :
	 * 			the user ID
	 * @param UserDTO pUser :
	 * 			the user 
	 * @throws BusinessException :
	 * 			if the user profile does not exist in database 
	 * @throws TokenException :
	 * 			if the token in parameter is invalid 
	 */
	@RequestMapping(value="/users/{pUserId}", method=RequestMethod.PUT)
	@Transactional(rollbackFor = Exception.class)
	public void editUser(@RequestHeader("x-auth-token") String pToken, 
			@PathVariable Long pUserId, @RequestBody UserDTO pUser)
		throws BusinessException, TokenException {
		try {
			logger.info("editUser - START");
			
			// verify token validity
			isAccessGranted(pToken);
			
			User vUser = this.transformUserDTOInDomainObject(pUser);
			userService.updateUser(vUser);
			logger.info("editUser - END");
		} catch (BusinessException vBe) {
			logger.error("Error while editing user", vBe);
			throw vBe;
		}
	}
	
	/**
	 * Transform UserDTO in parameter in a User domain object.
	 * @param pUser UserDTO :
	 * 			the DTO to transform
	 * @return User : the domain object
	 * @throws BusinessException : 
	 * 			if the profile does not exist in database
	 */
	private User transformUserDTOInDomainObject(final UserDTO pUser)
		throws BusinessException {
		User vUser = new User();
		vUser.setId(pUser.getId());
		vUser.setLogin(pUser.getLogin());
		vUser.setNom(pUser.getNom());
		vUser.setPrenom(pUser.getPrenom());
		vUser.setPassword(pUser.getPassword());
		vUser.setProfil(profileService.getProfile(pUser.getProfileId()));
		return vUser;
	}
	
	/**
	 * Transform User domain object in parameter in a DTO.
	 * @param pUser User :
	 * 			the domain object to transform
	 * @return UserDTO : the DTO
	 */
	private UserDTO transformUserDomainObjectInDTO(final User pUser) {
		UserDTO vDto = new UserDTO();
		vDto.setId(pUser.getId());
		vDto.setNom(pUser.getNom());
		vDto.setPrenom(pUser.getPrenom());
		vDto.setLogin(pUser.getLogin());
		vDto.setPassword(pUser.getPassword());
		vDto.setProfileId(pUser.getProfil() != null ? pUser.getProfil().getId() : null);
		vDto.setProfile(pUser.getProfil() != null ? pUser.getProfil().getLibelle() : "");
		vDto.setHabilitations(pUser.getProfil().getHabilitations());
		return vDto;
	}
}
