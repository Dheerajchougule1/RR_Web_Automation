package Afni_testing;

import org.testng.annotations.Test;

import utility_RR.Utility_RR;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

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


public class nomination extends Utility_RR {
	
	
	
	@Parameters("corpID")
	@BeforeClass
	public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
		  
		  startBrowser(DataRunScript(2, 1));
		  newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));		  
	  }
		
	  
	  @BeforeMethod
	  public void beforeMethod() throws EncryptedDocumentException, IOException {
		  driver.get(DataRunScript(2, 1));
		  importWait();
		  
	  }
//	  public static String generateRandomString(int length) {
//	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
//	        Random random = new Random();
//	        StringBuilder sb = new StringBuilder(length);
//
//	        for (int i = 0; i < length; i++) {
//	            int index = random.nextInt(characters.length());
//	            sb.append(characters.charAt(index));
//	        }
//
//	        return sb.toString();
//	    }
	  public void nominationInput(String nominee) throws InterruptedException {
		
		    WebElement searchBox = driver.findElement(By.xpath("(//input[@class='w-full p-5 rounded-lg inputField p3'])[1]"));
		    searchBox.sendKeys(nominee);
		    Thread.sleep(2000);
			act = new Actions(driver);
			act.sendKeys(Keys.chord(Keys.ARROW_DOWN)).build().perform();
			act.sendKeys(Keys.chord(Keys.ENTER)).build().perform();
			Thread.sleep(1000);
		}

	  public void nominateBudgetselect(Double award) throws InterruptedException {
			String AwardNum1 = award.toString();
			//driver.findElement(By.xpath("(//div[@class='slick-track'])["+AwardNum1+"]")).click();
			driver.findElement(By.xpath("//div[@id='budget']//div[@data-index="+AwardNum1+"]")).click();
			//App_budget_name = driver.findElement(By.xpath("//ul[@class='selectBudgetSliderContainer']//li["+AwardNum1+"]//div[1]")).getText();
			Thread.sleep(1000);
		}
	  public void awardSelect(Double award) throws InterruptedException {
			String AwardNum = award.toString();
			driver.findElement(By.xpath("//div[@id='awardSlider']//div[@data-index="+AwardNum+"]")).click();
			//App_award_name = driver.findElement(By.xpath("//ul[@class='slides slides-select-award']//li["+AwardNum+"]//div[1]")).getText();
			Thread.sleep(1000);
		}
	  @Parameters("corpID")
	  @Test(priority=1,enabled=true)
	  public void singleNominationFlow(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
		  driver.get(DataRunScript(1, 1)+"ph/pages/nominate_for_award");
		  nominationInput(DataNominateFlow(corpID,10,2));
		  Thread.sleep(1000);
		  nominateBudgetselect(DataNominateFlowNum(corpID,18,1));
		  awardSelect(DataNominateFlowNum(corpID,19,1));
		 
		  
		  nominateBudgetselect((double)0);
		  Thread.sleep(2000);
		  awardSelect((double)1);
		  Thread.sleep(2000);
		  WebElement points_input = driver.findElement(By.xpath("//input[@id=\"award_point\"]"));
		  Thread.sleep(2000);
		  points_input.sendKeys("1");
	        WebElement message_box = driver.findElement(By.xpath("//textarea[@class='w-full h-40 p-5 rounded-lg inputField messageTextArea form-control greyBorder p3']"));
	     
	        message_box.sendKeys(generatedString);
	        //System.out.print(generatedString);
	        screenShot("nominate_for_award");
	       if(DataNominateFlow(corpID,2,6).contains("Yes")) {
	        List<WebElement> value_statement_hidden = driver.findElements(By.xpath("//div[@id='valueStatements'][@class='w-full hidden flex items-center']"));
	        if(!value_statement_hidden.isEmpty()) {
	        	System.out.println("Value statement not present");
	        }
	        else {
	        	CustomReporterRed.log("Value statement found", "red");
	        }
	       }	
	       WebElement submit_button = driver.findElement(By.xpath("//button[@class=\"rounded-lg p-4 w-56 btn submitBtn p1 font-semibold\"]"));
	        submit_button.click();
	        try {
	            WebElement success_popup = driver.findElement(By.xpath("//div[@class='border border-gray-200 rounded-sm shadow-sm relative p3']"));
	            System.out.println("Nomination sent successfully");
	        } catch (NoSuchElementException e) {
	            System.out.println("Nomination was not sent successfully or the success popup did not appear.");
	        }
	  	  
	  	 
	        
		}

	  

	  

  @AfterMethod
  public void afterMethod() {
  }

 

  @AfterClass
  public void afterClass() {
  }

}
