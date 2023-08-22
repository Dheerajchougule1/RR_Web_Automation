package utility_RR;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Utility_RR {
	
	protected static WebDriver driver;
	protected Actions act;
	
	public void startBrowser(String instanceName) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:\\Dheeraj C_Old\\Dheeraj C\\Setup\\chromedriver_win32_(114)\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.get(instanceName);
		driver.manage().window().maximize();
		Thread.sleep(2000);
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
	
	public void login(String username, String password) throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='login-section']")).click();
		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		importWait();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content']//li[4]")).click();
	}
	
	public void appriciateEmpSearch(String empName) throws InterruptedException {
		driver.findElement(By.xpath("(//input[@class='token-input ui-autocomplete-input'])[1]")).sendKeys(empName);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'])[1]//li[1]")).click();
	}
	
	public void appriciateCCEmpSearch(String empName) throws InterruptedException {
		driver.findElement(By.xpath("(//input[@class='token-input ui-autocomplete-input'])[2]")).sendKeys(empName);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content'])[2]//li[1]")).click();
	}
	
	public void importWait() {
		//driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	}
	
	
	

}
