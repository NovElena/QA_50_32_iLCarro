package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.PopUpPage;

public class LoginTests extends ApplicationManager {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .email("123qwe@gmail.com")
                .password("123Qwerty!")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
    }

    @Test
    public void loginPositiveTest_WithPopUpPage(){
        User user = User.builder()
                .email("123qwe@gmail.com")
                .password("123Qwerty!")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Logged in success"));
    }

    @Test
    public void loginNegativeTest_WrongPassword_WOSpecSymbol(){
        User user = User.builder()
                .email("123qwe@gmail.com")
                .password("123Qwerty")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Login or Password incorrect"));
    }

    @Test
    public void loginNegativeTest_WrongEmail_WOAt(){
        User user = User.builder()
                .email("123qwegmail.com")
                .password("123Qwerty!")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("Login or Password incorrect"));
    }

    @Test  //new
    public void loginNegativeTest_WrongEmail_Empty() {
        User user = User.builder()
                .email("")
                .password("123Qwerty!")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isTextInErrorPresent
                ("Email is required"), "validate field email");
        softAssert.assertAll();
    }

    @Test  //new
    public void loginNegativeTest_WrongEmailAndPassword_Empty() {
        User user = User.builder()
                .email("")
                .password("")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isTextInErrorPresent
                ("Email is required"), "validate field email");
        System.out.println("wrong text!!");
        softAssert.assertTrue(loginPage.isTextInErrorPresent
                ("Password is required"), "validate field password");
        softAssert.assertAll();
    }
    @Test //new
    public void loginNegativeTest_WrongPassword_Empty(){
        User user = User.builder()
                .email("123qwe@gmail.com")
                .password("")
                .build();
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertTrue(loginPage.isTextInErrorPresent
                ("Password is required"), "validate field password");
        softAssert.assertAll();
    }
}