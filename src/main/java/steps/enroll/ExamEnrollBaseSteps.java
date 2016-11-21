package steps.enroll;

import model.AtendeeModel;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import pages.LandingPage;
import pages.applications.ApplicationsPage;
import pages.enroll.*;
import steps.BaseScenarioSteps;

/**
 * Created by MASTAH on 2016-11-20.
 */
public class ExamEnrollBaseSteps extends BaseScenarioSteps {
    protected AtendeeModel atendeeModel;

    protected final ExamEnrollPageBase examEnrollPageBase = getCurrentPage(ExamEnrollPageBase.class);
    protected ExamEnrollPageStep1 examEnrollPageStep1 = getCurrentPage(ExamEnrollPageStep1.class);
    protected ExamEnrollPageStep2 examEnrollPageStep2 = getCurrentPage(ExamEnrollPageStep2.class);
    protected ExamEnrollPageStep3 examEnrollPageStep3 = getCurrentPage(ExamEnrollPageStep3.class);
    protected ExamEnrollPageConfirmation examEnrollPageConfirmation = getCurrentPage(ExamEnrollPageConfirmation.class);
    protected ApplicationsPage applicationsPage = getCurrentPage(ApplicationsPage.class);

    public ExamEnrollBaseSteps(Pages pages) {
        super(pages);
    }

    public void setAtendeeModel(int attendeeID){
        atendeeModel = new AtendeeModel(attendeeID);
    }

    public void clearAtendeeModel(){
        atendeeModel = null;
    }

    @Step
    public void goToIndividualEnrollpage(String individualSessionId){
        LandingPage landingPage = getCurrentPage((LandingPage.class));
        landingPage.goToLandingPage();
        landingPage.getIndividualRegisterButtonById(individualSessionId).click();
    }

    @Step
    public void deleteAllEnrollments(){
        applicationsPage.goToEnrollmentsPage();
        applicationsPage.deleteAllApplications();
    }

    public ExamEnrollPageStep1 getExamEnrollPageStep1() {
        return examEnrollPageStep1;
    }

    public ExamEnrollPageStep2 getExamEnrollPageStep2() {
        return examEnrollPageStep2;
    }

    public ExamEnrollPageStep3 getExamEnrollPageStep3() {
        return examEnrollPageStep3;
    }
}
