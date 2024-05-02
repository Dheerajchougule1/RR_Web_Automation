package utility_RR;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utility_RR {
	
	//protected static WebDriver driver;s w
	protected Actions act;
	protected File actualScreenshot;
	protected static SoftAssert sa;
	protected String oldTabHandle;
	protected String newTabHandle;
	protected static JavascriptExecutor js;
	protected String App_award_name;
	protected String give_award_budget_point;
	protected String token ;
	protected WebDriverWait wait ;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	
	 protected String username= "dheerajc"; 
	 protected String accesskey= "Ov10dY7ZKURRQlLaw3GzQnqApPhSf5SmKTjmtFXuOph6guPsXt";
	 protected static WebDriver driver=null;
  //  protected static WebDriver driver;
     String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
//    protected DesiredCapabilities capabilities;
	

				
	
	public void startBrowser(String instanceName) throws InterruptedException, EncryptedDocumentException, IOException {
		
//		System.setProperty("webdriver.chrome.driver","C:\\Dheeraj Chougule old\\Dheeraj old\\chromedriver_win32\\chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\AC\\chromdriver\\chrome-win64\\chrome-win64\\chrome.exe");
		
		//System.setProperty("webdriver.edge.driver","C:\\Dheeraj C_Old\\Dheeraj C\\Setup\\edgedriver_win64\\msedgedriver.exe");
		
		if(DataRunScript(5, 1).contains("local")) {
			
		try {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
//		ChromeOptions options = new ChromeOptions();
		options.addArguments("inprivate");
////		options.addArguments("force-device-scale-factor=0.8");
//		driver = new ChromeDriver(options);
		driver = new EdgeDriver(options);
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
		driver.get(instanceName);
		driver.manage().window().maximize();
		waitForPageLoad();
//		Thread.sleep(2000);
//		((JavascriptExecutor) driver).executeScript("document.body.style.zoom = '50%'");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		}
		
		else if(DataRunScript(5, 1).contains("lambda")) {	
//			capabilities = new DesiredCapabilities();
//		  	capabilities.setCapability("browserName", "chrome");
//	        capabilities.setCapability("version", "123.0");
//	        capabilities.setCapability("platform", "win11"); // If this cap isn't specified, it will just get the any available one
//	        capabilities.setCapability("build", "JenkinScript");
//	        capabilities.setCapability("name", "JenkinScript");
////	        capabilities.setCapability("network", "true");
//	        capabilities.setCapability("console", "true");
//	        capabilities.setCapability("terminal", "true");
//	        capabilities.setCapability("console", "true");
	        
	        ChromeOptions browserOptions = new ChromeOptions();
	        browserOptions.setPlatformName("Windows 11");
	        browserOptions.setBrowserVersion("123.0");
	        browserOptions.addArguments("--disable-css-hover-effects");
	        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
	        ltOptions.put("username", "dheerajc");	
	        ltOptions.put("accessKey", "Ov10dY7ZKURRQlLaw3GzQnqApPhSf5SmKTjmtFXuOph6guPsXt");
	        ltOptions.put("project", "Untitled");
	        ltOptions.put("w3c", true);
	       
	        browserOptions.setCapability("LT:Options", ltOptions);
	        
	        


	      try {
	          driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), browserOptions);
	      } catch (MalformedURLException e) {
	          System.out.println("Invalid grid URL");
	      } catch (Exception e) {	
	          System.out.println(e.getMessage());
	      }
	      
	      // Set implicit wait time
	      	Thread.sleep(8000);	
//	        driver.manag	e().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	      
	      	driver.get(instanceName);
//	      	driver.get("https://development1.advantageclub.co/in");
			driver.manage().window().maximize();
			Thread.sleep(2000);
			
			Actions action=new Actions(driver);
			action.moveByOffset(0, 0).perform();
//			((JavascriptExecutor) driver).executeScript("document.documentElement.style.transform = 'scale(0.7)'");
//			((RemoteWebDriver) driver).executeScript("document.body.style.zoom=0.5;");
//			Thread.sleep(2000);
			
		}	
			
		
//		DesiredCapabilities cap = new DesiredCapabilities();
//		
//		cap.setCapability(CapabilityType.BROWSER_NAME,"Chrome");
//		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
//		
//		cap.setCapability("apppium:chromeOptions", ImmutableMap.of("w3c", false));
//		
//		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.23:4723/wd/hub"), cap);
//		Thread.sleep(2000);
//		
//		driver.get(instanceName);
//		Thread.sleep(2000);
	}
	
	
	public String DataRunScript(int rowIndex,int columnIndex) throws EncryptedDocumentException, IOException {
		FileInputStream run_script = new FileInputStream("excel/Run_Script.xlsx");	
		Sheet Mysheet = WorkbookFactory.create(run_script).getSheet("Sheet1");	
		String value = Mysheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
		return value;
		
	}
	
	public String DataAppriciateFlow(String corp  ,int rowIndex,int columnIndex) throws EncryptedDocumentException, IOException {
		FileInputStream run_script = new FileInputStream("excel/Appriciate_Flow.xlsx");	
		Sheet Mysheet = WorkbookFactory.create(run_script).getSheet(corp);	
		String value = Mysheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
		return value;
		
	}
	
	public String DataReportFlow(String corp  ,int rowIndex,int columnIndex) throws EncryptedDocumentException, IOException {
		FileInputStream run_script = new FileInputStream("excel/Report/reports.xlsx");	
		Sheet Mysheet = WorkbookFactory.create(run_script).getSheet(corp);	
		String value = Mysheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
		return value;
		
	}
	
	
	
	public String DataGiveAwardFlow(String corp  ,int rowIndex,int columnIndex) throws EncryptedDocumentException, IOException {
		FileInputStream giveAwardFlow = new FileInputStream("excel/GiveAwardFlow/GiveAwardFlow.xlsx");	
		Sheet Mysheet2 = WorkbookFactory.create(giveAwardFlow).getSheet(corp);	
		String value = Mysheet2.getRow(rowIndex).getCell(columnIndex).getStringCellValue();	
		return value;
		
	}
	
	public String DataNewsFeed(int rowIndex,int columnIndex) throws EncryptedDocumentException, IOException {
		FileInputStream run_script = new FileInputStream("excel/GenericFlow/NewsFeedDelete.xlsx");	
		Sheet Mysheet2 = WorkbookFactory.create(run_script).getSheet("Sheet1");	
		String value = Mysheet2.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
		return value;
		
	}
	
	public Double DataAppriciateFlowNum(String corp  ,int rowIndex,int columnIndex) throws EncryptedDocumentException, IOException {
		FileInputStream run_script = new FileInputStream("excel/Appriciate_Flow.xlsx");	
		Sheet Mysheet = WorkbookFactory.create(run_script).getSheet(corp);	
		Double value = Mysheet.getRow(rowIndex).getCell(columnIndex).getNumericCellValue();
		return value;
		
	}
	
	public String DataNominationFlow(String corp  ,int rowIndex,int columnIndex) throws EncryptedDocumentException, IOException {
		FileInputStream giveAwardFlow = new FileInputStream("excel/NominationFlow/NominationFlow.xlsx");	
		Sheet Mysheet2 = WorkbookFactory.create(giveAwardFlow).getSheet(corp);	
		String value = Mysheet2.getRow(rowIndex).getCell(columnIndex).getStringCellValue();	
		return value;
		
	}
	
	public Double DataNominationFlowNum(String corp  ,int rowIndex,int columnIndex) throws EncryptedDocumentException, IOException {
		FileInputStream run_script = new FileInputStream("excel/NominationFlow/NominationFlow.xlsx");	
		Sheet Mysheet = WorkbookFactory.create(run_script).getSheet(corp);	
		Double value = Mysheet.getRow(rowIndex).getCell(columnIndex).getNumericCellValue();
		return value;
		
	}
	
	
	public Double DataGivaAwardFlowNum(String corp  ,int rowIndex,int columnIndex) throws EncryptedDocumentException, IOException {
		FileInputStream run_script = new FileInputStream("excel/GiveAwardFlow/GiveAwardFlow.xlsx");	
		Sheet Mysheet = WorkbookFactory.create(run_script).getSheet(corp);	
		Double value = Mysheet.getRow(rowIndex).getCell(columnIndex).getNumericCellValue();
		return value;
		
	}
	
	public void login(String corpID ,String username, String password) throws InterruptedException, AWTException, EncryptedDocumentException, IOException {
		driver.findElement(By.xpath("//div[@class='login-section']")).click();
		waitForPageLoad();
		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		Thread.sleep(3000);
		 Robot robot = new Robot();
		  for (int i = 0; i < 6; i++) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
		
		  Thread.sleep(3000);
		
		 importWait();
		if(DataAppriciateFlow(corpID, 2, 4).isEmpty()) {
			driver.findElement(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//li[4]")).click();
		}
		 Thread.sleep(3000);
	}
	
	public void newui_login(String corpID ,String username, String password) throws InterruptedException, AWTException, EncryptedDocumentException, IOException {
		driver.findElement(By.xpath("//div[@class='login-section']")).click();
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
		  
		  Thread.sleep(2000);
//		  waitForPageLoad();
		
		
		if(DataAppriciateFlow(corpID, 2, 4).isEmpty()) {
			driver.findElement(By.xpath("//input[@id='location-search-bar']")).click();
			driver.findElement(By.xpath("//input[@id='location-search-bar']")).sendKeys("gur");
			  Thread.sleep(2000);
			act = new Actions(driver);
			act.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
			act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
			
		}
		
		 Thread.sleep(2000);
	}
	
	public void appriciateEmpSearch(String empName) throws InterruptedException {
		driver.findElement(By.xpath("(//input[@class='token-input ui-autocomplete-input'])[1]")).sendKeys(empName);
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'])[1]//li[1]")).click();
	}
	
	public void newui_appriciateEmpSearch(String empName) throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='recipient_tokenfield']")).sendKeys(empName);
		Thread.sleep(2000);
		act = new Actions(driver);
		act.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
		act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
		//driver.findElement(By.xpath("(//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'])[1]//li[1]")).click();
	}
	
	
	public void GiveAwardEmpSearch(String empName) throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='tokenfield']")).sendKeys(empName);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'])[1]//li[1]")).click();
	}
	

	public void appriciateCCEmpSearch(String empName) throws InterruptedException {
		driver.findElement(By.xpath("(//input[@class='token-input ui-autocomplete-input'])[2]")).sendKeys(empName);
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'])[2]//li[1]")).click();
	}
	
	public void newui_appriciateCCEmpSearch(String empName) throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='cc_tokenfield']")).sendKeys(empName);
		Thread.sleep(3000);
		act = new Actions(driver);
		act.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
		act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
		//driver.findElement(By.xpath("(//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'])[2]//li[1]")).click();
	}
	
	public void importWait() {
		//driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	}
	
	
	
	public void mailCheck(String corpID1,String screenName, String EmpName) throws EncryptedDocumentException, IOException, InterruptedException {
//		act= new Actions(driver);
//		act.keyDown(Keys.chord(Keys.CONTROL)).sendKeys("t").keyUp(Keys.chord(Keys.CONTROL)).build().perform();
		((JavascriptExecutor) driver).executeScript("window.open('', '_blank');");
		// Get a list of window handles (tabs)
		Set<String> windowHandles = driver.getWindowHandles();

		// Switch to the new tab (assuming it's the last tab opened)
		newTabHandle = windowHandles.toArray()[windowHandles.size() - 1].toString();
		driver.switchTo().window(newTabHandle);

		// Navigate to a URL in the new tab
		driver.get("https://yopmail.com/en/");
		importWait();
		Thread.sleep(1500);
		WebElement mailID = driver.findElement(By.xpath("//div[@class='ycptctn']"));
		mailID.click();
		act = new Actions(driver);
		act.sendKeys(EmpName).build().perform();
		act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
		screenShot(screenName);
		
		//driver.quit();
		oldTabHandle = windowHandles.toArray()[windowHandles.size()- 2].toString();
		driver.switchTo().window(oldTabHandle);
		
		
			
	}
	
	public void mailCheck_multiple_Appreciate(String corpID1,String screenName, int RowNum, int CellNum) throws EncryptedDocumentException, IOException, InterruptedException {
//		act= new Actions(driver);
//		act.keyDown(Keys.chord(Keys.CONTROL)).sendKeys("t").keyUp(Keys.chord(Keys.CONTROL)).build().perform();
		for(Double q = DataAppriciateFlowNum(corpID1, 7, 2); q>0; q--) {
		((JavascriptExecutor) driver).executeScript("window.open('', '_blank');");
		// Get a list of window handles (tabs)
		}
		Set<String> windowHandles = driver.getWindowHandles();
		
		
		
		for(Double q = DataAppriciateFlowNum(corpID1, 7, 2); q>0; q--) {
			
		// Switch to the new tab (assuming it's the last tab opened)
		newTabHandle = windowHandles.toArray()[windowHandles.size() - q.intValue()].toString();
		driver.switchTo().window(newTabHandle);

		// Navigate to a URL in the new tab
		driver.get("https://yopmail.com/en/");
		importWait();
		Thread.sleep(1000);
		// close
		boolean yopmail_clear_button = driver.findElement(By.xpath("//i[@class='material-icons-outlined f24 ycptbutgray']")).isDisplayed();
		if(yopmail_clear_button == true) {
			driver.findElement(By.xpath("//i[@class='material-icons-outlined f24 ycptbutgray']")).click();
		}
		
		WebElement mailID = driver.findElement(By.xpath("//div[@class='ycptctn']"));
		mailID.click();
		act = new Actions(driver);
		act.sendKeys(DataAppriciateFlow(corpID1,RowNum,CellNum)).build().perform();
		act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
		screenShot(screenName);
		CellNum ++;
		}
		//driver.quit();
		oldTabHandle = windowHandles.toArray()[windowHandles.size()- windowHandles.size()].toString();
		driver.switchTo().window(oldTabHandle);		
			
	}
	
	
	public void mailCheck_multiple_GiveAward(String corpID1,String screenName, int RowNum, int CellNum) throws EncryptedDocumentException, IOException, InterruptedException {
//		act= new Actions(driver);
//		act.keyDown(Keys.chord(Keys.CONTROL)).sendKeys("t").keyUp(Keys.chord(Keys.CONTROL)).build().perform();
		for(Double q = DataAppriciateFlowNum(corpID1, 7, 2); q>0; q--) {
		((JavascriptExecutor) driver).executeScript("window.open('', '_blank');");
		// Get a list of window handles (tabs)
		}
		Set<String> windowHandles = driver.getWindowHandles();
		
		
		
		for(Double q = DataAppriciateFlowNum(corpID1, 7, 2); q>0; q--) {
			
		// Switch to the new tab (assuming it's the last tab opened)
		newTabHandle = windowHandles.toArray()[windowHandles.size() - q.intValue()].toString();
		driver.switchTo().window(newTabHandle);

		// Navigate to a URL in the new tab
		driver.get("https://yopmail.com/en/");
		importWait();
		Thread.sleep(1000);
		// close
		boolean yopmail_clear_button = driver.findElement(By.xpath("//i[@class='material-icons-outlined f24 ycptbutgray']")).isDisplayed();
		if(yopmail_clear_button == true) {
			driver.findElement(By.xpath("//i[@class='material-icons-outlined f24 ycptbutgray']")).click();
		}
		
		WebElement mailID = driver.findElement(By.xpath("//div[@class='ycptc	tn']"));
		mailID.click();
		act = new Actions(driver);
		act.sendKeys(DataGiveAwardFlow(corpID1,RowNum,CellNum)).build().perform();
		act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
		screenShot(screenName);
		CellNum ++;
		}
		//driver.quit();
		oldTabHandle = windowHandles.toArray()[windowHandles.size()- windowHandles.size()].toString();
		driver.switchTo().window(oldTabHandle);		
			
	}
	
	//Screenshot
	public void screenShot(String screenName) throws IOException, InterruptedException
	{
		Date d = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("dd_mm_yyyy");
//		String strDate= formatter.format(d);
//		System.out.println(strDate);
//		String FileName = strDate + ".png";
//		System.out.println(FileName);
		
	    String FileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
	    File scr=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    
	    File dest= new File("C:\\Dheeraj C_Old\\Automation Testing onboarding\\ScreenShot\\"+screenName+FileName+".png");
	    FileUtils.copyFile(scr, dest);
	}
	
	//awardSelection
	
	public void newui_awardSelect(Double award) throws InterruptedException {
		String AwardNum1 = award.toString();
		char AwardNum = AwardNum1.charAt(0);
		driver.findElement(By.xpath("//div[@data-slick-index='"+AwardNum+"']")).click();
		Thread.sleep(3000);
		App_award_name = driver.findElement(By.xpath("//div[@data-slick-index='"+AwardNum+"']")).getText();
		Thread.sleep(1000);
	}
	
	public void awardSelect(Double award) throws InterruptedException {
		String AwardNum = award.toString();
		driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li["+AwardNum+"]")).click();
		App_award_name = driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li["+AwardNum+"]//div[1]")).getText();
		Thread.sleep(1000);
	}
	
	public void BudgetGiveAwardelect(Double award) throws InterruptedException {
		String AwardNum1 = award.toString();
		driver.findElement(By.xpath("//ul[@class='selectBudgetSliderContainer']//li["+AwardNum1+"]")).click();
		App_award_name = driver.findElement(By.xpath("//ul[@class='selectBudgetSliderContainer']//li["+AwardNum1+"]//div[1]")).getText();
		Thread.sleep(1000);
	}
	
	public void CardSelect(Double award) throws InterruptedException {
		String AwardNum = award.toString();
		
		WebElement budgetSelect = driver.findElement(By.xpath("//ul[@class='slides']//li["+AwardNum+"]"));
		budgetSelect.click();
		give_award_budget_point = budgetSelect.findElement(By.xpath("(//span[@class='notranslate'])["+AwardNum+"]")).getText();
		System.out.println(give_award_budget_point);
		Thread.sleep(1000);
	}
	
	
	public void ValueStatementSingle(Double valueStatement) throws InterruptedException {
		Double value2 = valueStatement+1.0;
		String valueNum = value2.toString();
		driver.findElement(By.xpath("//button[@id='valueStatementButton']")).click();
		driver.findElement(By.xpath("//ul[@id='value_statements']//li["+valueNum+"]")).click();
		Thread.sleep(1000);
	}
	
	
	//Scroll up to Element
	
	public void ScrollIntoView(String NewsFeedID1) throws InterruptedException {
		WebElement element1 = driver.findElement(By.id(NewsFeedID1));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		
	}
	
	public void ScrollIntoView1(String NewsFeedID1) throws InterruptedException {
		WebElement element1 = driver.findElement(By.xpath("//div[@data_id='"+NewsFeedID1+"']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		
	}
	
	public void ScrollIntoView_by_webelement(String ScrollByElement) throws InterruptedException {
		WebElement element1 = driver.findElement(By.xpath(ScrollByElement));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
		Thread.sleep(1000);
		
	}
	
	//Vertical Scroll	
			public void verticalScroll() throws InterruptedException {
				js = (JavascriptExecutor) driver;	
				js.executeScript("window.scrollBy(0,-500)");
			    Thread.sleep(1000);
	
	}
			
	public void SelectOptionInNestedNevigation(String MainAndInsideName) throws InterruptedException {
		
		 String[] MainAndInsideName1 = MainAndInsideName.split(",");
		
		driver.findElement(By.xpath("//span[text()='"+MainAndInsideName1[0]+"']")).click();
		importWait();
		driver.findElement(By.xpath("//span[text()='"+MainAndInsideName1[1]+"']")).click();
		
		
	}
	
	 public void selectDateUsingJavaScript(WebDriver driver, WebElement element, String date) {
	      
		  		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	       
		      	jsExecutor.executeScript("arguments[0].value='" + date + "';", element);
	    }
	 
	 public static String getToken() throws InterruptedException {
	      
		 // After login, get cookies
			Thread.sleep(2000);
			 String javascriptCode = "return userDetails.authentication_token;";
		     String token = (String) ((JavascriptExecutor) driver).executeScript(javascriptCode);
		     System.out.println(token);
		     
		     

	    if (token != null) {
	        System.out.println("Token found: " + token);
	    } else {
	        System.out.println("Token not found");
	    }
	    
	    	return token;
	 	}
	 
	 public void sendEmailWithAttachment(String to, String subject, String body, String filePath) {
	        try {
	            // Create the attachment
	            EmailAttachment attachment = new EmailAttachment();
	            attachment.setPath(filePath);
	            attachment.setDisposition(EmailAttachment.ATTACHMENT);

	            // Create the email
	            MultiPartEmail email = new MultiPartEmail();
	            email.setHostName("smtp.gmail.com"); // Replace with your SMTP host
	            email.setSmtpPort(587); // Replace with your SMTP port
	            email.setAuthenticator(new DefaultAuthenticator("dheerajc@advantageclub.in", ""));
	            email.setStartTLSEnabled(true); // Enable TLS
	            email.setFrom("dheerajc@advantageclub.in");
	            email.setSubject(subject);
	            email.setMsg(body);
	            email.addTo(to);

	            // Add the attachment
	            email.attach(attachment);

	            // Send the email
	            email.send();

	            System.out.println("Email sent successfully with attachment");
	        } catch (EmailException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void takeActualScreenshot() {
			
		  actualScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		
	}
	 
	 public void waitForPageLoad() {
			
		 	wait = new WebDriverWait(driver, Duration.ofSeconds(40));
//	 		wait.until((ExpectedCondition<Boolean>) webDriver -> ((JavascriptExecutor) webDriver)
//              .executeScript("return document.readyState").equals("complete"));
	 		
	 	// Wait for the document.readyState to be 'complete'
//	        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
//
////	        // Wait for all API calls to finish
//	        
//	        wait.until(ExpectedConditions.jsReturnsValue("return (window.performance.getEntriesByType('resource').filter(function(e) { return e.initiatorType === 'xmlhttprequest' && e.responseEnd === 0; }).length === 0);"));
//	        });
//			
	        // Execute JavaScript to monitor pending AJAX requests
	        ExpectedCondition<Boolean> pendingRequestsCompleted = new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	                return (Boolean) jsExecutor.executeScript(
	                    "return jQuery.active == 0");
	            }
	        };

	        // Wait until there are no pending AJAX requests
	        wait.until(pendingRequestsCompleted);
	        
	        // Continue with your test after all AJAX requests are completed
	        System.out.println("No pending AJAX requests. Proceeding with the test...");
		
	}
	 
	 public class CustomReporterRed {

		    public static void log(String message, String colour) {
		        String styledMessage = "<font color=\"" + colour + "\">" + message + "</font>";
		        Reporter.log(styledMessage);
		    }
		    
	 
     }
	 
	 
	 public static Map<String, String> getBackendDataFromApi(String apiUrl, String objectName, int objectNumber, String Payload) throws Exception {
		 JSONObject payload = new JSONObject(Payload);
		 	CloseableHttpClient httpClient = HttpClients.createDefault();
	            // Create an HttpPost request for API 1
	            HttpPost request = new HttpPost(apiUrl);
	            // Add custom header with token if needed
	            request.setHeader("token", getToken());
	            
	            request.setEntity(new StringEntity(payload.toString()));
	            // Send the request and get the response
	            CloseableHttpResponse response1 = httpClient.execute(request) ;
	                // Extract JSON response body for API 1
	                String jsonResponse1 = EntityUtils.toString(response1.getEntity());
	                
	                System.out.println(jsonResponse1);
	        // Parse API response JSON
	        JsonObject jsonObject = new Gson().fromJson(jsonResponse1, JsonObject.class);
	        JsonArray jsonArray = jsonObject.getAsJsonArray(objectName);

	        // Get the object at the specified index
	        JsonObject object = jsonArray.get(objectNumber).getAsJsonObject();

	        // Create a map to store object data
	        Map<String, String> objectData = new HashMap<>();

	        // Iterate through object properties and store key-value pairs in the map
	        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
	            String key = entry.getKey();
	            JsonElement valueElement = entry.getValue();
	            String value = valueElement.isJsonNull() ? null : valueElement.getAsString();
//	            String value = entry.getValue().getAsString(); // Assuming all values are strings
	            objectData.put(key, value);
	        }

	        return objectData;
	    }
	 
	 public static Map<String, String> getObjectData(String tableXpath, int rowNumber) {
	         
	        WebElement table = driver.findElement(By.xpath(tableXpath));

	        // Find all header cells of the table
	        List<WebElement> headerCells = table.findElements(By.xpath(".//th"));

	        // Find the specified row of the table
	        WebElement specifiedRow = table.findElement(By.xpath(".//tr[" + rowNumber + "]"));

	        // Extract data from the cells of the specified row
	        Map<String, String> dataMap = new HashMap<>();
	        List<WebElement> dataCells = specifiedRow.findElements(By.xpath(".//td"));
	        for (int i = 0; i < headerCells.size(); i++) {
	            // Get header text
	            String header = headerCells.get(i).getText();
	            // Get corresponding value from data cells
	            String value = dataCells.get(i).getText();
	            // Add to dataMap
	            dataMap.put(header, value);
	        }


	        return dataMap;
	    }
	 
	 
	 public static boolean compareMaps(Map<String, String> map1, Map<String, String> map2) {
	        // Create HashMaps to store counts of keys and values
	        Map<String, Integer> keyCounts1 = new HashMap<>();
	        Map<String, Integer> keyCounts2 = new HashMap<>();
	        Map<String, Integer> valueCounts1 = new HashMap<>();
	        Map<String, Integer> valueCounts2 = new HashMap<>();

	        // Calculate counts for keys and values in map1
	        for (Map.Entry<String, String> entry : map1.entrySet()) {
	            String key = entry.getKey();
	            String value = entry.getValue();
	            keyCounts1.put(key, keyCounts1.getOrDefault(key, 0) + 1);
	            valueCounts1.put(value, valueCounts1.getOrDefault(value, 0) + 1);
	        }

	        // Calculate counts for keys and values in map2
	        for (Map.Entry<String, String> entry : map2.entrySet()) {
	            String key = entry.getKey();
	            String value = entry.getValue();
	            keyCounts2.put(key, keyCounts2.getOrDefault(key, 0) + 1);
	            valueCounts2.put(value, valueCounts2.getOrDefault(value, 0) + 1);
	        }

	        // Check if the counts of keys and values match
	        if (keyCounts1.equals(keyCounts2) && valueCounts1.equals(valueCounts2)) {
	            return true;
	        } 
	        
	        else {
	            // If counts don't match, print keys with mismatched counts
//	            System.out.println("Keys with mismatched counts:");
	            for (String key : keyCounts1.keySet()) {
	                int count1 = keyCounts1.getOrDefault(key, 0);
	                int count2 = keyCounts2.getOrDefault(key, 0);
	                if (count1 != count2) {
//	                    System.out.println("Key: " + key + ", Count in Map 1: " + count1 + ", Count in Map 2: " + count2);
	                }
	            }

	            // If counts of values don't match, print values with mismatched counts
	            CustomReporterRed.log("Values with mismatched counts:","red");
	            for (String value : valueCounts1.keySet()) {
	                int count1 = valueCounts1.getOrDefault(value, 0);
	                int count2 = valueCounts2.getOrDefault(value, 0);
	                if (count1 != count2) {
	                	CustomReporterRed.log("Value not present in Frontend : " + value + ", Count in Map 1: " + count1 + ", Count in Map 2: " + count2,"red");
	       
	                }
	            }
	            return false;
	        }
	    }
	 	
	 
	 
}
