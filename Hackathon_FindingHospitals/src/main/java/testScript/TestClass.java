package testScript;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automationScript.BaseUI;
import automationScript.CorporateWellness;
import automationScript.HospitalsList;
import automationScript.TopCities;
import resources.ExtentReportManager;

public class TestClass {
	
	public static ExtentReports report;
	public static ExtentTest logger;
	
	@BeforeClass
	public void open() throws Exception 
	{
	  	BaseUI ba=new BaseUI();
		ba.openBrowserAndNavigateToPracto();
	
	}
	
	@Test(priority = 1)
	public void HospitalList() throws InterruptedException, IOException {
			
			  report = ExtentReportManager.getReportInstance();
		      logger=report.createTest("HospitalListPage");
			  
			  HospitalsList hospitallist = BaseUI.hospitals();
			  
			  logger.log(Status.INFO, "Enter the Hospital in field");
			  hospitallist.Enter_location();
			  
			  logger.log(Status.INFO, "Filter 24x7 Hospitals");
			  hospitallist.HospitalsOpen24x7();
			  
			  logger.log(Status.INFO, "Filter parking facility Hospitals");
			  hospitallist.applyHasparkingFilter();
			  
			  logger.log(Status.INFO, "Enter the List of Hospital");
			  hospitallist.ListHospitals();
			  
			  //hospitallist.ResultScreenShot();
			  
			  logger.log(Status.INFO, "Navigate to Home Page");
			  hospitallist.navigateToHomePage();
			  
			  //logger.log(Status.PASS, "Hospital List Test Executed Successfully");
			  
	}
	
	@Test(priority = 2 , dependsOnMethods = {"HospitalList"})
	public void DiagonosticsPage() throws InterruptedException, IOException {
		
		logger=report.createTest("DiagonosticsPage");
		TopCities topcity = HospitalsList.nextpage();
		
		logger.log(Status.INFO, "Get List of Top Cities");
		topcity.ListTopCities();
		
		//topcity.ResultScreenShot();
		logger.log(Status.INFO, "Navigate To HomePage");
		topcity.navigateToHomePage();
		
		//logger.log(Status.PASS, "Diagonostics Page Test Executed Successfully");
		
	}
	
	@Test(priority = 3 , dependsOnMethods = {"DiagonosticsPage"})
	public void corporateWellnessPage() throws InterruptedException, IOException {
		
		logger=report.createTest("CorporateWellnessPage");
		CorporateWellness corporatepage = TopCities.nextpage1();
		
		logger.log(Status.INFO, "Click Provider Button");
		corporatepage.clickProvider();
		
		logger.log(Status.INFO, "Click CorporateWellness Button");
		corporatepage.clickCorporateWellness();
		
		logger.log(Status.INFO, "Enter data to the fields");
		corporatepage.enterData();
		
		//logger.log(Status.INFO, "Handle the alerts");
		//corporatepage.hanldeAlert();
		//corporatepage.ResultScreenShot();
		//logger.log(Status.PASS, "CorporateWellness Page Test Executed Successfully");
		
	}
	@AfterTest
	public void endReport() {
		report.flush();
	}
	@AfterClass
	public void close_browser() {
		BaseUI bs = new BaseUI();
		bs.closeBrowser();
	}
	
}
