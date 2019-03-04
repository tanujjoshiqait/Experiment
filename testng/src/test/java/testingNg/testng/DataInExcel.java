package testingNg.testng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.*;

public class DataInExcel {
	private WebDriver driver;
	private List<WebElement> data;
	private List<String> staticData = new ArrayList<String>();
	private List<String> webData = new ArrayList<String>();

	// Create Instance of web driver
	@BeforeTest
	public void createInstance() {

		System.setProperty("webdriver.chrome.driver",
				"/home/qainfotech/Eclipse Workspace New Data And Version/selenium/chromedriver");

		driver = new ChromeDriver();

		driver.get("http://10.0.14.57:9292/");

	}

	// Fetching data from the file
	@Test
	@DataProvider(name = "authentication")
	public void dataProvider() throws Exception {

		String line;

		File athenticationData = new File("/home/qainfotech/Eclipse Workspace New Data And Version/testng/src/test/Resource/data.xlsx");

		FileInputStream fis = new FileInputStream(athenticationData);

		// we create an XSSF Workbook object for our XLSX Excel File
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// we get first sheet
		XSSFSheet sheet = workbook.getSheetAt(0);

		// we iterate on rows
		Iterator<Row> rowIt = sheet.iterator();

		while (rowIt.hasNext()) {
			Row row = rowIt.next();

			// iterate on cells for the current row
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				staticData.add(cellIterator.toString());
				Cell cell = cellIterator.next();
				System.out.print(cell.toString() + ";");
			}
			
			/*
			 * BufferedReader br = new BufferedReader(new FileReader(athenticationData));
			 * 
			 * while ((line = br.readLine()) != null) {
			 * 
			 * staticData.add(line); }
			 * 
			 * br.close();
			 */
			 Assert.assertEquals(staticData.size(), 39);
		}

	}

	@Test
	public void validateResult() {

		data = driver.findElements(By.cssSelector("#content > ul > li"));

		System.out.println(data.size());

		for (WebElement text : data) {
			webData.add(text.getText());
		}
		System.out.println("Static Data = "+staticData);
		System.out.println("Web Data = "+webData);

		
		for (int index = 0; index < staticData.size(); index++) {
			Assert.assertEquals(webData.get(index).equals(staticData.get(index)), true);
		}

	//	Assert.assertEquals(data.size(), 39);

	}

	// Web driver close
	@AfterTest
	public void closeConnection() {

		driver.close();
	}
}
