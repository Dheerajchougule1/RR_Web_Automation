package Afni_testing;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.classic.methods.HttpPost;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility_RR.Utility_RR;
import utility_RR.Utility_RR.CustomReporterRed;




public class genericNominationFlow1 extends Utility_RR {
	//ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
//	 private List<WebDriver> drivers = new ArrayList<>();
//	 protected static WebDriver driver=null;
	public String message ;
	public WebElement custom_message;
	public String cmessage ;
	public int currentPointsNominee;
	public int currentBudgetPoints;
	public String Budget_name ;
	public String award_name;
	public String budgetOwnerName;
	public String currentIssued;
	
	public static String getName(String name,int row,int col) throws EncryptedDocumentException, IOException {
        
        String filePath = "excel/NominationFlow/GenericNomination.xlsx";

        String entityName = name;

        // Using try-with-resources to ensure the streams are closed properly
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet("C1307");
            if (sheet == null) {
                throw new RuntimeException("Sheet 'C1307' doesn't exist.");
            }

            Row amountRow = sheet.getRow(row);
            if (amountRow == null) {
                throw new RuntimeException("Row " + row + " doesn't exist in the sheet.");
            }

            Cell cell = amountRow.getCell(col);
            if (cell == null) {
                throw new RuntimeException("Cell " + col + " in row " + row + " doesn't exist.");
            }
            cell.setCellValue(name);

            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
        }

        return entityName;
    }
	public void testpendingapporvalentry(String custom_message2) {
		if(custom_message2.contains(message)) {
        	System.out.println("Nomination entry verified ");
        }
        else {
        	CustomReporterRed.log("Nomination not found", "red");
        }
	}
	 
	    @Parameters("corpID")
		@BeforeClass
		public void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
	    	multiSessionTest(corpID, DataRunScript(2, 1));	
	    	

	   
	    }
  
			
	    public void setDriver(WebDriver driver) {
	        threadLocalDriver.set(driver);
	        Utility_RR.driver = driver;
	        //System.out.println("ThreadLocal Driver set: " + driver);
	    }

	    protected WebDriver getDriver() {
	    	driver = threadLocalDriver.get();
	    	//System.out.println("ThreadLocal Driver retrieved: " + driver);
	        return threadLocalDriver.get();
	        
	    }

	    @BeforeMethod
	    public void beforeMethod() {
	        // Any setup before each test method
	    }

	    @Parameters("corpID")
	    @Test(priority = 1, enabled = false)
	    public void nominateForAward(String corpID) throws InterruptedException, EncryptedDocumentException, AWTException, IOException {
	        setDriver(drivers.get(0));
	        driver = getDriver();

	        Thread.sleep(2000);
	      //login for nominator 
	        if(corpID=="1307" && DataGenericNominationFlow(corpID, 27, 1).contains("multiple")) {
	        newui_login(corpID,DataGenericNominationFlow(corpID, 4, 4),DataGenericNominationFlow(corpID, 4, 5));
	        }
	        
	        else {
	        	newui_login(corpID,DataGenericNominationFlow(corpID, 4, 1),DataGenericNominationFlow(corpID, 4, 2));
	        }
	        
	        
	       // redirect to nominate for award 
	        Thread.sleep(1000);
	        driver.get(DataRunScript(1, 1)+"in/pages/nominate_for_award");
	 
	        //send nomination 
	        nominationInput(DataGenericNominationFlow(corpID,13,3));
	        nominateBudgetSelect(DataGenericNominationFlowNum(corpID, 24, 1));
	        getName(NominationBudget_name,24,2);
	        nominateAwardSelect(DataGenericNominationFlowNum(corpID, 25, 1));
	        getName(Nominationaward_name,25,2);
	        WebElement points_input = driver.findElement(By.xpath("//input[@id='award_point']"));
	        Double Point = DataGenericNominationFlowNum(corpID, 26, 1);
	        points_input.sendKeys(Double.toString(Point));
	        WebElement valueStatement = driver.findElement(By.xpath("//div[@id='valueStatements']//select[@class='p-5 cursor-pointer p3 rounded-lg inputField greyBorder bg-white selectInputWidth']"));
	        valueStatement.click();
	        WebElement option1 = driver.findElement(By.xpath("//option[@value='Trust']"));
	        option1.click();
	        WebElement message_box = driver.findElement(By.xpath("//textarea[@class='w-full h-40 p-5 rounded-lg inputField messageTextArea form-control greyBorder p3']"));
	        message = generatedString;
	        message_box.sendKeys(message);
	        
	        WebElement submitButton = driver.findElement(By.xpath("//button[@class='rounded-lg p-4 w-56 btn submitBtn p1 font-semibold']"));
	        submitButton.click();
	        Thread.sleep(3000);
	        //WebElement popup = driver.findElement(By.xpath("//div[@class='popupContainer bg-gradient-to-tl']"));
	        WebElement popup1= driver.findElement(By.xpath("//div[@class='message_container_section']"));
	        String success_message = popup1.getText();
	        System.out.println(success_message);
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//div[@class='cross-top absolute -top-12 -right-1 h-8 w-8 flex justify-center items-center rounded-full cursor-pointer closePopup']")).click();
	        //nominationverification in nomination-status 
	        Thread.sleep(2000);
	        driver.get(DataRunScript(1, 1)+"in/pages/nomination-status");
	        Thread.sleep(2000);
	        //	        Actions actions = new Actions(driver);
//	        for (int i = 0; i < 5; i++) {
//	            actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
//	            Thread.sleep(500); // Add a small delay between each zoom out action
//	        }
	        ScrollIntoView_by_webelement("//tbody[@id='table-body']//tr[1]");
//	        Map<String,String> nominationStatus = getObjectData("//tbody[@id='table-body']",2);
//            cmessage = nominationStatus.get("Custom Message");
//           System.out.println("nomination status entry : " + nominationStatus);
	       // check for message in first entry 
	       custom_message = driver.findElement(By.xpath("(//td[@class='custom_message'])[1]"));
	       cmessage = custom_message.getText();
	        testpendingapporvalentry(cmessage);
	        		
	        
	        
	    }
	    
	    @Parameters("corpID")
	    @Test(priority = 1, enabled = false)
	    public String budgetUtilReportTest(String corpID) throws EncryptedDocumentException, InterruptedException, AWTException, IOException {
	    	 setDriver(drivers.get(0));
		      driver = getDriver();
	    	newui_login(corpID,DataGenericNominationFlow(corpID, 11, 1),DataGenericNominationFlow(corpID, 11, 2));
	    	
	    	driver.get(DataRunScript(1, 1)+"in/new_reports/budget_utilization_report");
	    	budgetOwnerName = DataGenericNominationFlow(corpID, 12, 3);
	    	// NominationBudget_name 
	    	WebElement table = driver.findElement(By.tagName("tbody"));
	          
	          // Locate all rows in the table
	        List<WebElement> rows = table.findElements(By.tagName("tr"));

	        boolean rowFound = false;

	          for (WebElement row : rows) {
	              List<WebElement> cells = row.findElements(By.tagName("td"));
	              String currentOwnerName = cells.get(1).getText();
	              String currentBudgetName = cells.get(2).getText();

	              if (currentOwnerName.equals(budgetOwnerName) && currentBudgetName.equals(NominationBudget_name)) {
	                  rowFound = true;
	                  // Extract the required data
	                  
	                  currentIssued = cells.get(8).getText();
	                  System.out.println("Current Issued amount of Nomination Budget : " + currentIssued);
	    	break;
	    	
	              }
	    }
	          Assert.assertTrue(rowFound, "The row with the specified budget owner and budget name was not found.");
	          
	          return currentIssued;
	 }
	  

	    @Parameters("corpID")
	    @Test(priority = 3, enabled = true)
	    public void nomineeAmountVerification(String corpID) throws InterruptedException, EncryptedDocumentException, IOException, AWTException {
	        setDriver(drivers.get(2));
	        driver = getDriver();
	        //admin login 
	        newui_login(corpID,DataGenericNominationFlow(corpID, 13, 1),DataGenericNominationFlow(corpID, 13, 2));
	        driver.get(DataRunScript(1, 7)+"in/pages/my_wallet");
	        WebElement amount = driver.findElement(By.xpath("(//h1[@class='ac-point-details m-0 text-sm'])[1]"));
	        String wallet_points = amount.getText();
            String[] parts = wallet_points.split(" ");
            String number = parts[0];
            Double currentPointsNominee = Double.parseDouble(number);
	       // currentPointsNominee = Integer.parseInt(wallet_points);
	        System.out.println("Points for employee : " + currentPointsNominee);
	        
	        
		    }

	    @Parameters("corpID")
	    @Test(priority = 4, enabled = true)
	    public void budgetDeductorAmountVerification(String corpID) throws EncryptedDocumentException, InterruptedException, AWTException, IOException {
	        setDriver(drivers.get(3));
	        driver = getDriver();
	        
	        // Budget deductor login 
	        newui_login(corpID, DataGenericNominationFlow(corpID, 12, 1), DataGenericNominationFlow(corpID, 12, 2));
	        driver.get(DataRunScript(1, 7) + "in/pages/users_budget");

	        List<WebElement> walletContainers = driver.findElements(By.xpath("//div[@class='user_wallet_name_and_balance_container leading-5 mt-3 px-4']"));
	        for (WebElement container : walletContainers) {
	            WebElement walletNameElement = container.findElement(By.xpath(".//div[@class='user_wallet_name font-medium text-2xl']"));
	            String walletName = walletNameElement.getText();
	            //System.out.println(walletName);
	           // System.out.println(NominationBudget_name);
	            if (walletName.contains(NominationBudget_name)) {
	                WebElement pointsElement = container.findElement(By.xpath(".//div[contains(@class, 'user_wallet_balance') and contains(text(), 'Points')]"));
	                String currentPoints = pointsElement.getText();
	                String[] parts = currentPoints.split(" ");
	                String number = parts[0];
	                Double currentBudgetPoints = Double.parseDouble(number);
	                System.out.println("Budget Deductor Points  : "+ currentBudgetPoints);
	            }
	        }
	    }
	    	
	    	
	    @Parameters({"corpID"})
	    @Test(priority = 5, enabled = true)
	    public void checkPendingNominationEntries(String corpID ) throws Exception {
	    	setDriver(drivers.get(1));
	    	driver = getDriver();
	    	//newui_login(corpID, DataGenericNominationFlow(corpID ,11, 1) , DataGenericNominationFlow(corpID ,11, 2));
	    	driver.get(DataRunScript(1, 7)+"in/new_reports/pending_nomination_report");
	    	
	    	
	    	String apiUrl = DataRunScript(1, 1)+"pending_nomination_report_new_2?page=1";
//	    	String payload = " ";
//	    	String response = null;
			Map<String,String> nominationData = getBackendDataFromApi(apiUrl, "response", 0, "{}");
			System.out.println(nominationData);
			String custMessage = nominationData.get("custom_message");
			testpendingapporvalentry(custMessage);
			
			
			
	    	
	    }
	    
	    
	    
	    @Parameters({"corpID"})
	    @Test(priority = 6, enabled = true)
	    public void approverTasks(String corpID) throws Exception {
	        // Loop through the number of approvers
	    	Double noOfApprovers = DataGenericNominationFlowNum(corpID, 1, 3);
	        for (int i = 1; i <=noOfApprovers; i++) {
	            // Use the driver for each approver
	            setDriver(drivers.get(3 + i));
	            driver = getDriver();
	            //driver.get("https://development1.advantageclub.co/login");

	            // Fetch the credentials for the current approver from the specified row
	            int rowIndex = 4 + i; // Adjust starting row index as needed
	            String username = DataGenericNominationFlow(corpID, rowIndex, 1);
	            String password = DataGenericNominationFlow(corpID, rowIndex, 2);

	            // Log in using the fetched credentials
	            newui_login(corpID, username, password);
	            driver.get(DataRunScript(1, 7)+"in/pages/pending_approvals");
	            Thread.sleep(2000);
	            ScrollIntoView_by_webelement("//table[@class='nomination_table']//tr[1]");
//	            Map<String,String> pendingApproval = getObjectData("//table[@class='nomination_table']",1);
//	            cmessage = pendingApproval.get("Custom Message");
//	            System.out.println(pendingApproval);
	            custom_message = driver.findElement(By.xpath("(//div[@data-key='Custom Message'])[1]"));
	            cmessage = custom_message.getText();
	           testpendingapporvalentry(cmessage);
	           
//		        WebElement comment = driver.findElement(By.xpath("(//textarea[@id='commentShow282311'])[1]"));
//		        comment.sendKeys("not approved");
	           Thread.sleep(2000);
		        WebElement check_box = driver.findElement(By.xpath("(//input[@class='px-4 py-4 select-checkbox'])[1]"));
		        check_box.click();
		        WebElement approve_button = driver.findElement(By.xpath("//input[@id='approveBtn']"));
		        approve_button.click();
		        Thread.sleep(10000);
		        List <WebElement> popup = driver.findElements(By.xpath("//div[@class='popupContainer bg-gradient-to-tl']"));
		        if(popup.isEmpty()) {System.out.println("approval popup not found");}
		        WebElement status = driver.findElement(By.xpath("(//td[@class='px-4 py-6 each-data-cont'])[1]"));
		        WebElement result = driver.findElement(By.xpath("(//td[@class='px-4 py-6 each-data-cont'])[2]"));
		        String Result = result.getText();
		        String Status = status.getText();
		        if(Status.equals("Success")) {
		        	if(Result.equals("Approved")) {
		        	System.out.println("Approved Nomination");
		        	}
		        	else {
		        		//write here code for final approver checking nomination status and pending approval 
		        		System.out.println("Awarded");
		        		
		        	}
		        }
		        else {
		        	System.out.println(Status + " : " + Result);
		        	
		        	}
		        
		        //else mei get the error message as well 
	            
	            
	            
	            

	           

	            // Optionally, log out after each test run
	            // logout();
	        }
	    }
	    @Parameters("corpID")
	    @Test(priority = 7 , enabled = true)
	    public void checkEntriesAfterApproval(String corpID) throws EncryptedDocumentException, InterruptedException, AWTException, IOException {
	    	//login and driver to be added 
	    	 // Add other test steps for approver as needed
	    	 setDriver(drivers.get(5));
	         driver = getDriver();
	         newui_login(corpID, DataGenericNominationFlow(corpID ,11, 1) , DataGenericNominationFlow(corpID ,11, 2));
	        String currentBudgetValue = budgetUtilReportTest(corpID);
	        System.out.println("After approval current issued value of Budget : " + currentBudgetValue);
	        budgetDeductorAmountVerification(corpID);
	        nomineeAmountVerification(corpID);
	        
	        
	    }
	   
	    
	    
	        
	        
	        
	        
	        
	     
	       
	       // driver.get("https://www.advantageclub.co/login");
	        
	        // Use utility functions that use the global driver reference

	    
//	    @Parameters("corpID")
//	    @Test(priority = 3, enabled = true)
//	    public void approverFlow(String corpID) throws InterruptedException {
//	        setDriver(drivers.get(2));
//	        driver = getDriver();
//	       
//	      //input[@id='sig-email']
//	        
//	       // driver.get("https://development1.advantageclub.co;/pages/get_demo");
//	        // Use utility functions that use the global driver 																								reference																																																										
//	    }

	    @AfterMethod
	    public void afterMethod() {
	        // Any cleanup after each test method
	    }

	    @AfterClass
	    public void afterClass() {
//	        if (driver1 != null) {
//	            driver1.quit();
//	        }
//	        if (driver2 != null) {
//	            driver2.quit();
//	        }
	    }

}