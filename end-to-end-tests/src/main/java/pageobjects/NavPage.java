package pageobjects;

import com.microsoft.playwright.Page;

public class NavPage extends BasePage {

    public NavPage(Page page) {
        super(page);
    }

    public void clickReport() {
        page.click("#reportLink");
    }

    public void clickBranding() {
        page.click("#brandingLink");
    }

    public void clickNotification() {
        page.click(".badge");
    }

    public void clickFrontPage() {
        page.click("#frontPageLink");
    }
}
