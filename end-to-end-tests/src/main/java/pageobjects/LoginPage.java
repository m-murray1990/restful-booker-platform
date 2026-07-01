package pageobjects;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    public LoginPage(Page page) {
        super(page);
    }

    public LoginPage populateUsername(String username) {
        page.fill("#username", username);
        return this;
    }

    public LoginPage populatePassword(String password) {
        page.fill("#password", password);
        return this;
    }

    public RoomListingPage clickLogin() {
        page.click("#doLogin");
        return new RoomListingPage(page);
    }
}
