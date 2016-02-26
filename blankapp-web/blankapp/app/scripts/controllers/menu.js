'use strict';

/**
 * @ngdoc function
 * @name blankApp.controller:MenuCtrl
 * @description
 * # MenuCtrl
 * Controller of the blankApp
 */
angular.module('blankApp')
  .controller('MenuCtrl', function ($scope, SessionService, $location, HabilitationService) {
    
    // Variable permettant d'afficher la barre de navigation
    $scope.isAuthenticated = SessionService.isUserAuthenticated();
    
    // Variables permettant d'afficher les liens vers la liste des profils et des users
    $scope.seeProfiles = HabilitationService.hasRight(['profiles.list']);
    $scope.seeUsers = HabilitationService.hasRight(['users.list']);
    
    // On ajoute un watcher sur l'attribut isAuthenticated pour gerer la connexion/deconnexion
    $scope.$watch(SessionService.isUserAuthenticated, function () {
      if(SessionService.isUserAuthenticated()) {
        console.log('Show menu');
        $scope.isAuthenticated = true;
        $scope.seeProfiles = HabilitationService.hasRight(['profiles.list']);
        $scope.seeUsers = HabilitationService.hasRight(['users.list']);
      } else {
        console.log('Hide menu');
        $scope.isAuthenticated = false;
      }
    }, true);
    
    //Fonction permettant d'afficher l'option active dans le menu
    $scope.isActive = function (viewLocation) {
      return viewLocation === $location.path();
    };
  });