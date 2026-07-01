package pageobjects;

import com.microsoft.playwright.Page;

public class BrandingPage extends BasePage {

    public BrandingPage(Page page) {
        super(page);
    }

    public String getNameValue() {
        page.waitForSelector("#name");
        return page.inputValue("#name");
    }
}
