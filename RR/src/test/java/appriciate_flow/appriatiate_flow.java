package appriciate_flow;

import org.testng.annotations.Test;

import utility_RR.Utility_RR;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;

public class appriatiate_flow extends Utility_RR {
	
	
  //@DataProvider(name="corp")
//  public Object[] excelDP() throws IOException{
//  	//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
//  	String arrObj = DataRunScript(3, 3);
//  	 String[] splitedString = DataRunScript(3, 3).split(",");
//  	 List<String> corplist = new ArrayList<>(Arrays.asList(splitedString));
//  	 Object[] corpID = corplist.toArray();
//  	 System.out.println(corpID);
//  	 return corpID;
//  
//}
	  
  @Parameters("corpID")
  @BeforeClass
  public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException {
	  startBrowser(DataRunScript(2, 1));
	  login(DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
  }
	
  @Parameters("corpID")
  @Test (priority=1)
  public void AppriateFlowSingle(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//div[text()='Appreciate']")).click();
	  Thread.sleep(3000);
	   
		  appriciateEmpSearch(DataAppriciateFlow(corpID, 10, 2));
		  driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li[3]")).click(); 
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//textarea[@id='nonmonetary_award_user_custom_message']")).sendKeys(DataAppriciateFlow(corpID, 25, 2));
		  appriciateCCEmpSearch(DataAppriciateFlow(corpID, 29, 2));
		  driver.findElement(By.xpath("//input[@value='Submit']")).click();
		  driver.findElement(By.xpath("//span[text()='OK']")).click();
	  
  }
  
//  @Test (priority=2)
//  public void AppriateFlowbulk() {
//	  
//	  
//  }

   

  @AfterClass
  public void afterClass() {
	 driver.close();
  }
  }

