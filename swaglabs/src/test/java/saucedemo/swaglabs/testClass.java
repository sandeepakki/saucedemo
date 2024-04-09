package saucedemo.swaglabs;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


//test class inherits base class to open or close browser before every @Test file is executed
public class testClass extends BaseClass {
	
	
	// @Test contains the test scripts that needs to be automatically executed
	
	@Test
	public void testSwaglabs() throws InterruptedException {
		
		/* implicitly wait is a global wait used to match execution speed with the application loading speed */
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// fetched the current URL of the page
		String url = driver.getCurrentUrl();
		// Validates whether current URL and expected URL are same or not
		Assert.assertEquals(url, "https://www.saucedemo.com/");
		// driver waits explictly to validate the visibility of username component in UI
		WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
		WaitForElement(username,10);
		username.sendKeys("standard_user");
		// driver waits explictly to validate the visibility of password component in UI and sends input
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		WaitForElement(password,10);
		password.sendKeys("secret_sauce");
		// The execution stops for 1second using sleep method
		Thread.sleep(1000);
		// driver waits explictly to validate the visibility of login component in UI
		WebElement Login = driver.findElement(By.xpath("//input[@id='login-button']"));
		WaitForElement(Login,10);
		Login.click();
		
		// locates the webelement and perform click action
		WebElement product = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
		product.click();
		
		// locates the product description and fetches text from webelement
		String pdescription = driver.findElement(By.xpath("//div[@data-test='inventory-item-desc']")).getText();
		System.out.println(pdescription);
		
		// locates and fetches the text from item price
		String pprice = driver.findElement(By.xpath("//div[@data-test='inventory-item-price']")).getText();
		Assert.assertEquals(pprice,"$29.99");
		
		//driver waits explictly to validate the visibility of addtoCart component in UI and clicks
		WebElement addtoCart = driver.findElement(By.xpath("//button[@id='add-to-cart']"));
		WaitForElement(addtoCart,10);
		addtoCart.click();
		
	//	driver waits explictly to validate the visibility of cart component in UI and clicks
		WebElement cart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
		WaitForElement(cart,10);
		cart.click();
		
		WebElement checkout = driver.findElement(By.cssSelector("button#checkout"));
		WaitForElement(checkout,10);
		checkout.click();
		
		WebElement fn = driver.findElement(By.cssSelector("input#first-name"));
		WaitForElement(fn,10);
		fn.sendKeys("sandeep");
		
		WebElement ln = driver.findElement(By.cssSelector("input#last-name"));
		WaitForElement(ln,10);
		ln.sendKeys("akki");
	
		WebElement pc = driver.findElement(By.cssSelector("input#postal-code"));
		WaitForElement(pc,10);
		pc.sendKeys("500001");
		
		WebElement contnu = driver.findElement(By.cssSelector("input#continue"));
		WaitForElement(contnu,10);
		contnu.click();
		
		String subtotal = driver.findElement(By.cssSelector("div.summary_subtotal_label")).getText();
		System.out.println(subtotal);
		
		String tax = driver.findElement(By.cssSelector("div.summary_tax_label")).getText();
		System.out.println(tax);
		
		
		/* splits the subtotal at $ sign into array and converts string to float value and 
		using the array index 1 we can add the total price */
		
        String[] subpart = subtotal.split("\\$");
        float value1 = Float.parseFloat(subpart[1]);
        System.out.println(value1);
        String[] taxpart = tax.split("\\$");
        float value2 = Float.parseFloat(taxpart[1]);
        System.out.println(value2);
        float total = value1+value2;
        
        // DecimalFormat method rounds off the decimal value at 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        String finalPrice = df.format(total);
        // validates final price is matching with expected value
        Assert.assertEquals(finalPrice,"32.39");
        
        WebElement finish = driver.findElement(By.cssSelector("button#finish"));
        WaitForElement(finish,10);
        finish.click();
       
        String thankumsg = driver.findElement(By.cssSelector("h2.complete-header")).getText();
        Assert.assertEquals(thankumsg,"Thank you for your order!");

		
	}
	
	// Method to reuse the explicit wait condition in script
	public void WaitForElement(WebElement element,long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}

}
