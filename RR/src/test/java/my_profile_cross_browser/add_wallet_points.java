	package my_profile_cross_browser;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;
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

public class add_wallet_points extends Utility_RR {
	
	  SoftAssert sa;
	  Alert alert;
	
	  @Parameters({"browserName", "corpID", "platformName"})
	  @BeforeClass
	  private void beforeClass(  String browserName,  String corpID, String platformName) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
		 
//		  startBrowser(DataRunScript(2, 1));	
		  startCrossBrowser(DataRunScript(2, 1), browserName, platformName);
		  newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
		  
	  }
  
  @BeforeMethod
  private void before_Method_Appriciate() throws EncryptedDocumentException, IOException, InterruptedException {
	
	  driver.get(DataRunScript(1, 1)+"in/pages/add_wallet_points");
	  Thread.sleep(2000);
	  importWait();
  }
  
  @Parameters("corpID")
  @Test (priority=301,enabled = true)
  public void verify_hardcoded_amount_available(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
	
	 
	  waitForPageLoad();
	  
	  List<WebElement> listOfamount = driver.findElements(By.xpath("//p[@class='p1 wallet-points cursor-pointer']"));
	  ArrayList<String> actaulListOfAmount = new ArrayList<String>();
	  
	  for(WebElement label: listOfamount) {
		  
		  String labelName = label.getText();	  
		  actaulListOfAmount.add(labelName);  
	  }
	  
	  ArrayList<String> expectedListOfAmount = new ArrayList<String>();
	  expectedListOfAmount.add("500");
	  expectedListOfAmount.add("1000");
	  expectedListOfAmount.add("5000");
	  expectedListOfAmount.add("10000");
	  expectedListOfAmount.add("20000");
	 
	  
	  sa= new SoftAssert();
	  
	  sa.assertEquals(actaulListOfAmount.size(),expectedListOfAmount.size(), "Label count mismatch");
	  
	  for (int i = 0; i < expectedListOfAmount.size(); i++) {
          sa.assertEquals(expectedListOfAmount.get(i), expectedListOfAmount.get(i), "Element mismatch at index " + i);
          System.out.println(expectedListOfAmount.get(i));
      }

      // If you need to assert that the order is the same
      sa.assertEquals(expectedListOfAmount, actaulListOfAmount, "Order mismatch");

      // Verify all assertions
      sa.assertAll();
	   
      System.out.println(actaulListOfAmount.toString());
      
      
   }

  @Parameters("corpID")
  @Test (priority=302,enabled = true)
  public void wallet_balance_verification(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
	   
	    WebElement actualBalance1 = driver.findElement(By.xpath("//span[@class='init_balance']"));
	    
	    String actualBalance = actualBalance1.getText().replace(" Points", "");
	    
	    System.out.println(actualBalance);
	    
	   String expectedBalance = driver.findElement(By.xpath("//div[@class='my-points-value mx-2']")).getText();
	   
	   sa= new SoftAssert();
	  
	   sa.assertEquals(actualBalance, expectedBalance, "Wallet balance is mismatch");
	  
	   sa.assertAll();
	   	  
 }     
   
   @Parameters("corpID")
   @Test (priority=303,enabled = true)
   public void error_amount_verification(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
	   
	   //amount blank
	   
	   driver.findElement(By.xpath("//button[@onclick='proceedToAddPoints()']")).click();
	   
	   sa= new SoftAssert();
	   
	   alert = driver.switchTo().alert();
	   
	   String alertMessage = alert.getText();
	   
	   sa.assertEquals(alertMessage, "Please Enter points to be added.", "Alert message is mismatched by submitting form without data ");
	   
	   alert.dismiss();
	   
	   Reporter.log("amount blank verifed");
	   
	   //amount 1
	   waitForPageLoad();
	   driver.findElement(By.xpath("//input[@name='points']")).sendKeys("1");
	   
	   driver.findElement(By.xpath("//button[@onclick='proceedToAddPoints()']")).click();
	   
	   Thread.sleep(2000);
	   alert = driver.switchTo().alert();
	   String alertMessage2 = alert.getText();
	   
	   sa.assertEquals(alertMessage2,"Points to be added should be in the range of 10 - 20000.", "Alert message is mismatched by submitting form with amount 1 ");
	   alert.dismiss();
	   Reporter.log("amount 1 verifed");
	   
	   //amount 30000
	   
	   Thread.sleep(2000);
	   driver.findElement(By.xpath("//input[@name='points']")).sendKeys("30000");
	   driver.findElement(By.xpath("//button[@onclick='proceedToAddPoints()']")).click();
	   
	   Thread.sleep(2000);
	   alert = driver.switchTo().alert();
	   String alertMessage3 = alert.getText();
	   
	   sa.assertEquals(alertMessage3,"Points to be added should be in the range of 10 - 20000.", "Alert message is mismatched by submitting form with amount 20000 ");
	   alert.dismiss();
	   Reporter.log("amount 30000 verifed");
	     
	   sa.assertAll();
	   	  
  }
   
   @Parameters("corpID")
   @Test (priority=304,enabled = true)
   public void error_rajorpay_verification(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
	   
	   //amount blank
	   
	   driver.findElement(By.xpath("(//p[@class='p1 wallet-points cursor-pointer'])[1]")).click();
	   driver.findElement(By.xpath("//button[@onclick='proceedToAddPoints()']")).click();
	   
	   Thread.sleep(3000);
	   
	   driver.navigate().back();
	   
	   
	   sa= new SoftAssert();
	   
	   Thread.sleep(2500);
	   alert = driver.switchTo().alert();
	   String alertMessage4 = alert.getText();
	   
	   sa.assertEquals(alertMessage4, "Are you sure, you want to close the form?", "Alert message is mismatched by cancelling rajor pay payment ");
	   
	   alert.accept();
	 
	   sa.assertAll();
	   	  
  }
   
   
  @AfterMethod
  public void afterMethod() { 
	   	importWait();
  }

 

  @AfterClass
  public void afterClass() {
	  	 driver.close();
  }

}

