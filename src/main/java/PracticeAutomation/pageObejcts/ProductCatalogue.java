package PracticeAutomation.pageObejcts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PracticeAutomation.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	// driver.findElement(By.cssSelector(".ng-animating"))
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	// By.cssSelector(".mb-3"))
	By productBy = By.cssSelector(".mb-3");

	// By.cssSelector(".card-body button:last-of-type")
	By addToCart = By.cssSelector(".card-body button:last-of-type");

	// By.cssSelector("#toast-container")
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
	}

	public WebElement getProductName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		return prod;

	}

	public void addproductToCart(String productName) throws InterruptedException {
		// prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		WebElement prod = getProductName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}

}
