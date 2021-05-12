package automationScript;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.ReadOrWriteExcelSheet;

public class CorporateWellness extends BaseUI{
	
	@FindBy(xpath="//*[@id='root']/div/div/div[1]/div[1]/div[2]/div/div[3]/div[1]/span[1]")
	public static WebElement providerelement;
	
	@FindBy(xpath="//*[@id='root']/div/div/div[1]/div[1]/div[2]/div/div[3]/div[1]/div/div[4]/a")
	public static WebElement corporatewellnesselement;
	
	@FindBy(id ="name")
	public static WebElement nameelement;
	
	@FindBy(id ="organization_name")
	public static WebElement organizationelement;
	
	@FindBy(id ="official_email_id")
	public static WebElement mailelement;
	
	@FindBy(id ="official_phone_no")
	public static WebElement phoneelement;
	
	@FindBy(id ="button-style")
	public static WebElement button;
	
	@FindBy(id="organization_size")
	public static WebElement organization;
	
	public void clickProvider() throws InterruptedException {
		
		try {
			
		providerelement.click();
		TimeUnit.SECONDS.sleep(5);
		
		reportPass("Successfully clicked the Click Provider");
		}catch(Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
		
	}
	public void clickCorporateWellness() throws InterruptedException {
		
		try {
		
		corporatewellnesselement.click();
		TimeUnit.SECONDS.sleep(5);
		
		reportPass("Navigated to CorporateWellness Link Successfully");
		}catch(Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
		
	}
	public void enterData() throws InterruptedException, IOException {
		
		try {
		
		Set<String> windowids = driver.getWindowHandles();
		Iterator<String> itr = windowids.iterator();
		String mainpageId = itr.next();
		String CorporatePage = itr.next();
		
		driver.switchTo().window(CorporatePage);
		
		ReadOrWriteExcelSheet.ReadFromExcel();
			
		 reportPass("Entered the data successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
		
	}
	
	
}
