package org.ab.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ab.admin.model.CollateralInfoModel;
import org.ab.admin.model.CollateralLinkedModel;
import org.ab.admin.model.ReportCollateralLinkedModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.lowagie.text.Cell;

public class ExcelLoanLinkedAccount  extends  AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		
		 List<ReportCollateralLinkedModel> linkedAcct=(List<ReportCollateralLinkedModel>) model.get("alllinkedLoanAcc");
		
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("AllBranchLinkedAccount");
        sheet.setDefaultColumnWidth(30);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.GREEN.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        
        CellStyle style2 = workbook.createCellStyle();
        Font font2 = workbook.createFont();
        font2.setFontName("Arial");
        style2.setFillForegroundColor(HSSFColor.BLUE.index);
        style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font2.setColor(HSSFColor.WHITE.index);
        style2.setFont(font);
        
        // create header row
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Collateral Code");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Collateral Type");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Collateral Name");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Estimated Amount");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Branch");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Customer Name");
        header.getCell(5).setCellStyle(style);
        header.createCell(6).setCellValue("Account No");
        header.getCell(6).setCellStyle(style);
        header.createCell(7).setCellValue("Loan Amount");
        header.getCell(7).setCellStyle(style);
        header.createCell(8).setCellValue("Collateral Amt");
        header.getCell(8).setCellStyle(style);
        int rowCount = 1;
        int i=0;
        int j=1;
	    for(ReportCollateralLinkedModel col : linkedAcct){
//	    	HSSFRow collLoan = sheet.createRow(rowCount++);
	    	
	    
	    	for(CollateralInfoModel colInfo:col.getColInfo()){
	    		HSSFRow collLoan = sheet.createRow(rowCount++);
	    		
	    		       
	    	collLoan.createCell(0).setCellValue(colInfo.getCollateralCode());
	    	collLoan.createCell(1).setCellValue(colInfo.getCollateralType());
	    	collLoan.createCell(2).setCellValue(colInfo.getCollateralName());
	    	collLoan.createCell(3).setCellValue(colInfo.getEstimatedAmount());
	    	collLoan.createCell(4).setCellValue(colInfo.getBranch());
	    
	    	
	    	
	    	}
	    	
	  
	    		
	    		for(CollateralLinkedModel colLinked:col.getLinkedAct()){
	    			
	    			HSSFRow collLoanL = sheet.createRow(rowCount++);
	    			
		    		
//		    		collLoan.createCell(5).setCellValue(colInfo.getBranch());
		    	
		    		//HSSFRow collLinked0 = sheet.createRow(rowCount++);
	    			collLoanL.createCell(5).setCellValue(colLinked.getCustomerName());
	    			collLoanL.createCell(6).setCellValue(colLinked.getAccountNo());
		    		
	    			collLoanL.createCell(7).setCellValue(colLinked.getLoanAmount());
	    			collLoanL.createCell(8).setCellValue(colLinked.getLinkedcollateralAmt());
		    		
		    		
		    	}	
	    		
	    	
	    	
	    	
	    	
	    	/**
	    	for(CollateralLinkedModel colLinked:col.getLinkedAct()){
//	    		collLoan.createCell(5).setCellValue(colInfo.getBranch());
	    	
	    		HSSFRow collLinked0 = sheet.createRow(j++);
	    		collLinked0.createCell(5).setCellValue(colLinked.getCustomerName() +"\/rn"+ collLinked0.createCell(6).setCellValue(colLinked.getAccountNo()));
	    		collLinked0.createCell(6).setCellValue(colLinked.getAccountNo());
	    		
	    		collLinked0.createCell(7).setCellValue(colLinked.getLoanAmount());
	    		collLinked0.createCell(8).setCellValue(colLinked.getLinkedcollateralAmt());
//		    	collLinked0.createCell(1).setCellValue("");
//		    	collLinked0.createCell(2).setCellValue("");
//		    	
//		    	HSSFRow collLinked = sheet.createRow(rowCount++);
//		    	collLinked.createCell(1).setCellValue("Linked Acct No.");
//		    	collLinked.getCell(1).setCellStyle(style2);
//		    	collLinked.createCell(2).setCellValue(colLinked.getAccountNo());
//		    	
//		    	
//		    	HSSFRow collLinked00 = sheet.createRow(rowCount++);
//		    	collLinked00.createCell(1).setCellValue("");
//		    	collLinked00.createCell(2).setCellValue("");
//		    	
//		    	HSSFRow collLinked2 = sheet.createRow(rowCount++);
//		    	collLinked2.createCell(1).setCellValue("Loan Amount");
//		    	collLinked2.getCell(1).setCellStyle(style2);
//		    	collLinked2.createCell(2).setCellValue(colLinked.getLoanAmount());
//		    	
//		    	
//		    	HSSFRow collLinked000 = sheet.createRow(rowCount++);
//		    	collLinked000.createCell(1).setCellValue("");
//		    	collLinked000.createCell(2).setCellValue("");
//		    	
//		    	HSSFRow collLinked3 = sheet.createRow(rowCount++);
//		    	collLinked3.createCell(1).setCellValue("Linked Collateral Amount");
//		    	collLinked3.getCell(1).setCellStyle(style2);
//		    	collLinked3.createCell(2).setCellValue(colLinked.getLinkedcollateralAmt());
		    	
	    	}****/
	    	
	    }
	}
		
	

}
