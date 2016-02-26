'use strict';

/**
 * @ngdoc function
 * @name blankApp.service:ProfileManagementService
 * 
 * Le service SessionService permet de gerer les profils utilisateur  
 */
angular.module('blankApp')
  .service('ProfileManagementService', function ($resource, BACKEND_URL, CONTEXT) {
    
	// Resource for profile requests
	var ProfileResource = $resource(BACKEND_URL + CONTEXT + '/profiles/:profileId', {profileId: '@id'}, {
		update: {
			method: 'PUT' // this method issues a PUT request
		}
	});
	
	// Resource for user requests
	var HabilitationResource = $resource(BACKEND_URL + CONTEXT + '/habilitations');
    
    // Fonction renvoyant la liste des profils
    this.getAllProfiles = function () {
      return ProfileResource.query();
    };
    
    // Fonction renvoyant la liste des habilitations
    this.getAllHabilitations = function() {
    	return HabilitationResource.query();
    };
    
    // Fonction creant un nouveau profil
    this.createProfile = function(pProfile) {
    	var vProfile = new ProfileResource();
    	vProfile.libelle = pProfile.libelle;
    	vProfile.description = pProfile.description;
    	vProfile.habilitations = pProfile.habilitations;
    	return vProfile.$save();
    };
    
    
    // Fonction renvoyant un profil
    this.getProfileDetails = function (pIdProfile) {
    	var vProfile = ProfileResource.get({profileId: pIdProfile});
    	return vProfile;
    };
    
    
    // Fonction mettant a jour un profil
    this.editProfile = function (pProfile) {
    	var vDto = this.getProfileDetails(pProfile.id);
        console.log('get profile ok');
    	console.log(vDto);
    	var vProfile = new ProfileResource();
    	vDto.$promise.then(function() {
    		console.log('promise get profile');
        	vProfile.id = pProfile.id;
    		vProfile.libelle = vDto.libelle;
        	vProfile.description = vDto.description;
        	// only habilitations can change
    		vProfile.habilitations = pProfile.habilitations;
        	vProfile.$update();	
    	});
    	
    	return vProfile;
    };
    
    // Fonction appelant le service REST de suppression de profil
    this.deleteProfile = function(pProfileId) {
    	var vProfile = ProfileResource.delete({profileId: pProfileId});
    	return vProfile;
    };
    
  });