import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponents{

    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);




    }

    @FindBy (css=".hero-primary")
    WebElement ConfirmationMesaage;


    public String getConfirmationMessage(){
       return ConfirmationMesaage.getText();


    }
}
