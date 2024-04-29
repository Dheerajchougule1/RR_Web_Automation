package my_profile;

import org.testng.annotations.Test;
import org.testng.annotations.Parameters; // Correct import statement for Parameters annotation

import utility_RR.Utility_RR;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;

public class my_favourites extends Utility_RR {
    
    @Parameters("corpID")
    @BeforeClass
    private void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
         
          startBrowser(DataRunScript(2, 1));
          newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
    }

    @BeforeMethod
    public void beforeMethod() throws EncryptedDocumentException, IOException, InterruptedException {
        driver.get(DataRunScript(1, 1)+"in/pages/your_favourites");
        importWait();
        Thread.sleep(2000);
    }
    
    private List<String> favouriteVendors = new ArrayList<>();
   private List<String> favouritesList = new ArrayList<>();
    private String str1 = "No Favourites yet!\n" + "Tap on the 🤍 follow your favourites.";
//    @Parameters("corpID")
//    @Test(priority=3,enabled=true)
//    public void checkScroll(String corpID) throws InterruptedException, EncryptedDocumentException, IOException {
//    	driver.get(DataRunScript(1, 1)+"in/pages/your_favourites");
//    	int count = 0;
//    	while(count<3) {
//    		verticalScroll();
//    		count++;
//    	}
//    	if(count==0) {
//    		System.out.println("Scroll functionality not working");
//    	}
//    	
//    	else {
//    		 System.out.println("Scroll functionality verified");
//    		 }
//    }
    
  @Parameters("corpID")
  @Test(priority=3,enabled=true)
  public void ScrollIntoView_by_webelement() throws InterruptedException, EncryptedDocumentException, IOException, AWTException {
	
  	try {
          // Navigate to the desired page
  	   	List<WebElement> elements = driver.findElements(By.xpath("//div[@id='vendors']//div[@class='flex justify-between flex-col vendor-content']"));

          WebElement lastElement = elements.get(elements.size() - 1);
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lastElement);
          Thread.sleep(1000);
          String LastFavouriteVendor = favouriteVendors.get(favouriteVendors.size()-1);
          String lastElementText = lastElement.getText();
          System.out.print(lastElementText);
          System.out.print(LastFavouriteVendor);
          
          if (LastFavouriteVendor.equals(lastElementText)) {
              System.out.println("Scroll functionality verified");
          } else {
              System.out.println("Scroll functionality not working");
          }
      } catch (NoSuchElementException e) {
          System.out.println("Scroll functionality not working");   
      }
  	
  }

    
    @Parameters("corpID")
    @Test(priority=2,enabled=true)
    public void add_favourites(String corpID) throws EncryptedDocumentException, IOException, InterruptedException {
    	driver.get(DataRunScript(1, 1)+"in/pages/sections?section_id=3");
        for (int i = 0; i < 6; i++){
            // Construct XPath for the current vendor using index (1-based)
            String xpath = "(//div[@id='vendors']/div)[" + (i + 1) + "]";
            
            // Find the vendor element
            WebElement vendor = driver.findElement(By.xpath(xpath));
            
            // Click on the favorite icon for the current vendor
            WebElement favouriteIcon = vendor.findElement(By.xpath(".//div[@class='rounded-full px-4 py-4 bg-white']"));
            favouriteIcon.click();
            Thread.sleep(1000);            
           
             WebElement vendorNameElement = vendor.findElement(By.xpath(".//div[@class=\"p2 font-semibold w-3/5 whitespace-nowrap overflow-hidden overflow-ellipsis\"]"));
             String vendorName = vendorNameElement.getText();
             favouriteVendors.add(vendorName);
        }
        //driver.get(DataRunScript(1, 1) + "in/pages/your_favourites");
        
        System.out.println( favouriteVendors); 
        driver.get(DataRunScript(1, 1)+"in/pages/your_favourites");
        
    	
   	
   	 System.out.println("testing entries "); 
   	List<WebElement> vendorcontainer = driver.findElements(By.xpath("//div[@id='vendors']//div[@class='flex justify-between flex-col vendor-content']"));
   	for (int i = 0; i < vendorcontainer.size(); i++){
        // Construct XPath for the current vendor using index (1-based)
        String xpath = "(//div[@id='vendors']/div)[" + (i + 1) + "]";
        
        // Find the vendor element
        WebElement vendor = driver.findElement(By.xpath(xpath));
                  
       
         WebElement vendorNameElement = vendor.findElement(By.xpath(".//div[@class=\"p2 font-semibold w-3/5 whitespace-nowrap overflow-hidden overflow-ellipsis\"]"));
         String vendorName = vendorNameElement.getText();
         favouritesList.add(vendorName);
    }
        
        System.out.println(favouritesList);
       // System.out.println(favouriteVendors);
        if(favouritesList.containsAll(favouriteVendors) && favouriteVendors.containsAll(favouritesList)) {
       	 System.out.println("Favourites verified ");
       	 
        }
        else {
       	 System.out.println("List not verified");
        }
  
   }
        

    
    
    @Parameters("corpID")
    @Test(priority=4,enabled=true)
    public void remove_favourites(String corpID) throws EncryptedDocumentException, IOException, InterruptedException, AWTException {
       // driver.get(DataRunScript(1, 1) + "in/pages/your_favourites");

        if (!areFavoritesPresent()) {
            System.out.println("No favourites found. Test stopped.");
            return; // Stop the function if no favorites are found
        }

        removeFavorites();
        driver.get(DataRunScript(1, 1) + "in/pages/your_favourites");

        if (!areFavoritesPresent()) {
            System.out.println("No favourites found. Test completed");
            return; // Stop the function if no favorites are found after removal
        }

        // Recursive call to remove favorites if still present after initial removal
        remove_favourites(corpID);
        
    }

//    private void zoomInPage() throws AWTException {
//        Robot robot = new Robot();
//        for (int i = 0; i < 5; i++) {
//            robot.keyPress(KeyEvent.VK_CONTROL);
//            robot.keyPress(KeyEvent.VK_ADD);
//            robot.keyRelease(KeyEvent.VK_ADD);
//            robot.keyRelease(KeyEvent.VK_CONTROL);
//        }
//    }
//
//    private void zoomOutPage() throws AWTException {
//        Robot robot = new Robot();
//        for (int i = 0; i < 5; i++) {
//            robot.keyPress(KeyEvent.VK_CONTROL);
//            robot.keyPress(KeyEvent.VK_SUBTRACT);
//            robot.keyRelease(KeyEvent.VK_SUBTRACT);
//            robot.keyRelease(KeyEvent.VK_CONTROL);
//        }
//    }

    private boolean areFavoritesPresent() {
        WebElement emptyMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='vendors']")));
        String str1 = "No Favorites yet!\n" + "Tap on the 🤍 follow your favorites.";
        String message = emptyMessage.getText();
        return !message.equals(str1);
    }

    private void removeFavorites() throws InterruptedException {
        List<WebElement> vendorContainer = driver.findElements(By.xpath("//div[@id='vendors']//div[@class='flex justify-between flex-col vendor-content']"));
        for (WebElement vendor : vendorContainer) {
            WebElement favouriteIcon = driver.findElement(By.xpath(".//div[@class='rounded-full px-4 py-4 bg-white']"));
            Thread.sleep(1000);
            favouriteIcon.click();
        }
    }
   
   
    
    
    @SuppressWarnings("unused")
	@Parameters("corpID")
    @Test(priority=1,enabled=true)
    public void check_favourites(String corpID) throws EncryptedDocumentException, IOException, InterruptedException, AWTException {
        WebElement empty_message = null;
        empty_message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='vendors']")));
        // No favorites found message is displayed, indicating no favorites are present
        String message = empty_message.getText();
      //  System.out.print(message);
      
        if (message.equals(str1)) {
           // System.out.println("No favorites found");
            // Add favorites
        	System.out.print(message);
        	
           // add_favourites(corpID);
        }
        else {
        	//System.out.print("Favorites found ");
        remove_favourites(corpID);
       
        }
        
    }


 
    
    @AfterMethod
    public void afterMethod() {
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
}

//package my_profile;
//
//import org.testng.annotations.Test;
//
//import com.beust.jcommander.Parameters;
//
//import utility_RR.Utility_RR;
//
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//
//import java.awt.AWTException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import org.apache.poi.EncryptedDocumentException;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.testng.annotations.AfterClass;
//
//public class my_favourites extends Utility_RR {
//	
//	@Parameters("corpID")
//	@BeforeClass
//	private void beforeClass(String corpID) throws EncryptedDocumentException, InterruptedException, IOException, AWTException {
//		 
//		  startBrowser(DataRunScript(2, 1));
//		  newui_login(corpID,DataAppriciateFlow(corpID, 3, 1), DataAppriciateFlow(corpID, 3, 2));
//	}
//
//	@BeforeMethod
//	public void beforeMethod() throws EncryptedDocumentException, IOException, InterruptedException {
//		driver.get(DataRunScript(1, 1)+"in/pages/your_favourites");
//	  	importWait();
//	  	Thread.sleep(2000);
//	}
//	
//	private List<String> favouriteVendors = new ArrayList<>();
//
//	@Parameters("corpID")
//	@Test(priority=1,enabled=true)
//
//  public void check_favourites(String corpID) throws EncryptedDocumentException, IOException {
//		@SuppressWarnings("unused")
//		WebElement empty_message = null;
//        try {
//            empty_message= driver.findElement(By.xpath("//*[@id='vendors']/div/h3"));
//            add_favourites(corpID); 
//        } catch (org.openqa.selenium.NoSuchElementException e) {
//            remove_favourites(corpID);
//            add_favourites(corpID);
//            
//        }
//		
//  }
//	
//	public void add_favourites(String corpID) throws EncryptedDocumentException, IOException {
//		
//		
//		driver.get(DataRunScript(1, 1)+"in/pages/sections?section_id=3");
//		WebElement vendorContainer = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/div[5]"));
//		int vendorCount = 0;
//		for(WebElement vendor : vendorContainer.findElements(By.tagName("div"))) {
//			if(vendorCount>=10) {
//				break;
//			}
//			WebElement vendorNameElement = vendor.findElement(By.className("vendor-name-rating-container")).findElement(By.tagName("div"));
//			WebElement favourite_icon = driver.findElement(By.xpath(".//div[@class='rounded-full px-4 py-4 bg-white']"));
//			favourite_icon.click();
//			String vendorName = vendorNameElement.getText();
//			favouriteVendors.add(vendorName);
//			
//			vendorCount++;
//		}
//		
//		
//	}
//	@SuppressWarnings("finally")
//	public void remove_favourites(String corpID) throws EncryptedDocumentException, IOException {
//		//List<String> favouriteVendors = new ArrayList<>();
//		
//		//driver.get(DataRunScript(1, 1)+"in/pages/sections?section_id=3");
//		WebElement vendorContainer = driver.findElement(By.xpath("//*[@id=\"vendors\"]"));
//		 int vendorCount = vendorContainer.findElements(By.tagName("div")).size();
//		for(WebElement vendor : vendorContainer.findElements(By.tagName("div"))) {
//			if(vendorCount==0) {
//				break;
//			}
//			//add try catch and work on getting vendor count from frontend 
//			try {
//				//WebElement vendorNameElement = vendor.findElement(By.className("vendor-name-rating-container")).findElement(By.tagName("div"));
//				WebElement favourite_icon = driver.findElement(By.xpath(".//div[@class='rounded-full px-4 py-4 bg-white']"));
//				favourite_icon.click();
//	        } catch (NoSuchElementException e) {
//	           // Handle NoSuchElementException if the elements are not found
//	            System.out.println("No more favourites . Exiting...");
//	        } finally {
//	            // Close the WebDriver
//	            break;
//	        }
//	    }
//			
//			vendorCount--;
//		}
//		
//		
//
//  
//  @AfterMethod
//  public void afterMethod() {
//  }
//
//  
//
//  @AfterClass
//  public void afterClass() {
//	  driver.quit();
//  }
//
//
//}
