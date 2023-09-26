package utility_RR;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utility_RR {
	
	//protected static WebDriver driver;
	protected Actions act;
	protected static SoftAssert sa;
	protected String oldTabHandle;
	protected String newTabHandle;
	protected JavascriptExecutor js;
	protected String App_award_name;
	protected String give_award_budget_point;
	
	String username= "dheerajc"; 
	String accesskey= "P2WuDglb4q3U0HM03sMH5lgzKaEPiv72qwqIfh44ZK1iETMAz3";
    protected static RemoteWebDriver driver = null;
    String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
	

	
	public void startBrowser(String instanceName) throws InterruptedException {
		
		//System.setProperty("webdriver.chrome.driver","C:\\Dheeraj C_Old\\Dheeraj C\\Setup\\chromedriver_win32_(114)\\chromedriver.exe");
		//System.setProperty("webdriver.edge.driver","C:\\Dheeraj C_Old\\Dheeraj C\\Setup\\edgedriver_win64\\msedgedriver.exe");
		WebDriverManager.edgedriver().setup();
		
		EdgeOptions options = new EdgeOptions();
		options.addArguments("inprivate");
		driver = new EdgeDriver(options);
		//driver = new EdgeDriver();
		//WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();
		driver.get(instanceName);
		driver.manage().window().maximize();
		Thread.sleep(2000);
        
//		 DesiredCapabilities capabilities = new DesiredCapabilities();
//	      capabilities.setCapability("browserName", "chrome");
//	      capabilities.setCapability("version", "108.0");
//	      capabilities.setCapability("platform", "win11");
//	      capabilities.setCapability("resolution", "3840x1080");// If this cap isn't specified, it will just get any available one.
//	      capabilities.setCapability("build", "Jubilient_Corporate");
//	      capabilities.setCapability("name", "Jubilient_Corporate");
//	      //capabilities.setCapability("idleTimeout","20"); 
//	      try {
//	          driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
//	      } catch (MalformedURLException e) {
//	          System.out.println("Invalid grid URL");
//	      } catch (Exception e) {	
//	          System.out.println(e.getMessage());
//	      }
//	      
//	      	driver.get(instanceName);
//			driver.manage().window().maximize();
//			Thread.sleep(2000);
			
			
		
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
		importWait();
		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		importWait();
		 Robot robot = new Robot();
		  for (int i = 0; i < 4; i++) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
		
		  Thread.sleep(2000);
		
		importWait();
		if(DataAppriciateFlow(corpID, 2, 4).isEmpty()) {
			driver.findElement(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//li[4]")).click();
		}
		
	}
	
	public void appriciateEmpSearch(String empName) throws InterruptedException {
		driver.findElement(By.xpath("(//input[@class='token-input ui-autocomplete-input'])[1]")).sendKeys(empName);
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'])[1]//li[1]")).click();
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
		
		WebElement mailID = driver.findElement(By.xpath("//div[@class='ycptctn']"));
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

}
