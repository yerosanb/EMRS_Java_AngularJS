'use strict';

var myapp = angular
    .module('myApp', ['ngResource', 'ngRoute', 'swaggerUi', 'http-auth-interceptor',
    	'ngAnimate', 'angular-spinkit','ui.select','ngSanitize','angular-loading-bar']);


myapp.constant('USER_ROLES', {
    all: '*',
    admin: 'admin',
    user: 'user'
});

myapp.filter('propsFilter', function() {
	  return function(items, props) {
	    var out = [];

	    if (angular.isArray(items)) {
	      var keys = Object.keys(props);

	      items.forEach(function(item) {
	        var itemMatches = false;

	        for (var i = 0; i < keys.length; i++) {
	          var prop = keys[i];
	          var text = props[prop].toLowerCase();
	          if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
	            itemMatches = true;
	            break;
	          }
	        }

	        if (itemMatches) {
	          out.push(item);
	        }
	      });
	    } else {
	      // Let the output be the input untouched
	      out = items;
	    }

	    return out;
	  };
	});


myapp.config(function ($routeProvider, USER_ROLES, $httpProvider) {


    // Expermental, there is already a progressbar http interceptor
    // let implement reponseInterceptor only here
    $httpProvider.interceptors.push(function($q, $injector, $rootScope) {
        return {
          responseError: function(rejection) {
        	  $rootScope.$broadcast("httpErrorEvent", rejection);
            return $q.reject(rejection);
          }
        };
      });
    
    $routeProvider
    .when("/home", {
        templateUrl: "partials/home.html",
        controller: 'HomeController',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/', {
        redirectTo: '/admin/user'
    }).when('/users', {
        templateUrl: 'partials/users.html',
        controller: 'UsersController',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.admin]
        }
    }).when('/apiDoc', {
        templateUrl: 'partials/apiDoc.html',
        controller: 'ApiDocController',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/tokens', {
        templateUrl: 'partials/tokens.html',
        controller: 'TokensController',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/login', {
        templateUrl: 'partials/login.html',
        controller: 'LoginController',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when('/loading', {
        templateUrl: 'partials/loading.html',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when("/logout", {
        template: " ",
        controller: "LogoutController",
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).when("/error/:code", {
        templateUrl: "partials/error.html",
        controller: "ErrorController",
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    }).otherwise({
        redirectTo: '/error/404',
        access: {
            loginRequired: false,
            authorizedRoles: [USER_ROLES.all]
        }
    })
    
    //Admin
    .when("/admin/user/", {
        templateUrl: "app/pages/admin/account/list.html",
        controller: "AccountListController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    }).when("/admin/user/new", {
        templateUrl: "app/pages/admin/account/edit.html",
        controller: "AccountEditController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    })
    .when("/admin/user/edit/:id", {
        templateUrl: "app/pages/admin/account/edit.html",
        controller: "AccountEditController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    })
    .when("/admin/roles", {
        templateUrl: "app/pages/admin/role/list.html",
        controller: "AccountRoleController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    })
    .when("/admin/role/edit/:id", {
        templateUrl: "app/pages/admin/role/edit.html",
        controller: "AccountRoleController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    })
     .when("/admin/role/new", {
        templateUrl: "app/pages/admin/role/edit.html",
        controller: "AccountRoleController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    })
     .when("/admin/lookups", {
        templateUrl: "app/pages/admin/lookups/list.html",
        controller: "LookupsController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    })
     .when("/admin/lookup/branch", {
        templateUrl: "/app/pages/admin/lookups/branch.html",
        controller: "BranchLookupController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    })
    .when("/admin/lookup/city", {
        templateUrl: "/app/pages/admin/lookups/city.html",
        controller: "CityLookupController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    })
     .when("/admin/lookup/department", {
        templateUrl: "/app/pages/admin/lookups/department.html",
        controller: "DepartmentLookupController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    })
    .when("/admin/menu", {
        templateUrl: "/app/pages/admin/menu/menu-edit.html",
        controller: "DepartmentLookupController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    })
    .when("/admin/account", {
        templateUrl: "/app/pages/admin/faccount/asd.html",
        controller: "DoomController",
        access: {
            loginRequired: false,	
            authorizedRoles: [USER_ROLES.all]
        }
    });
    
    
    
});

myapp.run(function (RunService ) {

	RunService.run()

});





