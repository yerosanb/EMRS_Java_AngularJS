
myapp.controller('BranchLookupController', branchLookupController);
myapp.controller('CityLookupController', CityLookupController);
myapp.controller('DepartmentLookupController', departmentLookupController);
myapp.controller('DoomController', doomController);


function doomController($scope){
	$scope.title = 'OIB HEllo!';
}

/**
 * Branch controller
 * @param $rootScope
 * @param $scope
 * @param Service
 * @param $routeParams
 * @returns
 */
function branchLookupController($rootScope, $scope, Service, 
		$routeParams, $location) {
	
	$scope.save           = saveBranchCallback;
	$rootScope.branches   = getAllBranches();
	$scope.editBranchId   = $routeParams.id || 0;
	$scope.mode 		  = $routeParams.m || 'list'; // defaults to a string value 'list'
	
	function saveBranchCallback(branch) {
		
		Service.infoToast();
		remoteCallInProgress(true);
		Service.branches.save(branch || {}, success, error);

		function success(data) {
			$scope.errors = null;
			$scope.successMessage = data.success;
			Service.successToast(data.success);
			remoteCallInProgress(false);
		}

		function error(obj) {
			$scope.errors = obj;
			$scope.successMessage= null;
			
			if(obj.status === -1) 
				Service.errorToast("Unable to access remote server. Please check your network.");  
			else
				Service.errorToast();
			
			remoteCallInProgress(false);
		}
	}
	
	if($scope.editBranchId){
		
		Service.branches.get({'bid':$scope.editBranchId}, success, error);
		
		  function success(data){
				$scope.branch = data.branch;
			} 
		   function error(){};
	}
	
	function getAllBranches() {
		console.log('getting all branchs');
		return Service.branches.query();
	}
	
   function remoteCallInProgress(status){
	   $scope.isRemoteCallInProgress = status || false;
   }
	
}


/**
 * City Lookup Controller
 * @param $rootScope
 * @param $scope
 * @param Service
 * @param $routeParams
 * @returns
 */
function CityLookupController($rootScope, $scope, Service, 
		$routeParams) {
	
	$scope.save         = saveCityCallback;
	$rootScope.cities   = getAllCities();
	$scope.editCityId   = $routeParams.id || 0;
	$scope.mode 		= $routeParams.m || 'list';

	function saveCityCallback(city) {

		Service.cities.save(city || {}, success, error);

		function success(data) {
			$scope.errors = null;
			console.log(data);
			$scope.successMessage = data.success;
		}

		function error(obj) {
			$scope.errors = obj;
			console.log(obj);
			$scope.successMessage= null;
		}
	}
	
	if($scope.editCityId){
		
		Service.cities.get({'cid':$scope.editCityId}, success, error);
		
		  function success(data){
				$scope.city = data.city;
			} 
		   function error(){};
	}
	
	function getAllCities() {
		console.log('getting all branchs');
		return Service.cities.query();
	}
}
	


/**
 * department Lookup Controller
 * @param $rootScope
 * @param $scope
 * @param Service
 * @param $routeParams
 * @returns
 */
function departmentLookupController($rootScope, $scope, Service, 
		$routeParams) {
	
	$scope.save              = saveCityCallback;
	$rootScope.departments   = getAlldepartments();
	$scope.editDepartmentId  = $routeParams.id || 0;
	$scope.mode 			 = $routeParams.m || 'list';
	
	console.log($routeParams.id);
	
	function saveCityCallback(department) {

		Service.departments.save(department || {}, success, error);

		function success(data) {
			$scope.errors = null;
			console.log(data);
			$scope.successMessage = data.success;
		}

		function error(obj) {
			$scope.errors = obj;
			$scope.successMessage= null;
		}
	}
	
	if($scope.editDepartmentId){
		
		Service.departments.get({'did':$scope.editDepartmentId}, success, error);
		
		  function success(data){
				$scope.department = data.department;
			} 
		   function error(){};
	}
	
	function getAlldepartments() {
		console.log('getting all branchs');
		return Service.departments.query();
	}
	
	
//	// ob idea to manage the menu from the application
//	$scope.menu = [
//		{
//			
//			"url":"foll",
//			"name": "uji",
//			child : []
//		},
//		{"url":"asd",
//			"name": "asd",
//			child : [
//				{
//					"url":"gg",
//					"name": "gg",
//					child : [
//						{
//							"url":"66",
//							"name": "66",
//							child : [
//								{
//									"url":"ik",
//									"name": "ik",
//									child : [
//										
//									]
//								}
//							]
//						}
//					]
//				},
//				{
//					"url":"pp",
//					"name": "pp",
//					child : []
//				}
//			]
//		}
//		
//	];
//	
//	$scope.m= [];
//	
//	$scope.filter = function(parentNodeName, childNodeData, keyNameForTheMenuLevel){
//		$scope.m[keyNameForTheMenuLevel] = _.filter(childNodeData, function(node){ return node.name == parentNodeName; })[0];
//		console.log($scope["m"][keyNameForTheMenuLevel], parentNodeName, childNodeData, keyNameForTheMenuLevel);
//		console.log(keyNameForTheMenuLevel.length);
//		//$scope.level2 = _.filter($scope.level1, function(n){ return n.name == iname; })[0];
//		//$scope.level3 = _.filter($scope.level2, function(n){ return n.name == iname; })[0];
//	}
//	
	
	
}
	
















