'use strict';

/**
 * @ngdoc overview
 * @name blankApp
 * @description
 * # blankApp
 *
 * Main module of the application.
 */
//Declarer ici tous les modules a utiliser dans l'application
var myApp = angular.module('blankApp', [
    'ngAnimate',
    'ngCookies',
    'ipCookie',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.sortable',
    'pascalprecht.translate',// angular-translate
    'tmh.dynamicLocale'// angular-dynamic-locale
  ]);

// Configure les routes pour l'application
myApp.config(function ($routeProvider) {
    $routeProvider
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .when('/home', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        access: {
            requiresLogin: true
        }
      })
      .when('/users', {
        templateUrl: 'views/users/list.html',
        controller: 'UsersCtrl',
        access: {
            requiresLogin: true,
            requiredPermissions: ['users.list']
        }
      })
      .when('/users/create', {
        templateUrl: 'views/users/create.html',
        controller: 'UserEditionCtrl',
        access: {
            requiresLogin: true,
            requiredPermissions: ['users.create']
        }
      })
      .when('/users/:userId', {
        templateUrl: 'views/users/create.html',
        controller: 'UserEditionCtrl',
        access: {
            requiresLogin: true,
            requiredPermissions: ['users.edit']
        }
      })
      .when('/profiles', {
        templateUrl: 'views/profiles/list.html',
        controller: 'ProfilesCtrl',
        access: {
            requiresLogin: true,
            requiredPermissions: ['profiles.list']
        }
      })
      .when('/profiles/create', {
        templateUrl: 'views/profiles/create.html',
        controller: 'ProfileEditionCtrl',
        access: {
            requiresLogin: true,
            requiredPermissions: ['profiles.create']
        }
      })
      .when('/profiles/:profileId', {
        templateUrl: 'views/profiles/edit.html',
        controller: 'ProfileEditionCtrl',
        access: {
            requiresLogin: true,
            requiredPermissions: ['profiles.edit']
        }
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        access: {
            requiresLogin: true
        }
      })
      .when('/myaccount', {
        templateUrl: 'views/changepassword.html',
        controller: 'ChangePasswordCtrl',
        access: {
            requiresLogin: true
        }
      })
      .when('/unauthorized', {
        templateUrl: '404.html'
      })
      .when('/#', {
        redirectTo: '/home'
      })
      .otherwise({
        redirectTo: '/login'
      });
  });

// define locales used in the App
myApp.constant('LOCALES', {
    'locales': {
        'fr-fr': 'Français',
        'en-us': 'English'
    },
    'preferredLocale': 'fr-fr'
  });

// Configure translateProvider
myApp.config(['$translateProvider', function ($translateProvider) {
	 
	  // log forgotten IDs in translation in the developper console
	  $translateProvider.useMissingTranslationHandlerLog();
	  
	  // Asynchronous loading for translations
	  $translateProvider.useStaticFilesLoader({
		  prefix: 'resources/locale-',// path to translations files
		  suffix: '.json'// suffix, currently- extension of the translations
	  });
	  $translateProvider.preferredLanguage('fr-fr');// is applied on first load
	  $translateProvider.useLocalStorage();// saves selected language to localStorage
	  
	  // Enable escaping of HTML (security strategy)
	  $translateProvider.useSanitizeValueStrategy('escaped');
  }]);

// Configure dynamic locale provider
myApp.config(function (tmhDynamicLocaleProvider) {
    tmhDynamicLocaleProvider.localeLocationPattern('bower_components/angular-i18n/angular-locale_{{locale}}.js');
  });

// Configure httpProvider
myApp.config(['$httpProvider', function($httpProvider) {
	
	$httpProvider.interceptors.push(function($q, $location, SessionService) {
		return {
			'request': function (config) {
				config.headers = config.headers || {};
	            var vToken = SessionService.getToken();
	            console.log('token interceptor');
	            console.log(vToken);
	            if (vToken !== null && !angular.isUndefined(vToken)) {
	            	config.headers['X-Auth-Token'] = vToken;
	            }
	            return config;
	        },
	        'responseError': function(response) {
	        	if(response.status === 403) {
	        		$location.path('/login');
	            }
	            return $q.reject(response);
	        }
	    };
	});
	
}]);