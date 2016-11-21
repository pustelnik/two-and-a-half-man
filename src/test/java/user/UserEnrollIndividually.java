package user;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import org.fluentlenium.core.annotation.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.session.SessionExamsPage;
import steps.BaseScenarioSteps;
import steps.enroll.ExamEnrollSteps;
import steps.session.AddSessionSteps;
import tools.RequestBase;

/**
 * Created by LewarskiT on 2016-11-17.
 */
@RunWith(SerenityRunner.class)
public class UserEnrollIndividually {
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

    @Test
    @Title("Try to enroll on an egzam session while not being logged in")
    public void enrollAsUnregisteredUser(){
        examEnrollSteps.setAtendeeModel(1);
        examEnrollSteps.fillFieldsFromStep1();
        examEnrollSteps.fillFieldsFromStep2();
        examEnrollSteps.fillFieldsFromStep3();
        examEnrollSteps.confirmEnrollment();
        //also -> go to enrollment details and verify that data is correct
    }

    @Test
    @Title("Try to enroll on an egzam session while being logged in")
    public void enrollAsSelfWhenRegistered(){
        examEnrollSteps.loginUsingRequest(driver);

        examEnrollSteps.setAtendeeModel(2);
        examEnrollSteps.fillFieldsFromStep1();
        examEnrollSteps.fillFieldsFromStep2();
        examEnrollSteps.fillFieldsFromStep3();
        examEnrollSteps.confirmEnrollment();
    }

    @Test
    @Title("Try to enroll same user twice")
    public void enrollSameUserTwice(){
        enrollAsUnregisteredUser();
        //start adding user for the second time
        examEnrollSteps.goToIndividualEnrollpage(addSessionSteps.getExamSessionPageId());
        examEnrollSteps.fillFieldsFromStep1();
        examEnrollSteps.fillFieldsFromStep2AndCheckForDuplicateEmailMessage();
    }

    @After
    public void removeExamSession(){
        examEnrollSteps.loginUsingRequest(driver);
        examEnrollSteps.deleteAllEnrollments();
        examEnrollSteps.clearAtendeeModel();
    }
}
