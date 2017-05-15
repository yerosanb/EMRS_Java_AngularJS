'use strict';

myapp.service('Session', function () {
    this.create = function (data) {
    	this.id = data.id;
        this.login = data.login;
        this.firstName = data.firstName;
        this.lastName = data.lastName;
        this.email = data.email;
        this.userRoles = [];
        angular.forEach(data.authorities, function (value, key) {
            this.push(value.name);
        }, this.userRoles);
    };
    this.invalidate = function () {
        this.id = null;
        this.login = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.userRoles = null;
    };
    return this;
});


myapp.service('AuthSharedService', function ($rootScope, $http, $resource, authService, Session) {
    return {
        login: function (userName, password, rememberMe) {
            var config = {
                ignoreAuthModule: 'ignoreAuthModule',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            };
            $http.post('authenticate', $.param({
                username: userName,
                password: password,
                rememberme: rememberMe
            }), config)
                .success(function (data, status, headers, config) {
                    authService.loginConfirmed(data);
                })
                .error(function (data, status, headers, config) {
                    $rootScope.authenticationError = true;
                    Session.invalidate();
                });
        },
        getAccount: function () {
            $rootScope.loadingAccount = true;
            $http.get('security/account')
                .then(function (response) {
                    authService.loginConfirmed(response.data);
                });
        },
        isAuthorized: function (authorizedRoles) {
            if (!angular.isArray(authorizedRoles)) {
            	
                if (authorizedRoles == '*') {
                    return true;
                }
                authorizedRoles = [authorizedRoles];
                console.log('roles..', authorizedRoles);
            }
            var isAuthorized = false;
            angular.forEach(authorizedRoles, function (authorizedRole) {
                var authorized = (!!Session.login &&
                Session.userRoles.indexOf(authorizedRole) !== -1);
                if (authorized || authorizedRole == '*') {
                    isAuthorized = true;
                }
            });
            return isAuthorized;
        },
        logout: function () {
            $rootScope.authenticationError = false;
            $rootScope.authenticated = false;
            $rootScope.account = null;
            $http.get('logout');
            Session.invalidate();
            authService.loginCancelled();
        }
    };
});

myapp.service('HomeService', function ($log, $resource) {
    return {
        getTechno: function () {
            var userResource = $resource('resources/json/techno.json', {}, {
                query: {method: 'GET', params: {}, isArray: true}
            });
            return userResource.query();
        }
    }
});


myapp.service('UsersService', function ($log, $resource) {
    return {
        getAll: function () {
            var userResource = $resource('users', {}, {
                query: {method: 'GET', params: {}, isArray: true}
            });
            return userResource.query();
        }
    }
});


myapp.service('TokensService', function ($log, $resource) {
    return {
        getAll: function () {
            var tokensResource = $resource('security/tokens', {}, {
                query: {method: 'GET', params: {}, isArray: true}
            });
            return tokensResource.query();
        }
    }
});

myapp.service('RunService', function ($rootScope, $location, $http, AuthSharedService, Session, 
		USER_ROLES, $q, $timeout, Service) {
	
	return {
		
	 run : function() {
		 
	  $rootScope.$on('$routeChangeStart', function (event, next) {

	        if(next.originalPath === "/login" && $rootScope.authenticated) {
	            event.preventDefault();
	        } else if (next.access && next.access.loginRequired && !$rootScope.authenticated) {
	            event.preventDefault();
	            $rootScope.$broadcast("event:auth-loginRequired", {});
	        } else if (next.access && !AuthSharedService.isAuthorized(next.access.authorizedRoles)) {
	        	console.log(next.access.authorizedRoles);
	            event.preventDefault();
	            $rootScope.$broadcast("event:auth-forbidden", {});
	        }
	        $rootScope.requestedUrl = next.originalPath;
	    });

	    $rootScope.$on('$routeChangeSuccess', function (scope, next, current) {
	        $rootScope.$evalAsync(function () {
	            $.material.init();
	        });
	    });

	    // Call when the the client is confirmed
	    $rootScope.$on('event:auth-loginConfirmed', function (event, data) {
	        console.log('login confirmed start ' + data);
	        $rootScope.loadingAccount = false;
	        console.log($location.path());
	        
	        var nextLocation = 
	        	($rootScope.requestedUrl && $rootScope.requestedUrl !== '/login' 
	        		? $rootScope.requestedUrl : "/home");
	        
	        var delay = ($location.path() === "/loading" ? 1500 : 0);

	        $timeout(function () {
	            Session.create(data);
	            $rootScope.account = Session;
	            $rootScope.authenticated = true;
	            $location.path(nextLocation).replace();
	        }, delay);

	    });

	    // Call when the 401 response is returned by the server
	    $rootScope.$on('event:auth-loginRequired', function (event, data) {
	        if ($rootScope.loadingAccount && data.status !== 401) {
	            $rootScope.requestedUrl = $location.path()
	            $location.path('/loading');
	        } else {
	            Session.invalidate();
	            $rootScope.authenticated = false;
	            $rootScope.loadingAccount = false;
	            $location.path('/login');
	        }
	    });

	    // Call when the 403 response is returned by the server
	    $rootScope.$on('event:auth-forbidden', function (rejection) {
	        $rootScope.$evalAsync(function () {
	            $location.path('/error/403').replace();
	        });
	    });

	    // Call when the user logs out
	    $rootScope.$on('event:auth-loginCancelled', function () {
	        $location.path('/login').replace();
	    });
	    
	    $rootScope.$on('httpErrorEvent', function (event, data) {
	    	 if(data.status === -1){
	    		 Service.errorToast("Unable to connect to remote servers. Please check your network.", 
	    				 null, { progressBar : true, timeOut: 10000 });
	    	}
	    });

	    // Get already authenticated user account
	    AuthSharedService.getAccount();
	  
	 }
	 }
});






