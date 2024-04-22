	package vendor_verify;
	
	import org.testng.annotations.Test;


import utility_RR.Utility_RR;

import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.BeforeClass;
	
	import java.awt.AWTException;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Set;
	import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
	import org.openqa.selenium.By;
	import org.openqa.selenium.Cookie;
	import org.testng.Reporter;
	import org.testng.annotations.AfterClass;
	import org.json.JSONArray;
	import org.json.JSONObject;
	
	public class vendor_api_verification extends Utility_RR{
		
		
		 @Parameters("corpID")
		 @BeforeClass
		  public void beforeClass(String corpID) throws EncryptedDocumentException, MalformedURLException, InterruptedException, IOException, AWTException {
			 
			 if(corpID.contains("C1170")) {
				 startBrowser(DataRunScript(2, 1));
			 }
			 else {
				 startBrowser(DataRunScript(2, 1));
			 }
			 	 newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
			  
		  }	
		 
		 @BeforeMethod
		  public void beforeMethod() throws EncryptedDocumentException, IOException, InterruptedException {
			 
		 
	//		 driver.get(DataRunScript(2, 1));
			  importWait();
		  }
		 
		 @Parameters("corpID")
		 @Test (priority = 1,enabled=true)
		  public void Country_verify(String corpID) throws EncryptedDocumentException, IOException, ParseException, InterruptedException {
			  
	//		  driver.get(DataRunScript(1, 1)+"/pages/sections?section_id=3");
	//		  
			 
			 importWait();
			 Thread.sleep(3000);
			  
			// URL of the API
		        String apiUrl = "https://www.advantageclub.co/api/v1/zones?zone_new=true";
	
		        // Create an instance of CloseableHttpClient
		        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
		            // Create an HttpGet request
		            HttpGet request = new HttpGet(apiUrl);
		            
		            // Add custom header with token
		            request.setHeader("token", getToken());
	
		            // Send the request and get the response
		            CloseableHttpResponse response = httpClient.execute(request);
	
		            // Extract JSON response body
		            String jsonResponse = EntityUtils.toString(response.getEntity());	
	
		            // Print the JSON response
//		            System.out.println(jsonResponse);
//		            Reporter.log(jsonResponse,true);
		            
		         // Parse JSON response
		            JSONObject jsonObject = new JSONObject(jsonResponse);
		            JSONArray countriesArray = jsonObject.getJSONArray("countries");
		            JSONArray zonesArrays = jsonObject.getJSONArray("zones");
	
		            // Iterate over countries
		            int countryLive = 0;
		            int i;
		            for ( i = 0; i < countriesArray.length(); i++) {
		                JSONObject countryObj = countriesArray.getJSONObject(i);
		                JSONObject zonesObj = zonesArrays.getJSONObject(i);
		                String countryName = countryObj.getString("name");
		                String latitude ;
		                Number latitude1;
		                Number longitude1;
		                if(zonesObj.isNull("latitude")) {
		                	
		                	 latitude = "";	                	
		                }
		                else {
			                 latitude1 = zonesObj.getNumber("latitude");
			                 latitude = String.valueOf(latitude1);
		                }
		                
		                String longitude;
		                if(zonesObj.isNull("longitude")) {
		                	
		                	 longitude = "";	                	
		                }
		                else {
		                	 longitude1 = zonesObj.getNumber("longitude");
			                 longitude = String.valueOf(longitude1);
		                }
		                
		                
		                
		                int	j = i+1;
		    	  		int k = 0;
	
		    	  		while (true) {
		    	  		    FileInputStream fis = new FileInputStream("excel/vendor/VendorLiveReport.xlsx");
		    	  		    Workbook workbook = WorkbookFactory.create(fis);
		    	  		    Sheet Mysheet1 = workbook.getSheet("Country_Listing");
	
		    	  		    Row row = Mysheet1.getRow(j);
		    	  		    if (row == null) {
		    	  		        // Create a new row if it doesn't exist
		    	  		        row = Mysheet1.createRow(j);
		    	  		    }
		    	  		    
		    	  		    Cell cell = (Cell) row.getCell(k);
		    	  		    Cell cell2 = (Cell) row.createCell(k+1);
		    	  		    Cell cell3 = (Cell) row.createCell(k+2);
		    	  		    
		    	  		    if (cell == null) {
		    	  		        // Create a new cell if it doesn't exist
		    	  		        cell = (Cell) row.createCell(k);
		    	  		        cell2 = (Cell) row.createCell(k+1);
		    	  		        cell3 = (Cell) row.createCell(k+2);
		    	  		    }
	
		    	  		    String value = ((org.apache.poi.ss.usermodel.Cell) cell).getStringCellValue();
		    	  		    String value2 = ((org.apache.poi.ss.usermodel.Cell) cell2).getStringCellValue();
		    	  		    String value3 = ((org.apache.poi.ss.usermodel.Cell) cell3).getStringCellValue();
		    	  		    
		    	  		    //System.out.println(value);
	
		    	  		    if (value == null || value.isEmpty() || value != null) {
		    	  		        // Set the extracted text as the cell value
		    	  		        ((org.apache.poi.ss.usermodel.Cell) cell).setCellValue(countryName);
		    	  		        ((org.apache.poi.ss.usermodel.Cell) cell2).setCellValue(latitude);
		    	  		        ((org.apache.poi.ss.usermodel.Cell) cell3).setCellValue(longitude);
	
		    	  		        // Write the modified workbook content back to the Excel file
		    	  		        FileOutputStream outputStream = new FileOutputStream("excel/vendor/VendorLiveReport.xlsx");
		    	  		        workbook.write(outputStream);
		    	  		        outputStream.close();
		    	  		        
		    	  		        break;
		    	  		    }
	
		    	  		    
		    	  		    fis.close(); // Close the input stream at the end of each iteration
		    	  		}
		                Reporter.log("Country Name: " + countryName, true);
		                
		                countryLive ++;
		            }
		            
		            System.out.println(countryLive);
		            String strNumber = String.valueOf(countryLive);
		            Reporter.log("Number of countries live in "+corpID+" :" +strNumber);
	
	
		            // Close resources
		            response.close();
		            httpClient.close();
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			  
			 
			 
			  	
		  }
		 
		  @Test (priority = 2,enabled=true)
		  public void vendor_verify() throws EncryptedDocumentException, IOException, ParseException, InterruptedException {
			  
	//		  driver.get(DataRunScript(1, 1)+"/pages/sections?section_id=3");
	//		  
	//		  importWait();
			  
			// URL of the API
			  
			  FileInputStream fis;
			  Workbook workbook;
			  Sheet Mysheet1;
			  Row row;
			  Cell cell;
			  Cell cell2;
			  Cell cell3;
			  String zone;
			  ArrayList<String> noDealArray = new ArrayList<>();
			  	int x = 1;
			  	int j = 1;
		  		int k = 0;
		  		
		  		
			  while (true) {
		  		     fis = new FileInputStream("excel/vendor/VendorLiveReport.xlsx");
		  		     workbook = WorkbookFactory.create(fis);
		  		     Mysheet1 = workbook.getSheet("Country_Listing");
	
		  		    row = Mysheet1.getRow(j);
		  		    if (row == null) {
		  		        // Create a new row if it doesn't exist
		  		        row = Mysheet1.createRow(j);
		  		    }
		  		    
		  		     cell = (Cell) row.getCell(k);
		  		     cell2 = (Cell) row.createCell(k+1);
		  		     cell3 = (Cell) row.createCell(k+2);
		  		    
		  		    if (cell == null) {
		  		    	
		  		        // Create a new cell if it doesn't exist
		  		        cell = (Cell) row.createCell(k);
		  		        cell2 = (Cell) row.createCell(k+1);
		  		        cell3 = (Cell) row.createCell(k+2);
		  		        
		  		    }
		  		   
		  		    zone = ((org.apache.poi.ss.usermodel.Cell) cell).getStringCellValue();
		  		   
		  		    
		  		    String lat = ((org.apache.poi.ss.usermodel.Cell) cell2).getStringCellValue();
		  		    String lang = ((org.apache.poi.ss.usermodel.Cell) cell3).getStringCellValue();
		  		    
		  		    
					if(cell.toString().isEmpty()) {
		  		    	break;
		  		    }
		  		    else if(cell.toString().contains("India")){
		  		    	j++;
		  		    	continue;
		  		    	
		  		    }
		  		    
		  		    else {
		  		    	
		  		  String apiUrl1 = "https://www.advantageclub.co/api/v1/business_and_redeem?page=0&zone="+zone.replace(" ", "+")+"&limit=2000&lat=0&lng=0&tag=section&section_id=3&sort_by=Popularity&search_key=";
	
			        // Create an instance of CloseableHttpClient
			        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			            // Create an HttpGet request
			            HttpGet request = new HttpGet(apiUrl1);
			            
			            // Add custom header with token
			            request.setHeader("token", getToken());
	
			            // Send the request and get the response
			            CloseableHttpResponse response = httpClient.execute(request);
	
			            // Extract JSON response body
			            
			           
			            String jsonResponse1 = EntityUtils.toString(response.getEntity());
			            JSONObject jsonObject1 = new JSONObject(jsonResponse1);																	
			            int vendorCount = jsonObject1.getInt("vendor_count");
			           
			            String vendor_count = Integer.toString(vendorCount);
			            while(true) {
			            	Reporter.log("Vendor count for "+zone+" :" +vendor_count);
			            	System.out.println("Vendor count for "+zone+" :" +vendor_count);	
			            	break;
			            }   
			            
			            // Print the JSON response
	//		            System.out.println(jsonResponse1);
	//		            Reporter.log(jsonResponse1,true);
			            
			            JSONArray dealListArray = jsonObject1.getJSONArray("dealList");
			            if(dealListArray.length() == 0) {
			            	
			            	
			            	noDealArray.add(zone);
			            	
			            }
			           
			            // Iterate over countries
			           
			            for (int i = 0; i < dealListArray.length(); i++) {
			                JSONObject dealListObj = dealListArray.getJSONObject(i);
			                JSONArray locations = dealListObj.optJSONArray("locations");
			                int dealId1 = dealListObj.getInt("id");
			                String dealId = String.valueOf(dealId1);
			                String dealName = dealListObj.optString("name");
			                System.out.println(dealName);
			                String dealSummary = dealListObj.optString("summary");
			                String dealRegion=null;
			                
			               
			                if (locations.length() == 0) {
			                	
			                	dealRegion = dealListObj.optString("region");
			                	
			                	
			                }
			                else {
			                	for (int l = 0; l <=0; l++) {
			                	JSONObject locationObj = locations.getJSONObject(l);
			                	dealRegion = locationObj.optString("country");
			                	
			                	}
			                }
			                
			           
		    	  		int y = 0;
		    	  		
	
		    	  		while (true) {
		    	  			
		    	  		    fis = new FileInputStream("excel/vendor/VendorLiveReport.xlsx");
		    	  		    workbook = WorkbookFactory.create(fis);
		    	  		    Mysheet1 = workbook.getSheet("Country_wise_vendor_Listing");
	
		    	  		     row = Mysheet1.getRow(x);
		    	  		    if (row == null) {
		    	  		        // Create a new row if it doesn't exist
		    	  		        row = Mysheet1.createRow(x);
		    	  		    }
		    	  		    
		    	  		     cell = (Cell) row.getCell(y);
		    	  		     cell2 = (Cell) row.createCell(y+1);
		    	  		     cell3 = (Cell) row.createCell(y+2);
		    	  		    Cell cell4 = (Cell) row.createCell(y+3);
		    	  		    Cell cell5 = (Cell) row.createCell(y+4);
		    	  		    Cell cell6 = (Cell) row.createCell(y+5);
		    	  		    
		    	  		    
		    	  		    if (cell == null) {
		    	  		        // Create a new cell if it doesn't exist
		    	  		        cell = (Cell) row.createCell(y);
		    	  		        cell2 = (Cell) row.createCell(y+1);
		    	  		        cell3 = (Cell) row.createCell(y+2);
		    	  		        cell4 = (Cell) row.createCell(y+3);
		    	  		        cell5 = (Cell) row.createCell(y+4);
		    	  		        cell6 = (Cell) row.createCell(y+5);
		    	  		    }
		    	  		    
		    	  		    
	
		    	  		    String value = ((org.apache.poi.ss.usermodel.Cell) cell).getStringCellValue();
		    	  		    String value2 = ((org.apache.poi.ss.usermodel.Cell) cell2).getStringCellValue();
		    	  		    String value3 = ((org.apache.poi.ss.usermodel.Cell) cell3).getStringCellValue();
		    	  		    String value4 = ((org.apache.poi.ss.usermodel.Cell) cell4).getStringCellValue();
		    	  		    String value5 = ((org.apache.poi.ss.usermodel.Cell) cell5).getStringCellValue();
		    	  		    String value6 = ((org.apache.poi.ss.usermodel.Cell) cell6).getStringCellValue();
			  		    
		    	  		  
		    	  		    //System.out.println(value);
	
		    	  		    if (value == null || value.isEmpty() || value != null) {
		    	  		        // Set the extracted text as the cell value
		    	  		        ((org.apache.poi.ss.usermodel.Cell) cell).setCellValue(zone);
		    	  		        ((org.apache.poi.ss.usermodel.Cell) cell2).setCellValue(dealRegion);
		    	  		        ((org.apache.poi.ss.usermodel.Cell) cell3).setCellValue(vendor_count);
		    	  		        ((org.apache.poi.ss.usermodel.Cell) cell4).setCellValue(dealId);
		    	  		        ((org.apache.poi.ss.usermodel.Cell) cell5).setCellValue(dealName);
		    	  		        ((org.apache.poi.ss.usermodel.Cell) cell6).setCellValue(dealSummary);
	
	
		    	  		        // Write the modified workbook content back to the Excel file
		    	  		        FileOutputStream outputStream = new FileOutputStream("excel/vendor/VendorLiveReport.xlsx");
		    	  		        workbook.write(outputStream);
		    	  		        outputStream.close();
		    	  		        x++;
		    	  		        fis.close();
		    	  		        break;
		    	  		 
		    	  		        
		    	  		    }
	
		    	  		   
		    	  		    
		    	  		    
		    	  		    // Close the input stream at the end of each iteration
		    	  		}
		    	  		
			            response.close();
			            httpClient.close();
			            
			            }
			        }
		  		    
			        catch (IOException e) {
			            e.printStackTrace();
			        }
		  		    }
			        
			        j++;
			       
			  }
			  			String combinedString = String.join(",", noDealArray);
			  			System.out.println(combinedString);
			  			Reporter.log(combinedString);
		    
			  
//			  		sendEmailWithAttachment("dheerajc@advantageclub.in", "Vendor Verification", "PFA", "C:\\Users\\AC\\git\\RR_Web_Automation\\RR\\excel\\vendor\\VendorLiveReport.xlsx");
		  
		  		    
		  	    
		  }
	
		  
	 @Test (priority = 3,enabled=false)
	  public void vendor_verify1() throws EncryptedDocumentException, IOException, ParseException, InterruptedException {
		 
		 FileInputStream fis;
		  Workbook workbook;
		  Sheet Mysheet1;
		  Row row;
		  Cell cell;
		  Cell cell2;
		  Cell cell3;
		    
 		  String apiUrl2 = "https://www.advantageclub.co/api/v1/business_and_redeem?page=0&zone=Indonesia&limit=2000&lat=0&lng=0&tag=section&section_id=3&sort_by=Popularity&search_key=";

	        // Create an instance of CloseableHttpClient
	        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
	            // Create an HttpGet request
	            HttpGet request = new HttpGet(apiUrl2);
	            
	            // Add custom header with token
	            request.setHeader("token", "cJ5xSPHZu2szw6y8Qncz");

	            // Send the request and get the response
	            CloseableHttpResponse response1 = httpClient.execute(request);

	            // Extract JSON response body
	            
	            Thread.sleep(3000);
	            String jsonResponse3 = EntityUtils.toString(response1.getEntity());
	            JSONObject jsonObject3 = new JSONObject(jsonResponse3);
	            int vendorCount = jsonObject3.getInt("vendor_count");
	            String vendor_count = Integer.toString(vendorCount);
	            Reporter.log(vendor_count);
	            
	            // Print the JSON response
//		            System.out.println(jsonResponse1);
//		            Reporter.log(jsonResponse1,true);
	            
	            JSONArray dealListArray = jsonObject3.getJSONArray("dealList");
	            
	            
	            // Iterate over countries
	           
	            for (int i = 0; i < dealListArray.length(); i++) {
	                JSONObject dealListObj = dealListArray.getJSONObject(i);
	                JSONArray locations = dealListObj.optJSONArray("locations");
	                int dealId1 = dealListObj.getInt("id");
	                String dealId = String.valueOf(dealId1);
	                String dealName = dealListObj.getString("name");
	                System.out.println(dealName);
	                String dealSummary = dealListObj.getString("summary");
	                String dealRegion=null;
	                
	               
	                if (locations.length() == 0) {
	                	
	                	dealRegion = dealListObj.getString("region");
	                	
	                	
	                }
	                else {
	                	for (int l = 0; l <=0; l++) {
	                	JSONObject locationObj = locations.getJSONObject(l);
	                	dealRegion = locationObj.getString("country");
	                	
	                	}
	                }
	                
	            
	            int x = 1;
   	  			int y = 0;

   	  		while (true) {
   	  		    fis = new FileInputStream("excel/vendor/VendorLiveReport.xlsx");
   	  		    workbook = WorkbookFactory.create(fis);
   	  		    Mysheet1 = workbook.getSheet("Country_wise_vendor_Listing");

   	  		     row = Mysheet1.getRow(x);
   	  		    if (row == null) {
   	  		        // Create a new row if it doesn't exist
   	  		        row = Mysheet1.createRow(x);
   	  		    }
   	  		    
   	  		     cell = (Cell) row.getCell(y);
   	  		     cell2 = (Cell) row.createCell(y+1);
   	  		     cell3 = (Cell) row.createCell(y+2);
   	  		    Cell cell4 = (Cell) row.createCell(y+3);
   	  		    Cell cell5 = (Cell) row.createCell(y+4);
   	  		    Cell cell6 = (Cell) row.createCell(y+5);
   	  		    
   	  		    
   	  		    if (cell == null) {
   	  		        // Create a new cell if it doesn't exist
   	  		        cell = (Cell) row.createCell(y);
   	  		        cell2 = (Cell) row.createCell(y+1);
   	  		        cell3 = (Cell) row.createCell(y+2);
   	  		        cell4 = (Cell) row.createCell(y+3);
   	  		        cell5 = (Cell) row.createCell(y+4);
   	  		        cell6 = (Cell) row.createCell(y+5);
   	  		    }
   	  		    
   	  		    

   	  		    String value = ((org.apache.poi.ss.usermodel.Cell) cell).getStringCellValue();
   	  		    String value2 = ((org.apache.poi.ss.usermodel.Cell) cell2).getStringCellValue();
   	  		    String value3 = ((org.apache.poi.ss.usermodel.Cell) cell3).getStringCellValue();
   	  		    String value4 = ((org.apache.poi.ss.usermodel.Cell) cell4).getStringCellValue();
   	  		    String value5 = ((org.apache.poi.ss.usermodel.Cell) cell5).getStringCellValue();
   	  		    String value6 = ((org.apache.poi.ss.usermodel.Cell) cell6).getStringCellValue();
	  		    
   	  		  
   	  		    //System.out.println(value);

   	  		    if (value == null || value.isEmpty()) {
   	  		        // Set the extracted text as the cell value
   	  		        ((org.apache.poi.ss.usermodel.Cell) cell).setCellValue("ind");
   	  		        ((org.apache.poi.ss.usermodel.Cell) cell2).setCellValue(dealRegion);
   	  		        ((org.apache.poi.ss.usermodel.Cell) cell3).setCellValue(vendor_count);
   	  		        ((org.apache.poi.ss.usermodel.Cell) cell4).setCellValue(dealId);
   	  		        ((org.apache.poi.ss.usermodel.Cell) cell5).setCellValue(dealName);
   	  		        ((org.apache.poi.ss.usermodel.Cell) cell6).setCellValue(dealSummary);


   	  		        // Write the modified workbook content back to the Excel file
   	  		        FileOutputStream outputStream = new FileOutputStream("excel/vendor/VendorLiveReport.xlsx");
   	  		        workbook.write(outputStream);
   	  		        outputStream.close();
   	  		        
   	  		        break;
   	  		    }

   	  		    x++;
   	  		    fis.close(); // Close the input stream at the end of each iteration
   	  		}
	            
	            response1.close();
	            httpClient.close();
	            
	            }
	        }
 		    
	        catch (IOException e) {
	            e.printStackTrace();
	        }
 		    
		 
//	  		sendEmailWithAttachment("dheerajc@advantageclub.in", "Vendor Verification", "PFA", "C:\\Users\\AC\\git\\RR_Web_Automation\\RR\\excel\\vendor\\VendorLiveReport.xlsx");

	 }  
			  
			  
		  @AfterMethod
		  public void afterMethod() {
		  }
		  
		 
		
		  @AfterClass
		  public void afterClass() {
			  driver.close();
		  }
	
	}	  
	
