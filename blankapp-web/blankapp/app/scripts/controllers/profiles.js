'use strict';

/**
 * @ngdoc function
 * @name blankApp.controller:ProfilesCtrl
 * @description
 * # ProfilesCtrl
 * Controller of the blankApp
 */
angular.module('blankApp')
  .controller('ProfilesCtrl', function ($scope, ProfileManagementService, $translate) {
   
   $scope.errorTextAlert = '';
   $scope.showErrorAlert = false;
	  
   $scope.profils = ProfileManagementService.getAllProfiles();
   
   
   // Fonction effectuant la suppression du profil
   $scope.deleteProfile = function(pProfileId) {
	   
	   // Appel du service de suppression du profil
	   var vRetour = ProfileManagementService.deleteProfile(pProfileId);  
	   
	   // Une fois le profil supprime, on met a jour la liste des profils cote Frontend
	   // Sinon on affiche le message d'erreur retourne par l'API
	   vRetour.$promise.then(function(){
		   var vIndex = 0;
		   angular.forEach($scope.profils, function(value){
			 if (value.id === pProfileId) {
				 $scope.profils.splice(vIndex, 1);
			 }
			 vIndex++;
		  }); 
	   }, function(error) {
		   console.log(error);
		   $translate(error.data).then(function(translatedValue) {
			   $scope.errorTextAlert = translatedValue;
			   $scope.showErrorAlert = true;
		   });
	   });
   };
    
  });