
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;


import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test(groups = "errorhandling")
    public void SubmitOrder() throws IOException, InterruptedException {



        landingPage.loginApplication("prakhar900000@gmail.com", "Prakhar@100");// Perform login
        landingPage.getErrorMessage();
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());


        // Uncomment the following lines once methods are implemented in ProductCatalog class
        // productCatalog.addProductToCart(productname);  // Add specific product to cart
        // productCatalog.ScrollandOverLayandCartButtonClick();  // Scroll, handle overlay, and click cart button

        // Close the browser session
        // driver.quit(); // Make sure to quit the driver to close the browser
    }


}
