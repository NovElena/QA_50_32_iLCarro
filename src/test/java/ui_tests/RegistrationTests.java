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
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Last name is required"), "validate error message Last Name is required");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Email is required"), "validate error message Email is required");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password is required"), "validate error message Password is required");

    }
    @Test  //new
    public void registrationNegativeTest_WihNonEnglishLetters(){
        User user = User.builder()
                .firstName("Аaa")
                .lastName("בבב")
                .email("123йцу@пьфшд.сщь")
                .password("123קראט!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.setCheckBoxAgreeTermsOfUse();
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Name must contain only english letters"),
                "error message: Name contains non-English letters, no error appeared");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Last name must contain only English letters"),
                "error message: Last name contains non-English letters, no error appeared");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong Email format"),
                "error message: wrong Email format for non-English letters, message is duplicated on UI!");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain 1 uppercase letter, 1 lowercase letter," +
                        " 1 number and one special symbol of [@$#^&*!]"),
                "error message: wrong Password format for non-English letters, Hebrew characters caused text reversal in the field");

    }
    @Test  //new
    public void registrationNegativeTest_FieldsWithLeadingAndMiddleSpaces(){
        User user = User.builder()
                .firstName(" Aaa")
                .lastName(" Bbb")
                .email("123qwe @gmail.com")
                .password("123 Qwerty!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong Name format"),
                "error message: field Name contains Space, error not appeared");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong Last name format"),
                "error message: field Last name contains Space, error not appeared");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Wrong email format"),
                "error message: Email contains space before @, message is duplicate on UI!");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain 1 uppercase letter, 1 lowercase letter," +
                " 1 number and one special symbol of [@$#^&*!]"), "error message: Password contains space, error not appeared");

    }
    @Test  //new
    public void registrationNegativeTest_NameWithNumbersAndSymbols(){
        User user = User.builder()
                .firstName("Aaa123")
                .lastName("Bbb#@!")
                .email("123qwe@gmail.com")
                .password("123Qwerty!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Name must contain only english letters"),
                "error message: Name with numbers is accept, error not appeared");
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Last name must contain only english letters"),
                "error message: Last name with symbols is accept, error not appeared");

    }
    @Test  //new
    public void registrationNegativeTest_ShortPassword() {
        User user = User.builder()
                .firstName("AAA123")
                .lastName("BBB#@!")
                .email("123qwe@gmail.com")
                .password("12Qwe!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain minimum 8 symbols"),
                "error message: Password contains less than 8 characters");

    }
    @Test  //new
    public void registrationNegativeTest_PasswordWithOnlyUpperCase() {
        User user = User.builder()
                .firstName("AAA123")
                .lastName("BBB#@!")
                .email("123qwe@gmail.com")
                .password("123QWERTY!")
                .build();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckBoxWithActions();
        registrationPage.clickBtnYalla();
        softAssert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain 1 lowercase letter"),
                "error message: Password not contains lowercase letters");
        softAssert.assertAll();
    }

}


