package PracticeAutomation.pageObejcts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PracticeAutomation.AbstractComponents.AbstractComponents;

public class checkOutPage extends AbstractComponents {

	WebDriver driver;

	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	Actions action = new Actions(driver);
//	action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-results']")));
//	driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//	driver.findElement(By.cssSelector("[class*='action__submit']")).click();

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryName;

	@FindBy(css = "[class*='action__submit']")
	WebElement submit;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	// By.cssSelector("[class*='ta-results']
	By results = By.cssSelector("[class*='ta-results']");

	public void selectCountry(String countryname) {
		Actions action = new Actions(driver);
		action.sendKeys(countryName, countryname).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}

	public confirmationPage submitOrder() {
		submit.click();
		return new confirmationPage(driver);
	}

}
