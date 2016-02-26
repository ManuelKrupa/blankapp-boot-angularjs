package com.mkrupa.blankapp.backoffice.exception;

/**
 * Exception to use for business rules exception.
 * 
 * @author mkrupa
 */
public class BusinessException extends Exception {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 3138416898020899075L;
	
	/**
	 * Code attribute used to get correct error message.
	 */
	private String code;
	
	/**
	 * Constructor with message in parameter.
	 * @param pMessage String :
	 * 		the exception message
	 */
	public BusinessException(String pCode, String pMessage) {
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
	public BusinessException(String pCode, String pMessage, Throwable pExc) {
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
