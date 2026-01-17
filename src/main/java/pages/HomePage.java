package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage{
    public HomePage (WebDriver driver){
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()='Log in']")
    WebElement btnLogin;

    public void clickBtnLogin(){
        btnLogin.click();
    }

    @FindBy(xpath = "//input[@id='email']")
    WebElement fieldEmail;

    public void fillFieldEmail(){
        fieldEmail.click();
        fieldEmail.sendKeys("123qwe!@gmail.com");
        pause(2);
    }

    @FindBy(xpath = "//input[@id='password']" )
    WebElement fieldPassword;

    public void fillFieldPassword(){
        fieldPassword.click();
        fieldPassword.sendKeys("123Qwerty!#");
        pause(2);
    }

    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;

    public void clickBtnYalla(){
        btnYalla.click();
        pause(2);
    }

    @FindBy(xpath = "//button[@class='positive-button ng-star-inserted']")
    WebElement btnOk;

    public void clickBtnOk(){
        btnOk.click();
        pause(2);
    }

    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    WebElement btnLogout;

    public void clickBtnLogout(){
        btnLogout.click();

    }

}
