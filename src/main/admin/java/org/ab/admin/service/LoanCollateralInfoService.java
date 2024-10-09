package org.ab.admin.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.ab.admin.mapper.LoanCollateralInfoMapper;
import org.ab.admin.model.Branch;
import org.ab.admin.model.CollateralInfoModel;
import org.ab.admin.model.CollateralLinkedModel;
import org.ab.admin.model.CollateralTypeModel;
import org.ab.admin.model.LoanInfoModel;
import org.ab.admin.model.ReportCollateralLinkedModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoanCollateralInfoService {
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	@Autowired
private LoanCollateralInfoMapper map;
	public LoanInfoModel getLoanAmountWithAccNo(@Param("accountNo") String accountNo, @Param("branchCode") String branchCode) {
		// TODO Auto-generated method stub
		return map.getLoanAmountWithAccNo(accountNo,branchCode);
	}
	
	public void saveCollaterals(CollateralInfoModel collateral) {
		Date dt =new Date();
		collateral.setEnteredDate(dateFormat.format(dt));
		
		String collCode=map.getCollaterlCode(collateral.getBranchCode());
		String [] brcd_Id=null;
		String collateralCd=null;
		if(collCode==null){
			
			Long incId=(long) 1;
			 collateralCd=collateral.getBranchCode()+"_"+incId;
		}
		else{
			 brcd_Id=collCode.split("_");
			Long incId =Long.parseLong(brcd_Id[1])+1;
			 collateralCd=collateral.getBranchCode()+"_"+incId;
		}
		collateral.setCollateralCode(collateralCd);
		for(CollateralInfoModel inf:collateral.getCollateralReg())
		{
		map.saveCollatersList(collateral.getCollateralCode(),inf.getCollateralType(),inf.getCollateralName(),inf.getEstimatedAmount(),
				inf.getEstimatedDate(),collateral.getBranch(),collateral.getBranchCode(),collateral.getMaker(),
				collateral.getEnteredDate(),inf.getYearOfMake(),inf.getGuarantorName());
			//map.saveCollaterals(collateral);
		}
		for(CollateralLinkedModel coll:collateral.getCollInfo())
		map.saveLinkedCollateral(collateral.getCollateralCode(),coll.getAccountNo(),coll.getLinkedcollateralAmt());
		
	}

	public List<CollateralInfoModel> getCollateralListForBranch(String branch) {
		// TODO Auto-generated method stub
		return map.getCollateralListForBranch(branch);
	}

	public List<CollateralInfoModel> getCollateralListWithAccnoBr(@Param("accountNo") String accountNo, @Param("branch") String branch) {
		// TODO Auto-generated method stub
		return map.getCollateralListWithAccnoBr(accountNo,branch);
	}

//	public void updateCollateral(CollateralInfoModel collateral) {
//		Date dt=new Date();
//		collateral.setUpdatedDate(dateFormat.format(dt));
//		map.updateCollateral(collateral);
//		
//	}

	public List<LoanInfoModel> browseCustListInBranch(String branchCode) {
		// TODO Auto-generated method stub
		return map.browseCustListInBranch(branchCode);
	}

	public List<Branch> getAllBranches() {
		// TODO Auto-generated method stub
		return map.getAllBranches();
	}

	public List<CollateralTypeModel> getCollateralType() {
		// TODO Auto-generated method stub
		return map.getCollateralType();
	}

	public List<ReportCollateralLinkedModel> getRepCollateralLinkedLoan(String branchCode) {
		// TODO Auto-generated method stub
		//return map.getRepCollateralLinkedLoan(branchCode);
		return map.getRepCollateralLinked_Report(branchCode);
	}

	public List<ReportCollateralLinkedModel> getAllBranchLinkedAcnt() {
		// TODO Auto-generated method stub
		return map.getAllBranchLinkedAcnt();
	}
	
	

}
