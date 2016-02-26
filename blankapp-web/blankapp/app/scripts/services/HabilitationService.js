'use strict';

/**
 * @ngdoc function
 * @name blankApp.service:HabilitationService
 * 
 * Le service HabilitationService permet de valider les droits de l'utilisateur  
 */
angular.module('blankApp')
  .service('HabilitationService', function ($rootScope, SessionService) {
	
    // Fonction verifiant si l'utilisateur
	// possede l'habilitation en parametre
    this.hasRight = function(pRight) {
    	
    	console.log(pRight[0]);
    	var grant = false;
    	if($rootScope.user === null || $rootScope.user === undefined) {
    		return false;
    	} else {
    		
    		angular.forEach($rootScope.user.habilitations, function(value) {
    			if(value.code === pRight[0]) {
    				grant = true;
    			}
    		});
    		console.log('grant : ' + grant);
    		return grant;
    	}
    };
    
    // Fonction appelee lors des changements d'URL afin de verifier si 
    // le user est bien loggue (si login requis)
    // le user possede bien les habilitations demandees
    this.authorize = function (loginRequired, requiredPermissions) {
        var result = 1,
            user = SessionService.getUserId();
        
        if (loginRequired === true && user === undefined) {
            result = -1;
        } else if ((loginRequired === true && user !== undefined) &&
            (requiredPermissions === undefined || requiredPermissions.length === 0)) {
            // Login is required but no specific permissions are specified.
            result = 1;
        } else if (requiredPermissions) {
            
            result = this.hasRight(requiredPermissions) ? 1 : 0;
        }

        return result;
    };
    
});