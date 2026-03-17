package ui_tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.enums.FooterMenuItem;

public class NavigationTests extends ApplicationManager {
    @Test(groups = "smoke")
    public void iconFacebookNavigationTest(){
        Assert.assertTrue(new HomePage(getDriver()).clickIconFooter
                (FooterMenuItem.ICON_FACEBOOK, "Facebook"));
    }
}
