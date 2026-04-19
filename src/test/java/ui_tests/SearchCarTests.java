package ui_tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import utils.TestNGListener;

import java.time.LocalDate;

@Listeners(TestNGListener.class)

public class SearchCarTests extends ApplicationManager {
    SoftAssert softAssert = new SoftAssert();
    HomePage homePage;

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(7);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.urlContains
                ("results", 5));
    }

    @Test
    public void searchCarPositiveTestWithCalendar() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.now().plusDays(3);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.urlContains
                ("results", 5));
    }

    @Test(expectedExceptions = org.openqa.selenium.
            TimeoutException.class)
    public void searchCarNegativeTest_EmptyFieldCity() {
        String city = "";
        LocalDate startDate = LocalDate
                .of(2026, 3, 12);
        LocalDate endDate = LocalDate
                .of(2026, 3, 22);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        homePage.clickBtnYalla();
    }

    @Test
    public void searchCarNegativeTest_EmptyFieldCityValidateError() {
        String city = "";
        LocalDate startDate = LocalDate
                .of(2026, 3, 12);
        LocalDate endDate = LocalDate
                .of(2026, 3, 22);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        Assert.assertTrue(homePage
                .isTextInErrorPresent("City is required"));
    }

    @Test
    public void searchCarNegativeTest_EndDateBeforeStartDate() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2026,2,20);
        LocalDate endDate = LocalDate.of(2026,2,18);
        homePage.typeSearchFormWOJS(city, startDate, endDate);
        Assert.assertFalse(homePage.urlContains("results", 3));
    }

    @Test
    public void searchNegativeTest_EndAndStartDateInThePast() {
        String city = "Rehovot";;
        LocalDate startDate = LocalDate.of(2026, 2, 10);
        LocalDate endDate = LocalDate.of(2026, 2, 12);
        homePage.typeSearchFormWOJS(city,startDate,endDate);
        Assert.assertFalse(homePage.isBtnYallaEnabled());
    }

    @Test
    public void searchNegativeTest_StartDateAndEndDateTheSame(){
        String city = "Rehovot";
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = LocalDate.now().plusDays(1);
        homePage.typeSearchFormWOJS(city,startDate,endDate);
        Assert.assertFalse(homePage.urlContains("results",3));
    }
    @Test
    public void searchCarNegativeTest_StartDateSameEndDate() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        softAssert.assertTrue(homePage.isTextInErrorPresent("City is required"),
                "validate message City is required");
        softAssert.assertTrue(homePage.isTextInErrorPresent("You can't book car for less than a day"),
                "validate message You can't book car for less than a day");
        softAssert.assertAll();
    }
    @Test
    public void searchCarNegativeTest_StartDateBeforeToday() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now();
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        softAssert.assertTrue(homePage.isTextInErrorPresent("City is required"),
                "validate message City is required");
        softAssert.assertTrue(homePage.isTextInErrorPresent("You can't pick date before today"),
                "validate message You can't pick date before today");
        softAssert.assertAll();
    }
    @Test
    public void searchCarNegativeTest_StartDateAfterEndDate() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(8);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        softAssert.assertTrue(homePage.isTextInErrorPresent("City is required"),
                "validate message City is required");
        softAssert.assertTrue(homePage.isTextInErrorPresent("Second date must be after first date"),
                "validate message Second date must be after first date");
        softAssert.assertAll();

    }
    @Test(expectedExceptions = java.time.DateTimeException.class)
    public void searchCarNegativeTest_DateNotValid() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate.of(2026, 2, 30);
        LocalDate endDate = LocalDate.of(2026, 2, 22);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();

    }







}
