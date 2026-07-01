import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobjects.HomePage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class AvailabilityTest extends TestSetup {

    @BeforeEach
    public void navigateToHomePage() {
        if (System.getenv("TARGET") != null && System.getenv("TARGET").equals("production")) {
            page.navigate("https://automationintesting.online");
        } else {
            page.navigate("http://localhost:3003");
        }
    }

    @Test
    public void availabilitySectionDisplaysOnLandingPage() {
        HomePage homePage = new HomePage(page);
        assertThat(homePage.availabilitySectionExists(), is(true));
    }

    @Test
    public void checkInDatePickerIsVisibleOnLandingPage() {
        HomePage homePage = new HomePage(page);
        assertThat(homePage.checkInDatePickerExists(), is(true));
    }

    @Test
    public void checkOutDatePickerIsVisibleOnLandingPage() {
        HomePage homePage = new HomePage(page);
        assertThat(homePage.checkOutDatePickerExists(), is(true));
    }

    @Test
    public void roomsAreDisplayedAfterCheckingAvailability() {
        HomePage homePage = new HomePage(page);
        homePage.setCheckInDate("27/06/2026");
        homePage.setCheckOutDate("29/06/2026");
        homePage.clickCheckAvailability();

        assertThat(homePage.availableRoomCount(), is(greaterThan(0)));
    }

    @Test
    public void bookNowLinkContainsSelectedCheckinDate() {
        HomePage homePage = new HomePage(page);
        homePage.setCheckInDate("27/06/2026");
        homePage.setCheckOutDate("29/06/2026");
        homePage.clickCheckAvailability();

        String bookNowHref = homePage.getFirstRoomBookNowLink();
        assertThat(bookNowHref, containsString("checkin="));
    }

    @Test
    public void bookNowLinkContainsSelectedCheckoutDate() {
        HomePage homePage = new HomePage(page);
        homePage.setCheckInDate("27/06/2026");
        homePage.setCheckOutDate("29/06/2026");
        homePage.clickCheckAvailability();

        String bookNowHref = homePage.getFirstRoomBookNowLink();
        assertThat(bookNowHref, containsString("checkout="));
    }
}
