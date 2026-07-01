package pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class SearchPage extends BasePage {

    public SearchPage(Page page) {
        super(page);
    }

    public List<Locator> getSearchResults() {
        return page.locator(".searchResult").all();
    }
}
