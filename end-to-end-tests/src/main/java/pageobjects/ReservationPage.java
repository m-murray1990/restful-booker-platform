package pageobjects;

import com.microsoft.playwright.Page;

public class ReservationPage extends BasePage {

    public ReservationPage(Page page) {
        super(page);
    }

    public Boolean bookingFormExists() {
        return page.locator("#doReservation").isVisible();
    }
}
