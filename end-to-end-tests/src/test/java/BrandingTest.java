import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageobjects.BrandingPage;
import pageobjects.LoginPage;
import pageobjects.NavPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class BrandingTest extends TestSetup {

    @BeforeEach
    public void logIntoApplication() {
        navigateToApplication();

        LoginPage loginPage = new LoginPage(page);
        loginPage.populateUsername("admin");
        loginPage.populatePassword("password");
        loginPage.clickLogin();
    }

    @Test
    public void brandingPageLoadsWithName() {
        NavPage navPage = new NavPage(page);
        navPage.clickBranding();

        BrandingPage brandingPage = new BrandingPage(page);
        String nameValue = brandingPage.getNameValue();

        assertThat("Branding name should not be empty", nameValue.length(), greaterThan(0));
    }

    @Test
    public void brandingNameIsNotBlank() {
        NavPage navPage = new NavPage(page);
        navPage.clickBranding();

        BrandingPage brandingPage = new BrandingPage(page);
        String nameValue = brandingPage.getNameValue();

        assertThat("Branding name should not be blank", nameValue.isBlank(), is(false));
    }
}
