package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private WebDriver driver;

	// Locators

	private By userNameField = By.name("username");
	private By passwordfield = By.name("password");
	private By loginButton = By.xpath("//button[@type='submit']");

	// Constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions

	public void enterUsername(String userName) {

		WebElement element = driver.findElement(userNameField);
		System.out.println("Element:="+element);
		element.sendKeys(userName);
	}

	public void enterpassword(String password) {

		WebElement element = driver.findElement(passwordfield);
		element.sendKeys(password);
	}

	public void clickLoginButton() {

		WebElement element = driver.findElement(loginButton);
		element.click();
	}

}
