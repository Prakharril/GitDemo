import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class AbstractComponents {
    WebDriver driver;
     WebDriverWait wait;

    @FindBy(css="[routerlink*='cart']")
    WebElement cartReader;

    @FindBy(css="[routerlink*='myorders']")
    WebElement myorder;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait with a default timeout
    }

    public void waitForElementToAppear(By findBy) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public WebElement waitForWebElementToAppear(By findBy) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToDisappear() throws InterruptedException {
   Thread.sleep(1000);
    }

    public CartPage goToCartPage() {
    cartReader.click();
        CartPage page = new CartPage(driver);
        return page ;

    }

    public OrderPage1 goToOrdersPage() {
        myorder.click();
        OrderPage1 page = new OrderPage1(driver);
        return page ;

    }
}
