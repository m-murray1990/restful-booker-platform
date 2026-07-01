package pageobjects;

import com.microsoft.playwright.Page;

public class RoomPage extends BasePage {

    public RoomPage(Page page) {
        super(page);
    }

    public void populateFirstname(String firstname) {
        page.fill("#firstname", firstname);
    }

    public void populateLastname(String lastname) {
        page.fill("#lastname", lastname);
    }

    public void populateTotalPrice(String totalPrice) {
        page.fill("#totalprice", totalPrice);
    }

    public void clickCreateBooking() {
        page.click("#createBooking");
    }

    public int getBookingCount() {
        page.waitForSelector(".detail");
        return page.locator(".detail").count();
    }

    public void populateCheckin(String checkInDate) {
        page.locator(".checkin input").click();
        page.locator(".react-datepicker__day--001").click();
    }

    public void populateCheckout(String checkOutDate) {
        page.locator(".checkout input").click();
        page.locator(".react-datepicker__day--002").click();
    }
}
