package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageFactory {

	private WebDriver driver;

	// Locators with page factory

	@FindBy(name = "username")
	private WebElement userNamefield;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement login;

	// Constructor

	public LoginPageFactory(WebDriver driver) {
		this.driver = driver;
	}

	// Actions

	public void enterUsername(String userName) {

		userNamefield.sendKeys(userName);

	}

	public void enterpassword(String password) {

		passwordField.sendKeys(password);
	}

	public void clickLoginButton() {

		login.click();
	}

}
