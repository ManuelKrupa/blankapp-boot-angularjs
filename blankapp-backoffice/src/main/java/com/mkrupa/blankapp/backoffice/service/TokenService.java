package com.mkrupa.blankapp.backoffice.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWTSigner;
import com.mkrupa.blankapp.backoffice.domain.User;

/**
 * Business Service on token management 
 * @author mkrupa
 *
 */
@Service
public class TokenService {

	/** Logger */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** Secret key */
	private String secret = "C00lSecretKeyF0rMyAP1";
	
	/**
	 * Create a token for the user in parameter
	 * @param pUser User :
	 * 			the user we want to create a token
	 * @return String : the generated token
	 */
	public String createToken(User pUser) {
		
		logger.info("Create token for user : " + pUser.getId());
		
		final JWTSigner vSigner = new JWTSigner(secret);
		
		final Map<String, Object> vMap = new HashMap<String, Object>();
		vMap.put("id", pUser.getId());
		vMap.put("login", pUser.getLogin());
		
		final String vToken = vSigner.sign(vMap);
		
		logger.info("End create token : " + vToken);
		
		return vToken;
	}
}
