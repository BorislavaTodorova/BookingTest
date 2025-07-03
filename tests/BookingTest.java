package tests;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

public class BookingTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.booking.com");
    }

    @Test
    public void testHotelBookingFlow() throws Exception {
        HomePage homePage = new HomePage(driver);
        homePage.setDestination("Sofia");
        homePage.selectDates();
        homePage.setGuests(2);
        homePage.submitSearch();

        SearchResultsPage searchResults = new SearchResultsPage(driver);
        searchResults.applyFilters();
        searchResults.selectThirdHotel();

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        HotelPage hotelPage = new HotelPage(driver);
        hotelPage.selectLowestPricedRoom();

        ReservationPage reservationPage = new ReservationPage(driver);
        reservationPage.fillGuestDetails();

        PaymentPage paymentPage = new PaymentPage(driver);
        Assert.assertTrue("Payment section is not visible", paymentPage.isPaymentVisible());
        paymentPage.takeScreenshot("payment_page.png");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
