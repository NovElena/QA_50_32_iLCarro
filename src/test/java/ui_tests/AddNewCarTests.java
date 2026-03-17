package ui_tests;

import dto.Car;
import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import pages.PopUpPage;
import utils.CarFactory;
import utils.enums.HeaderMenuItem;

import static utils.PropertiesReader.getProperty;

public class AddNewCarTests extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod(alwaysRun = true)
    public void openLetTheCarWorkPage() {
        User user = User.builder()
                .email(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        homePage = new HomePage(getDriver());
        loginPage = homePage.clickButtonHeader
                (HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        loginPage.clickBtnOk();
        homePage.pause(2);
        letTheCarWorkPage = homePage.clickButtonHeader
                (HeaderMenuItem.LET_THE_CAR_WORK);

    }

    @Test(groups = "smoke")
    public void addNewCarPositiveTest() {
        Car car = CarFactory.positiveCar();
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.typeImage("cat2.jpg");
        homePage.pause(3);
        letTheCarWorkPage.clickBtnSubmit();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("must not be blank"));
    }
}

