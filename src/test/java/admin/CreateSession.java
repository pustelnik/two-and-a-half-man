package admin;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
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
    private AddSessionSteps addSessionSteps;

    @Before
    public void login() {
        loginSteps.shouldLogin();
    }

    @Test
    public void shouldCreateSession() {
        addSessionSteps.shouldOpenCreateNewSessionPage();
        addSessionSteps.shouldCreateNewSession();
    }
}
