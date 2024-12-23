package PracticeAutomation.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PracticeAutomation.pageObejcts.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landing = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("ajithkumar63@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ajith@1235");
		driver.findElement(By.cssSelector("input[name='login']")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector("[class='cartSection'] h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector("ul button[class*='btn-primary']:last-of-type")).click();

		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-results']")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector("[class*='action__submit']")).click();

		String message = driver.findElement(By.tagName("h1")).getText();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");

		driver.close();

	}

}
