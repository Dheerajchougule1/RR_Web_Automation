package lookup;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility_RR.Utility_RR;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class Lookup extends Utility_RR {
  
  @Parameters("corpID")
  @BeforeClass
  public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	  
	  startBrowser(DataRunScript(2, 1));
 	  newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
	  
  }
	
  @Parameters("corpID")
  @BeforeMethod
  public void beforeMethod() throws EncryptedDocumentException, IOException {
	  
	  driver.get(DataRunScript(2, 1));
	  importWait();
  }
  
  @Parameters("corpID")
  @Test
  public void lookup_Positive(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
	  
	  if(corpID.contains("C1151")) {
	  		 driver.get(DataRunScript(1, 1)+"/in/pages/lookup");
	  	 }
	  else {
		  	 driver.findElement(By.xpath(DataRunScript(1, 1)+"/in/pages/lookup")).click();

	  	 }
	   importWait();
	   
	   	driver.findElement(By.id("lookup_tokenfield")).sendKeys(DataAppriciateFlow(corpID,10 , 2));
	   	Thread.sleep(2000);
	   	act = new Actions(driver);
		act.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
		act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
		
		String profile_name =driver.findElement(By.xpath("(//div[@class='MyProfile_value__RVtcH'])[2]")).getText();
		String expected_name = DataAppriciateFlow(corpID, 10, 2);
		sa= new SoftAssert();
		sa.assertEquals(profile_name, expected_name, "Profile name mismatched");
		sa.assertAll();
  }
  
  @Parameters("corpID")
  @Test
  public void lookup_Negative(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
	  
	  if(corpID.contains("C1151")) {
	  		 driver.get(DataRunScript(1, 1)+"/in/pages/lookup");
	  	 }
	  else {
	  	 driver.findElement(By.xpath(DataRunScript(1, 1)+"/in/pages/lookup")).click();
	  	 }
	   importWait();
	   
	   	driver.findElement(By.id("lookup_tokenfield")).sendKeys("fhewbfwref");
	   	Thread.sleep(2000);
		
		String profile_name =driver.findElement(By.xpath("//div[@class='p1']")).getText();
		String expected_name = "No user found";
		sa= new SoftAssert();
		sa.assertEquals(profile_name, expected_name, "Wrong search should not happen");
		sa.assertAll();
  }

  
  @Parameters("corpID")
  @AfterMethod
  public void afterMethod() {
	  
	  	importWait();
	  	
  }
  

 
  @Parameters("corpID")
  @AfterClass
  public void afterClass() {
	  
	  	driver.close();
  }

}
