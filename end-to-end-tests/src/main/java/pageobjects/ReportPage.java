package pageobjects;

import com.microsoft.playwright.Page;

public class ReportPage extends BasePage {

    public ReportPage(Page page) {
        super(page);
    }

    public Boolean reportExists() {
        return page.locator(".rbc-calendar").isVisible();
    }
}
