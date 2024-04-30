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
@Listeners(CustomListener.class)

public class i_want extends Utility_RR {	
	
	  SoftAssert sa;
	  Alert alert;
	
  @Parameters("corpID")
  @BeforeClass
  private void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	 
	  startBrowser(DataRunScript(2, 1));
	  newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
	
  }
  
  @BeforeMethod
  private void before_Method_Appriciate() throws EncryptedDocumentException, IOException {
	
	  driver.get(DataRunScript(1, 1)+"in/pages/i_want");
	  importWait();
  }
  
  @Parameters("corpID")
  @Test (priority=301,enabled = true)
  public void verify_label_all_label_name(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
	
	  driver.get(DataRunScript(1, 1)+"in/pages/i_want");
	  Thread.sleep(1000);
	  
	  List<WebElement> listOfLabel = driver.findElements(By.xpath("//label[@class='p1']"));
	  ArrayList<String> actaulLabelName = new ArrayList<String>();
	  
	  for(WebElement label: listOfLabel) {
		  
		  String labelName = label.getText();	  
		  actaulLabelName.add(labelName);  
	  }
	  
	  ArrayList<String> expectedLabelName = new ArrayList<String>();
	  expectedLabelName.add("Brand Name*");
	  expectedLabelName.add("Category*");
	  expectedLabelName.add("Address");
	  expectedLabelName.add("Refer a contact person (Personal)");
	  
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
	   
	   driver.findElement(By.xpath("//button[@onclick='iwantSubmit()']")).click();
	   
	   sa= new SoftAssert();
	   
	   alert = driver.switchTo().alert();
	   
	   String alertMessage = alert.getText();
	   
	   sa.assertEquals(alertMessage, "Please enter brand name.", "Alert message is mismatched by submitting form without data ");
	   
	   alert.dismiss();
	   
	   driver.findElement(By.id("brand_name")).sendKeys("Testing brand Name");
	   Thread.sleep(300);
	   driver.findElement(By.xpath("//button[@onclick='iwantSubmit()']")).click();
	   
	   String alertMessage2 = alert.getText();
	   
	   sa.assertEquals(alertMessage2,"Please select category.", "Alert message is mismatched by submitting form with brand name ");
	   alert.dismiss();
	     
	   sa.assertAll();
	   	  
  }
   
   
   
   
  @AfterMethod
  public void afterMethod() { 
	   	importWait();
  }

 

//  @AfterClass
//  public void afterClass() {
//	  	 driver.close();
//  }

}

