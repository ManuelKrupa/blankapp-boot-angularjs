'use strict';

/**
 * @ngdoc function
 * @name blankApp.controller:UsersCtrl
 * @description
 * # UsersCtrl
 * Controller of the blankApp
 */
angular.module('blankApp')
  .controller('UsersCtrl', function ($scope, UserManagementService) {
   
   $scope.users = UserManagementService.getAllUsers();
   
   // Fonction effectuant la suppression d'un utilisateur
   $scope.deleteUser = function(pUserId) {
	   
	   // Appel du service de suppression de l'utilisateur
	   var vRetour = UserManagementService.deleteUser(pUserId);  
	   
	   // Une fois l'utilisateur supprime, on met a jour la liste des utilisateurs cote Frontend
	   vRetour.$promise.then(function(){
		   var vIndex = 0;
		   angular.forEach($scope.users, function(value){
			 if (value.id === pUserId) {
				 $scope.users.splice(vIndex, 1);
			 }
			 vIndex++;
		  }, function(error) {
			  console.log(error);
		  }); 
	   });
   };
    
  });