import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;
import java.time.Duration;
public class ProductCatalog extends AbstractComponents {

    @FindBy(css = ".mb-3")
     List<WebElement> items;



    private By productsBy = By.cssSelector(".mb-3");
    private By addtocart = By.cssSelector(".card-body button:last-of-type");
    By toastContainer = By.cssSelector("#toast-container");
    By disappearToastContainer = By.cssSelector(".ng-animating");

    // Constructor
    public ProductCatalog(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getItems() {
        waitForElementToAppear(productsBy);
        return items;
    }


        public WebElement getItemByName(String productName) {


            WebElement prod = getItems().stream()
                .filter(item -> item.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
            return prod;


    }

    public void AddItemToCart(String productName) throws InterruptedException {
WebElement prod = getItemByName(productName);
prod.findElement(addtocart).click();
waitForElementToAppear(toastContainer);

waitForElementToDisappear();
    }

}
