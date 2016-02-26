package com.mkrupa.blankapp.backoffice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Domain class for Users
 * @author mkrupa
 *
 */
@Entity
@Table(name="BAPP_USERS")
public class User {

	/** Identifiant Utilisateur */
	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Long id;
	
	/** Nom de l'utilisateur */
	@Column(name="USER_NOM")
	private String nom;
	
	/** Prenom de l'utilisateur */
	@Column(name="USER_PRENOM")
	private String prenom;
	
	/** Login de l'utilisateur */
	@Column(name="USER_LOGIN")
	private String login;
	
	/** Password de l'utilisateur */
	@Column(name="USER_PASSWORD")
	private String password;
	
	/** JSON Web Token of the user */
	@Column(name="USER_JWT")
	private String jwt;
	
	/** Profil de l'utilisateur */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_PROFILE_ID", nullable = false)
	private Profile profil;

	/**
	 * Return the user id
	 * @return Long : id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the new user id
	 * @param pId Long :
	 * 		the new id
	 */
	public void setId(Long pId) {
		this.id = pId;
	}

	/**
	 * Return the user firstname
	 * @return String : nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Set the new user firstname
	 * @param pNom String :
	 * 		the new nom
	 */
	public void setNom(String pNom) {
		this.nom = pNom;
	}

	/**
	 * Return the user lastname
	 * @return String : prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Set the new user lastname
	 * @param pPrenom String :
	 * 		the new prenom
	 */
	public void setPrenom(String pPrenom) {
		this.prenom = pPrenom;
	}

	/**
	 * Return the user login
	 * @return String : login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Set the new user login
	 * @param pLogin String :
	 * 		the new login
	 */
	public void setLogin(String pLogin) {
		this.login = pLogin;
	}

	/**
	 * Return the password
	 * @return String : password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the new user password
	 * @param pPassword String :
	 * 		the new password
	 */
	public void setPassword(String pPassword) {
		this.password = pPassword;
	}
	
	/**
	 * Return the JSON Web Token
	 * @return String : the jwt
	 */
	public String getJwt() {
		return jwt;
	}

	/**
	 * Set the new JSON Web Token
	 * @param pJwt String :
	 * 			the new jwt
	 */
	public void setJwt(String pJwt) {
		this.jwt = pJwt;
	}

	/**
	 * Return the user profile
	 * @return Profile : profil
	 */
	public Profile getProfil() {
		return profil;
	}

	/**
	 * Set the new user profile
	 * @param pProfil Profile :
	 * 		the new profil
	 */
	public void setProfil(Profile pProfil) {
		this.profil = pProfil;
	}
}
