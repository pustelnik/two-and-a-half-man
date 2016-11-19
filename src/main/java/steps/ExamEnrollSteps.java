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
    private AtendeeModel atendeeModel;
    
    private LandingPage landingPage = getCurrentPage(LandingPage.class);
    private final ExamEnrollPageBase examEnrollPageBase = getCurrentPage(ExamEnrollPageBase.class);
    private ExamEnrollPageStep1 examEnrollPageStep1 = getCurrentPage(ExamEnrollPageStep1.class);
    private ExamEnrollPageStep2 examEnrollPageStep2 = getCurrentPage(ExamEnrollPageStep2.class);
    private ExamEnrollPageStep3 examEnrollPageStep3 = getCurrentPage(ExamEnrollPageStep3.class);
    private ExamEnrollPageConfirmation examEnrollPageConfirmation = getCurrentPage(ExamEnrollPageConfirmation.class);

    public ExamEnrollSteps(Pages pages) {
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
    public void fillFieldsFromStep1(){
        fillEnrollPageStep1();
        assertThat(examEnrollPageStep1.checkIfValidationWasTriggered()).describedAs("One of the fields was not set!").isFalse();
    }

    @Step
    public void fillFieldsFromStep2(){
        fillEnrollPageStep2();
        assertThat(examEnrollPageStep2.checkIfValidationWasTriggered()).describedAs("One of the fields was not set!").isFalse();
    }
    
    @Step
    public void fillFieldsFromStep2AndCheckForDuplicateEmailMessage(){
        fillEnrollPageStep2();
        assertThat(examEnrollPageStep2.checkIfValidationWasTriggered()).describedAs("Validation was not triggered").isTrue();
        assertThat(examEnrollPageStep2.getEmailValidationMessage()).describedAs("Duplicate email message is wrong").
                isEqualTo(ConfigFactory.load().getConfig("test.messages.enrollPage").getString("emailDuplicated"));
    }

    @Step
    public void fillFieldsFromStep3(){
        fillEnrollPageStep3();
        assertThat(examEnrollPageStep3.checkIfValidationWasTriggered()).describedAs("One of the fields was not set!").isFalse();
    }

    @Step
    public void confirmEnrollment(){
        assertThat(examEnrollPageConfirmation.isConfirmationVisible()).describedAs("Confirmation is not visible").isTrue();
        assertThat(examEnrollPageConfirmation.getConfirmationMessage()).describedAs("Confirmation message is wrong").
                isEqualTo(ConfigFactory.load().getConfig("test.messages.enrollPage").getString("enrollConfirmation"));
    }
    
    private void fillEnrollPageStep1(){
        examEnrollPageStep1.reload();
        examEnrollPageStep1.selelectLanguage(atendeeModel.getPrefferedLanguage());
        examEnrollPageStep1.selelectExamType(atendeeModel.getPrefferedExamType());

        if(examEnrollPageStep1.isOwnedCertificateRequired()){
            examEnrollPageStep1.setOwnedCertificateNumber(atendeeModel.getOwnedCertificateNumber());
            examEnrollPageStep1.setOwnedCertificateIssueDate(atendeeModel.getOwnedCertificateIssueDate());
            examEnrollPageStep1.setOwnedCertificateIssuedBy(atendeeModel.getOwnedCertificateIssuedBy());
        }
        examEnrollPageStep1.goToStep2();
    }
    
    private void fillEnrollPageStep2(){
        examEnrollPageStep2.reload();
        examEnrollPageStep2.setFirstName(atendeeModel.getFirstName());
        examEnrollPageStep2.setLastName(atendeeModel.getLastName());
        examEnrollPageStep2.setEmail(atendeeModel.getEmail());
        examEnrollPageStep2.setPhone(atendeeModel.getPhoneNumber());

        examEnrollPageStep2.goToStep3();
    }
    
    private void fillEnrollPageStep3(){
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
    }
}
