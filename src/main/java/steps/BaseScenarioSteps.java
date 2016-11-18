package steps;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.http.cookie.Cookie;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.util.List;
import static org.fest.assertions.Assertions.assertThat;
import tools.LoginRequest;
import static utils.Environment.TEST_PWD;
import static utils.Environment.TEST_USR;

/**
 * @author jakubp on 11.11.16.
 */
public class BaseScenarioSteps extends ScenarioSteps {    
    private static final Config CONF = ConfigFactory.load();
    public BaseScenarioSteps(Pages pages) {
        super(pages);
    }

    public <T extends PageObject> T getCurrentPage(Class<T> pageClass) {
        return getPages().currentPageAt(pageClass);
    }

    @Step
    public void goToDashboardPage() {
        this.getDriver().get(BasePage.BASE_URL+"/taahm");
    }
    @Step
    public void goToLandingPage() {
        this.getDriver().get(BasePage.BASE_URL+"/taahm");
    }

    @Step
    public void loginUsingRequest(WebDriver driver){
        LoginRequest loginRequest = new LoginRequest();
        assertThat(loginRequest.login(CONF.getString(TEST_USR.val), CONF.getString(TEST_PWD.val))).describedAs("Login status").isTrue();
        feedCookiesToTheDriver(driver,loginRequest.getCookies());
        goToDashboardPage();
    }
    
    @Step
    public void feedCookiesToTheDriver(WebDriver driver, List<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(new org.openqa.selenium.Cookie(
                    cookie.getName(),
                    cookie.getValue(),
                    cookie.getPath()
            ));
        }
    }
}
