package PracticeAutomation.pageObejcts;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PracticeAutomation.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> cartProducts = driver.findElements(By.cssSelector("[class='cartSection'] h3"));

	@FindBy(css = "[class='cartSection'] h3")
	List<WebElement> cartTitles;

	// driver.findElement(By.cssSelector("ul
	// button[class*='btn-primary']:last-of-type")).click();
	@FindBy(css = "ul button[class*='btn-primary']:last-of-type")
	WebElement checkOut;

//	Boolean match = cartProducts.stream()
//			.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
//	Assert.assertTrue(match);

	public Boolean verifyProductInCart(String productName) {
		Boolean match = cartTitles.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public checkOutPage checkOut() {
		checkOut.click();
		return new checkOutPage(driver);
	}

}
