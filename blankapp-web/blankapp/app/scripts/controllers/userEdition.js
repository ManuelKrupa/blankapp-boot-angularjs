'use strict';

/**
 * @ngdoc function
 * @name blankApp.controller:UserEditionCtrl
 * @description
 * # UserEditionCtrl
 * Controller of the blankApp
 */
angular.module('blankApp')
  .controller('UserEditionCtrl', function ($scope, ProfileManagementService, UserManagementService, 
		  $location, $routeParams, $translate) {
   
	  // la variable edition fail permet d'afficher les messages d'erreur sur enregistrement
	  $scope.editionFail = false;
	  
	  // variable decrivant si l'on est en mode creation ou modification
	  // true => modification
	  // false => creation
	  $scope.isEdition = true;
	  
	  // ces variables permettent d afficher les erreurs metier
	  $scope.errorTextAlert = '';
	  $scope.showErrorAlert = false;
	  
	  $scope.profiles = ProfileManagementService.getAllProfiles();
	  
	  if ($routeParams.userId) {
		  console.log('user edition - ' + $routeParams.userId);
		  $scope.isEdition = true;
		  // si on est en edition, on recupere l'utilisateur
		  var vUser = UserManagementService.getUserDetails($routeParams.userId);
		  console.log(vUser);
		  // The variable vUser is a promise so we have to chain the result
		  vUser.$promise.then(function(result) {
			  console.log(result);
			  $scope.user = result;
			  
			  // on selectionne le profil de la personne dans la combobox
			  angular.forEach($scope.profiles, function(value) {
	        	  if(value.id === result.profileId) {
	        		 $scope.profileSelected = value;
	              } 
	          });
		  });
	  } else {
		  console.log('user creation');
		  $scope.isEdition = false;
	  }
	  
	  // Function called each time the user select a profile
	  $scope.changedValue=function(pProfile){
		  console.log(pProfile);
		  $scope.user.profileId = pProfile.id;
	  };       
	  
	  // Function call to cancel edition
	  // Go to users page
	  $scope.cancelUser = function() {
		  $location.path('/users');
	  };
	  
	  $scope.createOrUpdateUser = function() {
		  if(!$scope.isEdition) {
			  $scope.createUser();
		  } else {
			  $scope.editUser();
		  }
	  };
	  
	  // Function call to save modifications
	  // At the end, go to users page
	  $scope.createUser = function() {
	      
	      // on verifie que le formulaire est OK
	      if ($scope.createForm.$valid) {
	        console.log('create user form valid');
	        
	        // on construit l'utilisateur
		    var vUser = {
		    	nom: $scope.user.nom,
		    	prenom: $scope.user.prenom,
		    	login: $scope.user.login,
		    	password: $scope.user.password,
		    	profileId: $scope.user.profileId
		    };
	        
		    console.log(vUser);
		    
		    var vRetour = UserManagementService.createUser(vUser);
		    
		    // The variable vRetour is a promise so we have to chain the result
		    vRetour.then(function(result) {
		    	console.log(result);
		    	$location.path('/users');
		    }, function(error) {
		    	console.log(error);
		    	$translate(error.data).then(function(translatedValue) {
		    		$scope.errorTextAlert = translatedValue;
					$scope.showErrorAlert = true;
				});
		    });
		    
	      } else {
	        console.log('create user form invalid');
	        $scope.editionFail=true;
	      }
	    
	  };
	  
	  // Function call to save modifications of user edition
	  // At the end, go to users page
	  $scope.editUser = function() {
		  
		  // on verifie que le formulaire est OK
		  if ($scope.createForm.$valid) {
			  	console.log('edit user form valid');
		        
		        // on construit l'utilisateur
			    var vUser = {
			    	id: $scope.user.id,
			        nom: $scope.user.nom,
			        prenom: $scope.user.prenom,
			        login: $scope.user.login,
			        password: $scope.user.password,
			        profileId: $scope.user.profileId
			    };
		        
			    var vRetour = UserManagementService.editUser(vUser);
			    console.log(vRetour);
			    $location.path('/users');
			    
		  } else {
			  console.log('edit user form invalid');
		      $scope.editionFail=true;
		  }
	  };
  });