package admin;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.LoginSteps;
import steps.session.AddSessionSteps;

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
        steps.shouldCreateNewSession();
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
    @Title("Session should not created - identical date, city and hour")
    public void sessionDuplicateNotAllowed() {
        steps.shouldCreateSession();
        steps.shouldOpenCreateNewSessionPage();
        steps.shouldFillNewSessionForm();
        steps.clickOnSaveSessionButton();
        steps.shouldNotCreateNewSession();
    }

    @Test
    @Title("'Dla sesji' should be default seat management method")
    public void defaultManagementMethod() {
        steps.sessionShouldBeDefaultManagementMethod();
    }

    @Test
    public void createSessionForOneExam() {
        steps.setOneExamSession();
        steps.shouldCreateNewSession();
    }

    @Test
    public void createSessionForFewExams() {
        steps.setFewExamsSession();
        steps.shouldCreateNewSession();
    }

    @Test
    @Pending
    public void createSessionWithMaxParticipantsPerExam() {

    }

    @Test
    @Pending
    public void createSessionWithMaxParticipantsPerExamSession() {

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

    public void shouldDeleteCreatedSession() {
        steps.shouldCreateSession();
        steps.shouldDeleteSession();
    }

}
