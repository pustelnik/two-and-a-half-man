import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.LoginSteps;

/**
 * @author jakubp on 11.11.16.
 */
@RunWith(SerenityRunner.class)
public class LoginTest {

    @Managed
    public WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    private LoginSteps steps;

    @Before
    public void goToLoginPage() {
        steps.goToDashboardPage();
        steps.clickOnLoginButton();
    }

    @Test
    public void shouldNotLogin() {
        steps.enterInvalidCredentials();
        steps.errorMessageShouldBeDisplayed();
    }

    @Test
    public void shouldLogin() {
        steps.enterValidCredentials();
    }
}
