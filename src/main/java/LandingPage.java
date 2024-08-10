import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents{

    WebDriver driver;

    // Constructor
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElement declarations using @FindBy
    @FindBy(css = "input[formcontrolname='userEmail']")
    WebElement userEmail;

    @FindBy(css = "input[formcontrolname='userPassword']")
    WebElement passwordEle;

    @FindBy(css = "input[id='login']")
    WebElement login;

//    @FindBy(css = "div[aria-label='Incorrect email or password.']")
//    WebElement ErrorMessage;


    public ProductCatalog loginApplication(String email , String password ){

        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        login.click();
        ProductCatalog productCatalog = new ProductCatalog(driver);
        return productCatalog;
    }

//    public String getErrorMessage(){
//waitForWebElementToAppear(ErrorMessage);
//       return ErrorMessage.getText();
//    }

    public String getErrorMessage() {
        WebElement errorElement = waitForWebElementToAppear(By.cssSelector("div[aria-label='Incorrect email or password.']"));
        return errorElement.getText();
    }





    public void goTo(){

        driver.get("https://rahulshettyacademy.com/client/");

    }
}
