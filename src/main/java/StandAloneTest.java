import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class StandAloneTest extends BaseTest {

    // Use the product name for testing
    String productname = "ZARA COAT 3";

    // Define the path for the screenshot
    String screenshotPath = "C:\\Users\\lenovo-pc\\OneDrive\\Desktop\\screenshot.png";

    @Test(dataProvider = "getData", groups = "purchase")
    public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
        // Initialize page objects and perform actions
        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> items = productCatalog.getItems();

        // Print items
        for (WebElement item : items) {
            System.out.println(item.getText());
        }

        productCatalog.AddItemToCart(input.get("productname"));

        CartPage page = productCatalog.goToCartPage();
        Boolean match = page.VerifyProductDisplay(input.get("productname"));
        Assert.assertTrue(match, "Product not found in the cart");

        CheckOutPage checkoutpage = page.goToCheckoutPage();
        checkoutpage.selectCountry("India");
        Thread.sleep(3000); // Consider using WebDriverWait instead

        ConfirmationPage confirmationPage = checkoutpage.SubmitOrder();
        String ConfirmationMessage = confirmationPage.getConfirmationMessage();
        System.out.println(ConfirmationMessage);

        // Assert confirmation message
        String actualMessage = "THANKYOU FOR THE ORDER.";
        Assert.assertEquals(ConfirmationMessage, actualMessage, "Confirmation message did not match");

        // Take screenshot
        takeScreenshot(driver, screenshotPath);

        System.out.println("Test Passed");


    }

    @Test(dependsOnMethods = "SubmitOrder")
    public void orderHistoryTest() {
        ProductCatalog productCatalog = landingPage.loginApplication("prakhar900000@gmail.com", "Prakhar@1004");
        OrderPage1 orderpage = productCatalog.goToOrdersPage();
        Assert.assertTrue(orderpage.VerifyProductDisplay(productname));
    }

    public void takeScreenshot(WebDriver driver, String filePath) {
        // Cast the driver to TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Capture screenshot and store it in a file
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            // Save the screenshot to the specified file path
            File destinationFile = new File(filePath);
            FileUtils.copyFile(sourceFile, destinationFile);
            System.out.println("Screenshot saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save screenshot.");
        }
    }

    @DataProvider
    public Object[][] getData() {
        GetJsonData jsonDataReader = new GetJsonData();
        jsonDataReader.readJsonData();
        List<HashMap<String, String>> dataList = jsonDataReader.getJsonAsHashMap();

        // Convert List<HashMap<String, String>> to Object[][] for DataProvider
        if (dataList != null) {
            Object[][] dataArray = new Object[dataList.size()][1];
            for (int i = 0; i < dataList.size(); i++) {
                dataArray[i][0] = new HashMap<>(dataList.get(i));
            }
            return dataArray;
        } else {
            return new Object[0][0]; // Return empty array if data is null
        }
    }
}
