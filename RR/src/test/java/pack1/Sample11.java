package pack1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Sample11 {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
	
FileInputStream files = new FileInputStream("example.xlsx");
org.apache.poi.ss.usermodel.Sheet sheett = WorkbookFactory.create(files).getSheet("Sheet1");

String value = sheett.getRow(3).getCell(0).getStringCellValue();
System.out.println(value);

}
}


