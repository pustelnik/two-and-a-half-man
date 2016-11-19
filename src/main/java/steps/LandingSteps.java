package steps;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import org.openqa.selenium.WebElement;
import pages.LandingPage;
import model.Session;
import model.EnrollEnums.EGZAM_PRODUCT;

import static org.junit.Assert.assertNotNull;


/**
 * Created by OtrembskiA on 2016-11-17.
 */
public class LandingSteps extends BaseScenarioSteps{
    private static final Config CONF = ConfigFactory.load();
    private final LandingPage landingPage = getCurrentPage(LandingPage.class);


    public LandingSteps(Pages pages) {
        super(pages);
    }

    @Step
    public Session chooseRegisterIndividual(Session session, EGZAM_PRODUCT product){
/*        WebElement examSessionContainer = landingPage.getExamSession(session);
        assertNotNull("Landing page : Created exam not found on exam list",examSessionContainer);

        WebElement registerIndividualBtn = landingPage.getIndividualRegisterButton(examSessionContainer, product);
        assertNotNull("Landing page : Couldn't find 'Rejestracja indywidualna' button for created exam on exam list",registerIndividualBtn);

        registerIndividualBtn.click();
*/
        return session;
    }

    @Step
    public String chooseRegisterIndividualByID(String examId){
        WebElement registerIndividualBtn = landingPage.getIndividualRegisterButtonById(examId);
        assertNotNull("Landing page : Couldn't find 'Rejestracja indywidualna' button for created exam on exam list",registerIndividualBtn);

        registerIndividualBtn.click();

        return examId;
    }

    @Step
    public Session chooseRegisterGroup(Session session) {
        WebElement examSessionContainer = landingPage.getExamSession(session);
        assertNotNull("Landing page : Created exam not found on exam list",examSessionContainer);

        //click on group register in container
        WebElement registerGroupBtn = landingPage.getGroupRegisterButton(examSessionContainer);
        assertNotNull("Landing page : Couldn't find 'Rejestracja grupowa' button for created exam on exam list",registerGroupBtn);

        registerGroupBtn.click();

        return session;
    }

}
