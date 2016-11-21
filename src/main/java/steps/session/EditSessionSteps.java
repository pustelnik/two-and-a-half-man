package steps.session;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.EditSessionPage;
import steps.BaseScenarioSteps;

/**
 * Created by OtrembskiA on 2016-11-21.
 */
public class EditSessionSteps  extends BaseScenarioSteps {
    private static final Logger LOGGER = LogManager.getLogger(EditSessionSteps.class);
    private EditSessionPage editSessionPage = getCurrentPage(EditSessionPage.class);

    public EditSessionSteps(Pages pages) {
        super(pages);
    }

    @Step
    public void goToEditSession(String sessionId){
        editSessionPage.goToEditSession(sessionId);
    }
}
