package user;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import org.fluentlenium.core.annotation.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.session.SessionExamsPage;
import steps.enroll.ExamEnrollSteps;
import steps.enroll.ExamEnrollValidationSteps;
import steps.session.AddSessionSteps;
import tools.RequestBase;
import tools.SessionRequest;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by LewarskiT on 2016-11-17.
 */
@RunWith(SerenityRunner.class)
public class EnrollFieldValidation {
    @Managed
    public WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Steps
    AddSessionSteps addSessionSteps;

    @Steps
    ExamEnrollValidationSteps examEnrollValidationSteps;

    @Steps
    ExamEnrollSteps examEnrollSteps;

    @Page
    SessionExamsPage sessionExamsPage;

    private RequestBase credentialsHolder;


    @Before
    public void createSession(){
        credentialsHolder = examEnrollValidationSteps.loginUsingRequest(driver);
        addSessionSteps.createActiveSession();
        examEnrollValidationSteps.goToIndividualEnrollpage(addSessionSteps.getExamSessionPageId());
    }

    @Test
    @Title("Check if Exam details(date/location/product/level are displayed correctly")
    public void validateExamDetails(){
        examEnrollValidationSteps.checkIfExamHeaderIsCorrect(addSessionSteps.getSession());
    }

    @Test
    @Title("Check if all required fields are set on Step1")
    public void checkIfAllRequiredFieldsAreSetOnStep1(){
        examEnrollValidationSteps.triggerValidation();
        //required fields
        examEnrollValidationSteps.checkIfStep1ValidateionWorksForRequiredFieldssAWhole();
        //check individual fields for format errors
        examEnrollValidationSteps.checkCertificateNumberValidationForFormatErrors();
        examEnrollValidationSteps.checkCertificateIssuedByValidationForFormatErrors();

        examEnrollValidationSteps.checkForErrors();
    }

    @Test
    @Title("Check if all required fields are set on Step2")
    public void checkIfAllRequiredFieldsAreSetOnStep2(){
        //set
        examEnrollSteps.setAtendeeModel(1);
        examEnrollSteps.fillFieldsFromStep1();
        examEnrollValidationSteps.triggerValidation();
        //required fields
        examEnrollValidationSteps.checkIfStep2ValidateionWorksForRequiredFieldssAWhole();
        //check individual fields for format errors
        examEnrollValidationSteps.checkFirstNameValidationForFormatErrors();
        examEnrollValidationSteps.checkLastNameValidationForFormatErrors();
        examEnrollValidationSteps.checkEmailValidationForFormatErrors();
        examEnrollValidationSteps.checkPhoneValidationForFormatErrors();

        examEnrollValidationSteps.checkForErrors();
    }

    @Test
    @Title("Check if all required fields are set on Step3")
    public void checkIfAllRequiredFieldsAreSetOnStep3(){
        examEnrollSteps.setAtendeeModel(1);
        examEnrollSteps.fillFieldsFromStep1();
        examEnrollSteps.fillFieldsFromStep2();
        //required fields
        examEnrollValidationSteps.triggerValidation();
        examEnrollValidationSteps.checkIfStep3ValidateionWorksForRequiredFieldssAWhole();
        //format fields
        examEnrollValidationSteps.checkCertificateFirstNameValidationForFormatErrors();
        examEnrollValidationSteps.checkCertificateLastNameValidationForFormatErrors();
        examEnrollValidationSteps.checkZipCodeValidationForFormatErrors();
        examEnrollValidationSteps.checkAddressValidationForFormatErrors();
        examEnrollValidationSteps.checkCityValidationForFormatErrors();

        examEnrollValidationSteps.checkForErrors();
    }

    @After
    public void deleteSession(){
        SessionRequest sessionRequest = new SessionRequest(credentialsHolder.getCookieStore());
        sessionRequest.deleteSession(addSessionSteps.getSession().getId().get());
    }
}
