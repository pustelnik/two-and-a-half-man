package user;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.LoginSteps;

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
    private LoginSteps loginSteps;

    @Before
    public void prepareEgzamSession(){

    }

    @Test
    public void enrollAsUnregisteredUser(){

    }

    @Test
    public void enrollAsSelfWhenRegistered(){
        loginSteps.login(CONF.getString(TEST_USR.val), CONF.getString(TEST_PWD.val));
        loginSteps.verifyLoginStatus();


    }

    @Test
    public void enrollAsSomeoneWhenRegistered(){
        loginSteps.login(CONF.getString(TEST_USR.val), CONF.getString(TEST_PWD.val));
        loginSteps.verifyLoginStatus();

    }

    @AfterClass
    public static void removeEgzamSession(){

    }
}
