package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	WebDriver driver;
	By Email = By.id("Username");
	By Password = By.id("Password");
	By Login = By.id("Login");

	public HomePage(WebDriver driver) {
		this.driver= driver;
	} 

	public void setEmailAdress(String text) {
		driver.findElement(Email).sendKeys(text);
	}

	public void setPassword(String text) {
		driver.findElement(Password).sendKeys(text);
	}
	public void HitLogin() {
		driver.findElement(Login).click();
	}

}


