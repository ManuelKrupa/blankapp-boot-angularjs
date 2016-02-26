package com.mkrupa.blankapp.backoffice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.mkrupa.blankapp.backoffice.exception.TokenException;
import com.mkrupa.blankapp.backoffice.service.UserService;

/**
 * Generic controller.
 * 
 * @author mkrupa
 */
public abstract class ApiController {

	/** Logger */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** Business service for User */
	@Autowired
	private UserService userService;
	
	/**
	 * Return true if access is granted for the token in parameter.
	 * @param pToken String :
	 * 			the token
	 * @return boolean : true or false
	 */
	public boolean isAccessGranted(final String pToken)
		throws TokenException {
		
		// verify that token is filled and exists in database
		if (pToken != null && !StringUtils.isEmpty(pToken)
				&& userService.tokenExists(pToken) != null) {
			logger.info("Token : " + pToken + " / OK ");
			return true;
		} else {
			throw new TokenException("error.token.invalid", "Token invalid : " + pToken);
		}
	}
}
