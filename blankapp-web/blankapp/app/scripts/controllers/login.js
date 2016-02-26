'use strict';

/**
 * @ngdoc function
 * @name blankApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the blankApp
 */
angular.module('blankApp')
  .controller('LoginCtrl', function ($scope, $location, SessionService, LoginService, 
		  $rootScope, UserManagementService, TokenService) {
    
    // la variable loginFail sert a afficher le bloc d'utilisateur inconnu
    $scope.loginFail=false;
    
    console.log('login');
    
    // si l'utilisateur est logge, on le redirige vers la page /main
    if (SessionService.isUserAuthenticated()) {
    	console.log('Authenticated user : redirect to /home');
      $location.path('/home');
    }
    
    // Fonction appelee lors du click sur le bouton de login
    $scope.loginUser = function() {
      
      // on verifie que le formulaire est OK
      if ($scope.loginForm.$valid) {
        console.log('login form valid');
        
        var vUser = {
        		login: $scope.user.email,
        		password: $scope.user.password
        };
        
        // we get a promise
        var vLoggedIn = LoginService.logUser(vUser);
        
        console.log(vLoggedIn);
        
        // we add a function call on the resulting promise
        vLoggedIn.then(function(result) {
        	
        	console.log(result);
        	
        	if (angular.isUndefined(result.value) || result.value === null) {
                
        		// On supprime le user authentifie
                SessionService.resetUserAuthenticated();
                
                console.log('login/password invalid');
                
                // Ajouter un message d'erreur
                $scope.loginFail=true;
                
            } else {
                
                $scope.loginFail = false;
                
                var currentUser = TokenService.getUserFromToken(result.value);
                
                var vResult = UserManagementService.getUserDetails(currentUser.id);
                
                vResult.$promise.then(function(resultUser){
                	
                	$rootScope.user = resultUser;
                	// On sauvegarde l'authentification
                    SessionService.setUserAuthenticated(result.value, currentUser.id);
                	
                    // Ajouter la redirection vers la page /main 
                    $location.path( '/home' );
                });
            }
        });
        
      } else {
        console.log('login form invalid');
        $scope.loginFail=true;
      }
    };
    
  });
