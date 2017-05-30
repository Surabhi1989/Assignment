package main;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Webhelper {
	WebDriver driver = null;

	public boolean initmethod(String URL) {
		System.setProperty("webdriver.gecko.driver", "./lib/geckodriver");
		System.out.println("ready to open firefox");
		try {
			driver = new FirefoxDriver();
			driver.navigate().to(URL);
			driver.manage().window().maximize();
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("can not open firefox");
			e.printStackTrace();
			driver.close();
			return false;
		}

		System.out.println("URL got is " + URL);
		return true;
	}

	public boolean IsDisplayed(String Path, String Element) {
		WaitUntill(Path);
		if (driver.findElement(By.xpath(Path)).isDisplayed()) {
			return true;
		} else {
			System.out.println(Element + "was not present");
			return false;
		}
	}

	public void CloseBrowser() {
		driver.close();
		//driver.quit();

	}

	public boolean IsDisplayedById(String element) {
		if (driver.findElement(By.id(element)).isDisplayed()) {
			System.out.println("Element is present " + element);
			return true;
		} else {
			return false;
		}
	}
	
	public void WaitUntill(String element) {
		try{
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
		}catch(Exception e){
			e.printStackTrace();
		
		}

	}
	public String getText(String Element) {
		String text = driver.findElement(By.xpath(Element)).getText();
		System.out.println("Text on page throgh get text is: " + text);
		return text;
	}
	

	public void ClickbyXpath(String webElement) {
		driver.findElement(By.xpath(webElement)).click();
		System.out.println("inside webhelper click element" + webElement);
	}

	public void ClickbyId(String Id) {
			
		driver.findElement(By.id(Id)).click();

	}
	public void enterTextByxpath(String Path,String Value) {
		
		WebElement element = driver.findElement(By.xpath(Path));
		element.sendKeys(Value);
	}	
	
	public void HoverElement(String Path1, String Path){
	Actions action = new Actions(driver);
	WebElement mainMenu = driver.findElement(By.xpath(Path1));
	action.moveToElement(mainMenu).moveToElement(driver.findElement(By.xpath(Path))).click().build().perform();
	}
	public void Back() {
		
		driver.navigate().back();
		
	}
	public List<WebElement> findElementsbyXpath(String Path){
		List<WebElement> arr= driver.findElements(By.xpath(Path));
		return arr;
	}
}
