package tek.retail.tdd.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tek.retail.tdd.base.UIBaseClass;

public class BaseSetupTest extends UIBaseClass {

    @Test
    public void validateLogo() {

        boolean isLogoDisplayed = isElementDisplayed(pattern.getHomePage().logoImage);
        Assert.assertTrue(isLogoDisplayed, "Logo should be displayed");

        boolean isCartDisplayed = isElementEnabled(pattern.getHomePage().cartButton);
        Assert.assertTrue(isCartDisplayed,"Cart button should be displayed");

        String actualSignInText = getElementText(pattern.getHomePage().signInLink);
        Assert.assertEquals(actualSignInText,"Sign in","sign in button should match");

    }

}
