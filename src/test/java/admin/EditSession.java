package admin;

/**
 * Created by OtrembskiA on 2016-11-21.
 */

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.LandingSteps;
import steps.LoginSteps;
import steps.session.AddSessionSteps;
import steps.session.EditSessionSteps;

@RunWith(SerenityRunner.class)
public class EditSession {
    @Managed
    public WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    private LoginSteps loginSteps;

    @Steps
    private LandingSteps landingSteps;

    @Steps
    private AddSessionSteps addSessionSteps;

    @Steps
    private EditSessionSteps editSessionSteps;

    @Before
    public void login() {
        loginSteps.shouldLogin();
    }



}
