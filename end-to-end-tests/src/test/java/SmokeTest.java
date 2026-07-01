import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobjects.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class SmokeTest extends TestSetup {

    @BeforeEach
    public void logIntoApplication() {
        navigateToApplication();

        LoginPage loginPage = new LoginPage(page);
        loginPage.populateUsername("admin");
        loginPage.populatePassword("password");
        loginPage.clickLogin();
    }

    @Test
    public void authSmokeTest() {
        RoomListingPage roomListingPage = new RoomListingPage(page);

        assertThat(roomListingPage.roomFormExists(), is(true));
    }

    @Test
    public void roomSmokeTest() {
        RoomListingPage roomListingPage = new RoomListingPage(page);
        int initialRoomCount = roomListingPage.roomCount();

        roomListingPage.populateRoomName("102");
        roomListingPage.setRoomPrice("100");
        roomListingPage.checkWifi();
        roomListingPage.checkSafe();
        roomListingPage.checkRadio();
        roomListingPage.clickCreateRoom();

        int currentRoomCount = roomListingPage.roomCount();

        assertThat(currentRoomCount, is(initialRoomCount + 1));
    }

    @Test
    public void bookingSmokeTest() {
        NavPage navPage = new NavPage(page);
        navPage.clickFrontPage();

        HomePage homePage = new HomePage(page);
        homePage.clickOpenBookingForm();

        ReservationPage reservationPage = new ReservationPage(page);
        assertThat(reservationPage.bookingFormExists(), is(true));
    }

    @Test
    public void reportSmokeTest() {
        NavPage navPage = new NavPage(page);
        navPage.clickReport();

        ReportPage reportPage = new ReportPage(page);

        assertThat(reportPage.reportExists(), is(true));
    }

    @Test
    public void brandingSmokeTest() {
        NavPage navPage = new NavPage(page);
        navPage.clickBranding();

        BrandingPage brandingPage = new BrandingPage(page);
        String nameValue = brandingPage.getNameValue();

        assertThat(nameValue.length(), greaterThan(0));
    }

    @Test
    public void messageSmokeTest() {
        NavPage navPage = new NavPage(page);
        navPage.clickNotification();

        MessagePage messagePage = new MessagePage(page);
        List<Locator> messages = messagePage.getMessages();

        assertThat(messages.size(), greaterThan(0));
    }

}
