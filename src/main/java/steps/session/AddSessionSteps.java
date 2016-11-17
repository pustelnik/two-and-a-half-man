package steps.session;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import pages.AddSessionPage;
import pages.DashboardPage;
import steps.BaseScenarioSteps;

import static org.fest.assertions.Assertions.assertThat;

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
    public void shouldFillNewSessionForm() {
        shouldSetLocationData();
        addSessionPage.getAdditionalData().sendKeys(session.getAdditionalInfo());
        shouldSelectSeatsManagement();
        shouldSelectLevels();
        shouldSelectProducts();
        shouldSelectExaminer();

    }

    @Step
    public void clickOnSaveSessionButton() {
        addSessionPage.getSaveSessionBtn().click();
    }

    @Step
    public void clickOnCancelButton() {
        addSessionPage.getCancelBtn().click();
    }

    @Step
    public void shouldNotCreateNewSession() {
        assertThat(addSessionPage.isCurrentPageAddSessionPage()).as("Session has been created").isTrue();
    }

    @Step
    public void shouldCreateNewSession() {
        assertThat(addSessionPage.isCurrentPageSessionDetailsPage()).as("Session has not been created").isTrue();
    }

    @Step
    public void shouldSelectExaminer() {
        addSessionPage.getExaminerBtn().click();
        addSessionPage.selectExaminer(session.getExaminer());
    }

    @Step
    public void shouldSelectSeatsManagement() {
        addSessionPage.selectSeatManagementMethod(session.getManagementMethod());
        addSessionPage.getNumberOfSeats().sendKeys(session.getNumberOfSeats());
    }

    @Step
    private void shouldSelectLevels() {
        addSessionPage.selectLevelBtn().click();
        addSessionPage.selectLevel(session.getLevels());
    }

    @Step
    private void shouldSelectProducts() {
        addSessionPage.selectProductBtn().click();
        addSessionPage.selectProduct(session.getProducts());
    }

    @Step
    public void shouldSetLocationData() {
        addSessionPage.setSessionDateInput(session.getSessionDate());
        addSessionPage.getPostalCode().sendKeys(session.getPostalCode());
        addSessionPage.getCity().sendKeys(session.getCity());
        addSessionPage.getAddress().sendKeys(session.getAddress());
    }

    @Step
    public void shouldOpenCreateNewSessionPage() {
        dashboardPage.clickOnAddSessionBtn();
    }
}
