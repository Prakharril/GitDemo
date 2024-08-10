import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPage extends AbstractComponents {

    @FindBy(xpath = "//a[.='Place Order ']")
    WebElement Submit;

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement Country;

    @FindBy(xpath = "//body//app-root//button[2]")
    WebElement SelectCountry;

    By results = By.cssSelector(".ta-results");

    public CheckOutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(String CountryName) {
        Actions a = new Actions(driver);
        a.sendKeys(Country, CountryName).build().perform();
        waitForElementToAppear(results);
        SelectCountry.click();
    }

    public ConfirmationPage SubmitOrder() {
        try {
            // Scroll to the submit button if needed
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Submit);

            // Wait for submit button to be clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.elementToBeClickable(Submit));

            // Click on the submit button
            Submit.click();

            // Return a new instance of ConfirmationPage
            return new ConfirmationPage(driver);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle or rethrow the exception as needed
        }
    }
}
