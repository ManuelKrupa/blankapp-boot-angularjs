package com.mkrupa.blankapp.backoffice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mkrupa.blankapp.backoffice.domain.User;
import com.mkrupa.blankapp.backoffice.dto.ValueDTO;
import com.mkrupa.blankapp.backoffice.exception.BusinessException;
import com.mkrupa.blankapp.backoffice.service.TokenService;
import com.mkrupa.blankapp.backoffice.service.UserService;

/**
 * Controller for login operations
 * @author mkrupa
 *
 */
@RestController
public class LoginController {

	/** Logger */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** The user business service */
	@Autowired
	private UserService userService;
	
	/** The token service */
	@Autowired
	private TokenService tokenService;
	
	/**
	 * Log a user
	 * @param User pUser :
	 * 			the credentials
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ValueDTO logUser(@RequestBody User pUser)
		throws BusinessException {
		logger.info("logUser - " + pUser.getLogin() + "/" + pUser.getPassword() + " - START");
		
		User vUser = userService.logUser(pUser.getLogin(), pUser.getPassword());
		
		String vToken = null;
		
		if (vUser != null) {
			// create token for logged user
			vToken = tokenService.createToken(vUser);
			
			// store token in database
			vUser.setJwt(vToken);
			userService.updateUser(vUser);
		}
		
		logger.info("logUser - " + (vUser != null) + " - END");
		
		final ValueDTO vDto = new ValueDTO(vToken);
		return vDto;
	}
}
