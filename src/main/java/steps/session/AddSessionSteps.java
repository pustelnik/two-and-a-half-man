package steps.session;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import pages.AddSessionPage;
import pages.DashboardPage;
import pages.session.SessionDetailsPage;
import steps.BaseScenarioSteps;

import static org.fest.assertions.Assertions.assertThat;
import static pages.AddSessionPage.ManagementMethod.SESSION;
import static pages.session.SessionNavigation.SessionStateChange.ACTIVATE_SESSION;

/**
 * @author jakubp on 16.11.16.
 */
public class AddSessionSteps extends BaseScenarioSteps {

    private AddSessionPage addSessionPage = getCurrentPage(AddSessionPage.class);
    private DashboardPage dashboardPage = getCurrentPage(DashboardPage.class);
    private SessionDetailsPage sessionDetailsPage = getCurrentPage(SessionDetailsPage.class);
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
    public void shouldSelectLevels() {
        addSessionPage.selectLevelBtn().click();
        addSessionPage.selectLevel(session.getLevels());
    }

    @Step
    public void shouldSelectProducts() {
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
    public void sessionShouldBeDefaultManagementMethod() {
        assertThat(addSessionPage.seatManagementMethod(SESSION).isSelected()).
                as("Session is not selected by default").isTrue();
    }

    @Step
    public void shouldOpenCreateNewSessionPage() {
        dashboardPage.clickOnAddSessionBtn();
    }

    @Step
    public void shouldCreateSession() {
        shouldOpenCreateNewSessionPage();
        shouldFillNewSessionForm();
        clickOnSaveSessionButton();
        shouldCreateNewSession();
    }

    @Step
    public void shouldActivateExamSession() {
        sessionDetailsPage.changeSessionState(ACTIVATE_SESSION, true);
    }

    @Step
    public void shouldCancelActivateExamSession() {
        sessionDetailsPage.changeSessionState(ACTIVATE_SESSION, false);
    }

    @Step
    public void shouldDeleteSession() {
        sessionDetailsPage.deleteSession(true);
    }

    public void setDefaultSession() {
        this.session = SessionBuilder.Instance().build();
    }

    public void setOneExamSession() {
        this.session = SessionBuilder.Instance().loadSessionFromConfig(1).build();
    }

    public void setFewExamsSession() {
        this.session = SessionBuilder.Instance().loadSessionFromConfig(2).build();
    }
}
