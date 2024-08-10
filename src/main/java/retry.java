import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;


public class retry implements IRetryAnalyzer {


    int count = 0;
    int maxtry=1;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if(count<maxtry){

count++;
return true;
        }
        return false;
    }
}
