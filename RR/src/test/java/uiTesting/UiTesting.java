	package uiTesting;
	
	import org.testng.annotations.Test;



import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import utility_RR.Utility_RR;


import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	
	import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
	import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebDriverBuilder;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.hc.client5.http.classic.methods.HttpGet;
	import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
	import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
	import org.apache.hc.client5.http.impl.classic.HttpClients;
	import org.apache.hc.core5.http.ParseException;
	import org.apache.hc.core5.http.io.entity.EntityUtils;
	import org.apache.poi.EncryptedDocumentException;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.im4java.core.CompareCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.ArrayListOutputConsumer;
import org.openqa.selenium.By;
	import org.openqa.selenium.Cookie;
	import org.testng.Reporter;
	import org.testng.annotations.AfterClass;
	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.openqa.selenium.support.ui.WebDriverWait;
	
	

	
	public class UiTesting extends Utility_RR{
		
		
		 @Parameters("corpID")
		 @Test (priority = 2,enabled=true)
		  public void Ui_Testing_actual(String corpID) throws EncryptedDocumentException, IOException, InterruptedException, AWTException  {
			 
			 startBrowser(DataRunScript(1, 7));
			 newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
			 
			String gotUrl = driver.getCurrentUrl();
			System.out.println(gotUrl);
				
			String baseUrl1 = gotUrl.replace("pages/rewards_home", "");
			String baseUrl = DataRunScript(1, 7);
//			System.out.println(baseUrl);
			 
			
			
			  FileInputStream fis;
			  Workbook workbook;
			  Sheet Mysheet1;
			  Row row;
			  Cell cell;
			  Cell cell2;
			  String url;
			  	int j = 1;
		  		int k = 0;
			 
			 while (true) {
	  		     fis = new FileInputStream("excel/UiTesting/urls.xlsx");
	  		     workbook = WorkbookFactory.create(fis);
	  		     Mysheet1 = workbook.getSheet("urls");

	  		    row = Mysheet1.getRow(j);
	  		    if (row == null) {
	  		        // Create a new row if it doesn't exist
	  		        row = Mysheet1.createRow(j);
	  		    }
	  		    
	  		     cell = (Cell) row.getCell(k);
	  		     cell2 = (Cell) row.createCell(k+1);
	  		   
	  		    
	  		    if (cell == null) {
	  		    	
	  		        // Create a new cell if it doesn't exist
	  		        cell = (Cell) row.createCell(k);
	  		        cell2 = (Cell) row.createCell(k+1);
	  		      
	  		        
	  		    }
	  		   
	  		    url = ((org.apache.poi.ss.usermodel.Cell) cell).getStringCellValue();
	  		    
	  		    if(cell.toString().isEmpty()) {
		  		    break;
		  		}
		  	 
	  		    driver.get(baseUrl+url);
	  	 		String urlEndPoint1 = driver.getCurrentUrl();
	  	 		String urlEndPoint = url.replaceAll("/", "_");
	  	 		
//	  	 		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(30000));
//	  	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
//	  	        Thread.sleep(6000);
	  	 		
	  	 		waitForPageLoad();
	  	 		Thread.sleep(3000);
	  	 		if(url.contains("pages/notifications") || url.contains("pages/rewards_home") || url.contains("pages/sections")|| url.contains("pages/poll_analytic")|| url.contains("pages/my_profile")|| url.contains("new_reports/login_session_report")|| url.contains("pages/upcoming_birthdays_anniversaries")|| url.contains("new_reports/dashboard")|| url.contains("new_reports/stored_value_report")|| url.contains("pages/hobby_clubs")) {
	  	 			
	  	 		Thread.sleep(5000);
	  	 		}
	  	 	
	            Screenshot screenshot = new AShot().takeScreenshot(driver);

	         // Save screenshot
	            
	            ImageIO.write(screenshot.getImage(), "PNG", new File("excel/UiTesting/actualImage/"+urlEndPoint+".png"));
	            
	            // Load saved reference screenshot
	            File expectedFile = new File("excel/UiTesting/baseImage/"+urlEndPoint+".png");
	            File actualFile = new File("excel/UiTesting/actualImage/"+urlEndPoint+".png");

	            // Compare screenshots
//	            Screenshot expectedScreenshot = new Screenshot(ImageIO.read(expectedFile));
//	            Screenshot actualScreenshot = new Screenshot(ImageIO.read(actualFile));
	            
	         // Compare images
	            boolean result = compareImages(actualFile, expectedFile, url);
	            
	            if(result) {
	                System.out.println("Images are identical");
	                CustomReporterRed.log("Images are identical : "+expectedFile.toString(), "green");
	     
	            } else {
	                System.out.println("Images are different : "+actualFile.toString());
	                CustomReporterRed.log("Difference in Screenshot, Please check the mentioned image : "+actualFile.toString(),"red");
	              
	            }
	            
	            
	            urlEndPoint.replace(url, "");

//	            ImageDiff diff = new ImageDiffer().makeDiff(expectedScreenshot, actualScreenshot);
//
//	            if (diff.hasDiff()) {
//	                System.out.println(url + " is different");
//	            } else {
//	                System.out.println(url + " is same");
//	            }
				
				j++;
			 }
			 driver.close();
	
			  	
		  }
		 
		 @Parameters("corpID")
		 @Test (priority = 1,enabled=true)
		  public void Ui_Testing_Expected(String corpID) throws EncryptedDocumentException, IOException, InterruptedException, AWTException  {
			 
			 
			 startBrowser(DataRunScript(8, 7));
			 newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
		
			String gotUrl = driver.getCurrentUrl();
			System.out.println(gotUrl);
				
			String baseUrl1 = gotUrl.replace("pages/rewards_home", "");
			String baseUrl = DataRunScript(8, 7);
			
			
//			System.out.println(baseUrl);
			 	
			  FileInputStream fis;
			  Workbook workbook;
			  Sheet Mysheet1;
			  Row row;
			  Cell cell;
			  Cell cell2;
			  String url;
			  	int m = 1;
		  		int n = 0;
			 
			 while (true) {
	  		     fis = new FileInputStream("excel/UiTesting/urls.xlsx");
	  		     workbook = WorkbookFactory.create(fis);
	  		     Mysheet1 = workbook.getSheet("urls");

	  		    row = Mysheet1.getRow(m);
	  		    if (row == null) {
	  		        // Create a new row if it doesn't exist
	  		        row = Mysheet1.createRow(m);
	  		    }
	  		    
	  		     cell = (Cell) row.getCell(n);
	  		     cell2 = (Cell) row.createCell(n+1);
	  		   
	  		    
	  		    if (cell == null) {
	  		    	
	  		        // Create a new cell if it doesn't exist
	  		        cell = (Cell) row.createCell(n);
	  		        cell2 = (Cell) row.createCell(n+1);
	  		      
	  		        
	  		    }
	  		    
	  		    if(cell.toString().isEmpty()) {
	  		    	break;
	  		    }
	  		   
	  		    url = ((org.apache.poi.ss.usermodel.Cell) cell).getStringCellValue();
//	  		    String baselineImg = ((org.apache.poi.ss.usermodel.Cell) cell2).getStringCellValue();
//	  		    System.out.println(baselineImg);
	  	 
	  		    
	  	 		driver.get(baseUrl+url);
	  	 		String urlEndPoint1 = driver.getCurrentUrl();
	  	 		String urlEndPoint = url.replaceAll("/", "_");
	  	 		
	  	 		waitForPageLoad();
	  	 		Thread.sleep(3000);
	  	 			
	  	 		if(url.contains("pages/notifications") || url.contains("pages/rewards_home") || url.contains("pages/sections")|| url.contains("pages/poll_analytic")|| url.contains("pages/my_profile")|| url.contains("new_reports/login_session_report")|| url.contains("pages/upcoming_birthdays_anniversaries")|| url.contains("new_reports/dashboard")|| url.contains("new_reports/stored_value_report")|| url.contains("pages/hobby_clubs")) {
	  	 			
		  	 		Thread.sleep(5000);
		  	 	}
	  	 		
	  	 	// Capture screenshot of the entire page
	            Screenshot screenshot = new AShot().takeScreenshot(driver);

	         // Save screenshot
	            
	            ImageIO.write(screenshot.getImage(), "PNG", new File("excel/UiTesting/baseImage/"+urlEndPoint+".png"));
	            
	            
	            urlEndPoint.replace(url, "");
	            
				
				m++;
			 }
	
			  	driver.close();	  
		}
		 
		 public static boolean compareImages(File actualFile, File expectedFile, String url) throws IOException {
		        // Read images
		        BufferedImage actualImg = ImageIO.read(actualFile);
		        BufferedImage expectedImg = ImageIO.read(expectedFile);
		        
		        // Compare dimensions
		        if(actualImg.getWidth() != expectedImg.getWidth() || actualImg.getHeight() != expectedImg.getHeight()) {
		        	System.out.println("dim compared");
		        	return false;
		        }
		        
		        int threshold;
		        // Compare images using a threshold
		        if(url.contains("pages/my_profile") || url.contains("pages/my_vouchers")|| url.contains("new_reports/dashboard")) {
	  	 			
		        	 threshold = (int) (actualImg.getWidth() * actualImg.getHeight() * 0.015); 
		  	 		}
		        else {
		        
		         threshold = (int) (actualImg.getWidth() * actualImg.getHeight() * 0.0005);
		        }// 5% threshold
		       
		        int diffCount = 0;
		        
		        // Compare pixels
		        for(int x = 0; x < actualImg.getWidth(); x++) {
		            for(int y = 0; y < actualImg.getHeight(); y++) {
		                if(actualImg.getRGB(x, y) != expectedImg.getRGB(x, y)) {
//		                   System.out.println("pixel compared");
		                   diffCount++;
		                  
		                   if (diffCount > threshold ) {
		                	   System.out.println(diffCount);  
		                	   return false; // Threshold exceeded, images are considered different
		                    }
		                   
		                }
		               
		            }
		            
		        }
		        
		        return true;
		    }
		 	 
	
	}	  
	
