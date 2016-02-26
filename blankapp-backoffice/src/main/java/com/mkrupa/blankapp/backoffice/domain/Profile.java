package com.mkrupa.blankapp.backoffice.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Domain class for Profiles
 * @author mkrupa
 *
 */
@Entity
@Table(name="BAPP_PROFILES")
public class Profile {

	/** Identifiant Profil */
	@Id
	@GeneratedValue
	@Column(name="PROFILE_ID")
	private Long id;
	
	/** Libelle profil */
	@Column(name="PROFILE_LIBELLE", unique=true)
	private String libelle;
	
	/** Description profil */
	@Column(name="PROFILE_DESC")
	private String description;
	
	/** Liste des habilitations du profil */
	@ManyToMany
	@JoinTable(
		      name="BAPP_PROFILE_HABILITATION",
		      joinColumns={@JoinColumn(name="PRFHAB_PROFILE_ID", referencedColumnName="PROFILE_ID")},
		      inverseJoinColumns={@JoinColumn(name="PRFHAB_HABILITATION_ID", referencedColumnName="HABILITATION_ID")})
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
	 * Set the new profile label
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
	 * Return the profile habilitations
	 * @return List<Habilitation> : the habilitations
	 */
	public List<Habilitation> getHabilitations() {
		return habilitations;
	}

	/**
	 * Set the profile habilitations
	 * @param pHabilitations List<Habilitation> :
	 * 		the new habilitations
	 */
	public void setHabilitations(List<Habilitation> pHabilitations) {
		this.habilitations = pHabilitations;
	}
}
