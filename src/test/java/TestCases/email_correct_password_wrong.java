package TestCases;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.HomePage;

public class email_correct_password_wrong {
	public static  WebDriver driver;

	@BeforeTest
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		Reporter.log("Web browser is opened");
	}
	@Test
	public void testcase() throws Exception {
		driver.manage().window().maximize();
		Reporter.log("Browser is Maximized");
		driver.get("http://testingtaritas.anatomysuite.com/");
		Reporter.log("Navigating to the URL");

		HomePage testCases =new HomePage(driver);
		testCases.setEmailAdress("prankur.pandey@taritas.com");
		Reporter.log("Entering Email Id");
		testCases.setPassword("Prankur");
		Reporter.log("Entering Wrong Password");
		testCases.HitLogin();
		Reporter.log("Clicked on LogIn");
	}
	@AfterTest
	public void testdown() {
		driver.close();
		driver.quit();
	}
}
