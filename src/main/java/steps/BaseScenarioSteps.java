package steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import pages.BasePage;

/**
 * @author jakubp on 11.11.16.
 */
public class BaseScenarioSteps extends ScenarioSteps {

    public BaseScenarioSteps(Pages pages) {
        super(pages);
    }

    public <T extends PageObject> T getCurrentPage(Class<T> pageClass) {
        return getPages().currentPageAt(pageClass);
    }

    @Step
    public void goToDashboardPage() {
        this.getDriver().get(BasePage.BASE_URL);
    }
}
