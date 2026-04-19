package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//h2[text()='Logged in success']")
    WebElement popUpSuccessfulLogin;
    @FindBy(xpath = "//button[text()='Ok']")
    WebElement buttonOk;

    public void clickBtnOk() {
        clickWait(buttonOk, 3);
    }

    public void typeLoginForm(User user) {
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }

    public String getEmailValidationMessage() {
        return inputEmail.getAttribute("validationMessage");
    }

    public boolean isLoggedInDisplayed() {
        return isElementDisplayed(popUpSuccessfulLogin);
    }
}
