package base;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import utils.LoggerUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extentReports;
    protected ExtentTest extentTest;

    @BeforeSuite
    public void setupReporting() {
        extentReports = new ExtentReports();
        extentReports.attachReporter(new ExtentSparkReporter("reports/ExtentReport.html"));
    }

    @AfterSuite
    public void tearDownReporting() {
        extentReports.flush();
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        
        

        // Disable Chrome password manager and automation prompts
        ChromeOptions options = new ChromeOptions();

        // Disable automation extension and infobars
        //options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        //options.setExperimentalOption("useAutomationExtension", false);

        // Run in incognito to avoid saved credentials
        options.addArguments("--incognito");

        // Disable password manager prompts
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        LoggerUtil.initLogger();
        LoggerUtil.getLogger().info("Browser started");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            LoggerUtil.getLogger().info("Browser closed");
        }
    }
    
    public String takeScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + "/screenshots";
        String screenshotPath = screenshotDir + "/" + testName + "_" + timestamp + ".png";

        new File(screenshotDir).mkdirs();

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }
    
}
