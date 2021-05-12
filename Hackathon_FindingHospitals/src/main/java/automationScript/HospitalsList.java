package automationScript;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class  HospitalsList extends BaseUI{
	
	@FindBy(xpath ="//input[@placeholder='Search location']")
	public static WebElement e;
	
	@FindBy(xpath="//div[text()='Bangalore']")
	public static WebElement clickcityelement;
	
	@FindBy(xpath ="//input[@placeholder='Search doctors, clinics, hospitals, etc.']")
	public static WebElement hospitalelement;
	
	@FindBy(xpath ="//div[text()='Hospital']")
	public static WebElement clickhospitalelement;
	
	@FindBy(xpath ="//label[@for='Open-24X70']//div")
	public static WebElement element24x7;
	
	@FindBy(xpath ="//span[@data-qa-id='all_filters']//span")
	public static WebElement filterelement;
	
	@FindBy(xpath ="//div[@data-qa-id='Has_Parking_checkbox']")
	public static WebElement hasparkingelement;
	
	@FindBy(xpath ="//*[text()='Next']")
	public static WebElement NextPageButton;
	
	@FindBy(xpath ="//div[@data-qa-id='hospital_card']")
	public static List<WebElement> ListOfHospital;
	
	/*@FindBy(xpath ="//*[@class='common__star-rating__value']")
	public static List<WebElement> rating;
	
	@FindBy(xpath ="//h2[@data-qa-id='hospital_name']")
	public static List<WebElement> hospital;*/

	
	public void Enter_location() throws InterruptedException {
		try {
		e.clear();
		e.sendKeys("Bangalore");
		TimeUnit.SECONDS.sleep(3);
		clickcityelement.click();
		TimeUnit.SECONDS.sleep(3);
		
		hospitalelement.sendKeys("Hospital");
		TimeUnit.SECONDS.sleep(5);
		
		clickhospitalelement.click();
		TimeUnit.SECONDS.sleep(3);
		reportPass("The Location has been entered successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}
	public void HospitalsOpen24x7() throws InterruptedException {
		
		try {
			
		element24x7.click();
		TimeUnit.SECONDS.sleep(3);
		reportPass("24x7 filter has been applied successfully");
		
		}catch(Exception e) {
			
			e.printStackTrace();
			reportFail(e.getMessage());
		}
		
	}
	
	public void applyHasparkingFilter() throws InterruptedException {
		
		try {
			
		filterelement.click();
		TimeUnit.SECONDS.sleep(3);
		
		hasparkingelement.click();
		TimeUnit.SECONDS.sleep(3);
		
		reportPass("Has parking filter has been applied successfully");
		
		}catch(Exception e) {
			
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}
	
	public void ListHospitals() throws InterruptedException {
		
		try {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 TimeUnit.SECONDS.sleep(3);  
		  //WebElement end=driver.findElement(By.xpath("//*[text()='Next']"));
		  for(int i=0;i<16;i++) { 
				 js.executeScript("arguments[0].scrollIntoView();",NextPageButton);
				 TimeUnit.SECONDS.sleep(3);
				 js.executeScript("window.scrollBy(0,-100)");
				 TimeUnit.SECONDS.sleep(3);
		  }
		  System.out.println("Ratings");
		  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  
		  //List<WebElement>  hosCard=driver.findElements(By.xpath("//div[@data-qa-id='hospital_card']"));
		  //System.out.println(hosCard.size());
		  int i=2;//c=0;
		  for(WebElement hos:ListOfHospital )
		  {
			 WebElement rating,hospital;
			 int  rateCount=driver.findElements(By.xpath("//*[@id=\"container\"]/div[3]/div/div[2]/div[1]/div/div[3]/div["+i+"]/div/div[1]/div[2]/div[1]/div")).size();
			 
			  if(rateCount==4) {
				   rating=driver.findElement(By.xpath("//*[@id=\"container\"]/div[3]/div/div[2]/div[1]/div/div[3]/div["+i+"]/div/div[1]/div[2]/div/div[1]/div/div/span[1]"));
				   hospital=driver.findElement(By.xpath("//*[@id=\"container\"]/div[3]/div/div[2]/div[1]/div/div[3]/div["+i+"]/div/div[1]/div[1]/div/div[2]/div/a/h2"));
				   
				   float rat=Float.parseFloat(((WebElement) rating).getText());
				   if(rat > 3.5 )
				   {
					   System.out.println(hospital.getText());
					   //c++;
				   }  
			  }
			  i++;
		  }
		  
		  reportPass("List of Hospitals has been displayed");
		  
		}catch (Exception e) {
			
			e.printStackTrace();
			reportFail(e.getMessage());
			
		}
		
		/*try {
			
		Iterator<WebElement> ritr = rating.iterator();
		float[] rate = new float[15];
		int i = 0;
		while(ritr.hasNext()) {
			WebElement e1 = ritr.next();
			rate[i] = Float.parseFloat(e1.getText());
			i++;
		}
		System.out.println("*************************List of Hospitals open 24*7 *********************************");
		System.out.println("****************************************************************************");
	
		Iterator<WebElement> hnItr = hospital.iterator();
		i = 0;
		while (hnItr.hasNext()) {
			WebElement ob = hnItr.next();
			if(rate[i]>3.5){
				System.out.println(ob.getText());
			}
			i++;
		}
		ResultScreenShot("HospitalsList.png");
		reportPass("List of Hospitals has been displayed");
	}catch(Exception e) {
		
		e.printStackTrace();
		reportFail(e.getMessage());
	}*/
	}

	public void navigateToHomePage() throws InterruptedException {
		
		try {
			
		driver.navigate().back();
		TimeUnit.SECONDS.sleep(3);
		driver.navigate().back();
		TimeUnit.SECONDS.sleep(3);
		driver.navigate().back();
		TimeUnit.SECONDS.sleep(3);
		
		reportPass("Successfully navigated to the Home Page");
		}catch(Exception e) {
			
			e.printStackTrace();
			reportFail(e.getMessage());
		}
	}
	public static TopCities nextpage() {
		return PageFactory.initElements(driver, TopCities.class);
	}
}
