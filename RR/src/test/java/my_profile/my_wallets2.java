package my_profile;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.poi.EncryptedDocumentException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility_RR.Utility_RR;




public class my_wallets2 extends Utility_RR{
	private int count2;
	
	@Parameters("corpID")
	@BeforeClass
	private void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
		 
		  startBrowser(DataRunScript(2, 1));
		  newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
	}

	@BeforeMethod
	public void beforeMethod1() throws EncryptedDocumentException, IOException {
		driver.get(DataRunScript(1, 1)+"in/pages/my_wallet");
	  	importWait();
	}

	private int getCountFromResponse(String jsonResponse) {
        // Parse JSON response and extract count
		JSONObject jsonObject = new JSONObject(jsonResponse);
	    return jsonObject.getInt("count");
    }
	  
	  @Parameters("corpID")
	  @Test (priority = 1,enabled=true)
	  public void wallet_count(String CorpID) throws InterruptedException, IOException, ParseException {
	        String apiUrl1 = "https://www.advantageclub.co/api/v1/mywallets?page=1&limit=100";
	        String apiUrl2 = "https://development1.advantageclub.co/api/v1/mywallets?page=1&limit=100";

	        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
	            // Create an HttpPost request for API 1
	            HttpPost request1 = new HttpPost(apiUrl1);
	            // Add custom header with token if needed
	            request1.setHeader("token", getToken());
	            // Send the request and get the response
	            try (CloseableHttpResponse response1 = httpClient.execute(request1)) {
	                // Extract JSON response body for API 1
	                String jsonResponse1 = EntityUtils.toString(response1.getEntity());
	                // Get count from response for API 1
	                int count1 = getCountFromResponse(jsonResponse1);
	                System.out.println("Count 1: " + count1);
	                
	                Thread.sleep(30000);

	                // Create an HttpPost request for API 2
	                HttpPost request2 = new HttpPost(apiUrl2);
	                // Add custom header with token if needed
	                request2.setHeader("token", getToken());
	                // Send the request and get the response
	                try (CloseableHttpResponse response2 = httpClient.execute(request2)) {
	                    String jsonResponse2 = EntityUtils.toString(response2.getEntity());
	                    count2 = getCountFromResponse(jsonResponse2);
	                    System.out.println("Count 2: " + count2);

	                    if (count1 == count2) {
	                        System.out.println("Count verified: " + count1);
	                    } else {
	                        System.out.println("My Wallet transactions are mismatched");
	                        CustomReporterRed.log("My Wallet transactions are mismatched","red");
	                    }
	                }
	            }
	        }

		
	  } 
	  @AfterMethod
	  public void afterMethod1() {
		  importWait();
	  }
	  
	 

	  @Parameters("corpID")
	  @Test (priority = 2,enabled=true)
	  
	  public void entires_count(String CorpID) {
		  WebElement tableBody = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[2]/div[2]/div[2]/table/tbody/tr[2]/td"));
	      int walletCount = tableBody.findElements(By.tagName("div")).size();
	      System.out.println("Count of wallet entries: " + walletCount);
	      if(count2!=walletCount) {
	    	  CustomReporterRed.log("Wallet transactions not getting populated correctly","red");
	    	  
	      }
	  }
	  

	  @AfterClass
	  public void afterClass() {
		  driver.close();
	  }

}


