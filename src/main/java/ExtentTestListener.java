import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.io.File;

public class ExtentTestListener implements ITestListener {

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private ExtentReports extent;
    private WebDriver driver; // Reference to WebDriver

    @Override
    public void onStart(ITestContext context) {
        // Create ExtentReports instance and attach reporter
        extent = new ExtentReports();
        String path = "C:\\Users\\lenovo-pc\\IdeaProjects\\NewMavenProject\\reports\\spark.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        spark.config().setReportName("Web Automation Results");
        spark.config().setDocumentTitle("Test Results");
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Prakhar Tiwari");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
        // Initialize WebDriver here if required, or retrieve from your setup method
       // driver = WebDriverManager.getDriver(); // Example method to get WebDriver instance
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }
    @Override
    @Test
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.fail(result.getThrowable());

            // Capture screenshot only if driver is initialized
            if (driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File sourceFile = ts.getScreenshotAs(OutputType.FILE);

                // Define the path for the screenshot
                String screenshotDir = "C:\\Users\\lenovo-pc\\IdeaProjects\\NewMavenProject\\reports\\screenshots\\";
                String filePath = screenshotDir + result.getMethod().getMethodName() + ".png";

                try {
                    // Ensure the directory exists
                    File screenshotDirFile = new File(screenshotDir);
                    if (!screenshotDirFile.exists()) {
                        boolean created = screenshotDirFile.mkdirs();
                        System.out.println("Directory created: " + created);
                    } else {
                        System.out.println("Directory already exists.");
                    }

                    // Save the screenshot
                    File destinationFile = new File(filePath);
                    FileUtils.copyFile(sourceFile, destinationFile);
                    System.out.println("Screenshot saved to: " + filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the report
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
