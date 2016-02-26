angular.module('serviceMock', [])
  .provider('SessionService', function() {
    this.vAuthenticated = false;
    this.$get = function() {
      return  {
    	  	isUserAuthenticated : function () {
    	      return this.vAuthenticated;
    	    },
    	    getUserId : function () {
    	      return (this.vAuthenticated ? 1 : null);
    	    },
    	    getToken : function () {
    	      return (this.vAuthenticated ? 'gjhgjhgjhgj' : null);
    	    },
    	    setUserAuthenticated : function () {
    	    	this.vAuthenticated = true;
    	    },
    	    resetUserAuthenticated : function() {
    	    	this.vAuthenticated = false;
    	    } 
    	  };
      };
    });