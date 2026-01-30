package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.PopUpPage;
import pages.RegistrationPage;

import java.util.Random;
import static utils.UserFactory.*;


public class RegistrationTests extends ApplicationManager {
    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToRegistrationPage() {
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        User user = User.builder()
                .firstName("UUU")
                .lastName("PPP")
                .email("lmkjiu"+i+"@defrt.bhy")
                .password("Pqwerty453!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationPositiveTest_WithFaker() {
        User user = positiveUser();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver())
                .isTextInPopUpMessagePresent("You are logged in success"));
    }

    @Test
    public void registrationNegativeTest_WrongEmail_WOAtSymbol(){
        User user = User.builder()
                .firstName("AAA")
                .lastName("BBB")
                .email("wrongEmail2@gmail.com")
                .password("Password123!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBox();
        registrationPage.clickBtnYalla();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"));
        softAssert.assertAll();
    }

    @Test
    public void registrationNegativeTest_WrongPassword_WOUpperCase(){
        User user = User.builder()
                .firstName("AAA")
                .lastName("BBB")
                .email("wrongEmail2@gmail.com")
                .password("password123!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBox();
        registrationPage.clickBtnYalla();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain 1 uppercase letter"));
        softAssert.assertAll();

    }

    @Test
    public void registrationNegativeTest_UserAlreadyExists(){
        User user = User.builder()
                .firstName("uhifg")
                .lastName("dhfgsh")
                .email("123qwe@gmail.com")
                .password("123Qwerty!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("User already exists"));

    }
    @Test
    public void registrationNegativeTest_WithSpaceInFirstName(){
        User user = User.builder()
                .firstName(" ")
                .lastName("dhfgsh")
                .email("123qwe@gmail.com")
                .password("123Qwerty!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(new PopUpPage(getDriver()).isTextInPopUpMessagePresent("Must not be blank"));

    }
    @Test
    public void registrationNegativeTest_WithAllEmpty(){
        User user = User.builder()
                .firstName("")
                .lastName("")
                .email("")
                .password("")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Name is required"), "validate error message Name is required");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Last Name is required"), "validate error message Last Name is required");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Email is required"), "validate error message Email is required");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password is required"), "validate error message Password is required");
        softAssert.assertAll();


    }

}


