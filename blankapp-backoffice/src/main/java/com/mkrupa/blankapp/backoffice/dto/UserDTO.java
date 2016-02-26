package com.mkrupa.blankapp.backoffice.dto;

import java.util.List;

import com.mkrupa.blankapp.backoffice.domain.Habilitation;


/**
 * DTO class to transfer User objects
 * @author mkrupa
 *
 */
public class UserDTO {

	/** Identifiant User */
	private Long id;
	
	/** Nom user */
	private String nom;
	
	/** Prenom user */
	private String prenom;
	
	/** Login user */
	private String login;
	
	/** User password */
	private String password;
	
	/** Profil user */
	private String profile;
	
	/** Profil ID user */
	private Long profileId;
	
	/** User habilitations */
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
	 * Return the nom
	 * @return String : the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Set the new nom
	 * @param pNom String :
	 * 		the new nom
	 */
	public void setNom(String pNom) {
		this.nom = pNom;
	}

	/**
	 * Return the prenom
	 * @return String : the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Set the new prenom
	 * @param pPrenom String :
	 * 		the new prenom
	 */
	public void setPrenom(String pPrenom) {
		this.prenom = pPrenom;
	}
	
	/**
	 * Return the login
	 * @return String : the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Set the new login
	 * @param pLogin String :
	 * 			the new login
	 */
	public void setLogin(String pLogin) {
		this.login = pLogin;
	}
	
	/**
	 * Return the password
	 * @return String : the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the new password
	 * @param password String :
	 * 			the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Return the profile
	 * @return String : the profile
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * Set the new profile
	 * @param pProfile String :
	 * 		the new profile
	 */
	public void setProfile(String pProfile) {
		this.profile = pProfile;
	}

	/**
	 * Return the profile ID
	 * @return Long : the profile ID
	 */
	public Long getProfileId() {
		return profileId;
	}

	/**
	 * Set the new profile ID
	 * @param pProfileId Long :
	 * 			the profile ID
	 */
	public void setProfileId(Long pProfileId) {
		this.profileId = pProfileId;
	}

	/**
	 * Return the user rights
	 * @return List<Habilitation> : right list
	 */
	public List<Habilitation> getHabilitations() {
		return habilitations;
	}

	/**
	 * Set the new user rights
	 * @param pHabilitations List<Habilitation> :
	 * 			the new right list
	 */
	public void setHabilitations(List<Habilitation> pHabilitations) {
		this.habilitations = pHabilitations;
	}
}
