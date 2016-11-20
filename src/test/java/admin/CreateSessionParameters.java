package admin;

import model.Session;
import model.SessionBuilder;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.LoginSteps;
import steps.session.AddSessionSteps;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author jakubp on 20.11.16.
 */
@RunWith(SerenityParameterizedRunner.class)
public class CreateSessionParameters {

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
    }

    @TestData
    public static Collection<Object[]> testData() {
        LocalDateTime sessionDateTime = LocalDateTime.now().plusWeeks(2).plusHours(2);
        Session session2 = SessionBuilder.Instance().
                withSessionDate(sessionDateTime).
                withCity("Łódź testowy").
                withPostalCode("99-500").
                build();
        Session session3 = SessionBuilder.Instance().
                withSessionDate(sessionDateTime).
                withCity("Warszawa testowy").
                withPostalCode("00-125").
                build();
        LocalDateTime sessionDateTime2 = LocalDateTime.now().plusWeeks(2).plusHours(2);
        Session session4 = SessionBuilder.Instance().
                withSessionDate(sessionDateTime2).
                withCity("Kraków testowy").
                withPostalCode("00-444").
                build();
        Session session5 = SessionBuilder.Instance().
                withSessionDate(sessionDateTime.plusMinutes(5)).
                withCity("Wrocław testowy").
                withPostalCode("00-555").
                build();

        return Arrays.asList(new Object[][]{
                {"identical date, different city and different postal code", session2, session3},
                {"identical time, different city and different postal code", session4, session5}
        });
    }

    private final String testDescription;
    private final Session session1;
    private final Session session2;

    public CreateSessionParameters(String testDescription, Session session1, Session session2) {
        this.testDescription = testDescription;
        this.session1 = session1;
        this.session2 = session2;
    }

    @Test
    @Title("Session should be created")
    public void sessionShouldBeCreated() {
        steps.printToReport(testDescription);
        steps.shouldCreateSession(session1);
        steps.sessionShouldBeCreated();
        steps.shouldCreateSession(session2);
        steps.sessionShouldBeCreated();
    }

    @After
    public void cleanUpSession() {
        steps.sessionDeleteRequest();
    }
}
