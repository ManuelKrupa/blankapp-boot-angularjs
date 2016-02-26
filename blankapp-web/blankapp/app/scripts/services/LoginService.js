'use strict';

/**
 * @ngdoc function
 * @name blankApp.service:LoginService
 * 
 * Le service LoginService permet de valider les identifiants de l'utilisateur  
 */
angular.module('blankApp')
  .service('LoginService', function ($resource, BACKEND_URL, CONTEXT) {
    
	// Resource for login requests
	var LoginResource = $resource(BACKEND_URL + CONTEXT + '/login');
	
	
    // Fonction appelant le service de verification des identifiants cote backend
    this.logUser = function(pUser) {
    	var vLogin = new LoginResource();
    	vLogin.login = pUser.login;
    	vLogin.password = pUser.password;
    	return vLogin.$save();
    };
    
});