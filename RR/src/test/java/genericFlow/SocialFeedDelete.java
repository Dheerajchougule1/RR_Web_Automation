package genericFlow;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Reporter;

import utility_RR.Utility_RR;

public class SocialFeedDelete extends Utility_RR {
	
	
  @Parameters("corpID")
  @BeforeClass
  public void FeedDelete_before_class(String corpID) throws InterruptedException, EncryptedDocumentException, AWTException, IOException {
//	  System.setProperty("webdriver.edge.driver","C:\\Dheeraj C_Old\\Dheeraj C\\Setup\\edgedriver_win64\\msedgedriver.exe");
	  WebDriverManager.edgedriver().setup();
	  
	  driver = new EdgeDriver();
		
	  //driver = new ChromeDriver();
	  driver.get("https://secure.workadvantage.in/delete_newsfeeds");
	  driver.manage().window().maximize();
	  //Thread.sleep(2000);
	  importWait();;
	  
	  //login
	  newui_login(corpID,DataRunScript(10, 1), DataRunScript(11, 1));
	  importWait();
	  
  }
  @Test 
  public void feedDelete() throws InterruptedException, EncryptedDocumentException, AWTException, IOException {
	  
	  
	  
	  driver.get("https://secure.workadvantage.in/delete_newsfeeds");
	  
	  String URL = "https://" + DataRunScript(14, 1) + ":" + DataRunScript(15, 1) + "@" + "secure.workadvantage.in/delete_newsfeeds";
	  driver.get(URL);
	  
	  int j=0;
	  int i=1;
	
//	  try {
          FileInputStream excelFile = new FileInputStream("excel/GenericFlow/NewsFeedDelete.xlsx");
          Workbook workbook = new XSSFWorkbook(excelFile);
          Sheet Mysheet3 = (Sheet) workbook.getSheetAt(0); // Assuming you're working with the first sheet
          
         
		for (Row row : Mysheet3) {
			Cell valueCell = row.getCell(j) ; 
			Cell statusCell = row.getCell(i);  
              
              if (statusCell.getStringCellValue().equalsIgnoreCase("Pending") || statusCell.getStringCellValue().equalsIgnoreCase("Pending-award")) {
                  String value = valueCell.getStringCellValue();	
                  String statusValue = statusCell.getStringCellValue();	
                  act = new Actions(driver);
            	  
            	  driver.findElement(By.xpath("//input[@name='newsfeed_id']")).sendKeys(value);
            	  driver.findElement(By.xpath("//div[@class='search-newsfeed']")).click();
            	  
            	  Thread.sleep(1000);
            	  driver.findElement(By.xpath("//input[@class='delete-newsfeed-btn']")).click();
            	  importWait();
            	  
            	  driver.get("https://secure.workadvantage.in/delete_newsfeeds");
            	  Reporter.log(value +" : NewsFeed Deleted", true);
            	  
            	  
            	  String DoneStatus = statusValue.replace(statusValue,"Done");
            	  statusCell.setCellValue(DoneStatus);
            	  
            	  FileOutputStream outputStream = new FileOutputStream("excel/GenericFlow/NewsFeedDelete.xlsx");
	  		      workbook.write(outputStream);
	  		      outputStream.close();
                     
              }
              else if (statusCell.getStringCellValue().contains("Delete") || statusCell.getStringCellValue().contains("Done")) {
            	  continue;
              }
              
              else {
            	  Reporter.log("Nothing to delete any News Feed", true);
            	  break;
          }
              
		}   
        
          workbook.close();
//      } 
//	  	catch (IOException e) {
//          e.printStackTrace();
//      }

	  
          
  }
  
  @AfterClass
  public void FeedDelete_after_class() throws InterruptedException, EncryptedDocumentException, AWTException, IOException {
	  driver.close();
	  
  
  }
}

