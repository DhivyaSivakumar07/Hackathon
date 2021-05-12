package automationScript;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.ReadOrWriteExcelSheet;


public class TopCities extends BaseUI{
	
	@FindBy(xpath="//a[@event='Nav:Interacted:Book diagnostic tests']")
	public static WebElement diagonisticselement;
	
	@FindBy(xpath="//*[@class='u-margint--standard o-f-color--primary']")
	public static List<WebElement> cities;
	
	public void ListTopCities() throws InterruptedException, IOException {
		
		try {
			
		String e = diagonisticselement.getAttribute("href");
		
		driver.navigate().to(e);
		TimeUnit.SECONDS.sleep(5);
		
		System.out.println("*************************List Of Top Cities**********************");
		System.out.println("****************************************************************");
		
		for( WebElement product : cities){
			System.out.println(product.getText());
		}
		
		ReadOrWriteExcelSheet.writeIntoExcel();
		
		ResultScreenShot("TopCities.png");
		
		reportPass("The top cities has been displayed successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
		
	}
	public void navigateToHomePage() throws InterruptedException {
		
		try {
			
		driver.navigate().back();
		TimeUnit.SECONDS.sleep(5);
		
		
		reportPass("Navigated To Home Page Successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}
	
	public static CorporateWellness nextpage1() {
		return PageFactory.initElements(driver, CorporateWellness.class);
	}

}
