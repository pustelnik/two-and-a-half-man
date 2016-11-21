package steps.session;

import model.Session;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.AddSessionPage;
import pages.EditSessionPage;
import steps.BaseScenarioSteps;

import static org.fest.assertions.Assertions.assertThat;
import static pages.AddSessionPage.ManagementMethod.SESSION;

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


    @Step
    public void shouldChangeSessionSeats(Session session, String seats) {
        shouldSelectNewSeats(session, seats);
        shouldSelectLevels(session);
        shouldSelectProducts(session);
        shouldSelectExaminer(session);
        clickOnSaveSessionButton();
    }


    @Step
    public void shouldSelectExaminer(Session session) {
        editSessionPage.getExaminerBtn().click();
        editSessionPage.selectExaminer(session.getExaminer());
    }
    @Step
    public void shouldSelectNewSeats(Session session, String seats) {
        editSessionPage.selectSeatManagementMethod(session.getManagementMethod());
        if(session.getManagementMethod().equals(SESSION)) {
            assertThat(editSessionPage.getNumberOfSeats().isVisible()).as("Number is seats input should be visible ").isTrue();
            editSessionPage.getNumberOfSeats().clear();
            editSessionPage.getNumberOfSeats().sendKeys(seats);
        }
    }
    @Step
    public void shouldSelectLevels(Session session) {
        editSessionPage.selectLevelBtn().click();
        editSessionPage.selectLevel(session.getLevels());
        // close dropdown menu
        editSessionPage.selectLevelBtn().click();
    }


    @Step
    public void clickOnSaveSessionButton() {
        editSessionPage.getSaveSessionBtn().click();
    }
    @Step
    public void shouldSelectProducts(Session session) {
        editSessionPage.selectProductBtn().click();
        if(session.getManagementMethod().equals(SESSION)) {
            editSessionPage.selectProduct(session.getProducts());
            editSessionPage.selectProductBtn().click();
        } else {
            editSessionPage.selectProductByExams(session.getExams());
        }
        // close dropdown menu

    }

}
