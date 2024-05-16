package appriciate_flow;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;

import org.apache.poi.ss.usermodel.Cell; 

import utility_RR.Utility_RR;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.AlreadyBoundException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class N_appriatiate_flow extends Utility_RR {
	private String newsFeedId; 
	private String likeCount;
	private String app_award_name_any;
	private String upperClick;
	private Alert alert ;
	
  @Parameters("corpID")
  @BeforeClass
  private void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	 
		  	   startBrowser(DataRunScript(2, 1));
		  	   newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
  }
  
  @BeforeMethod
  private void before_Method_Appriciate() throws EncryptedDocumentException, IOException {
		 
		   //driver.findElement(By.xpath("//li[@class='floatRight'][1]")).click();
		   driver.get(DataRunScript(2, 1));
		   importWait();
  }
		
  @Parameters("corpID")
  @Test (priority=1,enabled = true)
  private void AppriateFlowSingle(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
		  //Thread.sleep(3000);
		  		driver.get(DataRunScript(1, 1)+"in/pages/appreciate");
		 
		   importWait();
		  //Thread.sleep(4000);																																																															
	   
		  newui_appriciateEmpSearch(DataAppriciateFlow(corpID, 10, 2));
		  Thread.sleep(1000);
		  
		  //Award 
		  
		  if(DataAppriciateFlow(corpID,19,1).contains("YES")) {
			  newui_awardSelect(DataAppriciateFlowNum(corpID, 20, 1));
		  }
		  else {
			  driver.findElement(By.xpath("//div[@class='slick-slide slick-active'][1]")).click();
			  app_award_name_any = driver.findElement(By.xpath("//div[@class='slick-slide slick-active'][1]")).getText();
			  
		  }
		  importWait();
		  
		  //Card
		  
		  if(DataAppriciateFlow(corpID, 17, 4).contains("YES")) {  
		  
		  if(DataAppriciateFlow(corpID,19,4).contains("YES")) {
			  CardSelect(DataAppriciateFlowNum(corpID, 20, 4));
		  }
		  else {
			  driver.findElement(By.xpath("//ul[@class='slides']//li[1]")).click();
			  
		  }
		  importWait();
		  
		  }
		  
		  //message
		  
		  if(DataAppriciateFlow(corpID,25,1).contains("YES")) {
			  driver.findElement(By.xpath("//textarea[@class='w-full h-40 p-5 rounded-lg inputField messageTextArea form-control greyBorder p3']")).sendKeys(DataAppriciateFlow(corpID, 25, 3));
		  }
		  
		  //CC
		  if(DataAppriciateFlow(corpID,28,1).contains("YES")) {
			  newui_appriciateCCEmpSearch(DataAppriciateFlow(corpID, 29, 2));
		  }
		  driver.findElement(By.xpath("//button[@onclick='submitAppreciationHandler()']")).click();
		  importWait();
		  
		  importWait();
		  
//		  mailCheck(corpID, "appreciateFlow", DataAppriciateFlow(corpID, 10, 2));
	  
  }
  
  @Parameters("corpID")
  @Test (priority=2, enabled = true)
  private void SocialFeedIDGet(String corpID) throws InterruptedException, IOException, ParseException {
	  		//Thread.sleep(4000);
	  		
	  		//WebElement dev = driver.findElement(By.xpath("(//p[text()='"+DataAppriciateFlow(corpID, 25, 3)+"'])[1]"));
	  		//System.out.println(dev.getText());
//	  		  List<WebElement> new2 = driver.findElements(By.xpath("(//div[@class='newsfeed_text_content']"));
//	  		  
//	  		  for(WebElement n2 : new2) {
//	  			  
//	  			 String Newtext = n2.getText();
//	  			  
//	  			  if(Newtext.contains(DataAppriciateFlow(corpID, 25, 3))) {
//	  				  
//	  				  System.out.println(Newtext);
//	  				  break;
//	  			  }
//	  			  
//	  		  }
//	  		
//	  		
//	  		
//	  		WebElement devnew = driver.findElement(By.xpath("(//p[text()='"+DataAppriciateFlow(corpID, 25, 3)+"'])[1]/ancestor::div[5]"));
	  
	  String apiUrl = DataRunScript(1, 1)+"api/v1/newsfeeds";
	  
	  int newsfeedId = 0 ;
	  
	  
		
      // Create an instance of CloseableHttpClient
      try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
          // Create an HttpGet request
    	  HttpPost request = new HttpPost(apiUrl);
          
          // Add custom header with token
          request.setHeader("token", getToken());	
          // Set the payload	
          JSONObject payload = new JSONObject();
          payload.put("per", 10);
          payload.put("page", 1);
          payload.put("filter", "global");
          payload.put("versioning", true);
          payload.put("to_locale", "");
          payload.put("is_team_appreciation", "");

          request.setEntity(new StringEntity(payload.toString(), org.apache.hc.core5.http.ContentType.APPLICATION_JSON));
          CloseableHttpResponse response = httpClient.execute(request);
          

          // Extract JSON response body
          String jsonResponse = EntityUtils.toString(response.getEntity());	
          
          System.out.println(jsonResponse);
          

          // Print the JSON response
//          System.out.println(jsonResponse);
//          Reporter.log(jsonResponse,true);
          
       // Parse JSON response
          JSONObject jsonObject = new JSONObject(jsonResponse);
          JSONArray resultsArray = jsonObject.getJSONArray("results");
//          JSONArray zonesArrays = jsonObject.getJSONArray("zones");

          // Iterate over countries
          int results = 0;
          int k;
          
          for ( k = 0; k < resultsArray.length(); k++) {
              JSONObject resultObj = resultsArray.getJSONObject(k);
              String message = resultObj.getString("custom_message");
              System.out.println(message);
             
              
              
              if(message.contains(DataAppriciateFlow(corpID, 25, 3))) {
            	  newsfeedId = resultObj.getInt("id");
            	  
            	  System.out.println(newsfeedId);
            	  break;
            	  
              }
              
              
          }
          
          response.close();
          httpClient.close();
          
      	}
      
      		newsFeedId =Integer.toString(newsfeedId);
      
      		
	  		
//	  		newsFeedId = devnew.getAttribute("data_id");
	  		
//	  		String[] feedID = newsFeedId.split("-");
//	  		String newFeedID = Arrays.toString(feedID);
//	  		String newFeedID1 = newFeedID.replaceAll("[^0-9]", "");
	  		//System.out.println(newFeedID1);
	  		
	  		int i = 0;
	  		int j = 0;

	  		while (true) {
	  		    FileInputStream fis = new FileInputStream("excel/GenericFlow/NewsFeedDelete.xlsx");
	  		    Workbook workbook = WorkbookFactory.create(fis);
	  		    Sheet Mysheet1 = workbook.getSheet("Sheet1");

	  		    Row row = Mysheet1.getRow(i);
	  		    if (row == null) {
	  		        // Create a new row if it doesn't exist
	  		        row = Mysheet1.createRow(i);
	  		    }
	  		    
	  		    Cell cell = (Cell) row.getCell(j);
	  		    Cell cell2 = (Cell) row.createCell(j+1);
	  		    if (cell == null) {
	  		        // Create a new cell if it doesn't exist
	  		        cell = (Cell) row.createCell(j);
	  		        cell2 = (Cell) row.createCell(j+1);
	  		    }

	  		    String value = ((org.apache.poi.ss.usermodel.Cell) cell).getStringCellValue();
	  		    String value2 = ((org.apache.poi.ss.usermodel.Cell) cell2).getStringCellValue();
	  		    
	  		    //System.out.println(value);

	  		    if (value == null || value.isEmpty()) {
	  		        // Set the extracted text as the cell value
	  		        ((org.apache.poi.ss.usermodel.Cell) cell).setCellValue(newsFeedId);
	  		        ((org.apache.poi.ss.usermodel.Cell) cell2).setCellValue("Pending");

	  		        // Write the modified workbook content back to the Excel file
	  		        FileOutputStream outputStream = new FileOutputStream("excel/GenericFlow/NewsFeedDelete.xlsx");
	  		        workbook.write(outputStream);
	  		        outputStream.close();
	  		        
	  		        break;
	  		    }

	  		    i++;
	  		    fis.close(); // Close the input stream at the end of each iteration
	  		}
	  			  
	  
  } 
  
  @Parameters("corpID")
  @Test (priority=3,dependsOnMethods = "AppriateFlowSingle",enabled = true)
  private void LikePositive(String corpID) throws InterruptedException, IOException {
	  		if(DataAppriciateFlow(corpID, 37, 1).contains("YES")) {
	  		ScrollIntoView1(newsFeedId);
	  		//driver.findElement(By.xpath(corpID))
	  		driver.findElement(By.xpath("//div[@data_id='"+newsFeedId+"']//div[@class='w-9 relative react_emoji']")).click();
	  		likeCount = driver.findElement(By.xpath("//div[@data_id='"+newsFeedId+"']//div[@class='react_count cursor-pointer p3']")).getText();
	  		sa= new SoftAssert();
	  		
	  		sa.assertEquals(likeCount,"1", "Like Count is not equal to 1 like");
	  		sa.assertAll();
	  		
	  		}
	  		else {
	  			Reporter.log("Like functionality is not available");
	  		}
  }
  
  @Parameters("corpID")
  @Test (priority=4,dependsOnMethods = "AppriateFlowSingle",enabled = true)
  private void CommentPositiveNegative(String corpID) throws InterruptedException, IOException {
	  		if(DataAppriciateFlow(corpID, 42, 1).contains("YES")) {
		  	ScrollIntoView1(newsFeedId);
			//driver.findElement(By.xpath(corpID))
			driver.findElement(By.xpath("//div[@data_id='"+newsFeedId+"']//div[@class='comment_container likesharecomment']")).click();
			
			//Write Comment
			driver.findElement(By.xpath("//div[@data_id='"+newsFeedId+"']//input[@class='comment_post_container p3']")).sendKeys(DataAppriciateFlow(corpID, 42, 2));
			
			act = new Actions(driver);
			act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//div[@data_id='"+newsFeedId+"']")).click();
			
			//Verify Like
			
	  		//String likeCount = driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//span[text()='1 Like']")).getText();
	  		sa= new SoftAssert();
	  		
			Thread.sleep(2000);
			String CommentCount = driver.findElement(By.xpath("//div[@data_id='"+newsFeedId+"']//span[@class='comment_count']")).getText();
			
			sa.assertEquals(CommentCount,"1", "Comment Count is not equal to 1 Comment");
			sa.assertAll();
			Thread.sleep(2000);
			
	  		}
	  		else {
	  			Reporter.log("Comment functionality is not available");
	  		}
				  
	  
  }
  
  @Parameters("corpID")
  @Test (priority=5, enabled = true)
  private void Appriate_emp_mail_Negative(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  if(DataAppriciateFlow(corpID, 48, 1).contains("YES")) {	
		  sa= new SoftAssert();
	  
		  importWait();
		  driver.get(DataRunScript(1, 1)+"in/pages/appreciate");
		  importWait();
	  	
	  
		
		//without mail submit 1
		  driver.findElement(By.xpath("//button[@onclick='submitAppreciationHandler()']")).click();
		  Thread.sleep(2000);
		  
		  alert = driver.switchTo().alert();
		  
		  String empMailError = alert.getText();
		
		
		sa.assertEquals(empMailError, DataAppriciateFlow(corpID,52, 1), "Validation failed for without mail and submit");
		System.out.println("Without mail verified");
		
		alert.accept();
		
		//with wrong mail and submit
		
		driver.findElement(By.xpath("//input[@id='recipient_tokenfield']")).sendKeys(DataAppriciateFlow(corpID, 51, 2));
		waitForPageLoad();
		
		//driver.findElement(By.xpath("//input[@value='Submit']")).click();
		String empMailError1 = driver.findElement(By.xpath("//div[@class='p1']")).getText();
		sa.assertEquals(empMailError1, DataAppriciateFlow(corpID, 51, 1),"Validation failed for wrong mail and submit");
		System.out.println("Wrong mail verified");
		
		driver.navigate().refresh();
		importWait();
		
		//With 2 same mail search
		newui_appriciateEmpSearch(DataAppriciateFlow(corpID, 10, 2));
//		Thread.sleep(2000);
		waitForPageLoad();
		
		newui_appriciateEmpSearch(DataAppriciateFlow(corpID, 10, 2));
		
		Thread.sleep(1500);
		String empMailError2 = alert.getText();
		sa.assertEquals(empMailError2, DataAppriciateFlow(corpID, 53, 1),"Validation failed for 2 same mail");
		System.out.println("2 same verified");
		sa.assertAll();
		
		alert.accept();
	  }
	  else {
		Reporter.log("No need to test negative case");
	  }

}
  
  @Parameters("corpID")
  @Test (priority=6, enabled = true)
  private void Appriate_award_Negative(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  if(DataAppriciateFlow(corpID, 48, 1).contains("YES")) {
	  	sa= new SoftAssert();
	  	
	  	 //Thread.sleep(3000);
	  	importWait();
		driver.get(DataRunScript(1, 1)+"in/pages/appreciate");
		 
		   importWait();
		  //Thread.sleep(4000);																																																															
	   
		  newui_appriciateEmpSearch(DataAppriciateFlow(corpID, 10, 2));
		  Thread.sleep(1000);
		  
		  driver.findElement(By.xpath("//button[@onclick='submitAppreciationHandler()']")).click();
		  Thread.sleep(1500);
		 
		 
		 
			 String empAwardError = alert.getText();
			 sa.assertEquals(empAwardError, DataAppriciateFlow(corpID,60, 1), "validation failed for award not selection");
			 
		 sa.assertAll();
		 	alert.accept();
		  }
		  else {
			Reporter.log("No need to test negative case");
		  }
	  	
  }
  
  @Parameters("corpID")
  @Test (priority=7, enabled = true)
  private void Appriate_Custom_msg_Negative(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  if(DataAppriciateFlow(corpID, 48, 1).contains("YES")) {	 
		  sa= new SoftAssert();
	  	   	
			driver.get(DataRunScript(1, 1)+"in/pages/appreciate");
			 
			   importWait();
			  //Thread.sleep(4000);																																																															
		   
			  newui_appriciateEmpSearch(DataAppriciateFlow(corpID, 10, 2));
			  Thread.sleep(1000);
			  
			  //Award 
			  
			  if(DataAppriciateFlow(corpID,19,1).contains("YES")) {
				  newui_awardSelect(DataAppriciateFlowNum(corpID, 20, 1));
			  }
			  else {
				  driver.findElement(By.xpath("//div[@class='slick-slide slick-active'][1]")).click();
				  app_award_name_any = driver.findElement(By.xpath("//div[@class='slick-slide slick-active'][1]")).getText();
				  
			  }
			  importWait();
		  }
		 
		 //message
		  if(DataAppriciateFlow(corpID,25,1).contains("YES") && DataAppriciateFlow(corpID, 25, 2).contains("YES")) {
			 
			  driver.findElement(By.xpath("//button[@onclick='submitAppreciationHandler()']")).click();
//			  Thread.sleep(1500);
			  waitForPageLoad();
			 
			  if(DataAppriciateFlow(corpID, 25,4).contains("YES")) {
				  String customMessageError1 = alert.getText();
				  sa.assertEquals(customMessageError1, DataAppriciateFlow(corpID,65, 1), "validation failed for Custom Message");
				  alert.accept();
			  }
		  
			  	  sa.assertAll(); 
		  }
		  else {
			Reporter.log("No need to test negative case");
			
		  }
  }
  
  
  @Parameters("corpID")
  @Test (priority=8, dependsOnMethods = "AppriateFlowSingle", enabled = false)
  private void Appriated_List_Test(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  	
	  
	  	//Thread.sleep(5000);
	  	if(corpID.contains("C1151")) {
	  		 driver.get(DataRunScript(1, 1)+"/in/nonmonetary_award_users/new");
	  	 }
	  	 else {
	  	 driver.findElement(By.xpath("//a[@href='/in/nonmonetary_award_users/new']")).click();
	  	 }
		//Thread.sleep(3000);
		importWait();
		driver.findElement(By.xpath("//div[contains(text(),'List')]")).click();
		
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate= formatter.format(d);
		System.out.println(strDate);
		
		String app_date = driver.findElement(By.xpath("//table[@class='col-md-12']//tbody//tr[1]//td[1]")).getText();
		
		sa= new SoftAssert();
  		//date verify
  		sa.assertEquals(app_date,strDate, "Appreciated date mismatch");
  		
  		//award name verify
  		String app_award_actual = driver.findElement(By.xpath("//table[@class='col-md-12']//tbody//tr[1]//td[2]")).getText();
  		
  		System.out.println(app_award_name_any);
  		System.out.println(App_award_name);
  		if(DataAppriciateFlow(corpID,19,1).contains("YES")) {
  			sa.assertEquals(app_award_actual,App_award_name,"award name mismatch in appreciated list");
  		}
  	
  		else {
  			sa.assertEquals(app_award_actual, app_award_name_any,"award name mismatch in appreciated list");
  		}
  		
		
  		//appreciated given by verify
  		//Thread.sleep(2000);
  		importWait();
  		String app_givenBy_actual = driver.findElement(By.xpath("//table[@class='col-md-12']//tbody//tr[1]//td[3]")).getText();
  		
  		String[] spiltedString = app_givenBy_actual.split("\\R");
  		String spilted2 = Arrays.toString(spiltedString);
  		String app_givenTo_actual = spiltedString[1];
  		
  		System.out.println(app_givenTo_actual);
	    
	    String app_givenBy_excepted = DataAppriciateFlow(corpID, 10, 2);
		
		sa.assertEquals(app_givenTo_actual, app_givenBy_excepted, "Given by mismatch in appreciated list");
  		    
  		sa.assertAll();	
	  
	  
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

