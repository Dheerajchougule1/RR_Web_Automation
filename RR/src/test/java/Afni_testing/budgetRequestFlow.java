package Afni_testing;

import java.awt.AWTException;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility_RR.Utility_RR;

public class budgetRequestFlow extends Utility_RR {
	public int nextAmount;
	@Parameters("corpID")
	@BeforeClass
	public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
		  
		  startBrowser(DataRunScript(2, 1));
		  newui_login(corpID,DataAppriciateFlow(corpID, 12, 1), DataAppriciateFlow(corpID, 4, 2));		  
	  }
		
	  
	  @BeforeMethod
	  public void beforeMethod() throws EncryptedDocumentException, IOException {
		  
		  driver.get(DataRunScript(2, 1));
		  driver.get(DataRunScript(1, 1) + "ph/budget_request_page");
		  importWait();
		  
	  }
	  @Parameters("corpID")
	  @Test(priority=1,enabled=true)
	  public void requestAmount(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
		 
		  WebElement department_dropdown = driver.findElement(By.xpath("//select[@class='budget_department']"));
		  department_dropdown.click();
		  Thread.sleep(1000);
		  WebElement test_department = driver.findElement(By.xpath("//select[@class='budget_department']//option[@id='1-select_section']"));
		  test_department.click();
		  Thread.sleep(1000);
		  WebElement budget = driver.findElement(By.xpath("//div[@class='budget_img']"));
		  budget.click();
		  Thread.sleep(1000);
		  WebElement amount_input = driver.findElement(By.xpath("//input[@id='amount_added']"));
		  nextAmount = Utility_RR.amount_requested;
	      amount_input.sendKeys(String.valueOf(nextAmount));
		  // amount_input.sendKeys("1");
		  Thread.sleep(1000);
		  WebElement upload_button = driver.findElement(By.xpath("//div[@class='attach_document_box']//input[@id='inputType']"));
		  upload_button.sendKeys("C:\\Users\\AC\\git\\RR_Web_Automation\\RR\\excel\\Screenshots\\test_file_budget_request.pdf");
		  
		  //upload_button.sendKeys("C:\\Users\\AC\\git\\RR_Web_Automation\\RR\\excel\\Screenshots\\test_file_budget_request.docx");
		  Thread.sleep(1000);
		  WebElement submit_button = driver.findElement(By.xpath("//button[@class=\"submit_button\"]"));
		  submit_button.click();
		  Thread.sleep(1000);
		  WebElement popup = driver.findElement(By.xpath("//div[@class='body-content p-6 text-gray-600']"));
		  String success_message = popup.getText();
		  if(success_message.contains("Request Sent Successfully!")) {
			  System.out.println("Request submitted ");
		  }
		  else {
			  CustomReporterRed.log("Request not submitted please check","Red");
		  }
//		 success_message.contains("Attachment url translation missing: ph.errors.messages.extension_white_list_error")) {
//			  System.out.println("Wrong file type");
		  Thread.sleep(1000);
		  WebElement close = driver.findElement(By.xpath("//button[@class='closePopup']"));
		  close.click();
		  
	  }
	  @Parameters("corpID")
	  @Test(priority=2,enabled=true)
	  public void submitWithoutAmount(String corpID) throws InterruptedException {
		  WebElement department_dropdown = driver.findElement(By.xpath("//select[@class='budget_department']"));
		  department_dropdown.click();
		  Thread.sleep(1000);
		  WebElement test_department = driver.findElement(By.xpath("//select[@class='budget_department']//option[@id='1-select_section']"));
		  test_department.click();
		  Thread.sleep(1000);
		  WebElement budget = driver.findElement(By.xpath("//div[@class='budget_img']"));
		  budget.click();
		  Thread.sleep(1000);
		  WebElement submit_button = driver.findElement(By.xpath("//button[@class=\"submit_button\"]"));
		  submit_button.click();
		  try {
		  WebElement errorElement = driver.findElement(By.xpath("//div[@class='additional_error_message']"));
		  String errorMessage = errorElement.getText();
		  if(errorMessage.contains("Please fill the amount")) {
			  
		  }
		  }
		  catch(NoSuchElementException e) {
			  CustomReporterRed.log("Amount not entered error message not found", "red");
		  }
		  
		  
		  
	  
	}
	  @Parameters("corpID")
	  @Test(priority=3,enabled=true)
	  public void submitWithoutDocument(String corpID) throws InterruptedException {
			  WebElement department_dropdown = driver.findElement(By.xpath("//select[@class='budget_department']"));
			  Thread.sleep(2000);
			  department_dropdown.click();
			  Thread.sleep(1000);
			  WebElement test_department = driver.findElement(By.xpath("//select[@class='budget_department']//option[@id='1-select_section']"));
			  test_department.click();
			  Thread.sleep(1000);
			  WebElement budget = driver.findElement(By.xpath("//div[@class='budget_img']"));
			  budget.click();
			  WebElement amount_input = driver.findElement(By.xpath("//input[@id='amount_added']"));
		      amount_input.sendKeys("1");
			  // amount_input.sendKeys("1");
			  Thread.sleep(1000);
			  WebElement submit_button = driver.findElement(By.xpath("//button[@class=\"submit_button\"]"));
			  submit_button.click();
			  try {
			  WebElement errorElement = driver.findElement(By.xpath("//div[@class='additional_error_message']"));
			  String errorMessage = errorElement.getText();
			  if(errorMessage.contains("Please upload the document")) {
				  
			  }
			  }
			  catch(NoSuchElementException e) {
				  CustomReporterRed.log("Upload document error message not found", "red");
			  }
			  
			  
			  
		  }
	  

	  @AfterMethod
	  public void afterMethod() {
		 
	  }

	 

	  @AfterClass
	  public void afterClass() {
		  //driver.quit();
		  driver.quit();
	  }

}


