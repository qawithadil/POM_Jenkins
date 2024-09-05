package testCases;

import java.io.IOException;
import java.time.Duration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.WebConfig;
import utilities.Constant;
import utilities.ExcelUtil;
import utilities.ExtentManager;

public class Login extends ExcelUtil implements Constant {

	WebConfig conf;
	public WebDriver driver;

	@BeforeSuite
	public void setUp() {
		ExtentManager.setExtent();
		conf = new WebConfig(driver);
		driver = conf.callBrowser();
		System.out.println("Before Suite..");
	}

	@BeforeClass
	public void pageRedirect() {

		driver.get(Constant.baseURL);
		driver.manage().window().maximize();
	}

	@Test
	public void dataRead() {

		// Read Single Column Data
		int colNum = 0;
		String sheet = "Sheet1";
		readSingleColData(Constant.filPath, colNum, sheet);
		readSingleColData(Constant.filPath, colNum + 1, sheet);

		// Write Single Column Data

	}

	@Test(priority = 1)
	public void pageRedirection() {
		/*
		 * int i = 10; int j = 20; Assert.assertEquals(i, j,
		 * "Values are not matching...");
		 */

	}

	@Test(priority = 2)
	public void userLogin() {
		System.out.println("This is Test Function");
		int i = 10;
		int j = 10;
		Assert.assertEquals(i, j, "Values are Not MatchingMatching");

	}

	@Test(dataProvider = "loginData")
	public void testLoginData(String userName, String password) {
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String expected_result = "Invalid credentials";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']/div/p")));
		String actual_result = driver.findElement(By.xpath("//div[@role='alert']/div/p")).getText();
		Assert.assertEquals(actual_result, expected_result, "Login Error is not showing / matching");

	}

	@DataProvider(name = "loginData")
	public Object[][] testDataFeed() throws InvalidFormatException, IOException {

		int NumRows = getRowCount(Constant.filPath);
		int NumCols = getColCount(Constant.filPath);
		// int NumRows = 4;
		// int NumCols = 2;
		String Sheet = "Sheet1";

		System.out.println("Rows Count =" + NumRows + "  Columns Count = " + NumCols);
		Object[][] credentials = new Object[NumRows][NumCols];
		for (int i = 1; i <= NumRows; i++) {

			credentials[i - 1][0] = readSingleCell_Data(Constant.filPath, Sheet, i, 0);
			credentials[i - 1][1] = readSingleCell_Data(Constant.filPath, Sheet, i, 1);
		}

		return credentials;

	}

	@AfterSuite
	public void tearDown() {
		System.out.println("After Suite");
		ExtentManager.endReport();
		driver.close();
	}

}
