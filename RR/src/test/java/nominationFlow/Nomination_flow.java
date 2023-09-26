package nominationFlow;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility_RR.Utility_RR;

public class Nomination_flow extends Utility_RR {
  
  @Parameters("corpID")
  @Test
  public void nominateSessionCreation(String corpID) throws EncryptedDocumentException, IOException, InterruptedException, AWTException {
	  
	  int j=1;
	  int k=1;
	  for(int i=1;i<=DataNominationFlowNum(corpID, 4, 1).intValue(); i++) {	
		  
		  	System.setProperty("webdriver.edge.driver","C:\\Dheeraj C_Old\\Dheeraj C\\Setup\\edgedriver_win64\\msedgedriver.exe");
			//WebDriverManager.edgedriver().setup();
			
			EdgeOptions options = new EdgeOptions();
			options.addArguments("inprivate");
			driver = new EdgeDriver(options);
			driver.get(DataRunScript(20, 1));
			driver.manage().window().maximize();
			Thread.sleep(2000);
			
			login(corpID,DataNominationFlow(corpID, 6, j) , DataNominationFlow(corpID, 7, k));
			
			j++;
			k++;
	  
	  }
	  
	  
  }
}
