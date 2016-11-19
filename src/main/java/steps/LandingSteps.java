package steps;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import pages.LandingPage;
import model.Session;
import model.EnrollEnums.EGZAM_PRODUCT;


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
        return session;
    }

    @Step
    public String chooseRegisterIndividualByID(String id){
        landingPage.clickIndividualRegisterForExamByID(id);
        return id;
    }

    @Step
    public Session chooseRegisterGroup(Session session) {
        landingPage.clickGroupRegisterForExam(session);
        return session;
    }

}
