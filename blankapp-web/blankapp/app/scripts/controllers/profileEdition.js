'use strict';

/**
 * @ngdoc function
 * @name blankApp.controller:ProfileEditionCtrl
 * @description
 * # ProfileEditionCtrl
 * Controller of the blankApp
 */
angular.module('blankApp')
  .controller('ProfileEditionCtrl', function ($scope, ProfileManagementService, $location, $routeParams, $translate) {
   
	  // la variable edition fail permet d'afficher les messages d'erreur sur enregistrement
	  $scope.editionFail = false;
	  
	  // ces variables permettent d afficher les erreurs metier
	  $scope.errorTextAlert = '';
	  $scope.showErrorAlert = false;
	  
	  $scope.habilitations = ProfileManagementService.getAllHabilitations();
	  
	  if ($routeParams.profileId) {
		  console.log('profile edition - ' + $routeParams.profileId);
		  // si on est en edition, on recupere le profil
		  var vProfile = ProfileManagementService.getProfileDetails($routeParams.profileId);
		  console.log(vProfile);
		  // The variable vProfile is a promise so we have to chain the result
		  vProfile.$promise.then(function(result) {
			  console.log(result);
			  $scope.profile = result;
			  $scope.checkedHabilitations = $scope.profile.habilitations; 
		  });
	  } else {
		  console.log('profile creation');
		  // si on est en mode creation, il n'y a pas d'habilitation
		  $scope.checkedHabilitations = [];
	  }
	  
	  // Function call each time an habilitation is selected/unselected
	  $scope.selectHab = function(pHabSelected) {
		  
          var vIdx = -1;
          var vExist = false;
          angular.forEach($scope.checkedHabilitations, function(value) {
        	  vIdx++;
        	  if(value.id === pHabSelected.id) {
        		 $scope.checkedHabilitations.splice(vIdx, 1);
                 vExist = true;
        	  } 
          });
          console.log('Exist : ' + vExist);
          if (!vExist) {
        	  $scope.checkedHabilitations.push(pHabSelected);
          }
	  };
	  
	  // Function call at initialization of the checkbox
	  $scope.habIsChecked = function(pHabSelected) {
		  var vExist = false;
		  angular.forEach($scope.checkedHabilitations, function(value) {
        	  if(value.id === pHabSelected.id) {
        		 vExist = true;
        	  } 
          });
		  
		  // is currently selected
		  if (vExist) {
			  return 'checked';
		  }
	  };
	  
	  // Function call to cancel edition
	  // Go to profiles page
	  $scope.cancelProfile = function() {
		  $location.path('/profiles');
	  };
	  
	  // Function call to save modifications
	  // At the end, go to profile page
	  $scope.createProfile = function() {
	      
	      // on verifie que le formulaire est OK
	      if ($scope.createForm.$valid) {
	        console.log('create profile form valid');
	        
	        // on construit le profil
		    var vProfile = {
		    	libelle: $scope.profile.name,
		        description: $scope.profile.description,
		        habilitations: $scope.checkedHabilitations
		    };
	        
		    var vRetour = ProfileManagementService.createProfile(vProfile);
		    
		    // The variable vRetour is a promise so we have to chain the result
		    vRetour.then(function(result) {
		    	console.log(result);
		    	$location.path('/profiles');
		    }, function(error) {
		    	console.log(error);
		    	$translate(error.data).then(function(translatedValue) {
		    		$scope.errorTextAlert = translatedValue;
					$scope.showErrorAlert = true;
				});
		    });
		    
	      } else {
	        console.log('create profile form invalid');
	        $scope.editionFail=true;
	      }
	    
	  };
	  
	  // Function call to save modifications of profile edition
	  // At the end, go to profile page
	  $scope.editProfile = function() {
		  
		  // on verifie que le formulaire est OK
		  if ($scope.createForm.$valid) {
			  	console.log('edit profile form valid');
		        
		        // on construit le profil
			    var vProfile = {
			    	id: $scope.profile.id,
			        habilitations: $scope.checkedHabilitations
			    };
		        
			    var vRetour = ProfileManagementService.editProfile(vProfile);
			    console.log('retour');
			    console.log(vRetour);
			    $location.path('/profiles');
		  }
	  };
  });