package ui_tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestNGListener;

import java.time.LocalDate;
@Listeners(TestNGListener.class)

public class SearchCarTests extends ApplicationManager {
    HomePage homePage;

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate
                .of(2026, 3, 12);
        LocalDate endDate = LocalDate
                .of(2026, 3, 22);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYalla();
        Assert.assertTrue(homePage.urlContains
                ("results", 5));
    }

    @Test
    public void searchCarPositiveTestWithCalendar() {
        String city = "Rehovot";
        LocalDate startDate = LocalDate
                .of(2026, 3, 12);
        LocalDate endDate = LocalDate
                .of(2026, 3, 22);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        //homePage.clickBtnYalla();
        // Assert.assertTrue(homePage.urlContains
        //         ("results", 5));
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







}
