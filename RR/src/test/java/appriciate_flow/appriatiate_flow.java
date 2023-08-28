package appriciate_flow;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class appriatiate_flow extends Utility_RR {
	private String newsFeedId; 
	private String likeCount;
	private String app_award_name_any;

	
  @Parameters("corpID")
  @BeforeClass
  public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	 
	  startBrowser(DataRunScript(2, 1));
	  login(DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
  }
  
  @BeforeMethod
  public void afterMethod_Appriciate() throws EncryptedDocumentException, IOException {
		  importWait();
		   //driver.findElement(By.xpath("//li[@class='floatRight'][1]")).click();
		  driver.get(DataRunScript(2, 1));
		   importWait();
  }
		
  @Parameters("corpID")
  @Test (priority=1,enabled = true)
  public void AppriateFlowSingle(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
		  Thread.sleep(3000);
		  driver.findElement(By.xpath("//a[@href='/in/nonmonetary_award_users/new']")).click();
		  Thread.sleep(3000);
	   
		  appriciateEmpSearch(DataAppriciateFlow(corpID, 10, 2));
		  
		  //Award 
		  
		  if(DataAppriciateFlow(corpID,19,1).contains("YES")) {
			  awardSelect(DataAppriciateFlowNum(corpID, 20, 1));
		  }
		  else {
			  driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li[1]")).click();
			  app_award_name_any = driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li[1]//div[1]")).getText();
			  
		  }
		  Thread.sleep(2000);
		  
		  //Card
		  
		  if(DataAppriciateFlow(corpID, 17, 4).contains("YES")) {
			  
		  
		  if(DataAppriciateFlow(corpID,19,4).contains("YES")) {
			  CardSelect(DataAppriciateFlowNum(corpID, 20, 4));
		  }
		  else {
			  driver.findElement(By.xpath("//ul[@class='slides']//li[1]")).click();
			  
		  }
		  Thread.sleep(2000);
		  }
		  
		  //message
		  
		  if(DataAppriciateFlow(corpID,25,1).contains("YES")) {
			  driver.findElement(By.xpath("//textarea[@id='nonmonetary_award_user_custom_message']")).sendKeys(DataAppriciateFlow(corpID, 25, 3));
		  }
		  
		  //CC
		  if(DataAppriciateFlow(corpID,28,1).contains("YES")) {
			  appriciateCCEmpSearch(DataAppriciateFlow(corpID, 29, 2));
		  }
		  driver.findElement(By.xpath("//input[@value='Submit']")).click();
		  driver.findElement(By.xpath("//span[text()='OK']")).click();
		  Thread.sleep(2000);
		  
		  mailCheck(corpID, "appriciateSingleFlow", 10, 2);
		  
	  
  }
  
  @Parameters("corpID")
  @Test (priority=2, dependsOnMethods = "AppriateFlowSingle", enabled = true)
  public void SocialFeedIDGet(String corpID) throws InterruptedException, IOException {
	  		Thread.sleep(4000);
	  		
	  		
	  		WebElement dev = driver.findElement(By.xpath("(//p[text()='"+DataAppriciateFlow(corpID, 25, 3)+"'])[1]"));
	  		//System.out.println(dev.getText());
	  		WebElement devnew = driver.findElement(By.xpath("(//p[text()='"+DataAppriciateFlow(corpID, 25, 3)+"'])[1]/ancestor::div[5]"));
	  		
	  		newsFeedId = devnew.getAttribute("id");
	  		
	  		String[] feedID = newsFeedId.split("-");
	  		String newFeedID = Arrays.toString(feedID);
	  		String newFeedID1 = newFeedID.replaceAll("[^0-9]", "");
	  		System.out.println(newFeedID1);
	  		
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
	  		        ((org.apache.poi.ss.usermodel.Cell) cell).setCellValue(newFeedID1);
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
  @Test (priority=3,enabled = true)
  public void LikePositive(String corpID) throws InterruptedException, IOException {
	  		if(DataAppriciateFlow(corpID, 37, 1).contains("YES")) {
	  		ScrollIntoView(newsFeedId);
	  		//driver.findElement(By.xpath(corpID))
	  		driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//span[text()='Like']")).click();
	  		likeCount = driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//span[text()='1 Like']")).getText();
	  		sa= new SoftAssert();
	  		
	  		sa.assertEquals(likeCount,"1 Like", "Like Count is not equal to 1 like");
	  		sa.assertAll();	
	  		}
	  		else {
	  			Reporter.log("Like functionality is not available");
	  		}
  }
  
  @Parameters("corpID")
  @Test (priority=4,enabled = true)
  public void CommentPositiveNegative(String corpID) throws InterruptedException, IOException {
	  		if(DataAppriciateFlow(corpID, 42, 1).contains("YES")) {
		  	ScrollIntoView(newsFeedId);
			//driver.findElement(By.xpath(corpID))
			driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//span[text()='Comment']")).click();
			
			//Write Comment
			driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//textarea[@id='comment_text_slang']")).sendKeys(DataAppriciateFlow(corpID, 42, 2));
			driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//input[@class='btn btn-primary commentSubmitButton']")).click();
			
			//Verify Like
			
	  		String likeCount = driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//span[text()='1 Like']")).getText();
	  		sa= new SoftAssert();
	  		
	  		sa.assertEquals(likeCount,"1 Like", "Like Count is not equal to 1 like");
			
	  		act = new Actions(driver);
			act.sendKeys(Keys.chord(Keys.PAGE_UP)).build().perform();
			act.sendKeys(Keys.chord(Keys.PAGE_UP)).build().perform();
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//img[@class='closeCommentBox']")).click();
			
			///Verify Comment
			Thread.sleep(2000);
			String CommentCount = driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//span[text()='1 Comment']")).getText();
			
			sa.assertEquals(CommentCount,"1 Comment", "Comment Count is not equal to 1 Comment");
			sa.assertAll();
	  		}
	  		else {
	  			Reporter.log("Comment functionality is not available");
	  		}
				  
	  
  }
  @Parameters("corpID")
  @Test (priority=5, enabled = true)
  public void Appriate_emp_mail_Negative(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  	sa= new SoftAssert();
	  
	  try {
	  	Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@href='/in/nonmonetary_award_users/new']")).click();
		Thread.sleep(3000);
		
		//without mail submit 1
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		String empMailError = driver.findElement(By.xpath("//div[@class='unableToSubmitCustomMessageContainer']")).getText();
		
		
		sa.assertEquals(empMailError, DataAppriciateFlow(corpID,52, 1), "Validation failed for without mail and submit");
		System.out.println("Without mail verified");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//with wrong mail and submit
		
		driver.findElement(By.xpath("(//input[@class='token-input ui-autocomplete-input'])[1]")).sendKeys(DataAppriciateFlow(corpID, 51, 2));
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//input[@value='Submit']")).click();
		String empMailError1 = driver.findElement(By.xpath("//div[@id='userSearchErrorMessage']")).getText();
		sa.assertEquals(empMailError1, DataAppriciateFlow(corpID, 51, 1),"Validation failed for wrong mail and submit");
		System.out.println("Wrong mail verified");
		driver.navigate().refresh();
		importWait();
		
		//With 2 same mail search
		appriciateEmpSearch(DataAppriciateFlow(corpID, 10, 2));
		Thread.sleep(2000);
		appriciateEmpSearch(DataAppriciateFlow(corpID, 10, 2));
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		String empMailError2 = driver.findElement(By.xpath("//div[@id='userSearchErrorMessage']")).getText();
		sa.assertEquals(empMailError2, DataAppriciateFlow(corpID, 53, 1),"Validation failed for 2 same mail");
		System.out.println("2 same verified");
		sa.assertAll();  
		

}
  
  @Parameters("corpID")
  @Test (priority=6, enabled = true)
  public void Appriate_award_Negative(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  	 sa= new SoftAssert();
	  	
	  	 Thread.sleep(3000);
		 driver.findElement(By.xpath("//a[@href='/in/nonmonetary_award_users/new']")).click();
		 Thread.sleep(3000);
		 appriciateEmpSearch(DataAppriciateFlow(corpID, 10, 2));
		 driver.findElement(By.xpath("//input[@value='Submit']")).click();
		 
		 
		 try {
			 String empAwardError = driver.findElement(By.xpath("//div[@class='unableToSubmitCustomMessageContainer']")).getText();
			 sa.assertEquals(empAwardError, DataAppriciateFlow(corpID,60, 1), "validation failed for award not selection");
		 }
		 catch(AssertionError ea) {
		 System.out.println(ea.getMessage());
		 ea.printStackTrace();
			 
		 } 
		 sa.assertAll();
	  	
  }
  
  @Parameters("corpID")
  @Test (priority=7, enabled = true)
  public void Appriate_Custom_msg_Negative(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  	 sa= new SoftAssert();
	  	 
	  	 Thread.sleep(3000);
		 driver.findElement(By.xpath("//a[@href='/in/nonmonetary_award_users/new']")).click();
		 Thread.sleep(3000);
	   
		 appriciateEmpSearch(DataAppriciateFlow(corpID, 10, 2));
		 if(DataAppriciateFlow(corpID,19,1).contains("YES")) {
			  awardSelect(DataAppriciateFlowNum(corpID, 20, 1));
		 }
		 else {
			  driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li[1]")).click();
			  
		 }
		 Thread.sleep(2000);
		 
		//Card
		  
		  if(DataAppriciateFlow(corpID, 17, 4).contains("YES")) {
			  
		  
		  if(DataAppriciateFlow(corpID,19,4).contains("YES")) {
			  CardSelect(DataAppriciateFlowNum(corpID, 20, 4));
		  }
		  else {
			  driver.findElement(By.xpath("//ul[@class='slides']//li[1]")).click();
			  
		  }
		  Thread.sleep(2000);
		  }
		 
		 //message
		  if(DataAppriciateFlow(corpID,25,1).contains("YES") && DataAppriciateFlow(corpID, 25, 2).contains("Mandetory")) {
			 
			  driver.findElement(By.xpath("//input[@value='Submit']")).click();
			  String customMessageError = driver.findElement(By.xpath("//div[@class='unableToSubmitCustomMessageContainer']")).getText();
			  sa.assertEquals(customMessageError, DataAppriciateFlow(corpID,65, 1), "validation failed for Custom Message");
		  }
		  
		  	  sa.assertAll();  	 
  }
  
  
  
  @Parameters("corpID")
  @Test (priority=8, enabled = true)
  public void Appriated_List_Test(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  	
	  
	  	Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@href='/in/nonmonetary_award_users/new']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//div[text()='Appreciated List']")).click();
		
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
  		Thread.sleep(2000);
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
   public void afterMethod_Appriciate1() {
//	   driver.findElement(By.xpath("//a[@class='navbar-brand advantageClubImage']")).click();
	   importWait();
   }

 

  @AfterClass
  public void afterClass() {
	 driver.close();
	 
  }
 }

