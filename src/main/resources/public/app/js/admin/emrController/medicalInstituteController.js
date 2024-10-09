APP
.controller('medicalInstituteController', _medicalInstituteController)

.controller('xx', reportCollateralLoanController);




function _medicalInstituteController($rootScope, $scope, emrservice,
		$routeParams, $window, $route, $uibModal) {
	
	$('.input-group.date').datepicker({
	    format: "dd/mm/yyyy",
	    startDate: '10/10/1900',

	    todayBtn: "linked",
	    autoclose: true,
	    todayHighlight: true,
	    

	    });
	$scope.editId=$routeParams.id;
	$scope.empId=$scope.account.empId;
	$scope.name=$scope.account.firstName+','+$scope.account.empId;
	
	
	$scope.saveMedical=function(med){
		med.maker=$scope.name;
		console.log(med)
		emrservice.saveMedicalInfo.save(med,success,error);
		function success(obj){
			
			$scope.successMessage ="Saved";
			$scope.errors =null;
			
		} 
		
		function error(obj){
			$scope.errors = obj;
			$scope.successMessage =null;
		}
		
	}
	
	$scope.searchMedical=function(ids){
		ids.makerId=$scope.empId;
		console.log(ids)
		emrservice.searchMedical.query(ids,success);
		function success(data){
			$scope.medical=data;
			console.log(data)
			
		} 
		
	}
	
	if(!!$scope.editId){
		emrservice.getMedicalInfoById.get({'id':$scope.editId},success);
		
		function success(data){
			$scope.med=data;
		}
		
	}
	
	$scope.approveMedical=function(info){
		console.log(info)
		info.approver=$scope.name;	
		emrservice.approveMedical.save(info,success,error);
		function success(obj){
			
			$scope.successMessage ="approved!";
			$scope.errors =null;
			
		} 
		
		function error(obj){
			$scope.errors = obj;
			$scope.successMessage =null;
		}
	}
	
	$scope.removeActivateMedical=function(info){
		info.maker=$scope.name;	
		emrservice.removeActivateMedical.save(info,success,error);
		function success(obj){
			
			$scope.successMessage ="removed!";
			$scope.errors =null;
			
		} 
		
		function error(obj){
			$scope.errors = obj;
			$scope.successMessage =null;
		}
	}
}