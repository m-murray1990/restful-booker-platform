package pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class MessagePage extends BasePage {

    public MessagePage(Page page) {
        super(page);
    }

    public List<Locator> getMessages() {
        return page.locator(".roomDelete").all();
    }
}
