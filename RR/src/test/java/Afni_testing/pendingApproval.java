package Afni_testing;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utility_RR.Utility_RR;
import utility_RR.Utility_RR.CustomReporterRed;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.fail;

import java.awt.AWTException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;


public class pendingApproval extends Utility_RR {
	@Parameters("corpID")
	@BeforeClass
	public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
		  
		  startBrowser(DataRunScript(2, 1));
		  newui_login(corpID,DataAppriciateFlow(corpID, 4, 1), DataAppriciateFlow(corpID, 4, 2));		  
	  }
		
	  
	  @BeforeMethod
	  public void beforeMethod() throws EncryptedDocumentException, IOException {
		  driver.get(DataRunScript(2, 1));
		  importWait();
		  
	  }
	  
	  
	  @Parameters("corpID")
	  @Test(priority=1,enabled=true)
	  public void nominationApproval(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
		  driver.get(DataRunScript(1, 1) + "ph/pages/pending_approvals");
		  
		  	nomination newnom =new nomination();
		  	String customMessage = Utility_RR.generatedString;
		    //System.out.println(customMessage);
		    List<WebElement> pending_approvals = driver.findElements(By.xpath("//tbody[@class='nomination_table_body w-full']//tr[@class='pending_approval_table_row ']"));
		    WebElement firstRow = pending_approvals.get(0);
		    WebElement messageDiv = firstRow.findElement(By.xpath(".//div[@class='custom-message-container']"));
		    String messageText = messageDiv.getText();
		    //System.out.println("Message: " + messageText);
		    WebElement secondRow = pending_approvals.get(1);
		    WebElement messageDiv2 = secondRow.findElement(By.xpath(".//div[@class='custom-message-container']"));
		    String messageText2 = messageDiv2.getText();
		    System.out.println("Message: " + messageText2);
		    
		    SoftAssert sa = new SoftAssert();
		    sa.assertNotEquals(customMessage, messageText2, "multiple entries found ");
		    sa.assertAll();
		    if(messageText.contains(customMessage)) {
		    	System.out.println("Nomination found in pending approval");
		    	CustomReporterRed.log("Nomination found in pending approval", "green");
		    	
		    }
		    else {
		    	CustomReporterRed.log("Nomination not found in pending approval", "red");
		    	fail("Nomination not found in pending approval");
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
