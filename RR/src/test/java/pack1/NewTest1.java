package pack1;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import utility_RR.Utility_RR;

public class NewTest1 extends Utility_RR {
  @Test
  public void f() throws EncryptedDocumentException, InterruptedException, IOException {
	  
	  startBrowser(DataRunScript(2, 1));
	  
  }
}
