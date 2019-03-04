package testingNg.testng;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class TestExperiment {
	private WebDriver driver;
	private String query;
	private String result;

	public void NavigateTo(String url) {

		driver.navigate().to(url);
	}

	public void NavigateBack() {

		driver.navigate().back();
	}

	// Create Instance of web driver
	@BeforeTest
	public void createInstance() {

		System.setProperty("webdriver.chrome.driver",
				"/home/qainfotech/Eclipse Workspace New Data And Version/selenium/chromedriver");

		driver = new ChromeDriver();

		driver.get("https://www.google.com");

	}

	// INsert Query on the search box and search start
	@Test
	public void queryInsertion() {

		WebElement searchbox = driver.findElement(By.name("q"));

		/*
		 * Scanner sc = new Scanner(System.in); System.out.println("Enter the query");
		 * 
		 * query = sc.nextLine();
		 */
		query= "qa infotech";
		searchbox.sendKeys(query);
		searchbox.submit();
	}

	// evaluate the search results
	@Test
	public void validateResult() throws InterruptedException {

		List<WebElement> data = driver.findElements(By.className("iUh30"));
		
		System.out.println(data.size());
		
		List<String> text =new ArrayList<String>();
		
		int count=1;

		for (WebElement link : data) {
			text.add(link.getText());
		}
		
		for (String temp : text) {
			
			System.out.println(count++);
			
			NavigateTo(temp);

//			Thread.sleep(5000);
			
			String subtext=driver.findElement(By.tagName("body")).getText().toLowerCase();
						
//			System.out.println(subtext);
			
			Assert.assertEquals(subtext.contains(query), true);
			 
	        NavigateBack();

		}

		/*
		 * result = data.getText();
		 * 
		 * data = driver.findElement(By.className("LC20lb"));
		 * 
		 * result += data.getText().toLowerCase();
		 * 
		 * Assert.assertEquals(result.contains(query.toLowerCase()), true);
		 */
	}

	// Web driver close
	@AfterSuite
	public void closeConnection() {

		driver.close();
	}
}
