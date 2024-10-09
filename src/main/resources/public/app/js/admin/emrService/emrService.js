APP.service('emrservice', services);

function services($resource,$rootScope, toastr) {
	
	this.saveMedicalInfo = $resource('medicalInstitute/saveMedicalInfo/:medi', {medi: '@medi'}, {});
	this.searchMedical= $resource('medicalInstitute/searchMedical/:smedi', {smedi: '@smedi'}, {});
	this.getMedicalInfoById=$resource('medicalInstitute/getMedicalInfoById/:id', {id: '@id'}, {});
	this.approveMedical=$resource('medicalInstitute/approveMedical/:apd', {apd: '@apd'}, {});
	this.removeActivateMedical=$resource('medicalInstitute/removeActivateMedical/:rpd', {rpd: '@rpd'}, {});
	
$rootScope.error422 = function(errorCode){ return errorCode === 422 || false; }
	
	$rootScope.error400 = function(errorCode){ return errorCode === 400 || false; }
	
	var errorToastConfig = successToastConfig = { progressBar : true, timeOut: 1500 };
	
	var infoToastConfig =  { progressBar : true, timeOut: 0 };
	
	this.errorToast = function(errorText, title, config){
		      toastr.clear();
			  toastr.error(errorText || "An error has happened.", title || "Error",
					  config || errorToastConfig
			  );
	}
	
	this.successToast = function(successText, title){
	      toastr.clear();
		  toastr.success(successText || "Operation finished successfully", title || "Success",
				  successToastConfig
		  );
	}
	
	this.infoToast = function(infoText, title){
	      toastr.clear();
		  toastr.info(infoText || "Please wait...", title || "Working",
				  infoToastConfig
		  );
	}
	
	return this;

}