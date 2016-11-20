import model.Session;
import model.SessionBuilder;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.AddSessionPage;
import steps.LandingSteps;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static model.EnrollEnums.EGZAM_PRODUCT.BASIC_ISTQB;

/**
 * Created by kod on 2016-11-15.
 */
@RunWith(SerenityRunner.class)
public class LandingRegisterExam {

    @Managed
    public WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    private LandingSteps steps;

    @Before
    public void goToLoginPage() {
        steps.goToLandingPage();
    }

    @Test
    public void sprawdzamListe() {
        Session session = SessionBuilder.Instance().build();
        session.setCity("Zion");
        session.setSessionDate(LocalDateTime.of(2016,12,3,10,55));

        steps.chooseRegisterGroup(session);
    }


    @Test
    public void testProduct(){
        Session session = SessionBuilder.Instance().build();
        session.setCity("Lodz");
        session.setSessionDate(LocalDateTime.of(2016,12,3,10,55));
        session.setProducts(Collections.singletonList(BASIC_ISTQB));
        steps.chooseRegisterIndividual(session, session.getProducts().get(0));
    }

    @Test
    public void testId(){
        steps.chooseRegisterIndividualByID("518");
    }

}
