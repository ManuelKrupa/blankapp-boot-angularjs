'use strict';

/**
 * @ngdoc function
 * @name blankApp.service:TokenService
 * 
 * Le service TokenService permet de gerer les operations liees au token  
 */
angular.module('blankApp')
  .service('TokenService', function () {
    
	/**
	 * Decode Base64 token.
	 */
    this.urlBase64Decode = function (encodeStr) {
        var output = encodeStr.replace('-', '+').replace('_', '/');
        switch (output.length % 4) {
            case 0:
                break;
            case 2:
                output += '==';
                break;
            case 3:
                output += '=';
                break;
            default:
                throw 'Illegal base64url string!';
        }
        return window.atob(output);
    };

    /**
     * Get user datas from token
     */
    this.getUserFromToken = function(token) {
        var user = null;
        if (!angular.isUndefined(token)) {
            var encoded = token.split('.')[1];
            user = JSON.parse(this.urlBase64Decode(encoded));
        }
        return user;
    };
    
  });