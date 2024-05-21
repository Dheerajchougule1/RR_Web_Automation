	package my_profile;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import listener.CustomListener;
import utility_RR.Utility_RR;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
@Listeners(CustomListener.class)

public class add_gift_card extends Utility_RR {

	  SoftAssert sa;
	  Alert alert;
	
  @Parameters({"browserName", "corpID", "platformName"})
  @BeforeClass
  private void beforeClass(  String browserName,  String corpID, String platformName) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	 
//	  startBrowser(DataRunScript(2, 1));	
	  startCrossBrowser(DataRunScript(2, 1), browserName, platformName);
	  newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
	  
  }
  
  @BeforeMethod
  private void before_Method_Appriciate() throws EncryptedDocumentException, IOException {
	
	  driver.get(DataRunScript(1, 1)+"in/pages/add_gift_card");
	  importWait();
  }
  
  @Parameters("corpID")
  @Test (priority=301,enabled = true)
  public void verify_all_label_name(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
	
	 
	  waitForPageLoad();
	  
	  List<WebElement> listOfLabel = driver.findElements(By.xpath("//label[@class='p1 font-semibold']"));
	  ArrayList<String> actaulLabelName = new ArrayList<String>();
	  
	  for(WebElement label: listOfLabel) {
		  
		  String labelName = label.getText();	  
		  actaulLabelName.add(labelName);  
	  }
	  
	  ArrayList<String> expectedLabelName = new ArrayList<String>();
	  expectedLabelName.add("Add Gift Card*");
	  expectedLabelName.add("Pin Number*");
	 
	  
	  sa= new SoftAssert();
	  
	  sa.assertEquals(actaulLabelName.size(),expectedLabelName.size(), "Label count mismatch");
	  
	  for (int i = 0; i < expectedLabelName.size(); i++) {
          sa.assertEquals(expectedLabelName.get(i), actaulLabelName.get(i), "Element mismatch at index " + i);
          System.out.println(expectedLabelName.get(i));
      }

      // If you need to assert that the order is the same
      sa.assertEquals(expectedLabelName, actaulLabelName, "Order mismatch");

      // Verify all assertions
      sa.assertAll();
	   
      System.out.println(actaulLabelName.toString());
      
   }

 	     
   
   @Parameters("corpID")
   @Test (priority=304,enabled = true)
   public void error_message_verification(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
	   
	   driver.findElement(By.xpath("//button[@onclick='addGiftCard()']")).click();
	   
	   sa= new SoftAssert();
	   
	   alert = driver.switchTo().alert();
	   
	   String alertMessage = alert.getText();
	   
	   sa.assertEquals(alertMessage, "Please enter the Gift Card Code", "Alert message is mismatched by submitting form without data ");
	   
	   alert.dismiss();
	   
	   driver.findElement(By.xpath("//input[@id='gift-code']")).sendKeys("12345-678912-34578");
	   Thread.sleep(300);
	   driver.findElement(By.xpath("//button[@onclick='addGiftCard()']")).click();
	   
	   String alertMessage2 = alert.getText();
	   
	   sa.assertEquals(alertMessage2,"Please enter the Pin number", "Alert message is mismatched by submitting form with gift code ");
	   alert.dismiss();
	     
	   sa.assertAll();
	   	  
  }
   
   
  @AfterMethod
  public void afterMethod() { 
	   	importWait();
  }

 

  @AfterClass
  public void afterClass() {
	  	 driver.quit();
  }

}

