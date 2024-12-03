package PracticeAutomation.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PracticeAutomation.TestComponents.BaseTest;
import PracticeAutomation.pageObejcts.CartPage;
import PracticeAutomation.pageObejcts.OrderPage;
import PracticeAutomation.pageObejcts.ProductCatalogue;
import PracticeAutomation.pageObejcts.checkOutPage;
import PracticeAutomation.pageObejcts.confirmationPage;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrderTest(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landing.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addproductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductInCart(input.get("product"));
		Assert.assertTrue(match);
		checkOutPage checkOut = cartPage.checkOut();
		checkOut.selectCountry("Ind");
		confirmationPage confirmationPage = checkOut.submitOrder();
		String message = confirmationPage.verifyConfirmationMessage();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
	}

	@Test(dependsOnMethods = { "submitOrderTest" })
	public void OrderHistoryCheckTest() {
		ProductCatalogue productCatalogue = landing.loginApplication("ajithkumar63@gmail.com", "Ajith@1235");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// Manual creation of HashMap
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "ajithkumar63@gmail.com");
//		map.put("password", "Ajith@1235");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "mukeshk@gmail.com");
//		map1.put("password", "Mukesh@1235");
//		map1.put("product", "ADIDAS ORIGINAL");

		// By json creation
		List<HashMap<String, String>> data = getjsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\PracticeAutomation\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	// Previous method
	// @DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][] {{"ajithkumar63@gmail.com","Ajith@1235","ZARA COAT 3"},{"mukeshk@gmail.com","Mukesh@1235","ADIDAS ORIGINAL"}}
//	}

}
