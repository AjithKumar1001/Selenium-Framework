package PracticeAutomation.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PracticeAutomation.TestComponents.BaseTest;
import PracticeAutomation.pageObejcts.CartPage;
import PracticeAutomation.pageObejcts.LandingPage;
import PracticeAutomation.pageObejcts.ProductCatalogue;
import PracticeAutomation.pageObejcts.checkOutPage;
import PracticeAutomation.pageObejcts.confirmationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public confirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue = landing.loginApplication(username, password);
	}
	
	@When("^I add the product (.+) to cart$")
	public void i_add_the_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addproductToCart(productName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductInCart(productName);
		Assert.assertTrue(match);
		checkOutPage checkOut = cartPage.checkOut();
		checkOut.selectCountry("Ind");
		confirmationPage = checkOut.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string)
	{
		String message = confirmationPage.verifyConfirmationMessage();
		Assert.assertEquals(message, string);
		driver.close();
	}
	
	
	//Then "Incorrect email or password." message is displayed 
	@Then("{string} message is displayed")
	public void message_is_displayed(String string)
	{
		Assert.assertEquals(string, landing.getErrorMessage());
		driver.close();
	}

}
