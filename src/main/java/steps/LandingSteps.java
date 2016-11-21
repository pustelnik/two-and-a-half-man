package steps;

import model.EnrollEnums;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import org.openqa.selenium.WebElement;
import pages.LandingPage;
import model.Session;
import model.EnrollEnums.EGZAM_PRODUCT;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Created by OtrembskiA on 2016-11-17.
 */
public class LandingSteps extends BaseScenarioSteps{
    private final LandingPage landingPage = getCurrentPage(LandingPage.class);


    public LandingSteps(Pages pages) {
        super(pages);
    }

    @Step
    public Session clickRegisterIndividual(Session session, EGZAM_PRODUCT product){
        WebElement examSessionContainer = landingPage.getExamSession(session);

        WebElement registerIndividualBtn = landingPage.getIndividualRegisterButton(examSessionContainer, product);

        registerIndividualBtn.click();

        return session;
    }

    /**
     * Locating button with give ID and click it.
     * @param examId an ID of button to click on
     * @return given ID
     */
    @Step
    public String clickRegisterIndividualByID(String examId){
        WebElement registerIndividualBtn = landingPage.getIndividualRegisterButtonById(examId);

        registerIndividualBtn.click();

        return examId;
    }


    /**
     * Assert that given session is correctly showed on Landing page.
     * Check if Group and Individual registration buttons are present.
     * @param session - session data to check
     */
    @Step
    public void assertSessionIsCorrectlyShowed(Session session){
        assertTrue("Landing page : 'Rejestracja grupowa' for session is not available", isGroupRegistrationAvailable(session));
        for(EnrollEnums.EGZAM_PRODUCT product : session.getProducts()) {
            assertTrue("Landing page : 'Rejestracja indywidualna' for product "+product.name()+" is not available.",isIndividualRegistrationAvailable(session, product));
        }
        //TODO spawdzenie wolnych miejsc
    }

    /**
     * Check if given session is showed on Agenda(Landing) page.
     * Verify by checking existence of session container and register buttons.
     * @param session - session to check
     * @return true if session is available in agenda
     */
    @Step
    public boolean isSessionAvailableOnAgenda(Session session){
        if(!isGroupRegistrationAvailable(session)){
            return false;
        }
        for(EnrollEnums.EGZAM_PRODUCT product : session.getProducts()) {
            if(!isIndividualRegistrationAvailable(session, product)){
                return false;
            }
        }
    return true;
    }

    /**
     * Click on register group button of given session.
     * @param session session for which register button should be found
     * @return given session
     */
    @Step
    public Session clickRegisterGroup(Session session) {
        assertTrue("Landing page : Registration not available.", isGroupRegistrationAvailable(session));
        WebElement examSessionContainer = landingPage.getExamSession(session);
        WebElement registerGroupBtn = landingPage.getGroupRegisterButton(examSessionContainer);

        registerGroupBtn.click();

        return session;
    }

    /**
     * Assure that number of seats for given session is equal with information on Landing page.
     * Check for group register ( summary )
     * @param session - session to verify
     * @param freeSeats - number of free seats to verify with showed on page session
     * @return given session
     */
    @Step
    public Session assertSummaryNumberOfFreeSeatsEqualTo(Session session, int freeSeats){
        //jezeli sesja jest tworzona z miejscami dla produktu
        return session;
    }

    /**
     * Count number of free seats for every product on session and compare to summary value.
     * @param session - session to verify
     * @return given session
     */
    @Step
    public Session assertSummaryNumberOfFreeSeatsIsEqualSeatsCount(Session session){
        assertTrue("Landing page : Summary value of free seats and count of individual seats are different.",landingPage.getCountOfFreeSeats(session) == landingPage.getGroupRegisterFreeSeats(session));
        return session;
    }


    /**
     * verify that number od free seats for specific product is equal to given count
     * @param session - session to check
     * @param freeSeats - number of seats to verify
     * @return given session
     */
    @Step
    public Session assertNumberOfFreeSeatsForProduct(Session session, int freeSeats){
        return session;
    }



    /**
     * Check if given session is shown on page, and that group register button is available.
     * @param session - session to check
     * @return true if registration is available
     */
    private boolean isGroupRegistrationAvailable(Session session){
        return landingPage.isGroupRegistrationButtonAvailable(session);
    }



    /**
     * Check if given session is shown on page, and that individual register button is available for given product.
     * @param session - session to check
     * @return true if registration is available
     */
    private boolean isIndividualRegistrationAvailable(Session session, EGZAM_PRODUCT product){
        return landingPage.isIndividualRegistrationButtonAvailable(session, product);
    }
}
