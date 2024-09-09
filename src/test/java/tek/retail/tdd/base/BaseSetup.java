package tek.retail.tdd.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

/*
   Read config file
   Open browser
   quit browser
   Encapsulate instance of WebDriver
   */
public class BaseSetup {


    private static final Logger LOGGER = LogManager.getLogger(BaseSetup.class);
    protected static final long WAIT_TIME_SECONDS = 15;

    private static WebDriver driver;
    private final Properties properties;

    public BaseSetup() {
        String configFilePath = System.getProperty("user.dir") + "/src/test/resources/configs/dev-config.properties";
        try {
            LOGGER.debug("Reading config file from path {}", configFilePath);
            InputStream inputStream = new FileInputStream(configFilePath);
            properties = new Properties();
            properties.load(inputStream);

        } catch (IOException e) {
            LOGGER.error("Config file error with message {} ", e.getMessage());
            throw new RuntimeException("Config file error with message" + e.getMessage());
        }
    }

    public void setupBrowser() {
        String url = properties.getProperty("ui.url");
        String browserType = properties.getProperty("ui.browser");
        boolean isHeadless = Boolean.parseBoolean(properties.getProperty("ui.browser.headless"));
        LOGGER.info("Opening on {} browser and headless {}", browserType, isHeadless);

        if (browserType.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browserType.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        } else if (browserType.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (isHeadless) options.addArguments("--headless");
            driver = new EdgeDriver(options);
        } else {
            throw new RuntimeException("Wrong browser type, choose between chrome, firefox and edge");
        }

        LOGGER.info("Navigating to url {}", url);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_TIME_SECONDS));
    }

    public void quitBrowser() {
        if (driver != null) {
            LOGGER.info("Quitting the browser");
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
