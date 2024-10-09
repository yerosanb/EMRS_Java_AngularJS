APP.service('reportservice', medservice);

function medservice($resource,$rootScope, toastr) {
 this.getInstitutes = $resource('report/medicalInstitute/getall/:medi', {medi: '@medi'}, {});
 console.log("name pop from here")

 	this.getMedicalReport = $resource('report/medicalReport/getall/:medi', {
		  medi: '@medi',
		  name: '@name',
		  reportType: '@reportType'
		}, {});
 
 this.user = $resource('admin/account/user/:uid', {uid: '@uid'}, {});

// this.getMedicalReport = $resource('report/medicalReport/getall/:name/:reportType', {
//	  name: '@name',
//	  reportType: '@reportType'
//	}, {});
//	
 	
 
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