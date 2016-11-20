import model.EnrollEnums;
import model.Exam;
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
import steps.LoginSteps;
import steps.session.AddSessionSteps;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static model.EnrollEnums.EGZAM_PRODUCT.BASIC_ISTQB;

/**
 * Created by kod on 2016-11-15.
 */
@RunWith(SerenityRunner.class)
public class CheckLandingPage {

    @Managed
    public WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    private LandingSteps steps;

    @Steps
    private AddSessionSteps addSessionSteps;

    @Steps
    private LoginSteps loginSteps;

    @Before
    public void goToLoginPage() {
        steps.goToLandingPage();
    }

    @Test
    public void sprawdzamListe() {
        Session session = SessionBuilder.Instance().build();
        session.setCity("Zion");
        session.setSessionDate(LocalDateTime.of(2016,12,3,10,55));

        steps.clickRegisterGroup(session);
    }


    @Test
    public void testProduct(){
        Session session = SessionBuilder.Instance().build();
        session.setCity("Lodz");
        session.setSessionDate(LocalDateTime.of(2016,12,3,12,40));
        session.setProducts(Collections.singletonList(BASIC_ISTQB));
        steps.clickRegisterIndividual(session, session.getProducts().get(0));
    }

    @Test
    public void testId(){

        Session s = SessionBuilder.Instance().withSessionDate(LocalDateTime.of(2016,12,13,12,40)).
                withManagementMethod(AddSessionPage.ManagementMethod.PRODUCT).
                withExams(Collections.singletonList(
                        new Exam(EnrollEnums.EGZAM_LEVEL.ADVANCE, EnrollEnums.EGZAM_PRODUCT.ADVANCED_TEST_ANALYST, "44"))).
                withLevels(Arrays.asList(EnrollEnums.EGZAM_LEVEL.ADVANCE)).
                withProducts(Arrays.asList(EnrollEnums.EGZAM_PRODUCT.ADVANCED_TEST_ANALYST))
                .build();

        loginSteps.shouldLogin();
        addSessionSteps.shouldCreateSession(s);
        addSessionSteps.sessionShouldBeCreated();
        addSessionSteps.shouldActivateExamSession();
        System.out.println(s.getId().get());
        

        //steps.clickRegisterIndividualByID("518");
    }

}

/**
 * Rejestrowanie dwoch sesji nastepujacych po sobie w tym samym miescie, na ten sam egzamin ( moga byc w roznych dniach, ale sprawdzic dla roznych dni  i dla tego samego dnia ):
 *  blad: wystepujace po sobie sesjie z tymi samymi egzaminami tym samym miescie przypinaja sie do najwczesniejszej sesji
 *          pozostale terminy pokazuja sie na liscie ale sa puste, egzaminy sa przeniesione do pierwszej sesji
 *
 * - same exams in the same city, one over another
 *    - same day
 *    - different days
 *
 * - same exams in different cities
 */
