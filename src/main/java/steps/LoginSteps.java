package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import pages.LoginPage;

/**
 * @author jakubp on 11.11.16.
 */
public class LoginSteps extends BaseScenarioSteps {

    private LoginPage loginPage = getCurrentPage(LoginPage.class);

    public LoginSteps(Pages pages) {
        super(pages);
    }

    @Step
    public void enterInvalidCredentials() {
        loginPage.
                enterCredentials("invalid_email@test.pl", "invalid_password").
                pressLoginBtn();
    }
}
