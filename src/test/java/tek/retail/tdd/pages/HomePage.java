package tek.retail.tdd.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tek.retail.tdd.utility.SeleniumUtility;

public class HomePage extends SeleniumUtility {

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    @FindBy(xpath = "//img[@alt='LOGO']")
    public WebElement logoImage;

    @FindBy(linkText = "Sign in")
    public WebElement signInLink;

    @FindBy(xpath = "//span[contains(@class,'anticon-shopping-cart')]//parent::span//parent::button")
    public WebElement cartButton;
}
