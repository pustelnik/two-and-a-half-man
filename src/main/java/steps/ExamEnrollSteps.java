package steps;

import com.typesafe.config.ConfigFactory;
import model.AtendeeModel;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import pages.*;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class ExamEnrollSteps extends BaseScenarioSteps {
    private final LandingPage landingPage = getCurrentPage((LandingPage.class));

    private AtendeeModel atendeeModel;

    public ExamEnrollSteps(Pages pages) {
        super(pages);
        atendeeModel = new AtendeeModel(ConfigFactory.load().getConfig("test.attendee"));
    }

    @Step
    public void goToIndividualEnrollpage(String individualSessionId){
        landingPage.goToLandingPage();
        landingPage.getIndividualRegisterButtonById(individualSessionId).click();
    }

    @Step
    public void fillFieldsFromStep1(){
        ExamEnrollPageStep1 examEnrollPageStep1 = getCurrentPage(ExamEnrollPageStep1.class);
        examEnrollPageStep1.reload();
        examEnrollPageStep1.selelectLanguage(atendeeModel.getPrefferedLanguage());
        examEnrollPageStep1.selelectExamType(atendeeModel.getPrefferedExamType());

        if(examEnrollPageStep1.isOwnedCertificateRequired()){
            examEnrollPageStep1.setOwnedCertificateNumber(atendeeModel.getOwnedCertificateNumber());
            examEnrollPageStep1.setOwnedCertificateIssueDate(atendeeModel.getOwnedCertificateIssueDate());
            examEnrollPageStep1.setOwnedCertificateIssuedBy(atendeeModel.getOwnedCertificateIssuedBy());
        }
        examEnrollPageStep1.goToStep2();

        assertThat(examEnrollPageStep1.checkIfValidationWasTriggered()).describedAs("One of the fields was not set!").isFalse();
    }

    @Step
    public void fillFieldsFromStep2(){
        ExamEnrollPageStep2 examEnrollPageStep2 = getCurrentPage(ExamEnrollPageStep2.class);
        examEnrollPageStep2.reload();
        examEnrollPageStep2.setFirstName(atendeeModel.getFirstName());
        examEnrollPageStep2.setLastName(atendeeModel.getLastName());
        examEnrollPageStep2.setEmail(atendeeModel.getEmail());
        examEnrollPageStep2.setPhone(atendeeModel.getPhoneNumber());

        examEnrollPageStep2.goToStep3();
        assertThat(examEnrollPageStep2.checkIfValidationWasTriggered()).describedAs("One of the fields was not set!").isFalse();
    }

    @Step
    public void fillFieldsFromStep3(){
        ExamEnrollPageStep3 examEnrollPageStep3 = getCurrentPage(ExamEnrollPageStep3.class);
        examEnrollPageStep3.reload();
        examEnrollPageStep3.setCertificateLastName(atendeeModel.getCertificateLastName());
        examEnrollPageStep3.setCertificateFirstName(atendeeModel.getCertificateFirstName());
        examEnrollPageStep3.setAdditionalDetails(atendeeModel.getAdditionalDetails());
        examEnrollPageStep3.setAddress(atendeeModel.getAddress());
        examEnrollPageStep3.setZipCode(atendeeModel.getZipCode());
        examEnrollPageStep3.setCity(atendeeModel.getCity());
        examEnrollPageStep3.setInvoiceType(atendeeModel.getInvoice());
        examEnrollPageStep3.setLegalPolicy(atendeeModel.isAcceptLegalPolicy());
        examEnrollPageStep3.setMarketingPolicy(atendeeModel.isAcceptMarketingPolicy());

        examEnrollPageStep3.finishRegistration();
        assertThat(examEnrollPageStep3.checkIfValidationWasTriggered()).describedAs("One of the fields was not set!").isFalse();
    }

    @Step
    public void confirmEnrollment(){
        ExamEnrollPageConfirmation examEnrollPageConfirmation = getCurrentPage(ExamEnrollPageConfirmation.class);
        assertThat(examEnrollPageConfirmation.isConfirmationVisible()).describedAs("Confirmation is not visible").isTrue();
        assertThat(examEnrollPageConfirmation.getConfirmationMessage()).describedAs("Confirmation message is wrong").
                isEqualTo(ConfigFactory.load().getConfig("test.messages.enrollPage").getString("enrollConfirmation"));
    }
}
