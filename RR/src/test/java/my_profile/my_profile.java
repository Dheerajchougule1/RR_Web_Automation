package my_profile;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility_RR.Utility_RR;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class my_profile extends Utility_RR {
  
	
  @Parameters("corpID")
  @BeforeClass
  
  private void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	 
	  startBrowser(DataRunScript(2, 1));
	  newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
  }
  
  @BeforeMethod
  private void before_Method_Appriciate() throws EncryptedDocumentException, IOException {
	
	  driver.get(DataRunScript(2, 1));
	  importWait();
  }
  
  @Parameters("corpID")
  @Test (priority=301,enabled = true)
  public void profile_wallet_verification(String corpID) throws EncryptedDocumentException, IOException {
	
	  if(DataAppriciateFlow(corpID, 76, 1).contains("YES")) {
		  
		  if(corpID.contains("C1151")) {
	  	 driver.get(DataRunScript(1, 1)+"in/pages/my_profile");
	  	 }
	  	 else {
	  		driver.get(DataRunScript(1, 1)+"in/pages/my_profile");
	  	 }
	   importWait();
	   
	   for (Double a=DataAppriciateFlowNum(corpID, 77, 1); a >=1.0; a--) {
		   
		 String actual_wallet_name = driver.findElement(By.xpath("(//div[@class='MyProfile_name__uIWOe'])["+a.intValue()+"]")).getText();
		 Reporter.log(actual_wallet_name, true);
		 
		 String expected_wallet_name = DataAppriciateFlow(corpID, 78, a.intValue());
		 
		 sa= new SoftAssert();
		 sa.assertEquals(actual_wallet_name,expected_wallet_name, "Wallet name mismatch");
		 sa.assertAll();		 
		   
	   }
	  }
	  else {
		  
		  Reporter.log("Wallet Functionality is not available in this corpoarte");
	  }
	   
   }
	   
   @Parameters("corpID")
   @Test (priority=302,enabled = true)
   public void profile_pic_Verification(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
 	  
 	  if(corpID.contains("C1151")) {
 	  	 driver.get(DataRunScript(1, 1)+"in/pages/my_profile");
 	  	 }
 	  	 else {
 	  		driver.get(DataRunScript(1, 1)+"in/pages/my_profile");
 	  	 }
 	   importWait();
 	   
 	   driver.findElement(By.xpath("//img[@id='profile_image_curr_user']")).click();
 	   Thread.sleep(1000);
 	   
 	   driver.findElement(By.xpath("//i[@id='ProfileImageUpload_cancel__EuwlG']")).click();
 	   
 	   driver.findElement(By.xpath("//div[@class='MyProfile_imageContainer__DKiWK relative']")).click();
 	  
// 	   driver.findElement(By.xpath("//label[@class='ProfileImageUpload_Choose__27lec cursor-pointer']")).click();
// 	   
// 	   act = new Actions(driver);
//	   act.sendKeys(Keys.chord(Keys.ESCAPE)).build().perform();
	   
	   driver.findElement(By.xpath("//i[@id='ProfileImageUpload_cancel__EuwlG']")).click();
	   
	   Reporter.log("Profile picture edited",true);
 	     
	 	  
  }
   
   @Parameters("corpID")
   @Test (priority=303,enabled = true)
   public void profile_info_edit_Verification(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
 	  
 	  if(corpID.contains("C1151")) {
 	  	 driver.get(DataRunScript(1, 1)+"in/pages/my_profile");
 	  	 }
 	  	 else {
 	  		driver.get(DataRunScript(1, 1)+"in/pages/my_profile");
 	  	 }
 	   importWait();
 	   
 	   driver.findElement(By.xpath("//img[@class='myprofile_edit_profile_pen']")).click();
 	   
 	   driver.findElement(By.xpath("//button[@class='MyProfile_discard__BOo3z']")).click();
 	   Thread.sleep(2000);
 	   
 	   driver.findElement(By.xpath("//img[@class='myprofile_edit_profile_pen']")).click();
 	   Thread.sleep(2000);
	   
	   driver.findElement(By.xpath("//button[@onclick='updateViewProfileWithEditedData()']")).click();
	   
	   Reporter.log("Information edit tested",true);	
 	      	  
  }
   
   @Parameters("corpID")
   @Test (priority=304,enabled = true)
   public void profile_appreciated_list_Verification(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
 	  
 	  if(corpID.contains("C1151")) {
 	  	 driver.get(DataRunScript(1, 1)+"in/pages/my_profile");
 	  	 }
 	  	 else {
 	  	 driver.get(DataRunScript(1, 1)+"in/pages/my_profile");
 	  	 }
 	   importWait();
 	   
 	   if(DataAppriciateFlow(corpID, 81, 1).contains("YES")) {
 	   
 	   driver.findElement(By.xpath("(//a[@href='/in/pages/my_nonmonetary_awards'])[2]")).click();
 	   Thread.sleep(2000);
 	   driver.navigate().back();
	   
	   Reporter.log("Appreciation received in my profile is tested",true);	
 	   }
 	   else {
 		
 	   Reporter.log("Appreciation received is not available",true);	   
 	   }
  }
   
   @Parameters("corpID")
   @Test (priority=305,enabled = true)
   public void profile_awarded_list_Verification(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
 	  
 	  if(corpID.contains("C1151")) {
 	  	 driver.get(DataRunScript(1, 1)+"in/pages/my_profile");
 	  	 }
 	  	 else {
 	  		driver.get(DataRunScript(1, 1)+"in/pages/my_profile");
 	  	 }
 	   importWait();
 	   	
 	   if(DataAppriciateFlow(corpID, 82, 1).contains("YES")) {
 	   driver.findElement(By.xpath("(//a[@href='/in/pages/my_awards'])[2]")).click();
 	   Thread.sleep(2000);
 	   driver.navigate().back();
	   
	   Reporter.log("Award received in my profile is tested",true);
   	   }
	   else {
	 		
	   Reporter.log("Appreciation received is not available",true);	   
	   }
 	      	  
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
