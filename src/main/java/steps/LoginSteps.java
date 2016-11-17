package steps;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import pages.DashboardPage;
import pages.LoginPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.Environment.TEST_PWD;
import static utils.Environment.TEST_USR;

/**
 * @author jakubp on 11.11.16.
 */
public class LoginSteps extends BaseScenarioSteps {

    private static final Config CONF = ConfigFactory.load();
    private final DashboardPage dashboardPage = getCurrentPage(DashboardPage.class);
    private final LoginPage loginPage = getCurrentPage(LoginPage.class);

    public LoginSteps(Pages pages) {
        super(pages);
    }

    @Step
    public void clickOnLoginButton() {
        dashboardPage.clickOnLoginLink();
    }

    @Step
    public boolean login(String user,String password){
        loginPage.
                enterCredentials(user,password).pressLoginBtn();
        return loginPage.invalidCredentialsErrorMsg().isVisible() &&
                !CONF.getString("test.messages.loginPage.invalidCredentials").equals(loginPage.invalidCredentialsErrorMsg().getText());
    }

    @Step
    public void enterValidCredentials() {
        login(CONF.getString(TEST_USR.val), CONF.getString(TEST_PWD.val));
    }

    @Step
    public void enterInvalidCredentials() {
        login("invalid_email@test.pl", "invalid_password");
    }

    @Step
    public void verifyLoginStatus() {
        assertTrue("Error msg is not visible", loginPage.invalidCredentialsErrorMsg().isVisible());
        assertEquals("Invalid error message", CONF.getString("test.messages.loginPage.invalidCredentials"),
                loginPage.invalidCredentialsErrorMsg().getText());
    }
}
