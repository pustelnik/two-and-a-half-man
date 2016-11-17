package steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.http.cookie.Cookie;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.util.List;

/**
 * @author jakubp on 11.11.16.
 */
public class BaseScenarioSteps extends ScenarioSteps {

    BaseScenarioSteps(Pages pages) {
        super(pages);
    }

    <T extends PageObject> T getCurrentPage(Class<T> pageClass) {
        return getPages().currentPageAt(pageClass);
    }

    @Step
    public void goToDashboardPage() {
        this.getDriver().get(BasePage.BASE_URL);
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
