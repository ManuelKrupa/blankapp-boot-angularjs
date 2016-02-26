package com.mkrupa.blankapp.backoffice.dto;

/**
 * DTO class to transfer simple objects like Boolean (AngularJS patch)
 * 
 * @author mkrupa
 */
public class ValueDTO {
	
	/** The object to transfer */
	private Object value;

	/**
	 * Constructor
	 * @param pValue Object :
	 * 		the object to transfer
	 */
    public ValueDTO(Object pValue) {
        this.value = pValue;
    }

    /**
     * Return the object to transfer
     * @return Object : the object
     */
    public Object getValue() {
        return value;
    }

    /**
     * Set the new object to transfer
     * @param pValue Object :
     * 		the new object
     */
    public void setValue(Object pValue) {
        this.value = pValue;
    }

}
