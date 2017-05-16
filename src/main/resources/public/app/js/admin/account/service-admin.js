myapp.service('Service', services);

function services($resource,$rootScope, toastr) {
	
	this.user = $resource('/admin/account/user/:uid', {uid: '@uid'}, {});
	
	this.roles = $resource('/admin/role/roles/:rid', {rid: '@rid'}, {});
	
	this.rights = $resource('/admin/right/rights',{}, {});
	
	this.branches = $resource('/admin/lookups/branchs/:bid', {bid: '@bid'}, {});
	
	this.cities = $resource('/admin/lookups/cities/:cid', {cid: '@cid'}, {});
	
	this.departments = $resource('/admin/lookups/departments/:did', {did: '@did'}, {});
	
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

