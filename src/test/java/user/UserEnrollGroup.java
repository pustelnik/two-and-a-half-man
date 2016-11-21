package user;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.enroll.ExamEnrollSteps;
import steps.session.AddSessionSteps;
import tools.RequestBase;

/**
 * Created by LewarskiT on 2016-11-17.
 */
@RunWith(SerenityRunner.class)
public class UserEnrollGroup {
    @Managed
    public WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    ExamEnrollSteps examEnrollSteps;

    @Steps
    AddSessionSteps addSessionSteps;


    private RequestBase credentialsHolder;
    private int enrollmentID;

    @Before
    public void prepareEgzamSession(){
        credentialsHolder = examEnrollSteps.loginUsingRequest(driver);
        addSessionSteps.createActiveSession();
        examEnrollSteps.goToIndividualEnrollpage(addSessionSteps.getExamSessionPageId());
        examEnrollSteps.logout(driver);
    }
    //sessionId

    @After
    public void removeExamSession(){
        examEnrollSteps.loginUsingRequest(driver);
        examEnrollSteps.deleteAllEnrollments();
        examEnrollSteps.clearAtendeeModel();
    }
}
