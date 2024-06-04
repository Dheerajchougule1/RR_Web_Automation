package genericFlow;

import static org.testng.Assert.fail;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility_RR.Utility_RR;

public class ResponseTime extends Utility_RR{

	@Parameters("corpID")
	@Test
  public void response_time_test(String corpID) throws EncryptedDocumentException, InterruptedException, AWTException, IOException {
	  
		startBrowser(DataRunScript(2, 1));
//	  newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
	  
		waitForPageLoad();
	  	driver.findElement(By.xpath("(//a[@href='/login'])[1]")).click();
		 Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='user_email']")).sendKeys(DataAppriciateFlow(corpID, 3, 1));
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(DataAppriciateFlow(corpID, 3, 2));
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
		Thread.sleep(3000);
		
	  Thread.sleep(2000);
	  
	 
	  
	  String apiUrl = DataRunScript(8, 7)+"api/v1/newsfeeds";

      // Create an instance of CloseableHttpClient
      try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
          // Create an HttpGet request
          HttpGet request = new HttpGet(apiUrl);

          // Add custom header with token
          request.setHeader("token", getToken());

          // Capture start time
          long startTime = System.currentTimeMillis();

          // Send the request and get the response
          try (CloseableHttpResponse response = httpClient.execute(request)) {
              // Capture end time
              long endTime = System.currentTimeMillis();

              // Calculate response time
              long responseTime = endTime - startTime;

              // Get status code
              int statusCode = response.getCode();


              // Print status code, response time, and JSON response
              System.out.println("Status Code: " + statusCode);
              System.out.println("Response Time: " + responseTime + " ms");
              LocalDateTime currentDateTime = LocalDateTime.now();
              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
              String dateTimeString = currentDateTime.format(formatter);
              
              	int i = 0;
  	  			int j = 0;

  	  		while (true) {
  	  		    FileInputStream fis = new FileInputStream("excel/GenericFlow/NewsFeedDelete.xlsx");
  	  		    Workbook workbook = WorkbookFactory.create(fis);
  	  		    Sheet Mysheet1 = workbook.getSheet("Sheet2");

  	  		    Row row = Mysheet1.getRow(i);
  	  		    if (row == null) {
  	  		        // Create a new row if it doesn't exist
  	  		        row = Mysheet1.createRow(i);
  	  		    }
  	  		    
  	  		    Cell cell = (Cell) row.getCell(j);
  	  		    Cell cell2 = (Cell) row.createCell(j+1);
  	  		    Cell cell3 = (Cell) row.createCell(j+2);
  	  		    if (cell == null) {
  	  		        // Create a new cell if it doesn't exist
  	  		        cell = (Cell) row.createCell(j);
  	  		        cell2 = (Cell) row.createCell(j+1);
  	  		        cell3 = (Cell) row.createCell(j+2);
  	  		    }

  	  		    double value = ((org.apache.poi.ss.usermodel.Cell) cell).getNumericCellValue();
  	  		    double value2 = ((org.apache.poi.ss.usermodel.Cell) cell2).getNumericCellValue();
  	  		    String value3 = ((org.apache.poi.ss.usermodel.Cell) cell3).getStringCellValue();
  	  		
  	  		    //System.out.println(value);

  	  		    if (cell == null || cell.getCellType()==CellType.BLANK) {
  	  		        // Set the extracted text as the cell value
  	  		        ((org.apache.poi.ss.usermodel.Cell) cell).setCellValue(statusCode);
  	  		        ((org.apache.poi.ss.usermodel.Cell) cell2).setCellValue(responseTime);
  	  		        ((org.apache.poi.ss.usermodel.Cell) cell3).setCellValue(dateTimeString);

  	  		        // Write the modified workbook content back to the Excel file
  	  		        FileOutputStream outputStream = new FileOutputStream("excel/GenericFlow/NewsFeedDelete.xlsx");
  	  		        workbook.write(outputStream);
  	  		        outputStream.close();
  	  		        
  	  		        break;
  	  		    }

  	  		    i++;
  	  		    
  	  		    
  	  		    
  	  		}
  	  		
  	  			if(statusCode !=200 || responseTime >=1400) {
 		    	
	  		    CustomReporterRed.log("Status Code: " + statusCode, "red");
	  		    CustomReporterRed.log("Response Time: " + responseTime + " ms", "red");
	  		 
         
	  		    fail();
	  		    	
	  		    }
	  		    else {
	  		    	
	  		    CustomReporterRed.log("Status Code: " + statusCode, "green");
	  		    CustomReporterRed.log("Response Time: " + responseTime + " ms", "green");
	  		    }
              driver.close();
            
          }

      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
