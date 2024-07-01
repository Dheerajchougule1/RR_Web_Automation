package report_section;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import utility_RR.Utility_RR;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.EncryptedDocumentException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class NewUI_redemption_report extends Utility_RR {
 
  
   int BackendRowCount;
	
  @BeforeClass
  @Parameters("corpID")
  public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	  
	  startBrowser(DataRunScript(2, 1));
 	  newui_login(corpID,DataReportFlow(corpID, 3, 1), DataReportFlow(corpID, 3, 2));
	  
  }
	
  @Parameters("corpID")
  @BeforeMethod
  public void beforeMethod() throws EncryptedDocumentException, IOException {
	  
	  driver.get(DataRunScript(1, 1)+"in/new_reports/reedemption_report");
		 
	   importWait();
	   waitForPageLoad();
	
  }
    
	  @Parameters("corpID")
	  @Test (priority = 9001,enabled=true)
	  public void report_name_verification(String corpID) throws Exception {
		  
		  
		 String reportHeaderNameActual = driver.findElement(By.xpath("//div[@class='pageHeading']")).getText();
		 
		 sa= new SoftAssert();
		 
		 sa.assertEquals(reportHeaderNameActual, DataReportFlow(corpID, 128,1),"Header name is missing");
		   
	  
  }
	  
	  @Parameters("corpID")
	  @Test (priority = 9002,enabled=true)
	  public void filter_present_verification(String corpID) throws Exception {
		  
		 if(DataReportFlow(corpID, 129,1).contains("yes")) { 
		 boolean filterBoxPresent = driver.findElement(By.xpath("(//input[@class='each_filter_select hasDatepicker'])[1]")).isDisplayed();
		 
		 if(filterBoxPresent) {
			 
			 CustomReporterRed.log("Filter is available ", "green");
			 	 
		 }
		 else {
			 CustomReporterRed.log("Filter should be available but it is missing","red");
		 }
		}
		 
		 
		 if(DataReportFlow(corpID, 129,1).contains("no")) { 
			 
			 boolean filterBoxPresent = driver.findElement(By.xpath("(//input[@class='each_filter_select hasDatepicker'])[1]")).isDisplayed();
			 
			 if(filterBoxPresent) {
				 
				 CustomReporterRed.log("filter should not be available but it is showing","red");	 
			 }
			 
			 else {
				 CustomReporterRed.log("No need to test because filter functionality not available for this report ", "green");

			 }
			 
		 }
		   
	  
  }
	  
	  @Parameters("corpID")
	  @Test (priority = 9003,enabled=true)
	  public void clear_button_presnece_verification(String corpID) throws Exception {
		  	 	
		  
		  if(DataReportFlow(corpID, 130,1).contains("yes")) { 
			  
			  boolean clearButton = driver.findElement(By.xpath("//div[@onclick='clearFilterOptions()']")).isDisplayed();
				 
				 if(clearButton) {
					 
					 CustomReporterRed.log("Clear button is available ", "green");
					 	 
				 }
				 else {
					 CustomReporterRed.log("Clear button should be available but it is missing","red");
				 }
				 
				}
				 
				 
				 if(DataReportFlow(corpID, 130,1).contains("no")) { 
					 
					 boolean clearButton = driver.findElement(By.xpath("//div[@onclick='clearFilterOptions()']")).isDisplayed();
					 
					 if(clearButton) {
						 
						 CustomReporterRed.log("Clear button should not be available but it is showing","red");	 
					 }
					 
					 else {
						 CustomReporterRed.log("No need to test because clear functionality not available for this report ", "green");

					 }
					 
				 }
				 		   
	  
  }
	  
	  @Parameters("corpID")
	  @Test (priority = 9004,enabled=true)
	  public void download_button_presnece_verification(String corpID) throws Exception {
		  	 	
		  
		  if(DataReportFlow(corpID, 131,1).contains("yes")) { 
			  
			  boolean downloadButton = driver.findElement(By.xpath("//div[@class='download_filter_container hidden lg:flex']")).isDisplayed();
				 
				 if(downloadButton) {
					 
					 CustomReporterRed.log("Download button is available ", "green");
					 	 
				 }
				 else {
					 CustomReporterRed.log("Download button should available but it is missing","red");
				 }
				 
				}
				 
				 
				 if(DataReportFlow(corpID, 131,1).contains("no")) { 
					 
					 boolean downloadButton = driver.findElement(By.xpath("//div[@class='download_filter_container hidden lg:flex']")).isDisplayed();
					 
					 if(downloadButton) {
						 
						 CustomReporterRed.log("Download button should not be available but it is showing","red");	 
					 }
					 
					 else {
						 CustomReporterRed.log("No need to test because Download functionality not available for this report ", "green");

					 }
					 
				 }			 	   
	  
  }
	  
	  @Parameters("corpID")
	  @Test (priority = 9005,enabled=true)
	  public void pagination_presence_verification(String corpID) throws Exception {
		  	 	
		  
		  if(DataReportFlow(corpID, 132,1).contains("yes")) { 
			  
			  boolean downloadButton = driver.findElement(By.xpath("//div[@class='download_filter_container hidden lg:flex']")).isDisplayed();
				 
				 if(downloadButton) {
					 
					 CustomReporterRed.log("pagination is available ", "green");
					 	 
				 }
				 else {
					 CustomReporterRed.log("pagination should available but it is missing","red");
				 }
				 
				}
				 
				 
				 if(DataReportFlow(corpID, 132,1).contains("no")) { 
					 
					 boolean downloadButton = driver.findElement(By.xpath("//div[@class='pagination_container flex gap-3 items-center']")).isDisplayed();
					 
					 if(downloadButton) {
						 
						 CustomReporterRed.log("pagination should not be available but it is showing","red");	 
					 }
					 
					 else {
						 CustomReporterRed.log("No need to test because pagination functionality not available for this report ", "green");

					 }
					 
				 }			 	   
	  
  }
	  
	  @Parameters("corpID")
	  @Test (priority = 9006,enabled=true)
	  public void report_rowcount_verification(String corpID) throws Exception {
		  	 	
		  
		   getBackendRowFromApi(DataRunScript(1, 1)+"/redemption_report_new_2?page=1", "response");
		   
		   System.out.println(BackendRowCount);
		     
		   List<WebElement> FrontendRowElement = driver.findElements(By.xpath("//div[@class='reports_main_container']//tbody//tr"));
		   
		   int FrontendRowCount = FrontendRowElement.size();
		   
		   System.out.println(FrontendRowCount);
		   
		   if(FrontendRowCount == BackendRowCount) {
				 

			   CustomReporterRed.log("BackEnd rows are visible in report and count is : " +BackendRowCount , "green");
			   CustomReporterRed.log("Frontend rows are visible in report and count is : " +FrontendRowCount , "green");
				 	 
			 }
			 else {
				 
				 CustomReporterRed.log("BackEnd rows are incorrect in report and count is : " +BackendRowCount , "red");
				 CustomReporterRed.log("Frontend rows are incorrect in report and count is : " +FrontendRowCount , "red");
			 }
		  	 	   
	  
  }

	  
	  
	  public int getBackendRowFromApi(String apiUrl, String objectName) throws Exception {
			
			 	CloseableHttpClient httpClient = HttpClients.createDefault();
		            // Create an HttpPost request for API 1
		            HttpPost request = new HttpPost(apiUrl);
		            // Add custom header with token if needed
		            request.setHeader("token", getToken());
		            ;
		            // Send the request and get the response
		            CloseableHttpResponse response2 = httpClient.execute(request) ;
		                // Extract JSON response body for API 1
		                String jsonResponse2 = EntityUtils.toString(response2.getEntity());
		                
		                System.out.println(jsonResponse2);
		        // Parse API response JSON
		        JsonObject jsonObject = new Gson().fromJson(jsonResponse2, JsonObject.class);
		        JsonArray jsonArray = jsonObject.getAsJsonArray(objectName);
		        
		        BackendRowCount = jsonArray.size();
		        
		        return BackendRowCount;
		    }
	  
  
	  


  @AfterMethod
  private void afterMethod_Appriciate1() {
//	   driver.findElement(By.xpath("//a[@class='navbar-brand advantageClubImage']")).click();
	   importWait();
  }



 @AfterClass
 private void afterClass() {
	 driver.close();
	 
 }
}

