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
import steps.EgzamEnrollSteps;
import tools.EnrollRequest;
import tools.LoginRequest;

import static utils.Environment.TEST_PWD;
import static utils.Environment.TEST_USR;

/**
 * Created by LewarskiT on 2016-11-17.
 */
@RunWith(SerenityRunner.class)
public class UserEnrollIndividually {
    private static final Config CONF = ConfigFactory.load();
    @Managed
    public WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    BaseScenarioSteps baseScenarioSteps;

    @Steps
    EgzamEnrollSteps egzamEnrollSteps;


    @Before
    public void prepareEgzamSession(){
        //create new session
        //do it once for whole TS
        LoginRequest loginRequest = new LoginRequest();
        if(loginRequest.login(CONF.getString(TEST_USR.val), CONF.getString(TEST_PWD.val))){
            EnrollRequest enrollRequest = new EnrollRequest(loginRequest.getCookieStore());
            enrollRequest.setEgzamSessionId(464);
            egzamEnrollSteps.feedCookiesToTheDriver(driver,enrollRequest.getCookies());
        }
    }

    @Test
    @Title("Try to enroll on an egzam session while not being logged in")
    public void enrollAsUnregisteredUser(){
        /*org.openqa.selenium.Cookie name = new org.openqa.selenium.Cookie("mycookie", "123456789123");
        driver.manage().addCookie(name);
        for(org.openqa.selenium.Cookie cookie : driver.manage().getCookies()){
            System.out.println(cookie.getName() + " " + cookie.getValue());
        }*/
        //egzamEnrollSteps.selectLanguage();
    }
/*
    @Test
    @Title("Try to enroll on an egzam session while being logged in")
    @Pending
    public void enrollAsSelfWhenRegistered(){
        loginSteps.login(CONF.getString(TEST_USR.val), CONF.getString(TEST_PWD.val));
        loginSteps.verifyLoginStatus();


    }

    @Test
    @Title("Try to enroll same user twice")
    @Pending
    public void enrollSameUserTwice(){
        loginSteps.login(CONF.getString(TEST_USR.val), CONF.getString(TEST_PWD.val));
        loginSteps.verifyLoginStatus();


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

    }*/

    @Test
    @Title("Check if free seat counter is working correctly - Precondition create session with X free seats")
    @Pending
    public void checkIfFreeSeatCounterIsWorking(){
        //getNumber of free seats
        //create for loop and enroll user in each step - check if counter is being decremented correctly

        //finally verify that the counter shows 0 free seats
    }

    /*@Test
    @Title("Try to enroll on an egzam session while not being logged in")
    @Pending
    public void enrollAsSomeoneWhenRegistered(){
        loginSteps.login(CONF.getString(TEST_USR.val), CONF.getString(TEST_PWD.val));
        loginSteps.verifyLoginStatus();

    }*/

    @After
    public void removeEgzamSession(){
        //delete egzam session
        //do it once for whole TS
    }
}
