package com.mkrupa.blankapp.backoffice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Domain class for Habilitations
 * @author mkrupa
 *
 */
@Entity
@Table(name="BAPP_HABILITATIONS")
public class Habilitation {

	/** Identifiant Habilitation */
	@Id
	@GeneratedValue
	@Column(name="HABILITATION_ID")
	private Long id;
	
	/** Code habilitation */
	@Column(name="HABILITATION_CODE")
	private String code;
	
	/** Libelle habilitation */
	@Column(name="HABILITATION_LABEL")
	private String label;

	/**
	 * Return the ID
	 * @return Long : id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the habilitation ID
	 * @param id Long :
	 * 		the new ID
	 */
	public void setId(Long pId) {
		this.id = pId;
	}
	
	/**
	 * Return the habilitation code
	 * @return String : code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Set the new code
	 * @param pCode String :
	 * 			the new code
	 */
	public void setCode(String pCode) {
		this.code = pCode;
	}

	/**
	 * Return the habilitation name
	 * @return String : label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set the habilitation name
	 * @param label String :
	 * 		the new label
	 */
	public void setLabel(String pLabel) {
		this.label = pLabel;
	}
}
