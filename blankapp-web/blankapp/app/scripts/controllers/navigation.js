'use strict';

/**
 * @ngdoc function
 * @name blankApp.controller:NavigationCtrl
 * @description
 * # NavigationCtrl
 * Controller of the blankApp
 */
angular.module('blankApp')
  .controller('NavigationCtrl', function ($scope, SessionService, $rootScope) {
    
    // Variable permettant d'afficher la barre de navigation
    $scope.isAuthenticated = SessionService.isUserAuthenticated();
    
    // Fonction recuperant le nom du user connecte
    $scope.getUserConnecte = function() {
      if ($rootScope === null || angular.isUndefined($rootScope.user) || $rootScope.user === null) {
    	  return '';
      }else{
    	  return $rootScope.user.login;
      }
    };
    
    // On ajoute un watcher sur l'attribut isAuthenticated pour gerer la connexion/deconnexion
    $scope.$watch(SessionService.isUserAuthenticated, function () {
      $scope.isAuthenticated = SessionService.isUserAuthenticated();
    }, true);
    
    // Fonction de deconnexion
    $scope.disconnect = function() {
      $rootScope.user = null;
      SessionService.resetUserAuthenticated();
    };
  });