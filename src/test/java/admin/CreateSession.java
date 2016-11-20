package admin;

import model.Session;
import model.SessionBuilder;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.pages.Pages;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.LoginSteps;
import steps.session.AddSessionSteps;

import java.time.LocalDateTime;

/**
 * @author jakubp on 16.11.16.
 */
@RunWith(SerenityRunner.class)
public class CreateSession {

    @Managed
    public WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    private LoginSteps loginSteps;

    @Steps
    private AddSessionSteps steps;

    @Before
    public void login() {
        loginSteps.shouldLogin();
        steps.setDefaultSession();
    }

    @Test
    @Title("Session should be created - Valid Data")
    public void shouldCreateSession() {
        steps.shouldOpenCreateNewSessionPage();
        steps.shouldFillNewSessionForm();
        steps.clickOnSaveSessionButton();
        steps.sessionShouldBeCreated();
    }

    @Test
    @Title("Session should not be created - Valid Data, Cancel button")
    public void whenCanceledSessionShouldNotBeCreated() {
        steps.shouldOpenCreateNewSessionPage();
        steps.shouldFillNewSessionForm();
        steps.clickOnCancelButton();
        steps.shouldNotCreateNewSession();
    }

    @Title("Session should not be created - No levels selected")
    public void noLevelsSelected() {
        steps.shouldOpenCreateNewSessionPage();
        steps.shouldSetLocationData();
        steps.shouldSelectSeatsManagement();
        steps.clickOnSaveSessionButton();
        steps.shouldNotCreateNewSession();
    }

    @Title("Session should not be created - One level selected, no products selected")
    public void noProductsSelected() {
        steps.shouldOpenCreateNewSessionPage();
        steps.shouldSetLocationData();
        steps.shouldSelectSeatsManagement();
        steps.shouldSelectLevels();
        steps.clickOnSaveSessionButton();
        steps.shouldNotCreateNewSession();
    }

    @Test
    @Title("'Dla sesji' should be default seat management method")
    public void defaultManagementMethod() {
        steps.shouldOpenCreateNewSessionPage();
        steps.sessionShouldBeDefaultManagementMethod();
    }

    @Test
    public void createSessionForOneExam() {
        steps.setOneExamSession();
        steps.shouldCreateSession();
    }

    @Test
    public void createSessionForFewExams() {
        steps.setFewExamsSession();
        steps.shouldCreateSession();
    }

    @Test
    public void createSessionWithMaxParticipantsPerExam() {
        steps.setMaxProductSeats();
        steps.shouldCreateSession();
    }

    @Test
    public void createSessionWithMaxParticipantsPerExamSession() {
        steps.setMaxSessionSeats();
        steps.shouldCreateSession();
    }

    @Test
    public void activateCreatedExamSession() {
        steps.shouldCreateSession();
        steps.shouldActivateExamSession();
    }

    @Test
    public void shouldCancelSessionActivation() {
        steps.shouldCreateSession();
        steps.shouldCancelActivateExamSession();
    }

    @Test
    public void shouldDeleteCreatedSessionInNewState() {
        steps.shouldCreateSession();
        steps.shouldDeleteSession();
    }

    @Test
    @Title("Should delete session in activated state when no users are registered to a session")
    public void shouldDeleteCreatedSessionInActivatedState() {
        steps.shouldCreateSession();
        steps.shouldActivateExamSession();
        steps.shouldDeleteSession();
    }

    @Test
    @Title("Session should not be created - identical date, city and postal code")
    public void sessionDuplicateNotAllowedForIdenticalData() {
        Session session1 = SessionBuilder.Instance().
                withSessionDate(LocalDateTime.now().plusWeeks(2).plusHours(2)).
                withCity("Łódź").
                withPostalCode("99-500").
                build();
        steps.shouldCreateSession(session1);
        steps.sessionShouldBeCreated();
        steps.shouldCreateSession(session1);
        steps.shouldNotCreateNewSession();
    }

    @Test
    @Title("Session should not be created for negative number of seats")
    public void sessionShouldNotBeCreatedForNegativeSeatsNumber() {
        Session negativeSeatsSession = SessionBuilder.Instance().
                withNumberOfSeats("-500").
                build();
        steps.shouldCreateSession(negativeSeatsSession);
        steps.shouldNotCreateNewSession();
    }

    @Test
    @Title("Session should not be created for n > 999")
    public void sessionShouldNotBeCreatedForBigNumberOfSeats() {
        Session negativeSeatsSession = SessionBuilder.Instance().
                withNumberOfSeats("1000").
                build();
        steps.shouldCreateSession(negativeSeatsSession);
        steps.shouldNotCreateNewSession();
    }

    @After
    public void cleanUpSession() {
        steps.sessionDeleteRequest();
    }

}
