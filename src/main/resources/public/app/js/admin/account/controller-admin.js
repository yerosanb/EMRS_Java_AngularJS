APP
.controller('AccountEditController', _accountEditController)
.controller('AccountListController', _accountListController)
.controller('AccountRoleController', _accountRoleController)
.controller('LookupsController', _lookupsController);

/**
 * Account Edit controller
 * @param $rootScope
 * @param $scope
 * @param Service
 * @param $routeParams
 * @returns
 */
function _accountEditController($rootScope, $scope, Service,
		$routeParams) {
	
	$scope.save      = saveUserCalleback;
	$scope.roles     = getAllRoles();
	$scope.editUserId    = $routeParams.id;
	$scope.changePassword = changePassword;
	$scope.changeMyPassword = changeMyPassword;
	
	function saveUserCalleback(user) {

		Service.user.save(user || {}, userSaveSuccess, userSaveError);

		function userSaveSuccess(obj) {
			$scope.errors = null;
			console.log(obj);
			$scope.successMessage = "User saved successfully";
		}

		function userSaveError(obj) {
			$scope.errors = obj;
			console.log(obj);
			$scope.successMessage= null;
		}
	}
	
	
	if(!!$scope.editUserId){
		
		Service.user.get({'uid':$routeParams.id}, successFunction, errorFunction);
		
		  function successFunction(data){
				$scope.user = data;
				$scope.user.password = '________';
				$scope.user.confirmPassword = '________';
			} 
		   function errorFunction(){};
	}
	
	function getAllRoles() {
		console.log('getting all the roles');
		return Service.roles.query();
	}
	
	function changePassword(pass){
		
		if(angular.isUndefined(pass)) pass = {};
		
		pass.id = $scope.editUserId;
		
		Service.changePassword.save(pass, success, error);
			$scope.merror = '';
			$scope.msuccess = '';
			function success(data){ $scope.msuccess = "Password changed Successfully"; }
			function error(error){ $scope.merror = error; }
	}
	
	function changeMyPassword(pass){
		
		if(angular.isUndefined(pass)) pass = {};
		
		Service.changeMyPassword.save(pass, success, error);
			$scope.merror = '';
			$scope.msuccess = '';
			function success(data){ $scope.msuccess = data.success; }
			function error(error){ $scope.merror = error; }
	}
	

}


/**
 * 
 * Account List controller
 * @param $rootScope
 * @param $scope
 * @param Service
 * @param $routeParams
 * @returns
 */


function _accountListController($rootScope, $scope, 
		Service, $routeParams) {

	$scope.searchUser = searchUserCallback;

	function searchUserCallback(search){
		$scope.userAccountList = Service.user.query(search, successCallBack, errorCallback);
		console.log($scope.userAccountList);
		
		function successCallBack(){
			$scope.errors = null;
			$scope.searchKey = $scope.search.searchKey; 
		}
		function errorCallback(error){
			$scope.errors = error;
		}
		
	}
}




/**
 * Account Role controller
 * @param $rootScope
 * @param $scope
 * @param Service
 * @param $routeParams
 * @returns
 */


function _accountRoleController($rootScope, $scope, 
		Service,	$routeParams) {
	

	$scope.rights = getAllRights();
	$scope.roles = getAllRoles();
	$scope.save = saveRoleCallBack;
	$scope.role = {};
	
	if(!!$routeParams.id)
		Service.roles.get({'rid':$routeParams.id}, 
		  function(data){
				$scope.role = data;
			}, 
			function(data){});
	
	function getAllRoles() {
		return Service.roles.query();
	}
	
	function getAllRights(){
		return Service.rights.query();
	}
	
	function saveRoleCallBack(role){
		console.log(role);
		Service.roles.save(role, roleSaveSuccess, roleSaveError);

		function roleSaveSuccess(obj) {
			$scope.errors = null;
			$scope.successMessage = "User role saved successfully";
			console.log(obj);
		}

		function roleSaveError(obj) {
			$scope.errors = obj;
			$scope.successMessage = null
		}
	}
}

/**
 * Lookups controller
 * @param $rootScope
 * @param $scope
 * @param Service
 * @param $routeParams
 * @returns
 */
function _lookupsController($rootScope, $scope, 
		Service,	$routeParams,  $location){
	$scope.path = $location.path();
}

























