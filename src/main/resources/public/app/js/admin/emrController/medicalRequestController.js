APP
.controller('medicalRequestController', _medicalRequestController);


function _medicalRequestController($rootScope, $scope, emrservice,
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
}