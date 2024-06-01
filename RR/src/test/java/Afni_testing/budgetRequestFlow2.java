package Afni_testing;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;
import utility_RR.Utility_RR;

public class budgetRequestFlow2 extends Utility_RR {
	protected List<WebDriver> drivers;
	WebDriver driver1;
	WebDriver driver2;
	DesiredCapabilities capabilities1;
	
	@Parameters("corpID")
	@BeforeClass
	public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
		  
		  
		
		 drivers = new ArrayList<>();
		 
		 	String username = "dheerajc";
	        String accessKey = "Ov10dY7ZKURRQlLaw3GzQnqApPhSf5SmKTjmtFXuOph6guPsXt";
	        String hubURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";
	        
//	        capabilities1 = new DesiredCapabilities();
//	        capabilities1.setCapability("browserName", "chrome");
//	        capabilities1.setCapability("version", "latest");
//	        capabilities1.setCapability("platform", "Windows 10");
	        
	        ChromeOptions browserOptions = new ChromeOptions();
	        browserOptions.setPlatformName("Windows 11");
	        browserOptions.setBrowserVersion("123.0");
	        browserOptions.addArguments("--disable-css-hover-effects");
	        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
	        ltOptions.put("username", "dheerajc");	
	        ltOptions.put("accessKey", "Ov10dY7ZKURRQlLaw3GzQnqApPhSf5SmKTjmtFXuOph6guPsXt");
	        ltOptions.put("project", "Jenkins Script");
	        ltOptions.put("build", "JenkinScript");
	        ltOptions.put("w3c", true);
	       
	        browserOptions.setCapability("LT:Options", ltOptions);
	        
	        drivers.add(new RemoteWebDriver(new URL(hubURL), browserOptions));
	        drivers.add(new RemoteWebDriver(new URL(hubURL), browserOptions));
	        
	        
	        driver1 = drivers.get(0);
	        driver2 = drivers.get(1);
		 
		 
	  }
		
	  
	  @BeforeMethod
	  public void beforeMethod() throws EncryptedDocumentException, IOException {
		  
		
			  
			 
		  
	  }
	  @Parameters("corpID")
	  @Test(priority=1,enabled=true)
	  public void requestAmount(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
		 
		  driver1.get("https://development1.advantageclub.co/login");
		  
		  
	  }
	  
	  
	  @Parameters("corpID")
	  @Test(priority=2,enabled=true)
	  public void submitWithoutAmount(String corpID) throws InterruptedException {
		
		  driver2.get("https://www.advantageclub.co/login");
		  
	  
		  
	}
	  
	  @Parameters("corpID")
	  @Test(priority=3,enabled=true)
	  public void submitWithoutAmount1(String corpID) throws InterruptedException {
		
		  driver1.get("https://development1.advantageclub.co/pages/get_demo");
	  
		  
	}
	 
	  

	  @AfterMethod
	  public void afterMethod() {
		 
	  }

	 

	  @AfterClass
	  public void afterClass() {
		  //driver.quit();
		  driver1.quit();
		  driver2.close();
	  }

}


