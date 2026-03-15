package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

import static utils.PropertiesReader.*;

//public class HomePage extends BasePage {
//    public HomePage(WebDriver driver) {
//        setDriver(driver);
//        //driver.get("https://ilcarro.web.app/search");
//        driver.get(PropertiesReader.getProperty("base.properties", "baseUrl"));
//        PageFactory.initElements(new AjaxElementLocatorFactory
//                (driver, 10), this);
//    }
//
//    @FindBy(xpath = "//a[text()=' Log in ']")
//    WebElement btnLogin;
//    @FindBy(xpath = "//a[text()=' Sign up ']")
//    WebElement btnSignUp;
//
//    public void clickBtnLogin(){
//        btnLogin.click();
//    }
//
//    public void clickBtnSignUp(){
//        btnSignUp.click();
//    }

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        //driver.get("https://ilcarro.web.app/search");
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignUp;
    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnYearCalendar;

    public void clickBtnLogin() {
        btnLogin.click();
    }

    public void clickBtnSignUp() {
        btnSignUp.click();
    }

    public void typeSearchForm(String city,
                               LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        System.out.println(startDate.toString());
        String dates = startDate.getMonthValue() + "/"
                + startDate.getDayOfMonth() + "/"
                + startDate.getYear() + " - "
                + endDate.getMonthValue() + "/"
                + endDate.getDayOfMonth() + "/"
                + endDate.getYear();
        inputDates.sendKeys(dates);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute(\"disabled\")");
        //btnYalla.click();
        clickWait(btnYalla, 3);

    }

    public void clickBtnYalla() {
        clickWait(btnYalla, 3);
    }

    public void typeSearchFormWOJS(String city,
                                   LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        System.out.println(startDate.toString());
        String dates = startDate.getMonthValue() + "/"
                + startDate.getDayOfMonth() + "/"
                + startDate.getYear() + " - "
                + endDate.getMonthValue() + "/"
                + endDate.getDayOfMonth() + "/"
                + endDate.getYear();
        inputDates.sendKeys(dates);
    }

    private void typeCalendar(LocalDate date) {
        btnYearCalendar.click();
        //td[@aria-label='2026']
        String year = Integer.toString(date.getYear());
        WebElement btnYear = driver.findElement(By.
                xpath("//td[@aria-label='" + year + "']"));
        btnYear.click();

    }

    public void typeSearchFormWithCalendar
            (String city, LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        inputDates.click();
        typeCalendar(startDate);

    }

    public boolean isBtnYallaEnabled(){
        return btnYalla.isEnabled();
    }
}