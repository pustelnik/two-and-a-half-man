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
import steps.LandingSteps;
import steps.LoginSteps;
import steps.session.AddSessionSteps;
import tools.SessionRequest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    private LandingSteps landingSteps;

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
    @Title("Session should not be created for negative number of seats (seats per session)")
    public void sessionShouldNotBeCreatedForNegativeSeatsNumber() {
        Session negativeSeatsSession = SessionBuilder.Instance().
                withNumberOfSeats("-1").
                build();
        steps.shouldCreateSession(negativeSeatsSession);
        steps.shouldNotCreateNewSession();
    }

    @Test
    @Title("Session should not be created for n > 999 (seats per session)")
    public void sessionShouldNotBeCreatedForBigNumberOfSeats() {
        Session negativeSeatsSession = SessionBuilder.Instance().
                withNumberOfSeats("1000").
                build();
        steps.shouldCreateSession(negativeSeatsSession);
        steps.shouldNotCreateNewSession();
    }

    @Test
    @Title("Session should not be created for negative number of seats (seats per product)")
    public void sessionShouldNotBeCreatedForNegativeSeatsNumberPerProduct() {
        steps.setOverMinSessionSeatsPerProduct();
        steps.shouldCreateSession();
        steps.shouldNotCreateNewSession();
    }

    @Test
    @Title("Session should not be created for n > 999 (seats per product)")
    public void sessionShouldNotBeCreatedForBigNumberOfSeatsPerProduct() {
        steps.setOverMaxSessionSeatsPerProduct();
        steps.shouldCreateSession();
        steps.shouldNotCreateNewSession();
    }

    @Test
    @Title("Same exams created in the same day should be show under proper time section in agenda")
    public void sameExamsInTheSameDayShouldBeCorrectlyShowOnAgenda(){
        Session[] sessions = {
                SessionBuilder.Instance().loadSessionFromConfig(1).withSessionDate(LocalDateTime.now().plusMonths(1).withNano(0).withSecond(0)).build(),
                SessionBuilder.Instance().loadSessionFromConfig(1).withSessionDate(LocalDateTime.now().plusMonths(1).plusMinutes(10).withNano(0).withSecond(0)).build()
        };

        loginSteps.shouldLogin();
        for(Session session : sessions){
            steps.shouldCreateSession(session);
            steps.sessionShouldBeCreated();
            steps.shouldActivateExamSession();
        }

        landingSteps.goToLandingPage();
        for(Session session : sessions){
            landingSteps.assertSessionIsCorrectlyShowed(session);
        }

        for(Session session : sessions){
            steps.sessionDeleteRequest(session.getId().get());
        }
    }

    @Test
    @Title("Same exams created day after day should be show under proper date and time section in agenda")
    public void sameExamDayAfterDayShouldBeCorrectlyShowOnAgenda(){
        Session[] sessions = {
                SessionBuilder.Instance().loadSessionFromConfig(1).withSessionDate(LocalDateTime.now().plusMonths(1).withNano(0).withSecond(0)).build(),
                SessionBuilder.Instance().loadSessionFromConfig(1).withSessionDate(LocalDateTime.now().plusMonths(1).plusDays(1).withNano(0).withSecond(0)).build()
        };

        loginSteps.shouldLogin();
        for(Session session : sessions){
            steps.shouldCreateSession(session);
            steps.sessionShouldBeCreated();
            steps.shouldActivateExamSession();
        }

        landingSteps.goToLandingPage();
        for(Session session : sessions){
            landingSteps.assertSessionIsCorrectlyShowed(session);
        }

        for(Session session : sessions){
            steps.sessionDeleteRequest(session.getId().get());
        }
    }


    @Test
    public void tryToRemoveSessionWhileNotLogged(){
        shouldCreateSession();

        SessionRequest sessionRequest = new SessionRequest();
        sessionRequest.deleteSession(steps.getSession().getId().get());

        steps.goToSessioNDetailsPage();
        steps.shouldCreateSession();
    }

    @Test
    public void createSessionWithoutActivationAndCheckShouldBeMissingInAgenda(){
        Session session = SessionBuilder.Instance().loadSessionFromConfig(1).withSessionDate(LocalDateTime.now().plusMonths(1).withNano(0).withSecond(0)).build();
        steps.shouldCreateSession(session);
        steps.sessionShouldBeCreated();
        landingSteps.goToLandingPage();
        assertFalse("Session without activation should not be present on Agenda(Landing) page.",landingSteps.isSessionCorrectlyShowedOnAgenda(session));

        if(session.getId().isPresent()) {
            steps.sessionDeleteRequest(session.getId().get());
        }
    }

    @Test
    public void createSessionWithActivationAndCheckIsShownOnAgenda(){
        Session session = SessionBuilder.Instance().loadSessionFromConfig(1).withSessionDate(LocalDateTime.now().plusMonths(1).withNano(0).withSecond(0)).build();
        steps.shouldCreateSession(session);
        steps.sessionShouldBeCreated();
        landingSteps.goToLandingPage();
        landingSteps.assertSessionIsCorrectlyShowed(session);
        if(session.getId().isPresent()) {
            steps.sessionDeleteRequest(session.getId().get());
        }
    }

    @Test
    public void createSessionWithZeroSeatsShouldBeUnavailableToRegister(){
        Session session = SessionBuilder.Instance().loadSessionFromConfig(1).withNumberOfSeats("0").withSessionDate(LocalDateTime.now().plusMonths(1).withNano(0).withSecond(0)).build();
        steps.shouldCreateSession(session);
        steps.sessionShouldBeCreated();
        landingSteps.goToLandingPage();
        assertTrue("Session is not visible on Agenda(Landing) page.",landingSteps.isSessionContainerShowedOnAgenda(session));
        assertFalse("'Rejestracja grupowa' button should not be present", landingSteps.isRegisterGroupButtonAvailable(session));
        assertFalse("'Rejestracja indywidualna' button should not be present", landingSteps.isRegisterIndividualAvailable(session));

        if(session.getId().isPresent()) {
            steps.sessionDeleteRequest(session.getId().get());
        }

    }
    @After
    public void cleanUpSession() {
        steps.sessionDeleteRequest();
    }

}
