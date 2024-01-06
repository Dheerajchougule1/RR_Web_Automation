package giveAward;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class giveAward_flow extends Utility_RR{
  
	private String give_award_Budget_points_any;
	private String app_award_name_any;
	private String give_award_budget_point_after;
	private String newsFeedId;
	private String likeCount;

	private String upperClick;
	private WebElement pointEnter;
	
  @Parameters("corpID")
  @BeforeClass
  public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	  
	  startBrowser(DataRunScript(2, 1));
	  login(corpID, DataGiveAwardFlow(corpID, 4, 1), DataGiveAwardFlow(corpID, 4, 2));
	  
  }
	
  
  @BeforeMethod
  public void beforeMethod() throws EncryptedDocumentException, IOException {
	  driver.get(DataRunScript(2, 1));
	  importWait();
	  
  }
  
  
  
  @Parameters("corpID")
  @Test (priority=51,enabled = true)
  private void GiveAwardFlowSingle(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
		  //Thread.sleep(3000);
		  
		  if(corpID.contains("C1151")) {
		  		 driver.get(DataRunScript(1, 1)+"/in/monetary_award_users/new");
		  	 }
		  else if (DataGiveAwardFlow(corpID, 9, 1).contains("YES")) {
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 10, 1)+"']")).click();
			  	 
		  }
		  
		  	 else {
		  		 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 1)+"']")).click();
			  	 importWait();
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 2)+"']")).click();
		  	 }
		   importWait();
		   Thread.sleep(2000);
		   // Select employee xG
		   
		   appriciateEmpSearch(DataGiveAwardFlow(corpID, 22, 2));
		   
		   // select budget
		   
		   if(DataGiveAwardFlow(corpID, 33, 1).contains("YES")) {
			   CardSelect(DataGivaAwardFlowNum(corpID, 34, 1));
		   }
		   
		   importWait();
		   
		   //select award
		   
		   if(DataGiveAwardFlow(corpID, 40, 1).contains("YES")) {
			   awardSelect(DataGivaAwardFlowNum(corpID, 41, 1));
		   }
		   
		   // Point to be transfer
		   
		   if(DataGiveAwardFlow(corpID, 50, 1).contains("YES")) {
			   	
			   driver.findElement(By.xpath("//input[@id='monetary_award_user_amount']")).sendKeys(DataGivaAwardFlowNum(corpID, 50, 2).toString());
			   
		   }
		   else if(DataGiveAwardFlow(corpID, 51, 1).contains("YES")) {
			   	
			   driver.findElement(By.xpath("//input[@id='monetary_award_user_amount']")).sendKeys(DataGivaAwardFlowNum(corpID, 51, 2).toString());
			   
		   }
		   
		   importWait();
		   
		   //Select value statement
		   
		   if(DataGiveAwardFlow(corpID, 57, 1).contains("YES")) {
			   ValueStatementSingle(DataGivaAwardFlowNum(corpID, 58, 1));
		   }  
		   
		   else if(DataGiveAwardFlow(corpID, 57, 2).contains("YES")) {
			   
			   driver.findElement(By.xpath("//div[@class='valueStatementDropdown']")).click();
			   
			   for(int k= DataGivaAwardFlowNum(corpID, 58, 2).intValue(); k>0 ;k--) {
				   driver.findElement(By.xpath("//input[@id='"+k+"']")).click();
			   }
			   
			   Thread.sleep(1000); 
		   }
	  
		   importWait();
		   
		   //Write Custom message
		   
		   if(DataGiveAwardFlow(corpID,70,1).contains("YES")) {
				driver.findElement(By.xpath("//textarea[@id='textareaAssign']")).sendKeys(DataGiveAwardFlow(corpID, 70, 3));
		   }
		   
			//CC
		 	if(DataGiveAwardFlow(corpID,85,1).contains("YES")) {
		 		appriciateCCEmpSearch(DataGiveAwardFlow(corpID, 86, 2));
		 	}
	  
		   importWait();
		   
		   // submit 
		   
		   driver.findElement(By.xpath("//input[@value='Submit']")).click();
		   driver.findElement(By.xpath("//span[text()='OK']")).click();
			  
		   importWait();
			  
		   mailCheck(corpID, "GiveAwardSingle", DataGiveAwardFlow(corpID, 22, 2));
	   	
  }
  
  
  @Parameters("corpID")
  @Test (priority=52,dependsOnMethods = "GiveAwardFlowSingle",enabled = true)
  private void GiveAward_single_Amount_Deduction_Admin(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
		  //Thread.sleep(3000);
		  
		  if(corpID.contains("C1151")) {
		  		 driver.get(DataRunScript(1, 1)+"/in/monetary_award_users/new");
		  	 }
		  else if (DataGiveAwardFlow(corpID, 9, 1).contains("YES")) {
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 10, 1)+"']")).click();
			  	 
		  }
		  
		  	 else {
		  		 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 1)+"']")).click();
			  	 importWait();
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 2)+"']")).click();
		  	 }
		   importWait();
		   Thread.sleep(2000);	
		   // Select employee xG
		   
		   appriciateEmpSearch(DataGiveAwardFlow(corpID, 22, 2));
		   
		   // select budget
		   
		   if(DataGiveAwardFlow(corpID, 33, 1).contains("YES")) {
			   
			   	String budget_Number = DataGivaAwardFlowNum(corpID, 34, 1).toString();
			   	System.out.println(budget_Number);
			   	WebElement budgetSelect = driver.findElement(By.xpath("//ul[@class='slides']//li["+budget_Number+"]"));
				budgetSelect.click();
				give_award_budget_point_after = budgetSelect.findElement(By.xpath("(//span[@class='notranslate'])["+budget_Number+"]")).getText();
				System.out.println(give_award_budget_point_after);
				Thread.sleep(1000);
		   }
		   else {
			   driver.findElement(By.xpath("//ul[@class='slides']//li[1]")).click();
			   give_award_Budget_points_any = driver.findElement(By.xpath("//ul[@class='slides']//li[1]//div[1]//span[@class='notranslate'][1]")).getText();
			   Thread.sleep(1000);
		   }
		   
		   Double beforeAwarding = Double.parseDouble(give_award_budget_point) ;
		   Double AfterAwarding = Double.parseDouble(give_award_budget_point_after);
		   sa = new SoftAssert();
		   Double difference = beforeAwarding - AfterAwarding;
		   
		   String differnce1 = Double.toString(difference);
		   System.out.println(differnce1);
		   
		   if(DataGiveAwardFlow(corpID, 50, 1).contains("YES")) {
		   
		   sa.assertEquals(differnce1,DataGivaAwardFlowNum(corpID, 50, 2).toString());
		   Reporter.log(""+differnce1+"Point is deducted");
		   }
		   
		   else if(DataGiveAwardFlow(corpID, 49, 1).contains("YES")) {
			   
			  sa.assertEquals(differnce1,DataGivaAwardFlowNum(corpID, 49, 2).toString());
			  Reporter.log(""+differnce1+"Point is deducted");
		   }
		   
		   else if(DataGiveAwardFlow(corpID, 51, 1).contains("YES")) {
			   
			   sa.assertEquals(differnce1,DataGivaAwardFlowNum(corpID, 51, 2).toString());
			   Reporter.log(""+differnce1+"Point is deducted");
			}
		   else {
			   sa.assertEquals(differnce1, "0.0"); 
			   Reporter.log("No amount is deducted");
		   }
		   
		   sa.assertAll();
		   importWait();
		   
  }
  
  
  
  @Parameters("corpID")
  @Test (priority=53, dependsOnMethods = "GiveAwardFlowSingle", enabled = true)
  private void SocialFeedIDGet(String corpID) throws InterruptedException, IOException {
	  		//Thread.sleep(4000);
	  		
	  		WebElement dev = driver.findElement(By.xpath("(//p[text()='"+DataGiveAwardFlow(corpID, 70, 3)+"'])[1]"));
	  		//System.out.println(dev.getText());
	  		WebElement devnew = driver.findElement(By.xpath("(//p[text()='"+DataGiveAwardFlow(corpID, 70, 3)+"'])[1]/ancestor::div[5]"));
	  		
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
	  		        ((org.apache.poi.ss.usermodel.Cell) cell2).setCellValue("Pending-Award");

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
  @Test (priority=54,dependsOnMethods = "GiveAwardFlowSingle",enabled = true)
  private void LikePositive(String corpID) throws InterruptedException, IOException {
	  		if(DataGiveAwardFlow(corpID, 94, 1).contains("YES")) {
	  		ScrollIntoView(newsFeedId);
	  		//driver.findElement(By.xpath(corpID))
	  		driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//span[text()='Like']")).click();
	  		likeCount = driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//span[text()='1 Like']")).getText();
	  		sa= new SoftAssert();
	  		
	  		sa.assertEquals(likeCount,"1 Like", "Like Count is not equal to 1 like");
	  		sa.assertAll();
	  		upperClick = "//div[@class='item active']";
	  		ScrollIntoView_by_webelement(upperClick);
	  		}
	  		else {
	  			Reporter.log("Like functionality is not available");
	  		}
  }
  
  @Parameters("corpID")
  @Test (priority=55,dependsOnMethods = "GiveAwardFlowSingle",enabled = true)
  private void CommentPositiveNegative(String corpID) throws InterruptedException, IOException {
	  		if(DataGiveAwardFlow(corpID, 99, 1).contains("YES")) {
		  	ScrollIntoView(newsFeedId);
			//driver.findElement(By.xpath(corpID))
			driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//span[text()='Comment']")).click();
			
			//Write Comment
			driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//textarea[@id='comment_text_slang']")).sendKeys(DataGiveAwardFlow(corpID, 99, 2));
			driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//input[@class='btn btn-primary commentSubmitButton']")).click();
			
			//Verify Like
			
	  		//String likeCount = driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//span[text()='1 Like']")).getText();
	  		sa= new SoftAssert();
	  		if(DataGiveAwardFlow(corpID, 94, 1).contains("YES")) {
	  		sa.assertEquals(likeCount,"1 Like", "Like Count is not equal to 1 like");
	  		}
	  		act = new Actions(driver);
			act.sendKeys(Keys.chord(Keys.PAGE_UP)).build().perform();
			act.sendKeys(Keys.chord(Keys.PAGE_UP)).build().perform();
			
			//importWait();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//img[@class='closeCommentBox']")).click();
			
			///Verify Comment
			Thread.sleep(1000);
			String CommentCount = driver.findElement(By.xpath("//div[@id='"+newsFeedId+"']//span[text()='1 Comment']")).getText();
			
			sa.assertEquals(CommentCount,"1 Comment", "Comment Count is not equal to 1 Comment");
			sa.assertAll();
			Thread.sleep(2000);
			ScrollIntoView_by_webelement(upperClick);
	  		}
	  		else {
	  			Reporter.log("Comment functionality is not available");
	  		}
				  
	  
  }
  
  
  
  @Parameters("corpID")
  @Test (priority=56, enabled = true)
  private void Appriate_emp_mail_Negative(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  if(DataGiveAwardFlow(corpID, 105, 1).contains("YES")) {	
		  sa= new SoftAssert();
		  importWait();
		  if(corpID.contains("C1151")) {
		  		 driver.get(DataRunScript(1, 1)+"/in/monetary_award_users/new");
		  	 }
		  else if (DataGiveAwardFlow(corpID, 9, 1).contains("YES")) {
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 10, 1)+"']")).click();
			  	 
		  }
		  
		  	 else {
		  		 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 1)+"']")).click();
			  	 importWait();
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 2)+"']")).click();
		  	 }
		   importWait();
	 
		
		//with wrong mail and submit
		
		driver.findElement(By.xpath("(//input[@class='token-input ui-autocomplete-input'])[1]")).sendKeys(DataGiveAwardFlow(corpID, 108, 2));
		Thread.sleep(2000);
		
		
		String empMailError1 = driver.findElement(By.xpath("//div[@id='userSearchErrorMessage']")).getText();
		sa.assertEquals(empMailError1, DataGiveAwardFlow(corpID, 108, 1),"Validation failed for wrong mail and submit");
		System.out.println("Wrong mail verified");
		driver.navigate().refresh();
		importWait();
		
		//With 2 same mail search
		appriciateEmpSearch(DataGiveAwardFlow(corpID, 22, 2));
		Thread.sleep(2000);
		
		appriciateEmpSearch(DataGiveAwardFlow(corpID, 22, 2));
		Thread.sleep(2000);
		
		String empMailError2 = driver.findElement(By.xpath("//div[@id='userSearchErrorMessage']")).getText();
		sa.assertEquals(empMailError2, DataGiveAwardFlow(corpID, 109, 1),"Validation failed for 2 same mail");
		System.out.println("2 same verified");
		sa.assertAll();  
	  }
	  else {
		Reporter.log("No need to test negative case");
	  }

}
  
  
  
  @Parameters("corpID")
  @Test (priority=57,enabled = true)
  private void Give_award_custom_msg_negative(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
		  //Thread.sleep(3000);
	  if(DataGiveAwardFlow(corpID, 105, 1).contains("YES")) {	  
		  if(corpID.contains("C1151")) {
		  		 driver.get(DataRunScript(1, 1)+"/in/monetary_award_users/new");
		  	 }
		  else if (DataGiveAwardFlow(corpID, 9, 1).contains("YES")) {
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 10, 1)+"']")).click();
			  	 
		  }
		  
		  	 else {
		  		 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 1)+"']")).click();
			  	 importWait();
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 2)+"']")).click();
		  	 }
		   importWait();
		   Thread.sleep(2000);
		   // Select employee xG
		   
		   appriciateEmpSearch(DataGiveAwardFlow(corpID, 22, 2));
		   
		   // select budget
		   
		   if(DataGiveAwardFlow(corpID, 33, 1).contains("YES")) {
			   CardSelect(DataGivaAwardFlowNum(corpID, 34, 1));
		   }
		   else {
			   driver.findElement(By.xpath("//ul[@class='slides']//li[1]")).click();
			   give_award_Budget_points_any = driver.findElement(By.xpath("//ul[@class='slides']//li[1]//div[1]//span[@class='notranslate'][1]")).getText();
			   System.out.println(give_award_Budget_points_any);
			   Thread.sleep(1000);
		   }
		   
		   importWait();
		   
		   //select award
		   
		   if(DataGiveAwardFlow(corpID, 40, 1).contains("YES")) {
			   awardSelect(DataGivaAwardFlowNum(corpID, 41, 1));
		   }
		   else {
			   driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li[1]")).click();
			   app_award_name_any = driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li[1]//div[1]")).getText();
		   }
		   
		   // Point to be transfer
		   
		   if(DataGiveAwardFlow(corpID, 50, 1).contains("YES")) {
			   	
			   driver.findElement(By.xpath("//input[@id='monetary_award_user_amount']")).sendKeys(DataGivaAwardFlowNum(corpID, 50, 2).toString());
			   
		   }
		   else if(DataGiveAwardFlow(corpID, 51, 1).contains("YES")) {
			   	
			   driver.findElement(By.xpath("//input[@id='monetary_award_user_amount']")).sendKeys(DataGivaAwardFlowNum(corpID, 51, 2).toString());
			   
		   }
		   
		   importWait();
		   
		   //Select value statement
		   
		   if(DataGiveAwardFlow(corpID, 57, 1).contains("YES")) {
			   ValueStatementSingle(DataGivaAwardFlowNum(corpID, 58, 1));
		   }  
		   
		   else if(DataGiveAwardFlow(corpID, 57, 2).contains("YES")) {
			   
			   driver.findElement(By.xpath("//div[@class='valueStatementDropdown']")).click();
			   
			   for(int k= DataGivaAwardFlowNum(corpID, 58, 2).intValue(); k>0 ;k--) {
				   driver.findElement(By.xpath("//input[@id='"+k+"']")).click();
			   }
			   
			   Thread.sleep(1000); 
		   }
	  
		   importWait();
   
		   
		
			  
				  	  sa.assertAll(); 
			  }
			  else {
				Reporter.log("No need to test negative case");
				
			  }
  }
		   
  @Parameters("corpID")
  @Test (priority=58,enabled = true)
  private void Give_award_value_Statement_positive(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
		  //Thread.sleep(3000);
	  if(DataGiveAwardFlow(corpID, 105, 1).contains("YES")) {	  
		  if(corpID.contains("C1151")) {
		  		 driver.get(DataRunScript(1, 1)+"/in/monetary_award_users/new");
		  	 }
		  else if (DataGiveAwardFlow(corpID, 9, 1).contains("YES")) {
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 10, 1)+"']")).click();
			  	 
		  }
		  
		  	 else {
		  		 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 1)+"']")).click();
			  	 importWait();
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 2)+"']")).click();
		  	 }
		   importWait();
		   Thread.sleep(2000);
		   // Select employee xG
		   
		   appriciateEmpSearch(DataGiveAwardFlow(corpID, 22, 2));
		   
		   // select budget
		   
		   if(DataGiveAwardFlow(corpID, 33, 1).contains("YES")) {
			   CardSelect(DataGivaAwardFlowNum(corpID, 34, 1));
		   }
		   else {
			   driver.findElement(By.xpath("//ul[@class='slides']//li[1]")).click();
			   give_award_Budget_points_any = driver.findElement(By.xpath("//ul[@class='slides']//li[1]//div[1]//span[@class='notranslate'][1]")).getText();
			   System.out.println(give_award_Budget_points_any);
			   Thread.sleep(1000);
		   }
		   
		   importWait();
		   
		   //select award
		   
		   if(DataGiveAwardFlow(corpID, 40, 1).contains("YES")) {
			   awardSelect(DataGivaAwardFlowNum(corpID, 41, 1));
		   }
		   else {
			   driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li[1]")).click();
			   app_award_name_any = driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li[1]//div[1]")).getText();
		   }
		   
		   // Point to be transfer
		   
		   if(DataGiveAwardFlow(corpID, 50, 1).contains("YES")) {
			   	
			   driver.findElement(By.xpath("//input[@id='monetary_award_user_amount']")).sendKeys(DataGivaAwardFlowNum(corpID, 50, 2).toString());
			   
		   }
		   else if(DataGiveAwardFlow(corpID, 51, 1).contains("YES")) {
			   	
			   driver.findElement(By.xpath("//input[@id='monetary_award_user_amount']")).sendKeys(DataGivaAwardFlowNum(corpID, 51, 2).toString());
			   
		   }
		   
		   importWait();
		   
		   //Select value statement
		   
		   if(DataGiveAwardFlow(corpID, 57, 1).contains("YES")) {
			   	driver.findElement(By.xpath("//button[@id='valueStatementButton']")).click();
			   	int q=1;
			   	
			   for(int valueNum= 1; valueNum <=DataGivaAwardFlowNum(corpID, 59, 1).intValue() ; valueNum++) {	
//			   	Double value2 = DataGivaAwardFlowNum(corpID, 60, q)+1.0;
				int valueNum1 = valueNum +1;
				
				String valuestatementSingle = driver.findElement(By.xpath("//ul[@id='value_statements']//li["+valueNum1+"]")).getText();
				System.out.println(valuestatementSingle);
				
				sa.assertEquals(valuestatementSingle, DataGiveAwardFlow(corpID,60 , q),"Assertion failed for value statement");
				Thread.sleep(1000);
				
				q++;
			   }
			 
		   }  
		   
		   else if(DataGiveAwardFlow(corpID, 57, 2).contains("YES")) {
			   
			   driver.findElement(By.xpath("//div[@class='valueStatementDropdown']")).click();
			   
			   int p = 1;
			   for(int k= DataGivaAwardFlowNum(corpID, 59, 2).intValue(); k>0 ;k--) {
				  String valueStatement = driver.findElement(By.xpath("//input[@id='"+k+"']")).getText();
				  
				  try {
					  sa.assertEquals(valueStatement, DataGiveAwardFlow(corpID, 60, p), "value statement assertio is failed");
				  }
				  catch(AssertionError ar){
					  
					  ar.printStackTrace();
					  
					  Reporter.log("statement"+ p +" is failed");
					  
				  }
			   }
			   
			   Thread.sleep(1000); 
		   }
	  
		   importWait();
     
			sa.assertAll(); 
			  }
			  else {
				Reporter.log("No need to test negative case");
				
			  }
  }

  
  
  @Parameters("corpID")
  @Test (priority=59,enabled = true)
  private void GiveAward_Point_Transfer_negative_Single(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
		  //Thread.sleep(3000);
		  
		  if(corpID.contains("C1151")) {
		  		 driver.get(DataRunScript(1, 1)+"/in/monetary_award_users/new");
		  	 }
		  else if (DataGiveAwardFlow(corpID, 9, 1).contains("YES")) {
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 10, 1)+"']")).click();
			  	 
		  }
		  
		  	 else {
		  		 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 1)+"']")).click();
			  	 importWait();
			  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 2)+"']")).click();
		  	 }
		   importWait();
		   Thread.sleep(2000);
		   // Select employee for give award	
		   
		   appriciateEmpSearch(DataGiveAwardFlow(corpID, 22, 2));
		   
		   // select budget
		   
		   if(DataGiveAwardFlow(corpID, 33, 1).contains("YES")) {
			   CardSelect(DataGivaAwardFlowNum(corpID, 34, 1));
		   }
		   else {
			   driver.findElement(By.xpath("//ul[@class='slides']//li[1]")).click();
			   give_award_Budget_points_any = driver.findElement(By.xpath("//ul[@class='slides']//li[1]//div[1]//span[@class='notranslate'][1]")).getText();
			   Thread.sleep(1000);
		   }
		   
		   //select award
		   
		   if(DataGiveAwardFlow(corpID, 40, 1).contains("YES")) {
			   awardSelect(DataGivaAwardFlowNum(corpID, 41, 1));
		   }
		   else {
			   driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li[1]")).click();
			   app_award_name_any = driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li[1]//div[1]")).getText();
		   }
		   
		   pointEnter = driver.findElement(By.xpath("//input[@id='monetary_award_user_amount']"));
		   if(DataGiveAwardFlow(corpID, 48, 1).contains("YES")) {
			   try {
			 
			  String readOnly = pointEnter.getText();
			  System.out.println(readOnly);
			  sa = new SoftAssert();
			  
			  sa.assertEquals(readOnly, DataGiveAwardFlow(corpID, 48, 2));
			  sa.assertAll();
			  
			  Reporter.log("Test case is passed", true);	
			 }
			 catch (ElementClickInterceptedException ee){
				ee.printStackTrace();
				Reporter.log("Test case is failed", true);
			 }
			   
		   }
		   
		   else if(DataGiveAwardFlow(corpID, 49, 1).contains("YES")) {
			   
			  String FixedAmount = pointEnter.getText();
			  sa = new SoftAssert();
			  sa.assertEquals(FixedAmount, DataGivaAwardFlowNum(corpID, 49, 2).toString());
			  
			  Double Negaative_fixed_amount = DataGivaAwardFlowNum(corpID, 49, 2)+1;
			  
			  System.out.println(Negaative_fixed_amount.toString());
			  	  
			  sa.assertNotEquals(FixedAmount, Negaative_fixed_amount.toString());
			  Thread.sleep(2000);
			  
			  driver.findElement(By.xpath("//input[@value='Submit']")).click();
			  
			  String error1 = driver.findElement(By.xpath("//div[@class='unableToSubmitCustomMessageContainer']")).getText();
			  
			  sa.assertEquals(error1, DataGiveAwardFlow(corpID, 124, 1));
			  
			  driver.findElement(By.xpath("//div[@class='unableToSubmitCustomMessageContainer']")).click();
			   
			   
		   }
		   
		   else if(DataGiveAwardFlow(corpID, 50, 1).contains("YES")) {
			   
			   //for no input
			   
			  driver.findElement(By.xpath("//input[@value='Submit']")).click();
				  
			  String error1 = driver.findElement(By.xpath("//div[@class='unableToSubmitCustomMessageContainer']")).getText();
				  
			  sa.assertEquals(error1, DataGiveAwardFlow(corpID, 126, 1));
				  
			  
			  // for wrong minimum input 
			  
			  pointEnter.sendKeys(DataGivaAwardFlowNum(corpID, 50, 3).toString());
			  
			  //Select value statement
			   
			   if(DataGiveAwardFlow(corpID, 57, 1).contains("YES")) {
				   ValueStatementSingle(DataGivaAwardFlowNum(corpID, 58, 1));
			   }  
			   
			   else if(DataGiveAwardFlow(corpID, 57, 2).contains("YES")) {
				   
				   driver.findElement(By.xpath("//div[@class='valueStatementDropdown']")).click();
				   
				   for(int k= DataGivaAwardFlowNum(corpID, 58, 2).intValue(); k>0 ;k--) {
					   driver.findElement(By.xpath("//input[@id='"+k+"']")).click();
				   }
				   
				   Thread.sleep(1000); 
			   }
			  
			  driver.findElement(By.xpath("//input[@value='Submit']")).click();
			  
			  String error2 = driver.findElement(By.xpath("//div[@class='unableToSubmitCustomMessageContainer']")).getText();
			  
			  sa.assertEquals(error2, DataGiveAwardFlow(corpID, 125, 1));
			  
			  // for wrong maximum input 
			  
			  pointEnter.clear();
			  pointEnter.sendKeys(DataGivaAwardFlowNum(corpID, 50, 4).toString());
			  
			  //Select value statement
			   
			  if(DataGiveAwardFlow(corpID, 57, 1).contains("YES")) {
				   ValueStatementSingle(DataGivaAwardFlowNum(corpID, 58, 1));
			   }  
			   
			   else if(DataGiveAwardFlow(corpID, 57, 2).contains("YES")) {
				   
				   driver.findElement(By.xpath("//div[@class='valueStatementDropdown']")).click();
				   
				   for(int k= DataGivaAwardFlowNum(corpID, 58, 2).intValue(); k>0 ;k--) {
					   driver.findElement(By.xpath("//input[@id='"+k+"']")).click();
				   }
				   
				   Thread.sleep(1000); 
			   }
			  
			  driver.findElement(By.xpath("//input[@value='Submit']")).click();
			  
			  String error3 = driver.findElement(By.xpath("//div[@class='unableToSubmitCustomMessageContainer']")).getText();
			  
			  sa.assertEquals(error3, DataGiveAwardFlow(corpID, 125, 1));
			  
			  
		   }
		   
		   	   
		   importWait();
		   
  }
  
  
  
  
  @Parameters("corpID")
  @Test (priority=60, dependsOnMethods = "GiveAwardFlowSingle", enabled = true)
  private void Appriated_List_Test(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
	  	
	  
	  	//Thread.sleep(5000);
	  if(corpID.contains("C1151")) {
	  		 driver.get(DataRunScript(1, 1)+"/in/monetary_award_users/new");
	  	 }
	  else if (DataGiveAwardFlow(corpID, 9, 1).contains("YES")) {
		  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 10, 1)+"']")).click();
		  	 
	  }
	  
	  	 else {
	  		 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 1)+"']")).click();
		  	 importWait();
		  	 driver.findElement(By.xpath("//div[text()='"+DataGiveAwardFlow(corpID, 11, 2)+"']")).click();
	  	 }
	   importWait();
		driver.findElement(By.xpath("//div[contains(text(),'Given')]")).click();
		
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate= formatter.format(d);
		System.out.println(strDate);
		
		String app_date = driver.findElement(By.xpath("//table[@class='col-md-12']//tbody//tr[1]//td[1]")).getText();
		
		sa= new SoftAssert();
  		//date verify
  		sa.assertEquals(app_date,strDate, "Give award date mismatch");
  		
  		//award name verify
  		String give_award_actual = driver.findElement(By.xpath("//table[@class='col-md-12']//tbody//tr[1]//td[4]")).getText();
  		
  		System.out.println(App_award_name);
  		if(DataGiveAwardFlow(corpID,40,1).contains("YES")) {
  			sa.assertEquals(give_award_actual,App_award_name,"award name mismatch in appreciated list");
  		}
		
  		importWait();
  		String app_givenBy_actual = driver.findElement(By.xpath("//table[@class='col-md-12']//tbody//tr[1]//td[3]")).getText();
  		
//  		String[] spiltedString = app_givenBy_actual.split("\\R");
//  		String spilted2 = Arrays.toString(spiltedString);
//  		String app_givenTo_actual = spiltedString[1];
//  		
//  		System.out.println(app_givenTo_actual);
	    
	    String app_givenBy_excepted = DataGiveAwardFlow(corpID, 22, 2);
		
		sa.assertEquals(app_givenBy_actual, app_givenBy_excepted, "Given to mismatch in give award list");
  		    
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
