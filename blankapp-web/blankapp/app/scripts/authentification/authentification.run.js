'use strict';

angular.module('blankApp').run(function ($rootScope, $location,
		HabilitationService, SessionService, UserManagementService) {
        
		$rootScope.$on('$routeChangeStart', function (event, next) {
            var authorised;
            if (next.access !== undefined) {
                authorised = HabilitationService.authorize(next.access.requiresLogin,
                                                     next.access.requiredPermissions);
                if (authorised === -1) {
                    $location.path('/login');
                } else if (authorised === 0) {
                    $location.path('/unauthorized').replace();
                }
            }
        });
        
        // test if cookie exist and then load the user in rootScope if not exist
        var vUserId = SessionService.getUserId();
        if(vUserId !== null && !angular.isUndefined(vUserId)) {
        	console.log('cookie exist'); 
        	var vResult = UserManagementService.getUserDetails(vUserId);
        	vResult.$promise.then(function(resultUser) {
             	
                 // On sauvegarde l'authentification
                 $rootScope.user = resultUser;
                 
                 // Ajouter la redirection vers la page /main 
                 $location.path( '/home' );
             });
        } else {
        	console.log('No cookie');
        	$location.path('/login');
        }
    });