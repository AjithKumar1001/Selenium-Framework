package PracticeAutomation.pageObejcts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PracticeAutomation.AbstractComponents.AbstractComponents;

public class confirmationPage extends AbstractComponents {

	WebDriver driver;

	public confirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	String message = driver.findElement(By.tagName("h1")).getText();
//	Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");

	@FindBy(tagName = "h1")
	WebElement message;

	public String verifyConfirmationMessage() {

		return message.getText();
	}

}
