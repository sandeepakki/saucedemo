package saucedemo.swaglabs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseClass {
	
	// Creates selenium webdriver object
	public static WebDriver driver;

	
	// @Before Test will instantiate the chrome or firefox browser using browser executable plugins
	@BeforeTest
	public void setUp() {
 	String browser ="Chrome"; 
		if(browser.equals("Chrome"))
		{
			String path = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", path+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/"); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	 
	// @AfterTest will close all the browsers windows or sessions created
	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
