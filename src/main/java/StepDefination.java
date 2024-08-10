import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefination extends BaseTest{

public LandingPage landingPage;
public ProductCatalog productCatalog;
    @Given("I land on page")
    public void I_land_on_page() throws IOException {

        landingPage =launchApplication();
    }


    @Given("^Login with name (.+) and password  (.+)$")
    public void Login_with_username_password(String name , String password) throws IOException {

        productCatalog =landingPage.loginApplication(name,password);
    }

@When("^I add <productname> to the cart$")
public void add_product_to_cart(String product) throws IOException, InterruptedException {

   productCatalog.AddItemToCart(product);
    List<WebElement> items = productCatalog.getItems();

    // Print items
    for (WebElement item : items) {
        System.out.println(item.getText());
    }
}

@And("^I checkout <productname> and submit the order$")
public void checkout_and_submit_the_order(String product) throws IOException, InterruptedException {

    CartPage page = productCatalog.goToCartPage();
//    Boolean match = page.VerifyProductDisplay(input.get("productname"));
//    Assert.assertTrue(match, "Product not found in the cart");

    CheckOutPage checkoutpage = page.goToCheckoutPage();
    checkoutpage.selectCountry("India");
    Thread.sleep(3000); // Consider using WebDriverWait instead

    ConfirmationPage confirmationPage = checkoutpage.SubmitOrder();
    String ConfirmationMessage = confirmationPage.getConfirmationMessage();
    System.out.println(ConfirmationMessage);

}


@Then("I verify name is displayed on the screen")
    public void verify_order(String name) throws InterruptedException {
    CartPage page = productCatalog.goToCartPage();
    CheckOutPage checkoutpage = page.goToCheckoutPage();
    checkoutpage.selectCountry("India");
    Thread.sleep(3000); // Consider using WebDriverWait instead
    ConfirmationPage confirmationPage = checkoutpage.SubmitOrder();
    String ConfirmationMessage = confirmationPage.getConfirmationMessage();
    // Assert confirmation message
    String actualMessage = "THANKYOU FOR THE ORDER.";
    Assert.assertEquals(ConfirmationMessage, actualMessage, "Confirmation message did not match");




}



}

