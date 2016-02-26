'use strict';

/**
 * @ngdoc function
 * @name blankApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the blankApp
 */
angular.module('blankApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
