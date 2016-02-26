package com.mkrupa.blankapp.backoffice.dto;

import java.util.List;

import com.mkrupa.blankapp.backoffice.domain.Habilitation;


/**
 * DTO class to transfer Profile objects
 * @author mkrupa
 *
 */
public class ProfileDTO {

	/** Identifiant Profil */
	private Long id;
	
	/** Libelle profil */
	private String libelle;
	
	/** Description profil */
	private String description;
	
	/** Number of users having the profile */
	private Long nbUtilisateurs;
	
	/** Rights list */
	private List<Habilitation> habilitations;
	
	/**
	 * Return the id
	 * @return Long : id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the new profile id
	 * @param pId Long :
	 * 		the new id
	 */
	public void setId(Long pId) {
		this.id = pId;
	}

	/**
	 * Return the profile label
	 * @return String : libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Set the new libelle
	 * @param pLibelle String :
	 * 		the new libelle
	 */
	public void setLibelle(String pLibelle) {
		this.libelle = pLibelle;
	}

	/**
	 * Return the profile description
	 * @return String : description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the new profile description
	 * @param pDescription String :
	 * 		the new description
	 */
	public void setDescription(String pDescription) {
		this.description = pDescription;
	}

	/**
	 * Return number of users
	 * @return Long : nbUtilisateurs
	 */
	public Long getNbUtilisateurs() {
		return nbUtilisateurs;
	}

	/**
	 * Set the new number of users
	 * @param pNbUtilisateurs Long :
	 * 		the new number of users
	 */
	public void setNbUtilisateurs(Long pNbUtilisateurs) {
		this.nbUtilisateurs = pNbUtilisateurs;
	}

	/**
	 * Return the rights list
	 * @return List<Habilitation> : the rights list
	 */
	public List<Habilitation> getHabilitations() {
		return habilitations;
	}

	/**
	 * Set the new rights list
	 * @param habilitations List<Habilitation> :
	 * 		the new rights list
	 */
	public void setHabilitations(List<Habilitation> habilitations) {
		this.habilitations = habilitations;
	}
}
