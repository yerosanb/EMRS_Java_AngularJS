APP
.controller('reportController', _medicalReportController)

.controller('reportControllerEdit', _medicalReportControllerEdit)

//.controller('xx', reportCollateralLoanController);


function _medicalReportController($rootScope, $scope, reportservice,
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
	
	
	$scope.medicalInstituteList=getInstitutes();
	
	function getInstitutes(){
 		return reportservice.getInstitutes.query();
 	}
	
	$scope.getMedicalReport= function(search_keys){
  //		$scope.name=search_keys.name;
//		$scope.report_type = search_keys.reportType;
	 console.log("this is from service", search_keys.name, search_keys.reportType)
	 reportservice.getMedicalReport.query(search_keys,success);
 		function success(data){
			$scope.medical=data;
			console.log(data)	
			  $scope.myOptions = {
			    dom: '<"pull-right"B><"clear">frtip',
			    buttons: ['copy', 'csv', 'excel', 'pdf', 'print']
			  };
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
	
//    $(document).ready( function () {
//        $('#myTable').DataTable();
//    } );
}


function _medicalReportControllerEdit($rootScope, $scope, reportservice,
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
	
	
	$scope.editUserId =$routeParams.id;
	
	if(!!$scope.editUserId){
		
		Service.user.get({'uid':$routeParams.id}, successFunction, errorFunction);
		
		  function successFunction(data){
				$scope.user = data;
				$scope.user.password = '________';
				$scope.user.confirmPassword = '________';
			} 
		   function errorFunction(){};
	}
	
	 
}