package steps.enroll;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import model.Session;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import pages.enroll.ExamEnrollPageBase;
import pages.enroll.ExamEnrollPageStep1;
import pages.enroll.ExamEnrollPageStep2;
import pages.enroll.ExamEnrollPageStep3;
import steps.BaseScenarioSteps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by MASTAH on 2016-11-20.
 */
public class ExamEnrollValidationSteps extends ExamEnrollBaseSteps {
    private Config validationMessages = ConfigFactory.load().getConfig("test.messages.enrollPage");
    private Map<String,String> foundErrors = new HashMap<>();
    public ExamEnrollValidationSteps(Pages pages) {
        super(pages);
    }

    @Step
    public void triggerValidation(){
        examEnrollPageBase.goToNextStep();
    }

    @Step
    public void checkIfExamHeaderIsCorrect(Session sessionObject){
        assertThat(examEnrollPageBase.checkIfExamHeaderContainsGivenDetails(sessionObject.getCity()))
                .describedAs("Exam city must be wrong").isTrue();
        LocalDateTime sessionDate = sessionObject.getSessionDate();
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter day = DateTimeFormatter.ofPattern("c MMM").withLocale(new Locale("pl","PL"));

        assertThat(examEnrollPageBase.checkIfExamHeaderContainsGivenDetails(sessionDate.format(time)))
                .describedAs("Exam date must be wrong").isTrue();
        assertThat(examEnrollPageBase.checkIfExamHeaderContainsGivenDetails(sessionDate.format(day)))
                .describedAs("Exam time must be wrong").isTrue();
        assertThat(examEnrollPageBase.checkIfExamHeaderContainsGivenDetails(sessionObject.getProducts().get(0).name))
                .describedAs("Exam title must be wrong").isTrue();
    }

    @Step
    public void checkIfStep1ValidateionWorksForRequiredFieldssAWhole(){
        checkAndAddErrorIfNeeded("Languege selector",examEnrollPageStep1.getLanguageValidationMessage(),validationMessages.getString("examLangRegired"));
        checkAndAddErrorIfNeeded("Exam type selector",examEnrollPageStep1.getExamTypeValidationMessage(),validationMessages.getString("examTypeRequired"));

        if(examEnrollPageStep1.isOwnedCertificateRequired()){
            checkAndAddErrorIfNeeded(
                    "Owned cert number",
                    examEnrollPageStep1.getOwnedCertificateNumberValidationMessage(),
                    validationMessages.getString("ownedCertificateNumber"));
            checkAndAddErrorIfNeeded(
                    "Owned cert issue date",
                    examEnrollPageStep1.getOwnedCertificateIssueDateValidationMessage(),
                    validationMessages.getString("ownedCertificateIssueDateRequired"));
            checkAndAddErrorIfNeeded(
                    "Owned cert issuer",
                    examEnrollPageStep1.getOwnedCertificateIssuedByValidationMessage(),
                    validationMessages.getString("ownedCertificateIssuedByRequired"));
        }
    }

    @Step
    public void checkIfStep2ValidateionWorksForRequiredFieldssAWhole(){
        checkAndAddErrorIfNeeded("First name",examEnrollPageStep2.getfirstNameValidationMessage(),validationMessages.getString("firstNameRequired"));
        checkAndAddErrorIfNeeded("Last name",examEnrollPageStep2.getLastNameValidationMessage(),validationMessages.getString("lastNameRequired"));
        checkAndAddErrorIfNeeded("Email",examEnrollPageStep2.getEmailValidationMessage(),validationMessages.getString("emailRequired"));
    }

    @Step
    public void checkIfStep3ValidateionWorksForRequiredFieldssAWhole(){
        checkAndAddErrorIfNeeded("First name",examEnrollPageStep3.getCertificateFirstNameValidation(),validationMessages.getString("certificateFirstNameRequired"));
        checkAndAddErrorIfNeeded("Last name",examEnrollPageStep3.getCertificateLastNameValidation(),validationMessages.getString("certificateLastNameRequired"));
        checkAndAddErrorIfNeeded("City",examEnrollPageStep3.getCityValidation(),validationMessages.getString("cityRequired"));
        checkAndAddErrorIfNeeded("ZIP-Code",examEnrollPageStep3.getZipCodeValidation(),validationMessages.getString("zipCodeRequired"));
        checkAndAddErrorIfNeeded("Address",examEnrollPageStep3.getAddressValidation(),validationMessages.getString("addressRequired"));
        checkAndAddErrorIfNeeded("Invoice",examEnrollPageStep3.getInvoiceTypeValidation(),validationMessages.getString("invoceSettingRequired"));
        checkAndAddErrorIfNeeded("Legal policy",examEnrollPageStep3.getLegalPolicyValidation(),validationMessages.getString("legalPolicyRequired"));
    }

    @Step
    public void checkForErrors(){
        assertThat(foundErrors).describedAs("There are some validation messages that are wrong or missing").isEmpty();
    }

    private void checkAndAddErrorIfNeeded(String fieldName,String fieldMessage,String expectedMessage){
        if(!validateFieldValidationMessage(fieldMessage,expectedMessage)){
            foundErrors.put(fieldName,"Expected: "+expectedMessage+". Found:"+fieldMessage);
        }
    }

    private boolean validateFieldValidationMessage(String fieldMessage,String expectedMessage){
        if(fieldMessage != null && expectedMessage.equals(fieldMessage)){
            return true;
        }
        return false;
    }

    //get all messages seen on page
    //store in hash map: field => validation message

}
