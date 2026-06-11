package driverfactory;

import com.microsoft.playwright.*;

public class PlaywrightFactory {
    private static Playwright playwright;
    private static Browser browser;

    public static Page createPage() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
            .setRecordVideoDir(java.nio.file.Paths.get("videos")));
        return context.newPage();
    }

    public static void closePage(Page page) {
        if (page != null) {
            page.context().close();
        }
    }

    public static void closePlaywright() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
