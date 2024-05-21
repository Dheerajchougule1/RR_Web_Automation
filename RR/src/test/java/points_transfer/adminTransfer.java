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
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

import java.util.ArrayList;
import java.util.List;

//import groovyjarjarantlr.collections.List;
import utility_RR.Utility_RR;

public class adminTransfer extends Utility_RR{
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
		driver.get(DataRunScript(1, 1)+"in/pages/inter_manager_budget_transfer");
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
//	public void managerTransferInput(String managerName) throws InterruptedException {
//		// Find the search box
//		// Locate the search box
//		WebElement searchBox = driver.findElement(By.xpath("(//div[@id='"+searchBoxId+"'])[1]"));
//		searchBox.sendKeys(managerName);
//		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ManagerTokenField_tokenfield_dropdown")));
//		WebElement managerEntry = dropdown.findElement(By.xpath("//div[contains(text(), '" + managerName + "')]"));
//		managerEntry.click();
//
//	}
	
  @Parameters("corpID")
  @Test(priority=1,enabled=true)
  public void noPointsEnteredAlertVerification(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  String sender_manager = DataAppriciateFlow(corpID, 88, 1);
	  WebElement searchBox = driver.findElement(By.xpath("//input[@class='w-full p-5 rounded-lg inputField p3'][@id='fromManagerTokenField_tokenfield']"));
		searchBox.sendKeys(sender_manager);
		Thread.sleep(2000);
		act = new Actions(driver);
		act.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
		act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
	  Thread.sleep(2000);
	List<WebElement> budgets = driver.findElements(By.xpath("//div[@class='page-container p-0 lg:p-12']//div[@class='slick-slide slick-active']"));
	  
	for(WebElement budget : budgets) {
		WebElement points_element = budget.findElement(By.xpath(".//span[@class='p3 font-medium'][2]"));
		 String points = points_element.getText();
		 String[] parts = points.split(" ");
		 double pointsInt = Double.parseDouble(parts[0]);			 
	    if(pointsInt>0) {
			 budget.click();
			 Thread.sleep(1000);
			 String reciever_manager = DataAppriciateFlow(corpID, 89, 1);
			 WebElement search_Box = driver.findElement(By.xpath("//input[@class='w-full p-5 rounded-lg inputField p3'][@id='toManagerTokenField_tokenfield']"));
			 search_Box.sendKeys(reciever_manager);
			 Thread.sleep(2000);
			 act = new Actions(driver);
				act.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
				act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
				//select budget 
				WebElement reciever_budget = driver.findElement(By.xpath("(//div[@class=\"slick-slide slick-current slick-active\"])[2]"));
				reciever_budget.click();
			 
			 WebElement submitButton = driver.findElement(By.xpath("//button[@class=\"p1 bg-default-primary-color submitButtonGiftToColleague\"]"));
			 submitButton.click();
			 if(alertVerify("Please enter the amount")) {
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
	  String sender_manager = DataAppriciateFlow(corpID, 88, 1);
	  WebElement searchBox = driver.findElement(By.xpath("//input[@class='w-full p-5 rounded-lg inputField p3'][@id='fromManagerTokenField_tokenfield']"));
		searchBox.sendKeys(sender_manager);
		Thread.sleep(2000);
		act = new Actions(driver);
		act.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
		act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
		
//		WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ManagerTokenField_tokenfield_dropdown")));
//		WebElement managerEntry = dropdown.findElement(By.xpath("//div[contains(text(), '" + sender_manager + "')]"));
//		managerEntry.click();
	  Thread.sleep(2000);
	//managerTransferInput(DataAppriciateFlow(corpID, 88, 1));
	List<WebElement> budgets = driver.findElements(By.xpath("//div[@class='page-container p-0 lg:p-12']//div[@class='slick-slide slick-active']"));
	  
	for(WebElement budget : budgets) {
		WebElement points_element = budget.findElement(By.xpath(".//span[@class='p3 font-medium'][2]"));
		 String points = points_element.getText();
		 String[] parts = points.split(" ");
		 double pointsInt = Double.parseDouble(parts[0]);			 
	    if(pointsInt>0) {
			 budget.click();
			 Thread.sleep(1000);
			 String reciever_manager = DataAppriciateFlow(corpID, 89, 1);
			 WebElement search_Box = driver.findElement(By.xpath("//input[@class='w-full p-5 rounded-lg inputField p3'][@id='toManagerTokenField_tokenfield']"));
			 search_Box.sendKeys(reciever_manager);
			 Thread.sleep(2000);
			 act = new Actions(driver);
				act.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
				act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
				//select budget 
				WebElement reciever_budget = driver.findElement(By.xpath("(//div[@class=\"slick-slide slick-current slick-active\"])[2]"));
				reciever_budget.click();
				WebElement input_box = driver.findElement(By.xpath("//div[@class='p1 flex gap-16 items-center amountBox']//input[@id='greeting_amount']"));
				  input_box.sendKeys("0");
			 
			 WebElement submitButton = driver.findElement(By.xpath("//button[@class=\"p1 bg-default-primary-color submitButtonGiftToColleague\"]"));
			 submitButton.click();
			 if(alertVerify("Please enter the amount")) {
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
	  String sender_manager = DataAppriciateFlow(corpID, 88, 1);
	  WebElement searchBox = driver.findElement(By.xpath("//input[@class='w-full p-5 rounded-lg inputField p3'][@id='fromManagerTokenField_tokenfield']"));
		searchBox.sendKeys(sender_manager);
		Thread.sleep(2000);
		act = new Actions(driver);
		act.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
		act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
	  Thread.sleep(2000);
	List<WebElement> budgets = driver.findElements(By.xpath("//div[@class='page-container p-0 lg:p-12']//div[@class='slick-slide slick-active']"));
	  
	for(WebElement budget : budgets) {
		WebElement points_element = budget.findElement(By.xpath(".//span[@class='p3 font-medium'][2]"));
		 String points = points_element.getText();
		 String[] parts = points.split(" ");
		 double pointsInt = Double.parseDouble(parts[0]);			 
	    if(pointsInt>0) {
			 budget.click();
			 Thread.sleep(1000);
			 String reciever_manager = DataAppriciateFlow(corpID, 89, 1);
			 WebElement search_Box = driver.findElement(By.xpath("//input[@class='w-full p-5 rounded-lg inputField p3'][@id='toManagerTokenField_tokenfield']"));
			 search_Box.sendKeys(reciever_manager);
			 Thread.sleep(2000);
			 act = new Actions(driver);
				act.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
				act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
				//select budget 
				WebElement reciever_budget = driver.findElement(By.xpath("(//div[@class=\"slick-slide slick-current slick-active\"])[2]"));
				reciever_budget.click();
				double Inputpoint = pointsInt+1;
				 String input = Integer.toString((int) Inputpoint);
				 WebElement input_box = driver.findElement(By.xpath("//div[@class='p1 flex gap-16 items-center amountBox']//input[@id='greeting_amount']"));
				 input_box.sendKeys(input);
			 WebElement submitButton = driver.findElement(By.xpath("//button[@class=\"p1 bg-default-primary-color submitButtonGiftToColleague\"]"));
			 submitButton.click();
			 if(alertVerify("Amount to be transfer is less than budget total amount")) {
			  break;
		  }
			 else {
				 System.out.print("Test case failed");
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