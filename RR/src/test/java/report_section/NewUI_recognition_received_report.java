package report_section;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import utility_RR.Utility_RR;
import utility_RR.Utility_RR.CustomReporterRed;

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

public class NewUI_recognition_received_report extends Utility_RR {
 
   ArrayList<String> recognitionAllHeaderNameActual;
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
	  
	  driver.get(DataRunScript(1, 1)+"in/new_reports/recoginition_received_report");
		 
	   importWait();
	   waitForPageLoad();
	
  }
  
  @Parameters("corpID")
  @Test (priority = 1002, enabled = false)
  public void OpenReportAndNameVerfication(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	  
	  SelectOptionInNestedNevigation(DataReportFlow(corpID, 7, 1));
	  importWait();
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='pageHeading']")).getText(), DataReportFlow(corpID, 9, 1));
	  
	  Reporter.log("Report Name verfied Successfully", true);
	  
  }
  
  @Parameters("corpID")
  @Test (priority = 1001, enabled = false)
  public void HeaderAndRowDataVerification(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	  
	  SelectOptionInNestedNevigation(DataReportFlow(corpID, 7, 1));
	  importWait();
	  
	  //actual header
	  
	  List<WebElement> allHeaderNameActual = driver.findElements(By.xpath("//div[@class='each_header_chunk']"));
	  ArrayList<String> HeaderNameActual1= new ArrayList<>();
	  
	  for(WebElement each : allHeaderNameActual) {
		  
		  HeaderNameActual1.add(each.getText());
	  }
	  
	  System.out.println(HeaderNameActual1.size());
	  System.out.println(HeaderNameActual1);
	  
	  //1st row actual data
	  
	  List<WebElement> firstRowActual = driver.findElements(By.xpath("//tr[1]//div[@class='each_table_data']"));
	  ArrayList<String> firstRowActual1 = new ArrayList<>();
	  
	  for(WebElement each : firstRowActual) {
		  
		  firstRowActual1.add(each.getText());
	  }
	  
	  System.out.println(firstRowActual1.size());
	  System.out.println(firstRowActual1);
	  
	  //2nd row actual data
	  
	  List<WebElement> secondRowActual = driver.findElements(By.xpath("//tr[2]//div[@class='each_table_data']"));
	  ArrayList<String> secondRowActual1 = new ArrayList<>();
	  
	  for(WebElement each : secondRowActual) {
		  
		  secondRowActual1.add(each.getText());
	  }
	  
	  System.out.println(secondRowActual1.size());
	  System.out.println(secondRowActual1);
	  
	 
	  
	  ((JavascriptExecutor) driver).executeScript("window.open('"+DataRunScript(5, 7)+"', '_blank');");
	  importWait();
	  
	  // expected header test
	  Set<String> windowHandles = driver.getWindowHandles();

		// Switch to the new tab (assuming it's the last tab opened)
		String secureTab = windowHandles.toArray()[windowHandles.size() - 1].toString();
		driver.switchTo().window(secureTab);
	  
	  newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2)); 
	  SelectOptionInNestedNevigation(DataReportFlow(corpID, 7, 1));
	  importWait();
	  
	  //expected header data 
	  
	  List<WebElement> HeaderNameExpeacted = driver.findElements(By.xpath("//div[@class='each_header_chunk']"));
	  ArrayList<String> HeaderNameExpected1 = new ArrayList<>();
	  
	  
	  for(WebElement each : HeaderNameExpeacted) {
		  
		  
		  HeaderNameExpected1.add(each.getText());
	  }
	  
	  System.out.println(HeaderNameExpected1.size());
	  
	  if(HeaderNameExpected1.size()== HeaderNameActual1.size()) {
		  
		  Reporter.log("All Columns headers are present", true);
		  
	  }
	 
	  else {
		  Reporter.log("Some Column are MISMATCH", true);
	  }
	  
	  for(int i=0; i<= HeaderNameExpected1.size()-1; i++) {
		  
		  if(HeaderNameExpected1.get(i).equals(HeaderNameActual1.get(i))) {
			  	  
		  }
		  
		  else {
			  
			  Reporter.log("Missing column is : "+HeaderNameExpected1.get(i),true);
		  }
		  
	  }
	  
	  //expected 1st row data 
	  
	  List<WebElement> firstRowExpeacted = driver.findElements(By.xpath("//tr[1]//div[@class='each_table_data']"));
	  ArrayList<String> firstRowExpected1= new ArrayList<>();
	  
	  
	  for(WebElement each : firstRowExpeacted) {
		  
		  
		  firstRowExpected1.add(each.getText());
	  }
	  
	  System.out.println(firstRowExpected1.size());
	  
	  if(firstRowExpected1.size()==firstRowActual1.size()) {
		  
		  Reporter.log("All data are present in 1st row", true);
		  
	  }
	 
	  else {
		  Reporter.log("Some Column are MISMATCH", true);
	  }
	  
	  for(int i=0; i<= firstRowExpected1.size()-1; i++) {
		  
		  if(firstRowExpected1.get(i).equals(firstRowActual1.get(i))) {
			  	  
		  }
		  
		  else {
			  
			  Reporter.log("Missing rowdata is : "+firstRowExpected1.get(i) +" & Missing column header name is : "+HeaderNameExpected1.get(i),true);
		  }
		  
		 
		  
	  }
	  
	  //expected 2nd row data 
	  
	  List<WebElement> secondRowExpeacted = driver.findElements(By.xpath("//tr[2]//div[@class='each_table_data']"));
	  ArrayList<String> secondRowExpected1= new ArrayList<>();
	  
	  
	  for(WebElement each : secondRowExpeacted) {
		  
		  
		  secondRowExpected1.add(each.getText());
	  }
	  
	  System.out.println(secondRowExpected1.size());
	  
	  if(secondRowExpected1.size()==secondRowActual1.size()) {
		  
		  Reporter.log("All data are present in 2nd row", true);
		  
	  }
	 
	  else {
		  Reporter.log("Some Column are MISMATCH", true);
	  }
	  
	  for(int i=0; i<= secondRowExpected1.size()-1; i++) {
		  
		  if(secondRowExpected1.get(i).equals(secondRowActual1.get(i))) {
			  	  
		  }
		  
		  else {
			  
			  Reporter.log("Missing rowdata is : "+secondRowExpected1.get(i) +" & Missing column header name is : "+HeaderNameExpected1.get(i),true);
		  }
		  
	  }
	  
	  String oldTab = windowHandles.toArray()[windowHandles.size() - 2].toString();
	  driver.switchTo().window(oldTab);  
	  
  }
  
  @Parameters("corpID")
  @Test (priority = 1000,enabled=false)
  public void FilterVerfication(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	  
	  SelectOptionInNestedNevigation(DataReportFlow(corpID, 7, 1));
	  importWait();
	  
	  if(driver.findElement(By.xpath("//div[text()='Clear']")).isDisplayed()) {
		
		WebElement dateInput = driver.findElement(By.id("start_date_filter"));
		 dateInput.click();
		 String StartDate = "01-12-2023";
	     selectDateUsingJavaScript(driver, dateInput, StartDate);	
	     
	 
	     
	   	 WebElement dateInput1 = driver.findElement(By.id("end_date_filter"));
	     dateInput1.click();
	    // act.sendKeys(Keys.chord(Keys.ENTER)).perform();
		 String EndDate = "18-12-2023";
		 selectDateUsingJavaScript(driver, dateInput1, EndDate);
		 Thread.sleep(3000);
	     driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']")).click();
	     
	     Thread.sleep(2000);
	    
	     int starPagination = 1;
	     
	     
	     List<WebElement> AvailablePage = driver.findElements(By.xpath("//div[@onClick='changePagination(this)']"));
	     	ArrayList<Integer> paginationPages = new ArrayList<>();
	     	
	     	for(WebElement er : AvailablePage) {
	     		
	     		paginationPages.add(Integer.parseInt(er.getText()));	
	     	}
	     	
	     	int counter2 = 1;
	     	
	     	for (int i = paginationPages.size()-1 ; i>=0; i--) {
	     		
	    	 importWait();
	    	 if(starPagination>1) {
	    	 driver.findElement(By.xpath("//div[@id='page-"+starPagination+"']")).click();
	    	 
	    	 }
	    	 importWait();
	    	 List<WebElement> filteredDate = driver.findElements(By.xpath("(//tbody)[1]/tr/td[1]"));
	    	 importWait();
	     for (WebElement filteredData : filteredDate) {
	    	 importWait();
	    	 
	    	 System.out.println(filteredData.getText());
	    	 String[] spiltedDate = filteredData.getText().split("-");
	    	 
	    	 String[] startDate = StartDate.split("-");
	    	 
	    	 String[] endDate = EndDate.split("-");
	    	
	    	if(Integer.parseInt(startDate[0])<Integer.parseInt(spiltedDate[0]) || Integer.parseInt(spiltedDate[0]) > Integer.parseInt(endDate[0])){
//	    		System.out.println(Integer.parseInt(startDate[0]));
//	    		System.out.println(Integer.parseInt(endDate[0]));
	    		System.out.println(counter2 ++);
	    			
	    	}
	    	else {
	    		Reporter.log("data is over",true);
	    	}
	    	
	    	
	     }
	     Thread.sleep(5000);
	     Reporter.log(starPagination + " Page verifried suceessfully");
	     starPagination ++;
	    
	     
	     
	   }
	   	    
	    	     
	}
	else {
		Reporter.log("Filter is not present", true);
	}
	
	  
  }
  
  
  @Parameters("corpID")
  @Test (priority = 1001,enabled=false)
  public void dataVerfication(String corpID) throws Exception {
	  
	  Map<String, String> recogintionBackend = getBackendDataFromApi(DataRunScript(1, 1)+"recognition_received_report_new_2?page=1", "response", 3,"{}");
	  
	  System.out.println(recogintionBackend);
	  
	  driver.get(DataRunScript(1, 1)+"new_reports/recoginition_received_report");
	  waitForPageLoad();
	  
	  Map<String, String> recoginitionFrontend = getObjectData("//div[@class='reports_main_container']", 4);
	  
	  System.out.println(recoginitionFrontend);
  }
  
  @Parameters("corpID")
  @Test (priority = 1001,enabled=false)
  public void dataVerficationWITHPAYLOAD(String corpID) throws Exception {
	  
	  Map<String, String> recogintionBackend = getBackendDataFromApi(DataRunScript(1, 1)+"api/v1/past_given_nonmonetary_awards", "awards", 1,"{\"limit\":20,\"page\":1,\"search\":\"\"}");
	  
	  System.out.println(recogintionBackend);
	  System.out.println(recogintionBackend.size());
	  System.out.println(recogintionBackend.get("Date"));
	  
	  
	  driver.get(DataRunScript(1, 1)+"pages/appreciated_list");
	  waitForPageLoad();
	  
	  Map<String, String> recoginitionFrontend = getObjectData("//div[@class='overflow-auto']", 2);
	  
	  System.out.println(recoginitionFrontend);
	  System.out.println(recoginitionFrontend.size());
	  System.out.println(recoginitionFrontend.get("Date"));
	  
	  if(compareMaps(recogintionBackend, recoginitionFrontend)) {
		  
		  CustomReporterRed.log("data is matched", "green");
	  }
	  else {
		  CustomReporterRed.log("data is mis-matched kindly see the key above", "red");
	  }
  }
	  
	  @Parameters("corpID")
	  @Test (priority = 1008,enabled=true)
	  public void report_name_verification(String corpID) throws Exception {
		  
		  
		 String reportHeaderNameActual = driver.findElement(By.xpath("//div[@class='pageHeading']")).getText();
		 
		 sa= new SoftAssert();
		 
		 sa.assertEquals(reportHeaderNameActual, DataReportFlow(corpID, 13,1),"Header name is missing");
		   
	  
  }
	  
	  @Parameters("corpID")
	  @Test (priority = 1009,enabled=true)
	  public void filter_present_verification(String corpID) throws Exception {
		  
		 if(DataReportFlow(corpID, 14,1).contains("yes")) { 
		 boolean filterBoxPresent = driver.findElement(By.xpath("(//input[@class='each_filter_select hasDatepicker'])[1]")).isDisplayed();
		 
		 if(filterBoxPresent) {
			 
			 CustomReporterRed.log("Filter is available ", "green");
			 	 
		 }
		 else {
			 CustomReporterRed.log("Filter should be available but it is missing","red");
		 }
		}
		 
		 
		 if(DataReportFlow(corpID, 14,1).contains("no")) { 
			 
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
	  @Test (priority = 1010,enabled=true)
	  public void clear_button_presnece_verification(String corpID) throws Exception {
		  	 	
		  
		  if(DataReportFlow(corpID, 15,1).contains("yes")) { 
			  
			  boolean clearButton = driver.findElement(By.xpath("//div[@onclick='clearFilterOptions()']")).isDisplayed();
				 
				 if(clearButton) {
					 
					 CustomReporterRed.log("Clear button is available ", "green");
					 	 
				 }
				 else {
					 CustomReporterRed.log("Clear button should be available but it is missing","red");
				 }
				 
				}
				 
				 
				 if(DataReportFlow(corpID, 15,1).contains("no")) { 
					 
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
	  @Test (priority = 1011,enabled=true)
	  public void download_button_presnece_verification(String corpID) throws Exception {
		  	 	
		  
		  if(DataReportFlow(corpID, 16,1).contains("yes")) { 
			  
			  boolean downloadButton = driver.findElement(By.xpath("//div[@class='download_filter_container hidden lg:flex']")).isDisplayed();
				 
				 if(downloadButton) {
					 
					 CustomReporterRed.log("Download button is available ", "green");
					 	 
				 }
				 else {
					 CustomReporterRed.log("Download button should available but it is missing","red");
				 }
				 
				}
				 
				 
				 if(DataReportFlow(corpID, 16,1).contains("no")) { 
					 
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
	  @Test (priority = 1012,enabled=true)
	  public void pagination_presence_verification(String corpID) throws Exception {
		  	 	
		  
		  if(DataReportFlow(corpID, 17,1).contains("yes")) { 
			  
			  boolean downloadButton = driver.findElement(By.xpath("//div[@class='download_filter_container hidden lg:flex']")).isDisplayed();
				 
				 if(downloadButton) {
					 
					 CustomReporterRed.log("pagination is available ", "green");
					 	 
				 }
				 else {
					 CustomReporterRed.log("pagination should available but it is missing","red");
				 }
				 
				}
				 
				 
				 if(DataReportFlow(corpID, 17,1).contains("no")) { 
					 
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
	  @Test (priority = 1013,enabled=true)
	  public void report_rowcount_verification(String corpID) throws Exception {
		  	 	
		  
		   getBackendRowFromApi(DataRunScript(1, 1)+"/recognition_received_report_new_2?page=1", "response");
		   
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

