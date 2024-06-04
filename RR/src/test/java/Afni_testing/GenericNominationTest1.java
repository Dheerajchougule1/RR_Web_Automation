package Afni_testing;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility_RR.Utility_RR;




public class GenericNominationTest1 extends Utility_RR {
	//ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
//	 private List<WebDriver> drivers = new ArrayList<>();
//	 protected static WebDriver driver=null;
	
	public void newui_login(String corpID ,String username, String password) throws InterruptedException, AWTException  {
		driver.findElement(By.xpath("(//a[@href='/login'])[1]")).click();
		 Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		Thread.sleep(3000);
//		waitForPageLoad();	
		
		 Robot robot = new Robot();
		  for (int i = 0; i < 4; i++) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			
		  }
	}
	 
	    @Parameters("corpID")
		@BeforeClass
		public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	    	multiSessionTest(corpID);
	    	
//	 	        String username = "dheerajc";
//	        	String accessKey = "Ov10dY7ZKURRQlLaw3GzQnqApPhSf5SmKTjmtFXuOph6guPsXt";
//	        	String hubURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";
//	 
//	 	        ChromeOptions browserOptions = new ChromeOptions();
//	 	        browserOptions.setPlatformName("Windows 11");
//	 	        browserOptions.setBrowserVersion("123.0");
//	 	        browserOptions.addArguments("--disable-css-hover-effects");
//	 	        HashMap<String, Object> ltOptions = new HashMap<>();
//	 	         ltOptions.put("username", "dheerajc");	
//	        	ltOptions.put("accessKey", "Ov10dY7ZKURRQlLaw3GzQnqApPhSf5SmKTjmtFXuOph6guPsXt");
//	        	ltOptions.put("project", "Jenkins Script");
//	        	ltOptions.put("build", "JenkinScript");
//	       		ltOptions.put("w3c", true);
////	 	        browserOptions.setCapability("LT:Options", ltOptions);
//	 //
////	 	        driver1 = new RemoteWebDriver(new URL(hubURL), browserOptions);
////	 	        driver2 = new RemoteWebDriver(new URL(hubURL), browserOptions);
//	       		Double driverCount = DataGenericNominationFlowNum(corpID, 1, 1);
//
//	            WebDriverManager.chromedriver().setup();
//	            ChromeOptions options = new ChromeOptions();
//	            
//                
//	            for (int i = 0; i < driverCount; i++) {
//	            	driver = new ChromeDriver(options);
//	                driver.get("https://development1.advantageclub.co/login");
//	                drivers.add(driver);
//	            }
//	 	    	
	} 
	    WebDriver driver = Utility_RR.driver;
  
			
	    public void setDriver(WebDriver driver) {
	        threadLocalDriver.set(driver);
	        Utility_RR.driver = driver;
	        System.out.println("ThreadLocal Driver set: " + driver);
	    }

	    protected WebDriver getDriver() {
	    	driver = threadLocalDriver.get();
	    	System.out.println("ThreadLocal Driver retrieved: " + driver);
	        return threadLocalDriver.get();
	        
	    }

	    @BeforeMethod
	    public void beforeMethod() {
	        // Any setup before each test method
	    }

	    @Parameters("corpID")
	    @Test(priority = 1, enabled = true)
	    public void nominateForAward(String corpID) throws InterruptedException, EncryptedDocumentException, AWTException, IOException {
	        setDriver(drivers.get(0));
	        driver = getDriver();
	        Thread.sleep(5000);
	        System.out.println("Executing nominateForAward with driver: " + driver);
	        //login for nominator 
	        newui_login(corpID,DataGenericNominationFlow(corpID, 4, 1), DataGenericNominationFlow(corpID, 4, 2));
	        //redirect to nominate for award 
	        driver.get(DataRunScript(1, 1)+"in/pages/nominate_for_award");
	        //send nomination 
	        nominationInput(DataGenericNominationFlow(corpID,8,1));
	        nominateBudgetSelect(DataGenericNominationFlowNum(corpID, 19, 1));
	        nominateAwardSelect(DataGenericNominationFlowNum(corpID, 20, 1));
	        
	    }

	    @Parameters("corpID")
	    @Test(priority = 2, enabled = true)
	    public void storedValuePoints(String corpID) throws InterruptedException {
	        setDriver(drivers.get(1));
	        driver = getDriver();
	       
	        driver.get("https://www.advantageclub.co/login");
	        // Use utility functions that use the global driver reference
	    }

	    @Parameters("corpID")
	    @Test(priority = 3, enabled = true)
	    public void submitWithoutAmount1(String corpID) throws InterruptedException {
	        setDriver(drivers.get(2));
	        driver = getDriver();
	       
	      //input[@id='sig-email']
	        
	       // driver.get("https://development1.advantageclub.co/pages/get_demo");
	        // Use utility functions that use the global driver reference
	    }

	    @AfterMethod
	    public void afterMethod() {
	        // Any cleanup after each test method
	    }

	    @AfterClass
	    public void afterClass() {
//	        if (driver1 != null) {
//	            driver1.quit();
//	        }
//	        if (driver2 != null) {
//	            driver2.quit();
//	        }
	    }

}