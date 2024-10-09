'use strict';

var APP = angular
    .module('myApp', ['ngResource', 'ngRoute', 'swaggerUi', 'http-auth-interceptor',
    	'ngAnimate', 'angular-spinkit','ui.select','ngSanitize','angular-loading-bar', 
    	'toastr', 'ab.localStorage',  'ngCookies','ui.bootstrap']);


APP.constant('USER_ROLES', {
    all: '*',
    admin: 'ADMIN',
    user: 'user'
});
//angular.module('myApp', ['dataTableDirective']);
 
//APP.directive('myDataTable', ['$timeout', '$http', 'myService', function($timeout, $http, myService) {
//	  return require('.resources/js/dataTableDirective.js')($timeout, $http, myService);
//	  
// 	}]);

angular.module('App', [])
.directive('dataTable', ['$timeout', function($timeout) {
  return require('./resources/js/dataTableDirective.js')($timeout);
}]);

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
            authorizedRights: [USER_ROLES.all]
        }
    }).when("/admin/user/new", {
        templateUrl: "app/pages/admin/account/edit.html",
        controller: "AccountEditController",
        access: {
            loginRequired: true,	
            authorizedRights: [USER_ROLES.all]
        }
    })
    .when("/admin/user/edit/:id", {
        templateUrl: "app/pages/admin/account/edit.html",
        controller: "AccountEditController",
        access: {
            loginRequired: true,	
            authorizedRights:  [USER_ROLES.all]
        }
    })
    
    //Lookups
    .when("/admin/roles", {
        templateUrl: "app/pages/admin/role/list.html",
        controller: "AccountRoleController",
        access: {
            loginRequired: true,	
            authorizedRights: [USER_ROLES.all]
        }
    })
    .when("/admin/role/edit/:id", {
        templateUrl: "app/pages/admin/role/edit.html",
        controller: "AccountRoleController",
        access: {
            loginRequired: true,	
            authorizedRights:  [USER_ROLES.all]
        }
    })
     .when("/admin/role/new", {
        templateUrl: "app/pages/admin/role/edit.html",
        controller: "AccountRoleController",
        access: {
            loginRequired: true,	
            authorizedRights:  [USER_ROLES.all]
        }
    })
     .when("/admin/lookups", {
        templateUrl: "app/pages/admin/lookups/list.html",
        controller: "LookupsController",
        access: {
            loginRequired: true,	
            authorizedRights:  [USER_ROLES.all]
        }
    })
     .when("/admin/lookup/branch", {
        templateUrl: "app/pages/admin/lookups/branch.html",
        controller: "BranchLookupController",
        access: {
            loginRequired: true,	
            authorizedRights:  [USER_ROLES.all]
        }
    })
    .when("/admin/lookup/city", {
        templateUrl: "app/pages/admin/lookups/city.html",
        controller: "CityLookupController",
        access: {
            loginRequired: true,	
            authorizedRights:  [USER_ROLES.all]
        }
    })
     .when("/admin/lookup/department", {
        templateUrl: "app/pages/admin/lookups/department.html",
        controller: "DepartmentLookupController",
        access: {
            loginRequired: true,	
            authorizedRights:  [USER_ROLES.all]
        }
    })
    .when("/admin/menu", {
        templateUrl: "app/pages/admin/menu/menu-edit.html",
        controller: "DepartmentLookupController",
        access: {
            loginRequired: true,	
            authorizedRights:  [USER_ROLES.all]
        }
    })
    
    
    //EMRS
    
    .when("/medicalInstitute/search", {
        templateUrl: "app/pages/admin/emr/medicalInstitute_search.html",
        controller: "medicalInstituteController",
        access: {
            loginRequired: true,	
            authorizedRights: [USER_ROLES.all]
        }
    })
    .when("/medicalInstitute/add", {
        templateUrl: "app/pages/admin/emr/medicalInstitute.html",
        controller: "medicalInstituteController",
        access: {
            loginRequired: true,	
            authorizedRights: [USER_ROLES.all]
        }
    })
    .when("/medicalInstitute/add/:id", {
        templateUrl: "app/pages/admin/emr/medicalInstitute.html",
        controller: "medicalInstituteController",
        access: {
            loginRequired: true,	
            authorizedRights: [USER_ROLES.all]
        }
    })
    
    .when("/medicalRequest/request", {
        templateUrl: "app/pages/admin/emr/request.html",
        controller: "medicalRequestController",
        access: {
            loginRequired: true,	
            authorizedRights: [USER_ROLES.all]
        }
    })
    
    .when("/report/medicalReport", {
        templateUrl: "app/pages/admin/emr/reports/medical_report.html",
        controller: "reportController",
        access: {
            loginRequired: true,	
            authorizedRights: [USER_ROLES.all]
        }
    })
     
    .when("/report/edit/:id", {
        templateUrl: "app/pages/admin/emr/reports/edit.html",
        controller: "reportControllerEdit",
        access: {
            loginRequired: true,	
            authorizedRights: [USER_ROLES.all]
        }
    });
    
     
});

APP.run(function (RunService ) {

	RunService.run()

});





