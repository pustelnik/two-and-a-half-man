package steps.enroll;

import model.Session;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import pages.LandingPage;
import pages.enroll.ExamEnrollGroupPageBase;
import pages.enroll.ExamEnrollGroupPageStep1;
import pages.enroll.ExamEnrollGroupPageStep2;
import pages.enroll.ExamEnrollPageStep1;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by work on 2016-11-21.
 */
public class ExamGroupEnrollSteps extends ExamEnrollBaseSteps {
    protected ExamEnrollGroupPageBase examEnrollGroupPageBase = getCurrentPage(ExamEnrollGroupPageBase.class);
    protected ExamEnrollGroupPageStep1 examEnrollGroupPageStep1 = getCurrentPage(ExamEnrollGroupPageStep1.class);
    protected ExamEnrollGroupPageStep2 examEnrollGroupPageStep2 = getCurrentPage(ExamEnrollGroupPageStep2.class);

    public ExamGroupEnrollSteps(Pages pages) {
        super(pages);
    }

    @Step
    public void goToGroupEnrollPage(String sessionId){
        LandingPage landingPage = getCurrentPage((LandingPage.class));
        landingPage.goToLandingPage();
        landingPage.getIndividualRegisterButtonById(sessionId).click();
    }

    @Step
    public void fillFieldsFromStep1(Session sessionObject){
        fillEnrollPageStep1(sessionObject);
        assertThat(examEnrollGroupPageStep1.checkIfValidationWasTriggered()).describedAs("One of the fields was not set!").isFalse();
    }

    private void fillEnrollPageStep1(Session sessionObject){
        examEnrollGroupPageStep1.reload();
        examEnrollGroupPageStep1.setFirstName(atendeeModel.getFirstName());
        examEnrollGroupPageStep1.setLastName(atendeeModel.getLastName());
        examEnrollGroupPageStep1.setEmail(atendeeModel.getEmail());
        examEnrollGroupPageStep1.selectExamType(sessionObject.getProducts().get(0).name);

        if(examEnrollGroupPageStep1.isOwnedCertificateRequired()){
            examEnrollGroupPageStep1.setOwnedCertificateNumber(atendeeModel.getOwnedCertificateNumber());
            examEnrollGroupPageStep1.setOwnedCertificateIssueDate(atendeeModel.getOwnedCertificateIssueDate());
            examEnrollGroupPageStep1.setOwnedCertificateIssuedBy(atendeeModel.getOwnedCertificateIssuedBy());
        }
        examEnrollGroupPageStep1.goToStep2();
    }
}
