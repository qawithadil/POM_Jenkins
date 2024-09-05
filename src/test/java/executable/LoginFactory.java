package executable;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.configuration.Config;

import pages.LoginPage;
import pages.LoginPageFactory;
import utilities.ExtentManager;
import utilities.Listeners;
import utilities.WebConfig;

@org.testng.annotations.Listeners(Listeners.class)
public class LoginFactory {
	private WebDriver driver;
	private LoginPageFactory login;
	WebConfig wconf;

	@BeforeTest
	public void setUp() {
		ExtentManager.setExtent();
		wconf = new WebConfig(driver);
		driver = wconf.callBrowser();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		login = new LoginPageFactory(driver);

	}

	@Test
	public void login() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		login.enterUsername("Admin");
		login.enterpassword("123");
		login.clickLoginButton();

	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
