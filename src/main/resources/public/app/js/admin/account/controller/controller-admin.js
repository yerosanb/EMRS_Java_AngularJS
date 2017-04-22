myapp.controller('AccountController', accountController);

function accountController($rootScope, $scope, AccountService) {
	
    $scope.save = save;
 
    $scope.people = [
    	  { code: 'BRANCH_MANAGER',  description : 'Branch manager' },
    	  { code: 'SYSTEM_MANAGE',  description : 'System Manager' },
    	  { code: 'SUPER_ADMIN',  description : 'Super admin' },
    	  { code: 'IT_OPS',  description : 'IT Operations' },
    	  { code: 'IT_DIRECTOR',  description : 'IT Directors' },
    	  { code: 'ALL_REPORT_VIEWR',  description : 'All Report Viewer' },
    	  { code: 'CSO',  description : 'Customer service officer' },
      ];
    
    $scope.user = {};
    
    function save(user){
    	$scope.authenticationError = true;
    	AccountService.save(user, userSaveSuccess, userSaveError);
    	 
    }
    
    function userSaveSuccess(){
    	$scope.errors = null;
      console.log("Happy");	
    }
    
    function userSaveError(obj){
    	$scope.errors = obj;
    	console.log(obj);
    }
}