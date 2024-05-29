package Afni_testing;

import org.testng.annotations.Test;

import utility_RR.Utility_RR;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class budgetApproverFlow extends Utility_RR{
	protected String imageTabHandle;
	@Parameters("corpID")
	@BeforeClass
	public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
		  
		  startBrowser(DataRunScript(2, 1));
		  newui_login(corpID,DataAppriciateFlow(corpID, 13, 1), DataAppriciateFlow(corpID, 4, 2));		  
	  }
		
	  
	  @BeforeMethod
	  public void beforeMethod() throws EncryptedDocumentException, IOException {
		  
		 driver.get(DataRunScript(2, 1));
		 // driver.get(DataRunScript(1, 1) + "ph/pages/budget_allocation_approval");
		  importWait();
		  
	  }
	
	  @Parameters("corpID")
	  @Test(priority=1,enabled=true)
  public void budgetApprover(String corpID) throws EncryptedDocumentException, IOException {
	 driver.get(DataRunScript(1, 1) + "ph/pages/budget_allocation_approval");
	  int amountRequested = Utility_RR.amount_requested;
	 // System.out.print(amountRequested);
	  WebElement pendingRequests = driver.findElement(By.xpath("//div[@class='selected_request_header']//input[@value='Pending Requests']"));
	  pendingRequests.click();
//	  List <WebElement> pending_requests = driver.findElements(By.xpath("//tbody[@class='data_wrapper']//tr[@class='item']"));
//	  WebElement firstEntry = pending_requests.get(3);
	  WebElement amountEntry = driver.findElement(By.xpath("//tbody[@class='data_wrapper']//tr[1]//td[4]"));
	  String amount = amountEntry.getText();
	  int amount1 = Integer.parseInt(amount);
	 // System.out.print(amount1);
	  if(amount1==amountRequested) {
		  System.out.print("Amount request created");
	  }
	  else {
		  System.out.print("Request not found");
	  }
	  
  }
	  @Parameters("corpID")
	  @Test(priority=2,enabled=true)
	  public void uploadedFileCheck(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
		  driver.get(DataRunScript(1, 1) + "ph/pages/budget_allocation_approval");

		  WebElement pendingRequests = driver.findElement(By.xpath("//div[@class='selected_request_header']//input[@value='Pending Requests']"));
		  pendingRequests.click();
		  
		  WebElement view_file_element = driver.findElement(By.xpath("(//a[text()='View File'])[1]"));
	        view_file_element.click();
	        Thread.sleep(2000);
	       // String expectedFileUrl = "https://cdn0.workadvantage.in/images/budget_point_load_request/attachment_url/738/0b8a86e0fd.pdf";
	       // String originalWindow = driver.getWindowHandle();
	        Set<String> windowHandles = driver.getWindowHandles();
	        //switch to new tab 
	        newTabHandle = windowHandles.toArray()[windowHandles.size() - 1].toString();
			driver.switchTo().window(newTabHandle);
	        //URL of the new tab
	        String actualFileUrl = driver.getCurrentUrl();
	       // System.out.print(actualFileUrl);
	        
	        // Compare the actual URL with the expected URL
	        if (actualFileUrl.contains("https://cdn0.workadvantage.in")) {
	            System.out.println("Uploaded File URL matches expected URL.");
	        } else {
	            CustomReporterRed.log("Uploaded File URL does not match expected URL.","red");
	        }
	        String originalTab = windowHandles.toArray()[windowHandles.size()- 2].toString();

	        driver.switchTo().window(originalTab);
		
			
		  
	  }
	  
  @AfterMethod
  public void afterMethod() {
	  
  }

  @AfterClass
  public void afterClass() {
	 driver.quit();
  }

}
