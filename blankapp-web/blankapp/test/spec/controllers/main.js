'use strict';

describe('MainCtrl', function () {

  // load controllers/services/... of the module
  beforeEach(module('blankApp'));
  
  // load mock services
  beforeEach(module('serviceMock'));

  var createController, scope, vService;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, _SessionService_) {
	  vService = _SessionService_;
	  scope = $rootScope.$new();
	  createController = function() {
		  $controller('MainCtrl', {
			  '$scope': scope,
			  SessionService: vService
		  });
	  };
  }));
  
  // Test if user is null
  it('User should be null', function() {
	  createController();
	  expect(scope.user).toBeNull();
  });
  
  it('User should be defined', function() {
	  vService.setUserAuthenticated();
	  
	  createController();
	  expect(scope.user).toBeDefined();
  });

  /* Old Tests  
  // Test that there are no todo at application start
  it('should have no todo in the scope', function () {
    expect(scope.todos.length).toBe(0);
  });
  
  // Test add of a todo
  it('should add items to the list', function () {
    scope.todo = 'Test 1';
    scope.addTodo();
    expect(scope.todos.length).toBe(1);
  });

  // Test remove of a todo
  it('should add then remove an item from the list', function () {
    scope.todo = 'Test 1';
    scope.addTodo();
    scope.removeTodo(0);
    expect(scope.todos.length).toBe(0);
  });
*/
});
