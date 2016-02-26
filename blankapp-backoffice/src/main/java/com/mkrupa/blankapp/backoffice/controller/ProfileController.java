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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mkrupa.blankapp.backoffice.domain.Habilitation;
import com.mkrupa.blankapp.backoffice.domain.Profile;
import com.mkrupa.blankapp.backoffice.dto.ProfileDTO;
import com.mkrupa.blankapp.backoffice.exception.BusinessException;
import com.mkrupa.blankapp.backoffice.exception.TokenException;
import com.mkrupa.blankapp.backoffice.service.HabilitationService;
import com.mkrupa.blankapp.backoffice.service.ProfileService;
import com.mkrupa.blankapp.backoffice.service.UserService;

/**
 * Controller for profile operations
 * @author mkrupa
 *
 */
@RestController
public class ProfileController extends ApiController {

	/** Logger */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** The profile business service */
	@Autowired
	private ProfileService profileService;
	
	/** The habilitation business service */
	@Autowired
	private HabilitationService habilitationService;
	
	/** The user business service */
	@Autowired
	private UserService userService;
	
	/**
	 * Return all profiles
	 * @param pToken String :
	 * 			the security token
	 * @return List<ProfileDTO> : profiles list
	 * @throws TokenException :
	 * 			if the token in parameter is invalid 
	 */
	@RequestMapping(value="/profiles", method=RequestMethod.GET)
	public List<ProfileDTO> listAllProfiles(@RequestHeader("x-auth-token") String pToken)
		throws TokenException {
		
		// verify token validity
		isAccessGranted(pToken);
		
		final List<Profile> vProfiles = profileService.listAllProfiles();
		List<ProfileDTO> vListDto = new ArrayList<ProfileDTO>();
		if (vProfiles != null && !vProfiles.isEmpty()) {
			for (final Profile vProfile : vProfiles) {
				vListDto.add(transformProfileDomainObjectInDTO(vProfile));
			}
		}
		return vListDto;
	}
	
	/**
	 * Return all habilitations
	 * @return List<Habilitation> : habilitations list
	 */
	@RequestMapping("/habilitations")
	public List<Habilitation> listAllHabilitations() {
		return habilitationService.listAllHabilitations();
	}
	
	/**
	 * Create a new profile
	 * @param pToken String :
	 * 			the security token
	 * @param Profile pProfile :
	 * 			the new profile 
	 * @throws BusinessException :
	 * 			if profile with same label already exists
	 * @throws TokenException :
	 * 			if the token in parameter is invalid 
	 */
	@RequestMapping(value="/profiles", method=RequestMethod.POST,
			produces=MediaType.TEXT_PLAIN_VALUE)
	@Transactional(rollbackFor = Exception.class)
	public void createProfile(@RequestHeader("x-auth-token") String pToken, 
			@RequestBody ProfileDTO pProfile)
		throws BusinessException, TokenException {
		try {
			logger.info("createProfile - START");
			// verify token validity
			isAccessGranted(pToken);
			
			Profile vProfile = this.transformProfileDTOInDomainObject(pProfile);
			profileService.createProfile(vProfile);
			logger.info("createProfile - END");
		} catch (BusinessException vBe) {
			logger.error("Error while creating profile", vBe);
			throw vBe;
		}
	}
	
	/**
	 * Return the profile for the ID in parameter
	 * @param pToken String :
	 * 			the security token
	 * @param pProfileId Long :
	 * 			the profile ID
	 * @return Profile : the found profile
	 * @throws TokenException :
	 * 			if the token in parameter is invalid 
	 */
	@RequestMapping(value="/profiles/{pProfileId}", method=RequestMethod.GET)
	@ResponseBody
	public ProfileDTO getProfile(@RequestHeader("x-auth-token") String pToken, 
			@PathVariable Long pProfileId)
		throws TokenException, Exception {
		try {
			logger.info("getProfile - START");
			// verify token validity
			isAccessGranted(pToken);
						
			final Profile vProfile = profileService.getProfile(pProfileId);
			ProfileDTO vDto = transformProfileDomainObjectInDTO(vProfile);
			logger.info("getProfile - END");
			return vDto;
		} catch (Exception vE) {
			logger.error("Error while getting profile", vE);
			throw vE;
		}
	}
	
	/**
	 * Edit the profile
	 * @param pToken String :
	 * 			the security token
	 * @param Long pProfileId :
	 * 			the profile ID
	 * @param Profile pProfile :
	 * 			the profile
	 * @throws TokenException :
	 * 			if the token in parameter is invalid  
	 */
	@RequestMapping(value="/profiles/{pProfileId}", method=RequestMethod.PUT)
	@Transactional(rollbackFor = Exception.class)
	public void editProfile(@RequestHeader("x-auth-token") String pToken, 
			@PathVariable Long pProfileId, @RequestBody ProfileDTO pProfile)
		throws TokenException {
		try {
			logger.info("editProfile - START");
			// verify token validity
			isAccessGranted(pToken);
						
			Profile vProfile = this.transformProfileDTOInDomainObject(pProfile);
			profileService.editProfile(vProfile);
			logger.info("editProfile - END");
		} catch (Exception vE) {
			logger.error("Error while editing profile", vE);
			throw vE;
		}
	}
	
	/**
	 * Delete the profile for ID in parameter.
	 * @param pToken String :
	 * 			the security token
	 * @param pProfileId Long :
	 * 		the profile ID to delete
	 * @throws BusinessException :
	 * 			if profile to delete still have users
	 * @throws TokenException :
	 * 			if the token in parameter is invalid 
	 */
	@RequestMapping(value="/profiles/{pProfileId}", method=RequestMethod.DELETE, 
			produces=MediaType.TEXT_PLAIN_VALUE)
	@Transactional(rollbackFor = Exception.class)
	public void deleteProfile(@RequestHeader("x-auth-token") String pToken, 
			@PathVariable Long pProfileId)
		throws BusinessException, TokenException {
		try {
			logger.info("deleteProfile - START");
			
			// verify token validity
			isAccessGranted(pToken);
						
			profileService.deleteProfile(pProfileId);
			
			logger.info("deleteProfile - END");
		} catch (BusinessException vBe) {
			logger.error("Error while removing profile", vBe);
			throw vBe;
		}
	}
	
	/**
	 * Transform the profile DTO in a profile domain object
	 * @param pProfile ProfileDTO :
	 * 			the dto
	 * @return Profile : the resulting domain object
	 */
	private Profile transformProfileDTOInDomainObject(final ProfileDTO pProfile) {
		Profile vProfile = new Profile();
		
		vProfile.setId(pProfile.getId());
		vProfile.setLibelle(pProfile.getLibelle());
		vProfile.setDescription(pProfile.getDescription());
		vProfile.setHabilitations(pProfile.getHabilitations());
		
		return vProfile;
	}
	
	/**
	 * Transform User domain object in parameter in a DTO.
	 * @param pUser User :
	 * 			the domain object to transform
	 * @return UserDTO : the DTO
	 */
	private ProfileDTO transformProfileDomainObjectInDTO(final Profile pProfile) {
		ProfileDTO vDto = new ProfileDTO();
		
		vDto.setId(pProfile.getId());
		vDto.setLibelle(pProfile.getLibelle());
		vDto.setDescription(pProfile.getDescription());
		vDto.setNbUtilisateurs(userService.countUsersForProfile(pProfile));
		vDto.setHabilitations(pProfile.getHabilitations());
		return vDto;
	}
}
