package com.mkrupa.blankapp.backoffice.exception;

/**
 * Exception to use for token security exception.
 * 
 * @author mkrupa
 */
public class TokenException extends Exception {

	/** UID. */
	private static final long serialVersionUID = 8212304326847601483L;
	
	/**
	 * Code attribute used to get correct error message.
	 */
	private String code;
	
	/**
	 * Constructor with message in parameter.
	 * @param pMessage String :
	 * 		the exception message
	 */
	public TokenException(String pCode, String pMessage) {
		super(pMessage);
		code = pCode;
	}
	
	/**
	 * Constructor with message and root cause.
	 * @param pMessage String :
	 * 		the exception message
	 * @param pExc Throwable :
	 * 		the root cause
	 */
	public TokenException(String pCode, String pMessage, Throwable pExc) {
		super(pMessage, pExc);
		code = pCode;
	}
	
	/**
	 * Return the error code.
	 * @return String : the error code
	 */
	public String getCode() {
		return code;
	}
}
