package tek.retail.tdd.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tek.retail.tdd.pages.POMDesignPattern;
import tek.retail.tdd.utility.SeleniumUtility;

public class UIBaseClass extends SeleniumUtility {

    private static final Logger LOGGER = LogManager.getLogger(UIBaseClass.class);
    public POMDesignPattern pattern = new POMDesignPattern();


    @BeforeMethod
    public void setupTest() {
        LOGGER.info("Setup test and opening browser");
        setupBrowser();
    }

    @AfterMethod
    public void closeTest() {
        LOGGER.info("after each test and quite the browser");
        quitBrowser();
    }
}
