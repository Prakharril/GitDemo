import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver InitializeDriver() throws IOException {
        // Load properties file
        Properties pro = new Properties();
        FileInputStream file = new FileInputStream("C:\\Users\\lenovo-pc\\IdeaProjects\\NewMavenProject\\GlobalData.properties");
        pro.load(file);

        // Read browser name from properties
        String browsername = pro.getProperty("browser");

        // Initialize WebDriver based on browser name
        if(browsername.equalsIgnoreCase("chrome")) {
            //System.setProperty("webdriver.chrome.driver", "path_to_your_chromedriver_executable"); // Set path to your chromedriver executable
            driver = new ChromeDriver();  // Initialize ChromeDriver instance
        } else if (browsername.equalsIgnoreCase("firefox")) {
            System.out.println("Firefox browser is not implemented yet.");  // Placeholder message
        } else {
            throw new IllegalArgumentException("Browser value in properties file is not supported: " + browsername);
        }

        // Set common settings for WebDriver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // Implicit wait
        driver.manage().window().maximize();  // Maximize window

        return driver;
    }
@BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        // Initialize driver
        driver = InitializeDriver();

        // Proceed to launch application or perform other initialization steps
        landingPage = new LandingPage(driver);
        landingPage.goTo();  // Example method to navigate to landing page

        return landingPage;
    }

    // Example of how to use this BaseTest class

}
