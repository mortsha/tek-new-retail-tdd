package tek.retail.tdd.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tek.retail.tdd.base.BaseSetup;

import java.time.Duration;

public class SeleniumUtility extends BaseSetup {
    private static final Logger LOGGER = LogManager.getLogger(SeleniumUtility.class);

    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME_SECONDS));
    }
    private WebElement waitTillVisible(WebElement element){
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }
    private WebElement waitToBeClickable(WebElement element){
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean isElementDisplayed(By locator) {
        LOGGER.debug("Checking element {} display status ", locator);
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }
    public boolean isElementDisplayed(WebElement element) {
        LOGGER.debug("Checking element {} display status ", element);
        return waitTillVisible(element).isDisplayed();
    }
    public boolean isElementEnabled(WebElement element) {
        LOGGER.debug("Checking element {} Enable status ", element);
        return waitTillVisible(element).isEnabled();
    }


    public String getElementText(By locator) {
        LOGGER.debug("Getting text of locator {}", locator);
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public String getElementText(WebElement element) {
        LOGGER.debug("Getting text of element {}", element);
        return waitTillVisible(element).getText();
    }
    public void clickOnElement(WebElement element){
        LOGGER.debug("Clicking on element {}",element);
        waitToBeClickable(element).click();
    }
}
