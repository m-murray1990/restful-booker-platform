import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.Cookie;
import driverfactory.PlaywrightFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class TestSetup {

    Page page;

    @BeforeEach
    public void SetUp() {
        page = PlaywrightFactory.createPage();
    }

    @AfterEach
    public void TearDown() {
        PlaywrightFactory.closePage(page);
        PlaywrightFactory.closePlaywright();
    }

    void navigateToApplication() {
        if (System.getenv("TARGET") != null && System.getenv("TARGET").equals("production")) {
            page.navigate("https://automationintesting.online/admin");
            page.context().addCookies(List.of(
                new Cookie("welcome", "true").setUrl("https://automationintesting.online")
            ));
            page.reload();
        } else {
            page.navigate("http://localhost:3003/admin");
        }
    }
}

}
