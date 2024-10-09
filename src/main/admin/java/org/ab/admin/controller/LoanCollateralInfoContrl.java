package org.ab.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ab.admin.model.CollateralInfoModel;
import org.ab.admin.model.CollateralLinkedModel;
import org.ab.admin.model.ReportCollateralLinkedModel;
import org.ab.admin.service.LoanCollateralInfoService;
import org.ab.core.controller.response.AGPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/loanInformation/")
public class LoanCollateralInfoContrl extends BaseController {
	
	@Autowired
	private LoanCollateralInfoService serv;
	
	@RequestMapping("getLoanAmount")
	public ResponseEntity getLoanAmountWithAccNo(@RequestParam String accountNo,@RequestParam String branchCode) {
		return AGPResponse.response(serv.getLoanAmountWithAccNo(accountNo,branchCode));
	}
	
	@RequestMapping(value="saveCollateral", method = RequestMethod.POST)
	public ResponseEntity<AGPResponse> saveCollaterals(@RequestBody @Validated CollateralInfoModel collateral){
		
		try {
			
			serv.saveCollaterals(collateral);
		}
		catch(DuplicateKeyException ex){
			return AGPResponse.error(
					getMessage("role.save.error.duplicate.code"),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return AGPResponse.success(getMessage("user.saved.successfully"));
	}
	
//	@RequestMapping(value="updateCollateral", method = RequestMethod.POST)
//	public ResponseEntity<AGPResponse> updateCollateral(@RequestBody @Validated CollateralInfoModel collateral){
//		
//		try {
//			
//			serv.updateCollateral(collateral);
//		}
//		catch(DuplicateKeyException ex){
//			return AGPResponse.error(
//					getMessage("role.save.error.duplicate.code"),
//					HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//		
//		return AGPResponse.success(getMessage("user.saved.successfully"));
//	}
//	
	@RequestMapping("getCollateralListForBranch/{branch}")
	public ResponseEntity getCollateralListForBranch(@PathVariable("branch") String branch) {
		return AGPResponse.response(serv.getCollateralListForBranch(branch));
	}
	
	
	@RequestMapping("getCollateralListWithAccnoBr")
	public ResponseEntity getCollateralListForBranch(@RequestParam  String accountNo,@RequestParam  String branch ) {
		return AGPResponse.response(serv.getCollateralListWithAccnoBr(accountNo,branch));
	}
	
	@RequestMapping("browseCustListInBranch/{branchCode}")
	public ResponseEntity browseCustListInBranch(@PathVariable("branchCode") String branchCode) {
		return AGPResponse.response(serv.browseCustListInBranch(branchCode));
	}
	
	@RequestMapping("getBranches")
	public ResponseEntity getAllBranches() {
		return AGPResponse.response(serv.getAllBranches());
	}
	
	@RequestMapping("getCollateralType")
	public ResponseEntity getCollateralType() {
		return AGPResponse.response(serv.getCollateralType());
	}
	@RequestMapping("getRepCollateralLinkedLoan/{branchCode}")
	public ResponseEntity getRepCollateralLinkedLoan(@PathVariable("branchCode") String branchCode) {
		return AGPResponse.response(serv.getRepCollateralLinkedLoan(branchCode));
	}
	
	
	@RequestMapping("getAllBranchLinkedAct")
	public ResponseEntity getAllBranchLinkedAcnt() {
		return AGPResponse.response(serv.getAllBranchLinkedAcnt());
	}
	
	@RequestMapping(value = "ExcelAllBranchLinkedAct",  method = RequestMethod.GET)
	public ModelAndView ExcelAllBranchLinkedActs(HttpServletRequest request, HttpServletResponse response) throws Exception{
//	Date dt=new Date();
//	String date=dateFormat.format(dt);
	    response.setHeader("Content-disposition", "attachment; filename=AllBranchLinkedAct_.xls");
	    response.setContentType("application/vnd.ms-excel");
	   // String year=request.getParameter("year");
	   // String year="2017";
	 
	    List<ReportCollateralLinkedModel> allBranchLinkedAct = serv.getAllBranchLinkedAcnt();

	    return new ModelAndView(new ExcelLoanLinkedAccount(), "alllinkedLoanAcc", allBranchLinkedAct);
}
}