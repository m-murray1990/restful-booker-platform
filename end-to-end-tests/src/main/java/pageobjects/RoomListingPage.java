package pageobjects;

import com.microsoft.playwright.Page;

public class RoomListingPage extends BasePage {

    public RoomListingPage(Page page) {
        super(page);
        page.waitForSelector("div.col-sm-2.rowHeader");
    }

    public void populateRoomName(String roomName) {
        page.fill("#roomName", roomName);
    }

    public void setRoomPrice(String price) {
        page.fill("#roomPrice", price);
    }

    public void clickCreateRoom() {
        page.click("#createRoom");
    }

    public int roomCount() {
        return page.locator("div[data-type~='room']").count();
    }

    public void clickFirstRoom() {
        page.locator("div[data-type~='room']").first().click();
    }

    public void checkWifi() {
        page.click("#wifiCheckbox");
    }

    public void checkSafe() {
        page.click("#safeCheckbox");
    }

    public void checkRadio() {
        page.click("#radioCheckbox");
    }

    public Boolean roomFormExists() {
        return page.locator(".room-form").isVisible();
    }
}
