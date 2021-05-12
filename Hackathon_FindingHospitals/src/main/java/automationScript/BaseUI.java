package automationScript;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import testScript.TestClass;

public class BaseUI {
	static WebDriver driver = null;
	public static String data[];
	final String baseUrl = "https://www.practo.com/";
	//public static ExtentTest logger;
	public void openBrowserAndNavigateToPracto() throws IOException {
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
		  
		  String browser = datasheet.getRow(1).getCell(0).getStringCellValue();
		  
		  	driver = DriverSetup.getWebDriver(browser);
			driver.manage().window().maximize();
			driver.get(baseUrl);
			// Verify if correct page is loaded
			String pageTitle = driver.getTitle();
			Assert.assertEquals(pageTitle,
					"Practo | Video Consultation with Doctors, Book Doctor Appointments, Order Medicine, Diagnostic Tests");
	}
	
	ExtentTest logger1 = TestClass.logger;
	public void reportPass(String comments) {
		logger1.log(Status.PASS,comments);
		//Assert.assertTrue(true);
	}
	public void reportFail(String comments) {
		logger1.log(Status.FAIL,comments);
		//Assert.assertTrue(false);
		
	}
	
	public static void ResultScreenShot(String directory) throws IOException{
		
		ExtentTest logger1 = TestClass.logger;
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\ResultScreenShots\\"+directory));
		logger1.addScreenCaptureFromPath(System.getProperty("user.dir") + "\\ResultScreenShots\\"+directory);
		
	}
	
	public static void handleAlert() throws InterruptedException, IOException {
		
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		
	}
	
	public static boolean isAlertPresent(){
	    boolean foundAlert = false;
	    WebDriverWait wait = new WebDriverWait(driver, 0 /*timeout in seconds*/);
	    try {
	        wait.until(ExpectedConditions.alertIsPresent());
	        foundAlert = true;
	    } catch (Exception e) {
	        foundAlert = false;
	    }
	    return foundAlert;
	}

	public void closeBrowser() {
		driver.quit();
	}
	public static HospitalsList hospitals() {
		return PageFactory.initElements(driver, HospitalsList.class);
	}
}
