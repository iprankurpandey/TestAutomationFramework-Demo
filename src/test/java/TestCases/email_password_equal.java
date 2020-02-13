package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.HomePage;

public class email_password_equal {
	public static  WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;

	@BeforeTest
	public void setup() {
		htmlReporter = new ExtentHtmlReporter("extent.html");


		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// creates a toggle for the given test, adds all log events under it    
		ExtentTest test = extent.createTest("MyFirstTest", "Sample description");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Test
	public void testcase() throws Exception {

		driver.get("http://testingtaritas.anatomysuite.com/");
		driver.manage().window().maximize();

		HomePage testCases =new HomePage(driver);
		ExtentTest test1 =extent.createTest("This is FIRST test case");
		test1.log(Status.INFO, "ENTER email IN BOX ");
		testCases.setEmailAdress("prankur.pandey@taritas.com");
		test1.fail("EMAIL ENTERED ");

		ExtentTest test2 =extent.createTest("This is SECOND test case");
		test2.log(Status.INFO, "ENTER Password IN BOX ");
		testCases.setPassword("Prankur@1");
		test2.pass("EMAIL ENTERED ");

		testCases.HitLogin();
	}

	@AfterTest
	public void testdown() {
		extent.flush();
		driver.close();
		driver.quit();
	}
}
