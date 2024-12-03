package PracticeAutomation.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import PracticeAutomation.TestComponents.BaseTest;
import PracticeAutomation.TestComponents.Retry;
import PracticeAutomation.pageObejcts.CartPage;
import PracticeAutomation.pageObejcts.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void errorValidationTest() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";

		landing.loginApplication("ajithkumar63@gmail.com", "Ajith@123");
		// landing.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landing.getErrorMessage());
		// div[@class='ng-tns-c4-7 ng-star-inserted ng-trigger ng-trigger-flyInOut
		// ngx-toastr toast-error']
		// .ng-tns-c4-8.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		//// div[@aria-label='Incorrect email or password.']
	}

	@Test
	public void OrderPageTest() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landing.loginApplication("mukeshk@gmail.com", "Mukesh@1235");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addproductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductInCart("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
