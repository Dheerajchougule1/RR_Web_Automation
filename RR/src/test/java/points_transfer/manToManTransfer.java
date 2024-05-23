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
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//import groovy.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import groovyjarjarantlr.collections.List;
import utility_RR.Utility_RR;

public class manToManTransfer extends Utility_RR{
	private static final Object Yes = null;
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
		driver.get(DataRunScript(1, 1)+"in/pages/manager_to_manager_transfer");
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
	public void managerTransferInput(String managerName) throws InterruptedException {
		// Find the search box
		// Locate the search box
		WebElement searchBox = driver.findElement(By.id("ManagerTokenField_tokenfield"));
		searchBox.sendKeys(managerName);
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ManagerTokenField_tokenfield_dropdown")));
		WebElement managerEntry = dropdown.findElement(By.xpath("//div[contains(text(), '" + managerName + "')]"));
		managerEntry.click();

	}
	
  @Parameters("corpID")
  @Test(priority=1,enabled=true)
  public void noPointsEnteredAlertVerification(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  String manager = DataAppriciateFlow(corpID, 89, 1);
	  System.out.print(manager);
	managerTransferInput(DataAppriciateFlow(corpID, 89, 1));
	List<WebElement> budgets = driver.findElements(By.xpath("//div[@class='page-container p-0 lg:p-12']//div[@class='slick-slide slick-active']"));
	  
	for(WebElement budget : budgets) {
		WebElement points_element = budget.findElement(By.xpath(".//span[@class='p3 font-medium'][2]"));
		//budget.click();
		//Thread.sleep(1000);
		 String points = points_element.getText();
		 String[] parts = points.split(" ");
		 double pointsInt = Double.parseDouble(parts[0]);
		 
		 
		// Thread.sleep(2000);			 
	    if(pointsInt>0) {
			 budget.click();
			 Thread.sleep(1000);
			 WebElement submitButton = driver.findElement(By.xpath("//button[@class='mt-16 p1 submitButtonGiftToColleague']"));
			 submitButton.click();
			 if(alertVerify("Amount can not be blank")) {
			  break;
		  }
			 else {
				 System.out.print("Test case failed");
			 }
		  
	    }
  
	}
	
	
			
	 
  }
  @Parameters("corpID")
  @Test(priority=2,enabled=true)
  public void zeroPointsAlertVerification(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  managerTransferInput(DataAppriciateFlow(corpID, 89, 1));
	  List<WebElement> budgets = driver.findElements(By.xpath("//div[@class='page-container p-0 lg:p-12']//div[@class='slick-slide slick-active']"));
	  
		for(WebElement budget : budgets) {
			WebElement points_element = budget.findElement(By.xpath(".//span[@class='p3 font-medium'][2]"));
			budget.click();
			Thread.sleep(1000);
			 String points = points_element.getText();
			 String[] parts = points.split(" ");
			 double pointsInt = Double.parseDouble(parts[0]);
			 
			 
			 Thread.sleep(2000);			 
		    if(pointsInt>0) {
				 budget.click();
			  WebElement input_box = driver.findElement(By.xpath("//div[@class='p1 flex gap-16 mt-16 items-center amountBox']//input[@id='greeting_amount']"));
			  input_box.sendKeys("0");
			  WebElement submitButton = driver.findElement(By.xpath("//button[@class='mt-16 p1 submitButtonGiftToColleague']"));
			  submitButton.click();
			  if(alertVerify("Amount can not be blank")) {
				  break;
			  }
			  else {
					 System.out.print("Test case failed");
				 }
			  
		    }
	  
		}
  }
  @Parameters("corpID")
  @Test(priority=3,enabled=true)
  public void amountExceedingBudgetAlertVerification(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  managerTransferInput(DataAppriciateFlow(corpID, 89, 1));

	  List<WebElement> sender_budgets = driver.findElements(By.xpath("//div[@class='page-container p-0 lg:p-12']//div[@class='slick-slide slick-active']"));
	  
		for(WebElement budget : sender_budgets) {
			WebElement points_element = budget.findElement(By.xpath(".//span[@class='p3 font-medium'][2]"));
			 String points = points_element.getText();
			 String[] parts = points.split(" ");
			 double pointsInt = Double.parseDouble(parts[0]);
			 
				 
		    if(pointsInt>=1) {
		    	 double Inputpoint = pointsInt+1;
				 String input = Integer.toString((int) Inputpoint);
				 budget.click();
				 WebElement input_box = driver.findElement(By.xpath("//div[@class='p1 flex gap-16 mt-16 items-center amountBox']//input[@id='greeting_amount']"));
				 input_box.sendKeys(input);
				 WebElement submitButton = driver.findElement(By.xpath("//button[@class='mt-16 p1 submitButtonGiftToColleague']"));
				 submitButton.click();
				 if(alertVerify("You do not have sufficient amount to transfer")) {
					 break;
				 }
				 else {
					 System.out.print("Test case failed");
				 }
		    }
	  
		}
  }
  @Parameters("corpID")
  @Test(priority=4,enabled=true)
  public void uploadCSVButtonVerification(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	    if (DataAppriciateFlow(corpID, 90, 1).contains("Yes")) {
	        List<WebElement> uploadButtons = driver.findElements(By.xpath("//div[@class='w-1/6 border upload-m-to-m p-6 text-center rounded-2xl cursor-pointer']"));
	        if (!uploadButtons.isEmpty()) {
	            System.out.println("Element is present.");
	        } else {
	            System.out.println("Element is not present.");
	        }
	    } else {
	        System.out.print("Element not required");
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
