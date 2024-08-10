import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentTestListener.class)
public class ExtentReportTest {

    WebDriver driver;

    @BeforeClass
    public void config() {
        // Initialize WebDriver
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Specify the path to chromedriver
        driver = new ChromeDriver();
    }

    @Test
    public void demo() {
        // Start logging the test
        ExtentTestListener.getTest().info("Starting demo test");

        // Navigate to URL and perform actions
        driver.get("https://openai.com/index/chatgpt/");

        // Log test steps
        ExtentTestListener.getTest().info("Navigating to OpenAI");
        String title = driver.getTitle();
        ExtentTestListener.getTest().info("Title is: " + title);
        System.out.println(title);

        // Intentional failure for screenshot capture
        String actualMessage = "THANKYOU FOR THE ORDER.";
        Assert.assertEquals(title, actualMessage, "Confirmation message did not match");
    }

    @AfterClass
    public void tearDown() {
        // Quit driver
        if (driver != null) {
            driver.quit();
        }
    }
}
