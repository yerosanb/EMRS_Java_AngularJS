'use strict';

var APP = angular
    .module('myApp', ['ngResource', 'ngRoute', 'swaggerUi', 'http-auth-interceptor',
    	'ngAnimate', 'angular-spinkit','ui.select','ngSanitize','angular-loading-bar', 
    	'toastr', 'oib.localStorage',  'ngCookies']);


APP.constant('USER_ROLES', {
    all: '*',
    admin: 'ADMIN',
    user: 'user'
});
APP.value('version', '1494450883183');
APP.filter('propsFilter', function() {
	  return function(items, props) {
	    var out = [];

	    if (angular.isArray(items)) {
	      var keys = Object.keys(props);

	      items.forEach(function(item) {
	        var itemMatches = false;

	        for (var i = 0; i < keys.length; i++) {
	          var prop = keys[i];
	          var text = props[prop].toLowerCase();
	          if (item[prop]!== null && item[prop].toString().toLowerCase().indexOf(text) !== -1) {
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


APP.config(function ($routeProvider, USER_ROLES, $httpProvider) {


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
    .when("/home",  { 
    	templateUrl: "partials/home.html", 
    	controller: 'HomeController', 
    	access: { 
    		loginRequired: true,  
    		authorizedRights: [USER_ROLES.all] 
    	}
    }).when('/login', {
        templateUrl: 'partials/login.html',
        controller: 'LoginController',
        access: {
            loginRequired: false,
            authorizedRights: [USER_ROLES.all]
        }
    }).when('/loading', {
        templateUrl: 'partials/loading.html',
        access: {
            loginRequired: false,
            authorizedRights: [USER_ROLES.all]
        }
    }).when("/logout", {
        template: " ",
        controller: "LogoutController",
        access: {
            loginRequired: false,
            authorizedRights: [USER_ROLES.all]
        }
    }).when("/error/:code", {
        templateUrl: "partials/error.html",
        controller: "ErrorController",
        access: {
            loginRequired: true,
            authorizedRights: [USER_ROLES.all]
        }
    }).otherwise({
        redirectTo: '/error/404',
        access: {
            loginRequired: true,
            authorizedRights: [USER_ROLES.all]
        }
    })
    
    //Admin
    .when("/admin/user", {
        templateUrl: "app/pages/admin/account/list.html",
        controller: "AccountListController",
        access: {
            loginRequired: true,	
            authorizedRights: ['MANAGE_USERS']
        }
    }).when("/admin/user/new", {
        templateUrl: "app/pages/admin/account/edit.html",
        controller: "AccountEditController",
        access: {
            loginRequired: true,	
            authorizedRights: ['MANAGE_USERS']
        }
    })
    .when("/admin/user/edit/:id", {
        templateUrl: "app/pages/admin/account/edit.html",
        controller: "AccountEditController",
        access: {
            loginRequired: true,	
            authorizedRights: ['MANAGE_USERS']
        }
    })
    
    //Lookups
    .when("/admin/roles", {
        templateUrl: "app/pages/admin/role/list.html",
        controller: "AccountRoleController",
        access: {
            loginRequired: true,	
            authorizedRights: ['MANAGE_ADMIN_LOOKUP']
        }
    })
    .when("/admin/role/edit/:id", {
        templateUrl: "app/pages/admin/role/edit.html",
        controller: "AccountRoleController",
        access: {
            loginRequired: true,	
            authorizedRights: ['MANAGE_ADMIN_LOOKUP']
        }
    })
     .when("/admin/role/new", {
        templateUrl: "app/pages/admin/role/edit.html",
        controller: "AccountRoleController",
        access: {
            loginRequired: true,	
            authorizedRights: ['MANAGE_ADMIN_LOOKUP']
        }
    })
     .when("/admin/lookups", {
        templateUrl: "app/pages/admin/lookups/list.html",
        controller: "LookupsController",
        access: {
            loginRequired: true,	
            authorizedRights: ['MANAGE_ADMIN_LOOKUP']
        }
    })
     .when("/admin/lookup/branch", {
        templateUrl: "/app/pages/admin/lookups/branch.html",
        controller: "BranchLookupController",
        access: {
            loginRequired: true,	
            authorizedRights: ['MANAGE_ADMIN_LOOKUP']
        }
    })
    .when("/admin/lookup/city", {
        templateUrl: "/app/pages/admin/lookups/city.html",
        controller: "CityLookupController",
        access: {
            loginRequired: true,	
            authorizedRights: ['MANAGE_ADMIN_LOOKUP']
        }
    })
     .when("/admin/lookup/department", {
        templateUrl: "/app/pages/admin/lookups/department.html",
        controller: "DepartmentLookupController",
        access: {
            loginRequired: true,	
            authorizedRights: ['MANAGE_ADMIN_LOOKUP']
        }
    })
    .when("/admin/menu", {
        templateUrl: "/app/pages/admin/menu/menu-edit.html",
        controller: "DepartmentLookupController",
        access: {
            loginRequired: true,	
            authorizedRights: ['MANAGE_ADMIN_LOOKUP']
        }
    });
    
    
    
});

APP.run(function (RunService ) {

	RunService.run()

});





