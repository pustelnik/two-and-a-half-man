package user;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.BaseScenarioSteps;
import steps.ExamEnrollSteps;
import tools.LoginRequest;

import static org.fest.assertions.Assertions.assertThat;
import static utils.Environment.TEST_PWD;
import static utils.Environment.TEST_USR;

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
    BaseScenarioSteps baseScenarioSteps;

    @Steps
    ExamEnrollSteps examEnrollSteps;

//todo
    //-remove user enrollment after test
    //add test to check if user can not enroll twice
    @Before
    public void prepareEgzamSession(){
        //create new session
        //do it once for whole TS
        /*LoginRequest loginRequest = new LoginRequest();
        assertThat(loginRequest.login(CONF.getString(TEST_USR.val), CONF.getString(TEST_PWD.val))).describedAs("Login status").isTrue();
        examEnrollSteps.feedCookiesToTheDriver(driver,loginRequest.getCookies());*/

        //todo create a session and logout afer
    }

    @Test
    @Title("Try to enroll on an egzam session while not being logged in")
    public void enrollAsUnregisteredUser(){
        examEnrollSteps.setAtendeeModel("test.atendee1");
        examEnrollSteps.goToIndividualEnrollpage("464");
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

        examEnrollSteps.setAtendeeModel("test.atendee2");
        examEnrollSteps.goToIndividualEnrollpage("464");
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
        examEnrollSteps.fillFieldsFromStep1();
        examEnrollSteps.fillFieldsFromStep2();
    }

    @Test
    @Title("Try to enroll while there is only 1 seat available - Precondition - create session with one free seat")
    @Pending
    public void enrollAsLastUser(){

    }

    @Test
    @Title("Try to enroll while there are no more seats available - Precondition - create session with no free seat.")
    @Pending
    public void tryToEnrollWhenThereIsNoMoreSeats(){

    }

    @Test
    @Title("Check if free seat counter is working correctly - Precondition create session with X free seats")
    @Pending
    public void checkIfFreeSeatCounterIsWorking(){
        //getNumber of free seats
        //create for loop and enroll user in each step - check if counter is being decremented correctly

        //finally verify that the counter shows 0 free seats
    }

    @Test
    @Title("Try to enroll on an egzam session while not being logged in")
    @Pending
    public void enrollAsSomeoneWhenRegistered(){

    }

    @After
    public void removeEgzamSession(){
        //delete egzam session
        //do it once for whole TS
        examEnrollSteps.clearAtendeeModel();
    }
}
