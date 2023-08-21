package utility_RR;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utility_RR {
	
	protected static WebDriver driver;
	
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
	

}
