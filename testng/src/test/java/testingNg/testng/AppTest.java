package testingNg.testng;

import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

public class AppTest {

	private WebDriver driver;

	// Create Instance of web driver
	@Parameters("browser")
	@BeforeTest
	public void createInstance(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"/home/qainfotech/Eclipse Workspace New Data And Version/testng/chromedriver");
	
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {

			System.out.println("Before launch");
			driver = new FirefoxDriver();
			System.out.println("After launch");

		}

		driver.get("https://www.google.com");

	}

	// evaluate the search results
	public void validateResult(String query, WebDriver driver) {

		WebElement data = driver.findElement(By.className("st"));

		String result = data.getText().toLowerCase();

		data = driver.findElement(By.className("LC20lb"));

		result += data.getText().toLowerCase();

		Assert.assertEquals(result.contains(query.toLowerCase()), true);

	}

	// INsert Query on the search box and search start
	@Test
	public void queryInsertion() throws Exception {

		WebElement searchbox = driver.findElement(By.name("q"));

		
		/*
		 * Scanner sc = new Scanner(System.in); System.out.println("Enter the query");
		 * 
		 * String query = sc.nextLine(); sc.close();
		 */
		String query = "qa infotech";
		searchbox.sendKeys(query);
		searchbox.submit();
		
		Thread.sleep(5000);
		validateResult(query, driver);
	}

	// Web driver close
	@AfterTest
	public void closeConnection() {

		driver.close();
	}
}
