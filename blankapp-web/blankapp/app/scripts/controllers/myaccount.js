'use strict';

/**
 * @ngdoc function
 * @name blankApp.controller:ChangePasswordCtrl
 * @description
 * # ChangePasswordCtrl
 * Controller of the blankApp
 */
angular.module('blankApp')
  .controller('ChangePasswordCtrl', function ($scope, UserManagementService, $translate, SessionService, $location) {
    
    console.log('change password - INIT');
    
      // ces variables permettent d afficher les erreurs metier
	  $scope.errorTextAlert = '';
	  $scope.showErrorAlert = false;
    
    // Fonction appelee lors du click sur le bouton d'enregistrement
    $scope.changePassword = function() {
      
      // on verifie que le formulaire est OK
      if ($scope.passwordForm.$valid) {
        console.log('change password form valid');
        
        if($scope.user.newpwd === $scope.user.confirmpwd) {
        	
        	var userId = SessionService.getUserId();
        	
        	var vRetour = UserManagementService.updatePassword(userId, $scope.user.newpwd);
        	
        	console.log(vRetour);
        	
		    $location.path('/home');
        	
        } else {
        	console.log('newpwd and confirpwd are different');
        	$translate('error.myaccount.password.different').then(function(translatedValue) {
	    		$scope.errorTextAlert = translatedValue;
				$scope.showErrorAlert = true;
			});
        }
        
      } else {
        console.log('change password form invalid');
      }
    };
    
  });
