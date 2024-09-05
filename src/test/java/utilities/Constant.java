package utilities;

import java.sql.Driver;

public interface Constant {
	
	String filPath=System.getProperty("user.dir")+"\\src\\test\\resources\\data\\userLogin.xlsx";
	String browser="Chrome";
	String chrome_path = System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";
	String baseURL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
}
