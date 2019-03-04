package testingNg.testng;

import org.openqa.selenium.By;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public final class App {
    
    public static void main(String[] args) {
    	
        System.setProperty("webdriver.chrome.driver", "/home/qainfotech/Eclipse Workspace New Data And Version/selenium/chromedriver");
  
        WebDriver driver = new ChromeDriver();
        
        driver.get("https://www.google.com");
        
        WebElement  searchbox = driver.findElement(By.name("q"));

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the query");
        String query =sc.nextLine();
        searchbox.sendKeys(query);
        
        searchbox.submit();
        
        WebElement data=driver.findElement(By.className("st"));
        String result=data.getText().toLowerCase();
        data=driver.findElement(By.className("LC20lb"));
        result+=data.getText().toLowerCase();
        
        
        System.out.println(result.contains(query.toLowerCase()));
        
        driver.close();
        
        
	
    }
}
