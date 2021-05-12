package resources;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import automationScript.CorporateWellness;

import automationScript.TopCities;

public class ReadOrWriteExcelSheet extends TopCities{
	
	public static void writeIntoExcel() throws IOException {
		File file = new File(System.getProperty("user.dir")+"\\ExcelData\\TopCitiesDataSheet.xlsx");
		String fileName = "DataExcelSheet.xlsx";
		FileInputStream inputstream = new FileInputStream(file);
		Workbook workbook = null;

        
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if(fileExtensionName.equals(".xlsx")){

		    workbook = new XSSFWorkbook(inputstream);

		  }
		  else if(fileExtensionName.equals(".xls")){
			  workbook = new HSSFWorkbook(inputstream);
		  }

		  Sheet datasheet = workbook.getSheetAt(0);
	int i = 1;
	Row row = datasheet.createRow(0);
	
	for( WebElement product : cities){
		row = datasheet.createRow(i);
		row.createCell(0).setCellValue(product.getText());
		i++;
	}
	
	inputstream.close();
	FileOutputStream outputStream = new FileOutputStream(file);
	workbook.write(outputStream);
	outputStream.close();
	
	}
	public static void ReadFromExcel() throws InterruptedException, IOException, AWTException {
		
		CorporateWellness cs = TopCities.nextpage1();
		
		File file = new File(System.getProperty("user.dir")+"\\ExcelData\\DataExcelSheet.xlsx");
		String fileName = "DataExcelSheet.xlsx";
		FileInputStream inputstream = new FileInputStream(file);
		Workbook workbook = null;
        
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if(fileExtensionName.equals(".xlsx")){

		    workbook = new XSSFWorkbook(inputstream);

		  }
		  else if(fileExtensionName.equals(".xls")){
			  workbook = new HSSFWorkbook(inputstream);
		  }

		  Sheet datasheet = workbook.getSheetAt(0);
		  
		  for(int i=1;i<=2;i++) {
			  cs.nameelement.clear();
			  cs.nameelement.sendKeys(datasheet.getRow(i).getCell(1).getStringCellValue());
				TimeUnit.SECONDS.sleep(5);
				
				cs.organizationelement.clear();
				cs.organizationelement.sendKeys(datasheet.getRow(i).getCell(2).getStringCellValue());
				TimeUnit.SECONDS.sleep(5);
				
				cs.mailelement.clear();
				cs.mailelement.sendKeys(datasheet.getRow(i).getCell(3).getStringCellValue());
				TimeUnit.SECONDS.sleep(5);
				
				cs.phoneelement.clear();
				DataFormatter formatter = new DataFormatter();
				String val = formatter.formatCellValue(datasheet.getRow(i).getCell(4));
				cs.phoneelement.sendKeys(val);
				TimeUnit.SECONDS.sleep(5);
				
				Select org = new  Select(cs.organization);
				org.selectByVisibleText("501-1000");
				TimeUnit.SECONDS.sleep(5);
				
				cs.button.click();
				TimeUnit.MINUTES.sleep(3);


				if(isAlertPresent()) {
					handleAlert();
					WritePassOrFail_InExcelSheet(i,"Pass");
				}
				else {
					WritePassOrFail_InExcelSheet(i,"Fail");
				}
				ResultScreenShot("CorporateWellness" + i + ".png");
		  }
	}
	
	public static void WritePassOrFail_InExcelSheet(int row_num,String message) throws IOException {
		
		File file = new File(System.getProperty("user.dir")+"\\ExcelData\\DataExcelSheet.xlsx");
		String fileName = "DataExcelSheet.xlsx";
		FileInputStream inputstream = new FileInputStream(file);
		Workbook workbook = null;

        
		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if(fileExtensionName.equals(".xlsx")){

		    workbook = new XSSFWorkbook(inputstream);

		  }
		  else if(fileExtensionName.equals(".xls")){
			  workbook = new HSSFWorkbook(inputstream);
		  }

		  Sheet datasheet = workbook.getSheetAt(0);
		  
		  datasheet.getRow(row_num).createCell(5).setCellValue(message);
		  
		  inputstream.close();
		  FileOutputStream outputStream = new FileOutputStream(file);
		  workbook.write(outputStream);
		  outputStream.close();
	}
}
