package steps.session;

import model.Session;
import model.SessionBuilder;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.pages.Pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.AddSessionPage;
import pages.DashboardPage;
import pages.session.SessionDetailsPage;
import pages.session.SessionNavigation;
import steps.BaseScenarioSteps;
import tools.RequestBase;
import tools.SessionRequest;

import java.util.Optional;

import static org.fest.assertions.Assertions.assertThat;
import static pages.AddSessionPage.ManagementMethod.SESSION;
import static pages.session.SessionNavigation.SessionStateChange.ACTIVATE_SESSION;

/**
 * @author jakubp on 16.11.16.
 */
public class AddSessionSteps extends BaseScenarioSteps {

    private static final Logger LOGGER = LogManager.getLogger(AddSessionSteps.class);
    private AddSessionPage addSessionPage = getCurrentPage(AddSessionPage.class);
    private DashboardPage dashboardPage = getCurrentPage(DashboardPage.class);
    private SessionDetailsPage sessionDetailsPage = getCurrentPage(SessionDetailsPage.class);
    private Session session = SessionBuilder.Instance().build();
    private Optional<Session> sessionCopy = Optional.empty();

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
        assertThat(addSessionPage.isCurrentPageAddSessionPage()).as("Session not created").isTrue();
        setLastSessionId();
    }


    @Step
    public void sessionShouldBeCreated() {
        assertThat(addSessionPage.isCurrentPageSessionDetailsPage()).as("Session created").isTrue();
        setLastSessionId();
    }

    @Step
    public void shouldSelectExaminer() {
        addSessionPage.getExaminerBtn().click();
        addSessionPage.selectExaminer(session.getExaminer());
    }

    @Step
    public void shouldSelectSeatsManagement() {
        addSessionPage.selectSeatManagementMethod(session.getManagementMethod());
        if(session.getManagementMethod().equals(SESSION)) {
            assertThat(addSessionPage.getNumberOfSeats().isVisible()).as("Number is seats input should be visible ").isTrue();
            addSessionPage.getNumberOfSeats().clear();
            addSessionPage.getNumberOfSeats().sendKeys(session.getNumberOfSeats());
        }
    }

    @Step
    public void shouldSelectLevels() {
        addSessionPage.selectLevelBtn().click();
        addSessionPage.selectLevel(session.getLevels());
        // close dropdown menu
        addSessionPage.selectLevelBtn().click();
    }

    @Step
    public void shouldSelectProducts() {
        addSessionPage.selectProductBtn().click();
        if(session.getManagementMethod().equals(SESSION)) {
            addSessionPage.selectProduct(session.getProducts());
            addSessionPage.selectProductBtn().click();
        } else {
            addSessionPage.selectProductByExams(session.getExams());
        }
        // close dropdown menu

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
        sessionShouldBeCreated();
    }

    @Step
    public void shouldCreateSession(Session session) {
        this.sessionCopy = Optional.of(this.session);
        this.session = session;
        shouldOpenCreateNewSessionPage();
        shouldFillNewSessionForm();
        clickOnSaveSessionButton();
    }

    @Step
    public void printToReport(String msg) {
        // prints String to the test report
    }

    @Step
    @Title("Delete session using delete request")
    public void sessionDeleteRequest() {
        RequestBase requestBase = loginUsingRequest(getDriver());
        SessionRequest sessionRequest = new SessionRequest(requestBase.getCookieStore());
        if(session.getId().isPresent()) {
            int sessionId = session.getId().get();
            LOGGER.debug("Sending DELETE session " + sessionId);
            sessionRequest.deleteSession(sessionId);
        }
        if(sessionCopy.isPresent() && sessionCopy.get().getId().isPresent()) {
            int sessionId = sessionCopy.get().getId().get();
            LOGGER.debug("Sending DELETE session " + sessionId);
            sessionRequest.deleteSession(sessionId);
        }
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

    public Session getSession() {
        return this.session;
    }

    public void setOneExamSession() {
        this.session = SessionBuilder.Instance().loadSessionFromConfig(1).build();
    }

    public void setFewExamsSession() {
        this.session = SessionBuilder.Instance().loadSessionFromConfig(2).build();
    }

    public void setMaxSessionSeats() {
        this.session = SessionBuilder.Instance().loadSessionFromConfig(4).build();
    }

    public void setOverMaxSessionSeatsPerProduct() {
        this.session = SessionBuilder.Instance().loadSessionFromConfig(7).build();
    }

    public void setOverMinSessionSeatsPerProduct() {
        this.session = SessionBuilder.Instance().loadSessionFromConfig(8).build();
    }

    public void setMaxProductSeats() {
        this.session = SessionBuilder.Instance().loadSessionFromConfig(6).build();
    }

    private void setLastSessionId() {
        try {
            session.setId(sessionDetailsPage.getSessionId());
        } catch (SessionNavigation.FailedToParseSessionUrlException e) {
            LOGGER.warn(e);
        }
    }

}
