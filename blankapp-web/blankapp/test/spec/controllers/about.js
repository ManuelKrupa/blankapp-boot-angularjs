'use strict';

describe('AboutCtrl', function () {

  beforeEach(module('blankApp'));
	
  var createController,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    createController = function() {
    	$controller('AboutCtrl', {
    		'$scope': scope
    	});
    };
  }));

  it('should attach a list of awesomeThings to the scope', function () {
	  createController();
	  expect(scope.awesomeThings.length).toBe(3);
  });
});
