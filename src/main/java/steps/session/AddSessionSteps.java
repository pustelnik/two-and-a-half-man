package steps.session;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import pages.AddSessionPage;
import pages.DashboardPage;
import steps.BaseScenarioSteps;

/**
 * @author jakubp on 16.11.16.
 */
public class AddSessionSteps extends BaseScenarioSteps {

    private AddSessionPage addSessionPage = getCurrentPage(AddSessionPage.class);
    private DashboardPage dashboardPage = getCurrentPage(DashboardPage.class);
    private Session session = SessionBuilder.Instance().build();

    public AddSessionSteps(Pages pages) {
        super(pages);
    }

    @Step
    public void shouldCreateNewSession() {
        addSessionPage.setSessionDateInput(session.getSessionDate());
        addSessionPage.getPostalCode().sendKeys(session.getPostalCode());
        addSessionPage.getCity().sendKeys(session.getCity());
        addSessionPage.getAddress().sendKeys(session.getAddress());
        addSessionPage.getAdditionalData().sendKeys(session.getAdditionalInfo());
        addSessionPage.selectSeatManagementMethod(session.getManagementMethod());
        addSessionPage.getNumberOfSeats().sendKeys(session.getNumberOfSeats());
        addSessionPage.selectLevelBtn().click();
        addSessionPage.selectLevel(session.getLevels());
        addSessionPage.selectProductBtn().click();
        addSessionPage.selectProduct(session.getProducts());
        addSessionPage.getExaminerBtn().click();
        addSessionPage.selectExaminer(session.getExaminer());
    }

    @Step
    public void shouldOpenCreateNewSessionPage() {
        dashboardPage.clickOnAddSessionBtn();
    }
}
