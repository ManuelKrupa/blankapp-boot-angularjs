'use strict';

/**
 * @ngdoc function
 * @name blankApp.service:ProfileManagementService
 * 
 * Le service SessionService permet de gerer les profils utilisateur  
 */
angular.module('blankApp')
  .service('UserManagementService', function ($resource, BACKEND_URL, CONTEXT) {
    
	// Resource for profile requests
	var UserResource = $resource(BACKEND_URL + CONTEXT + '/users/:userId', {userId: '@id'}, {
		update: {
			method: 'PUT' // this method issues a PUT request
		}
	});
	
    // Fonction renvoyant la liste des utilisateurs
    this.getAllUsers = function () {
      return UserResource.query();
    };
    
    // Fonction creant un nouvel utilisateur
    this.createUser = function(pUser) {
    	var vUser = new UserResource();
    	vUser.nom = pUser.nom;
    	vUser.prenom = pUser.prenom;
    	vUser.login = pUser.login;
    	vUser.password = pUser.password;
    	vUser.profileId = pUser.profileId;
    	return vUser.$save();
    };
    
    
    // Fonction renvoyant un utilisateur
    this.getUserDetails = function (pIdUser) {
    	var vUser = UserResource.get({userId: pIdUser});
    	return vUser;
    };
    
    
    // Fonction mettant a jour un utilisateur
    this.editUser = function (pUser) {
    	var vDto = this.getUserDetails(pUser.id);
        console.log('get user ok');
    	console.log(vDto);
    	var vUser = new UserResource();
    	vDto.$promise.then(function() {
    		console.log('promise get user');
        	vUser.id = pUser.id;
        	vUser.login = vDto.login;
        	// only nom, prenom, password and profile can be change
        	vUser.nom = pUser.nom;
        	vUser.prenom = pUser.prenom;
        	vUser.password = pUser.password;
        	vUser.profileId = pUser.profileId;
        	vUser.$update();	
    	});
    	return vUser;
    };
    
    // Fonction appelant le service REST de suppression d'utilisateur
    this.deleteUser = function(pUserId) {
    	var vUser = UserResource.delete({userId: pUserId});
    	return vUser;
    };
    
    // Fonction effectuant la modification du mot de passe
    this.updatePassword = function(pUserId, newPwd) {
    	// on va rechercher le user
    	var vDto = this.getUserDetails(pUserId);
        console.log(vDto);
    	var vUser = new UserResource();
    	vDto.$promise.then(function() {
    		console.log('promise get user');
        	vUser.id = pUserId;
        	vUser.login = vDto.login;
        	vUser.nom = vDto.nom;
        	vUser.prenom = vDto.prenom;
        	vUser.profileId = vDto.profileId;
        	// only password is changed
        	vUser.password = newPwd;
        	vUser.$update();	
    	});
    	return vUser;
    };
    
  });