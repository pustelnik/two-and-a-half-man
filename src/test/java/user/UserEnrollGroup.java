package user;

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
import steps.enroll.ExamEnrollSteps;
import steps.enroll.ExamGroupEnrollSteps;
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
    ExamGroupEnrollSteps examGroupEnrollSteps;

    @Steps
    AddSessionSteps addSessionSteps;


    private RequestBase credentialsHolder;
    private int enrollmentID;

    @Before
    public void prepareEgzamSession(){
        credentialsHolder = examGroupEnrollSteps.loginUsingRequest(driver);
        addSessionSteps.createActiveSession();
        examGroupEnrollSteps.goToGroupEnrollPage(addSessionSteps.getSession().getId().get().toString());
        examGroupEnrollSteps.logout(driver);
    }
    //sessionId

    @Test
    public void enrollAsGroup(){
        examGroupEnrollSteps.setAtendeeModel(1);

    }

    @After
    public void removeExamSession(){
        examGroupEnrollSteps.loginUsingRequest(driver);
        examGroupEnrollSteps.deleteAllEnrollments();
        examGroupEnrollSteps.clearAtendeeModel();
    }
}
