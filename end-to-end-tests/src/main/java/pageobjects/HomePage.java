package pageobjects;

import com.microsoft.playwright.Page;

public class HomePage extends BasePage {

    public HomePage(Page page) {
        super(page);
    }

    public void clickOpenBookingForm() {
        page.locator(".display-5").first().scrollIntoViewIfNeeded();
        page.locator(".room-card a").first().click();
    }

    public void clickSubmitBooking() {
        page.click(".btn-outline-primary.book-room");
    }

    public Boolean bookingFormErrorsExist() {
        return page.locator(".alert-danger").isVisible();
    }

    public Boolean availabilitySectionExists() {
        return page.locator("#booking").isVisible();
    }

    public Boolean checkInDatePickerExists() {
        return page.locator("label:has-text('Check In')").isVisible();
    }

    public Boolean checkOutDatePickerExists() {
        return page.locator("label:has-text('Check Out')").isVisible();
    }

    public void setCheckInDate(String date) {
        page.locator(".dateWrapper input").first().fill(date);
    }

    public void setCheckOutDate(String date) {
        page.locator(".dateWrapper input").last().fill(date);
    }

    public void clickCheckAvailability() {
        page.locator("button:has-text('Check Availability')").click();
    }

    public int availableRoomCount() {
        page.locator("#rooms .room-card").first().waitFor();
        return page.locator("#rooms .room-card").count();
    }

    public String getFirstRoomBookNowLink() {
        return page.locator("#rooms .room-card a.btn").first().getAttribute("href");
    }
}
