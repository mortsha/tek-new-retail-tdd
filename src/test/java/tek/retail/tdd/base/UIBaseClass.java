package tek.retail.tdd.base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import tek.retail.tdd.pages.POMDesignPattern;
import tek.retail.tdd.utility.SeleniumUtility;
@Listeners({ExtentITestListenerClassAdapter.class})
public class UIBaseClass extends SeleniumUtility {

    private static final Logger LOGGER = LogManager.getLogger(UIBaseClass.class);
    public POMDesignPattern pattern = new POMDesignPattern();


    @BeforeMethod
    public void setupTest() {
        LOGGER.info("Setup test and opening browser");
        setupBrowser();
    }

    @AfterMethod
    public void closeTest(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE){
            TakesScreenshot screenshot = (TakesScreenshot)  getDriver();
            String shot = screenshot.getScreenshotAs(OutputType.BASE64);

            ExtentTestManager.getTest().fail("Test failed taking screen shot", MediaEntityBuilder.createScreenCaptureFromBase64String(shot).build());
        }

        LOGGER.info("after each test and quite the browser");
        quitBrowser();
    }
}
