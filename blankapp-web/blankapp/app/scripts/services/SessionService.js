'use strict';

/**
 * @ngdoc function
 * @name blankApp.service:SessionService
 * 
 * Le service SessionService permet de gerer l'etat de connexion du user  
 */
angular.module('blankApp')
  .service('SessionService', function (ipCookie) {
    
    // Nom du cookie
    var cookieName = 'blankappUser';
    
    // Fonction renvoyant vrai si l'utilisateur est authentifie
    this.isUserAuthenticated = function () {
      var vUserConnecte = ipCookie(cookieName),
      		vAuthenticated = true;
      if (vUserConnecte === null || angular.isUndefined(vUserConnecte) || !vUserConnecte) {
    	  vAuthenticated = false;
      }
      return vAuthenticated;
    };
    
    // Fonction renvoyant le user connecte
    this.getUserId = function () {
      var vStr = ipCookie(cookieName);
      if (vStr !== null && !angular.isUndefined(vStr)) {
    	  return vStr.split(':')[0];
      }
      return vStr;
    };
    
    // Fonction renvoyant le token
    this.getToken = function () {
      var vStr = ipCookie(cookieName);
      if (vStr !== null && !angular.isUndefined(vStr)) {
    	  return vStr.split(':')[1];
      }
      return vStr;
    };
    
    // Fonction authentifiant un user
    this.setUserAuthenticated = function (pToken, pUserId) {
    	
    	// Cookie is set for one day
    	ipCookie(cookieName, pUserId+':'+pToken, {expires:1});
    };
    
    // Fonction faisant un logout du user
    this.resetUserAuthenticated = function() {
    	ipCookie.remove(cookieName);
    	this.isUserAuthenticated();
    };
    
  });