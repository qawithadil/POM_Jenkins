package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebConfig implements Constant {
	private  WebDriver driver = null;

	public WebConfig(WebDriver driver) {
		this.driver = driver;
	}

	public  WebDriver callBrowser() {

		if (Constant.browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", Constant.chrome_path);
			driver = new ChromeDriver();
		}

		else if (Constant.browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver", Constant.chrome_path);
			driver = new ChromeDriver();
		} else {
			System.out.println("****No Browser Found****");
		}

		return driver;

	}

}
