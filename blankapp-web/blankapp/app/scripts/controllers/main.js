'use strict';

/**
 * @ngdoc function
 * @name blankApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the blankApp
 */
angular.module('blankApp')
  .controller('MainCtrl', function ($scope, $location, SessionService) {
    
    // On recupere le user connecte
    $scope.user = SessionService.getUserId();
    
    // On ajoute un watcher sur l'attribut isAuthenticated pour gerer la deconnexion
    $scope.$watch(SessionService.isUserAuthenticated, function () {
    	if(SessionService.isUserAuthenticated()) {
            console.log('Connect');
          } else {
            console.log('Disconnect');
          }
    }, true);
    
});
