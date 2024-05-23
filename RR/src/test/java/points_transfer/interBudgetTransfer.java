package points_transfer;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.poi.EncryptedDocumentException;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

//import groovyjarjarantlr.collections.List;
import utility_RR.Utility_RR;
import utility_RR.Utility_RR.CustomReporterRed;

public class interBudgetTransfer extends Utility_RR{
	SoftAssert sa;
	  Alert alert;
	
	@Parameters("corpID")
	@BeforeClass
	private void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
		 
		  startBrowser(DataRunScript(2, 1));
		  newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
	}

	@BeforeMethod
	public void beforeMethod() throws EncryptedDocumentException, IOException {
		driver.get(DataRunScript(1, 1)+"in/pages/manager_budget_transfer_new");
	  	importWait();
	}
	public boolean alertVerify(String message) {
	    sa = new SoftAssert();
	    boolean isMessageMatched = false;
	    
	    try {
	        alert = driver.switchTo().alert();
	        String alertMessage = alert.getText();
	        
	        sa.assertEquals(alertMessage, message, "Alert message is mismatched");
	        sa.assertAll();
	        
	        isMessageMatched = true;
	        alert.dismiss();
	    } catch (NoAlertPresentException e) {
	        // Handle case where no alert is present
	        System.out.println("No alert present");
	    }
	    
	    return isMessageMatched;
	}

  @Parameters("corpID")
  @Test(priority=1,enabled=true)
  public void zeroPointAlertVerification(String corpID) throws InterruptedException {
	List<WebElement> budgets = driver.findElements(By.xpath("//div[@class='page-container p-0 lg:p-12']//div[@class='slick-slide slick-active']"));
	for(WebElement budget : budgets) {
		//System.out.println("Testing points");
		
		WebElement points_element = budget.findElement(By.xpath(".//span[@class='p3 font-medium']"));
		
		 String points = points_element.getText();
		 
		 budget.click();
		 Thread.sleep(1500);
		 
	    if (points.contains("0.0 Points") ) {
	        
	      alertVerify("You cant transfer amount from budget with balance 0");
	 	   
	 	   break;
	    }
	   
	}
	 
  }
  @Parameters("corpID")
  @Test(priority=2,enabled=true)
  public void submitAlertVerification(String corpID) throws InterruptedException {
	  
	  List<WebElement> budgets = driver.findElements(By.xpath("//div[@class='page-container p-0 lg:p-12']//div[@class='slick-slide slick-active']"));
		for(WebElement budget : budgets) {
			WebElement points_element = budget.findElement(By.xpath(".//span[@class='p3 font-medium']"));
			
			 String points = points_element.getText();
			 String[] parts = points.split(" ");
			 double pointsInt = Double.parseDouble(parts[0]);
			 Thread.sleep(1000);
			 budget.click();
			 Thread.sleep(1500);
	
		    if(pointsInt>=1) {
			 WebElement submitButton = driver.findElement(By.xpath("//button[@class='mt-16 p1 submitButtonGiftToColleague']"));
			  submitButton.click();
			  Thread.sleep(1500);
			 
			  if(alertVerify("Please Select Receiver Budget")) {
				  break;
			  }
		    }
	  
		}
  }
  
  @Parameters("corpID")
  @Test(priority=3,enabled=true)
  public void zeroPointsAlertVerification(String corpID) throws InterruptedException {
	  
	  List<WebElement> budgets = driver.findElements(By.xpath("//div[@class='page-container p-0 lg:p-12']//div[@class='slick-slide slick-active']"));
	  
		for(WebElement budget : budgets) {
			WebElement points_element = budget.findElement(By.xpath(".//span[@class='p3 font-medium']"));
			budget.click();
			Thread.sleep(1000);
			 String points = points_element.getText();
			 String[] parts = points.split(" ");
			 double pointsInt = Double.parseDouble(parts[0]);	 
			 
			 Thread.sleep(1500);
		
		    if(pointsInt>=1) {
				 budget.click();
			  //submitButton.click();
			  Thread.sleep(1500);
			  
			  WebElement reciever_budget = driver.findElement(By.xpath("//div[@id='receiver_budget']//div[@class='slick-slide slick-current slick-active']"));
			  reciever_budget.click();
			  WebElement input_box = driver.findElement(By.xpath("//div[@class='p1 flex gap-16 mt-16 items-center amountBox']//input[@id='greeting_amount']"));
			  input_box.sendKeys("0");
			  WebElement submitButton = driver.findElement(By.xpath("//button[@class='mt-16 p1 submitButtonGiftToColleague']"));
			  submitButton.click();
			  if(alertVerify("Amount should be greater than 0")) {
				  break;
			  }
			  
			  
		    }
	  
		}
  }
  @Parameters("corpID")
  @Test(priority=4,enabled=true)
  public void amountExceedingBudgetAlertVerification(String corpID) throws InterruptedException {
	  
	  List<WebElement> sender_budgets = driver.findElements(By.xpath("//div[@class='page-container p-0 lg:p-12']//div[@class='slick-slide slick-active']"));
	  
		for(WebElement budget : sender_budgets) {
			WebElement points_element = budget.findElement(By.xpath(".//span[@class='p3 font-medium']"));
			budget.click();
			Thread.sleep(1000);
			 String points = points_element.getText();
			 String[] parts = points.split(" ");
			 double pointsInt = Double.parseDouble(parts[0]);
			 double Inputpoint = pointsInt+1;
			 String input = Integer.toString((int) Inputpoint);
			 Thread.sleep(1500);	
			
		    if(pointsInt>=1) {
				 budget.click();
			  Thread.sleep(1500);
			 
			  WebElement reciever_budget = driver.findElement(By.xpath("//div[@id='receiver_budget']//div[@class='slick-slide slick-current slick-active']"));
			  reciever_budget.click();
			  WebElement input_box = driver.findElement(By.xpath("//div[@class='p1 flex gap-16 mt-16 items-center amountBox']//input[@id='greeting_amount']"));
			  input_box.sendKeys(input);
			  WebElement submitButton = driver.findElement(By.xpath("//button[@class='mt-16 p1 submitButtonGiftToColleague']"));
			  submitButton.click();
			  WebElement popup = driver.findElement(By.xpath("//div[@class=\"body-content p-6 text-gray-600\"]"));
			  String popupMessage = popup.getText();
			  String message1 = "You do not have sufficient amout to transfer";
			  if(popupMessage.equals(message1)) {
				  System.out.print("Alert popup verified");
				  break;
			  }
			  else {
				  CustomReporterRed.log("Sender did not have sufficient balance yet points tranferred", "red");
			  }
		    }
	  
		}
  }
  
  
  

  @AfterMethod
  public void afterMethod() {
	  
  }

 

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
