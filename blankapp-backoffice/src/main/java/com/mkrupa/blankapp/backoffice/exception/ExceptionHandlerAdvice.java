package com.mkrupa.blankapp.backoffice.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Central class that manage behaviours for API exception.
 * 
 * @author mkrupa
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

	/** Logger */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Return 412 http status code if business exception is reached.
	 * @param pReq HttpServletRequest :
	 * 			the HTTP request
	 * @param pEx BusinessException :
	 * 			exception of business type
	 * @return String : the exception code
	 */
	@ExceptionHandler(BusinessException.class)
    @ResponseStatus(value=HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public String businessException(HttpServletRequest pReq, BusinessException pEx) {
        logger.warn("Business Exception in API : " + pEx.getCode());
		return pEx.getCode();
    }
	
	/**
	 * Return 403 http status code if token exception is reached.
	 * @param pReq HttpServletRequest :
	 * 			the HTTP request
	 * @param pEx TokenException :
	 * 			exception of token type
	 * @return String : the exception code
	 */
	@ExceptionHandler(TokenException.class)
    @ResponseStatus(value=HttpStatus.FORBIDDEN)
    @ResponseBody
    public String tokenException(HttpServletRequest pReq, TokenException pEx) {
        logger.warn("Token Exception in API : " + pEx.getCode());
		return pEx.getCode();
    }
}
