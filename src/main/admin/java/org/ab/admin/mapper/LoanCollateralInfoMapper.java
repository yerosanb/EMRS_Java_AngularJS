package org.ab.admin.mapper;

import java.util.List;

import org.ab.admin.model.Branch;
import org.ab.admin.model.CollateralInfoModel;
import org.ab.admin.model.CollateralLinkedModel;
import org.ab.admin.model.CollateralTypeModel;
import org.ab.admin.model.LoanInfoModel;
import org.ab.admin.model.ReportCollateralLinkedModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface LoanCollateralInfoMapper {

	@Select("select accountNo, loanAmount,customerName from loan where accountNo=#{accountNo} and branchCode=#{branchCode}")
	public LoanInfoModel getLoanAmountWithAccNo(@Param("accountNo") String accountNo, @Param("branchCode") String branchCode) ;

//	@Insert("insert into collateralInfo(accountNo,branch,branchCode,description,estimatedAmount,estimatedDate,checker,maker) "
//			+ "values(#{accountNo},#{branch},#{branchCode},#{description},#{estimatedAmount},#{estimatedDate},#{checker},#{maker})")
//	public void saveCollaterals(CollateralLinkedModel collateral);

	@Select("select * from collateralInfo where branch=#{branch}")
	public List<CollateralInfoModel> getCollateralListForBranch(String branch);

@Select("select * from collateralInfo where branch=#{branch} and accountNo=#{accountNo}")
	public List<CollateralInfoModel> getCollateralListWithAccnoBr(@Param("accountNo") String accountNo, @Param("branch") String branch);

	@Update("update collateralInfo set description=#{description},estimatedAmount=#{estimatedAmount},estimatedDate=#{estimatedDate},updatedBy=#{updatedBy},updatedDate=#{updatedDate} where id =#{id}")
	public void updateCollateral(CollateralInfoModel collateral);
@Select("select * from loan where branchCode=#{branchCode}")
	public List<LoanInfoModel> browseCustListInBranch(String branchCode);

@Insert("insert into collateralInfo(collateralType,collateralName,estimatedAmount,estimatedDate,branch,branchCode,maker,enteredDate,yearOfMake) "
		+ "values(#{collateralType},#{collateralName},#{estimatedAmount},#{estimatedDate},#{branch},#{branchCode},#{maker},#{enteredDate},#{yearOfMake}) ")
@Options(useGeneratedKeys=true, keyColumn="id",keyProperty="collateralCode"  )
//@Select("select collateralCode from collateralInfo where id=#{id}")
//@SelectKey(keyProperty="collateralCode", before=false, resultType=String.class, statement = "select collateralCode from collateralInfo where id=#{id}")
public void saveCollaterals( CollateralInfoModel collateral);

@Insert("insert into collateralLinkedAcct(collateralCode,accountNo,linkedcollateralAmt) values(#{collateralCode},#{accountNo},#{linkedcollateralAmt})")
public void saveLinkedCollateral(@Param("collateralCode") String collateralCode,@Param("accountNo") String accountNo, @Param("linkedcollateralAmt") Double linkedcollateralAmt);

@Select("select * from branches where status=0 order by branch")
public List<Branch> getAllBranches();

@Select("select * from collateralTypes where status=0")
public List<CollateralTypeModel> getCollateralType();

@Select("select a.collateralCode,a.collateralType,a.collateralName,a.estimatedAmount,b.accountNo,b.linkedcollateralAmt,a.maker from collateralInfo a inner join collateralLinkedAcct b on a.collateralCode=b.collateralCode where a.branchCode=#{branchCode} order by a.collateralCode desc")
public List<ReportCollateralLinkedModel> getRepCollateralLinkedLoan(String branchCode);

@Select("select collateralCode from collateralInfo where  branchCode=#{branchCode} group by collateralCode order by cast(SUBSTRING(collateralCode,CHARINDEX('_',collateralCode)+1,LEN(collateralCode)) as int)")
@Results(value = {
		@Result(property = "collateralCode", column = "collateralCode"),
		 @Result(property="colInfo", javaType=List.class, column = "collateralCode", 
     	many=@Many(select="getCollateralInfoListByCollCode")),
		 @Result(property="linkedAct", javaType=List.class, column = "collateralCode", 
     	many=@Many(select="getLinkedAcctListByCollateralCode"))
		 
    })
public  List<ReportCollateralLinkedModel> getRepCollateralLinked_Report(String branchCode);

@Select("select * from collateralInfo where collateralCode=#{collateralCode}")
public List<CollateralInfoModel> getCollateralInfoListByCollCode(String collateralCode);

@Select("select a.*, b.loanAmount,b.customerName from collateralLinkedAcct a inner join loan b on a.accountNo=b.accountNo where a.collateralCode=#{collateralCode}")
public List<CollateralLinkedModel> getLinkedAcctListByCollateralCode(String collateralCode);

@Select("select top 1 collateralCode from collateralInfo where branchCode=#{branchCode} order by cast(SUBSTRING(collateralCode,CHARINDEX('_',collateralCode)+1,LEN(collateralCode)) as int) desc")
public String getCollaterlCode(@Param("branchCode") String branchCode);

@Insert("insert into collateralInfo(collateralCode,collateralType,collateralName,estimatedAmount,estimatedDate,branch,branchCode,maker,enteredDate,yearOfMake,guarantorName) "
		+ "values(#{collateralCode},#{collateralType},#{collateralName},#{estimatedAmount},#{estimatedDate},#{branch},#{branchCode},#{maker},#{enteredDate},#{yearOfMake},#{guarantorName}) ")
public void saveCollatersList(@Param("collateralCode") String collateralCode, @Param("collateralType") String collateralType,@Param("collateralName") String collateralName,
		@Param("estimatedAmount") Double estimatedAmount,@Param("estimatedDate") String estimatedDate,@Param("branch") String branch,@Param("branchCode") String branchCode,
		@Param("maker") String maker,@Param("enteredDate") String enteredDate,@Param("yearOfMake") String yearOfMake, @Param("guarantorName") String guarantorName);


@Select("select collateralCode,branchCode from collateralInfo  group by collateralCode,branchCode order by branchCode,cast(SUBSTRING(collateralCode,CHARINDEX('_',collateralCode)+1,LEN(collateralCode)) as int)")
@Results(value = {
		@Result(property = "collateralCode", column = "collateralCode"),
		 @Result(property="colInfo", javaType=List.class, column = "collateralCode", 
     	many=@Many(select="getCollateralInfoListByCollCode")),
		 @Result(property="linkedAct", javaType=List.class, column = "collateralCode", 
     	many=@Many(select="getLinkedAcctListByCollateralCode"))
		 
    })
public List<ReportCollateralLinkedModel> getAllBranchLinkedAcnt();

}
