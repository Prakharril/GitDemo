import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class OrderPage1 extends AbstractComponents{
    WebDriver driver;
    @FindBy (css=".totalRow button")
    WebElement Checkoutele;

    @FindBy (css="tr td:nth-child(3)")
    private List<WebElement> productTitles;

    public OrderPage1(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }



    public boolean VerifyProductDisplay(String productName){
        Boolean match = productTitles.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;


    }

    public CheckOutPage goToCheckoutPage(){
        Checkoutele.click();
        return new CheckOutPage(driver);



    }




}
