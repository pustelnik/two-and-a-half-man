package user;

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
import steps.session.AddSessionSteps;
import tools.RequestBase;
import tools.SessionRequest;

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
    ExamEnrollSteps examEnrollSteps;

    @Page
    SessionExamsPage sessionExamsPage;

    private RequestBase credentialsHolder;


    @Before
    public void createSession(){

        credentialsHolder = examEnrollSteps.loginUsingRequest(driver);
        addSessionSteps.setOneExamSession();
        addSessionSteps.shouldCreateSession();
        sessionExamsPage.openSessionExamsPage(addSessionSteps.getSession().getId().get());

        //System.out.println(addSessionSteps.getSession().getId().get());
        //System.out.println(sessionExamsPage.getExamToSession(addSessionSteps.getSession().getProducts().get(0)));

    }

    @Test
    @Title("Check if Exam details(date/location/product/level are displayed correctly")
    public void validateExamDetails(){
        examEnrollSteps.goToIndividualEnrollpage(sessionExamsPage.getExamToSession(addSessionSteps.getSession().getProducts().get(0)));
        examEnrollSteps.checkIfExamHeaderIsCorrect(addSessionSteps.getSession());
    }

    @Test
    @Title("Check if email field is being validated correctly")
    @Pending
    public void checkEmailFieldForInvalidInput(){

        //check lenght
        //check format
        //check if correct email is accepted
    }

    @Test
    @Title("Check if phone numebr is being validated correclty")
    @Pending
    public void checkPhoneNumberForInvalidInput(){
        //check lenght
        //check format
        //check if correct email is accepted
    }

    @Test
    @Title("Check if first name from ContactDetails step is being validated correclty")
    @Pending
    public void checFirstNameInContactDetailsForInvalidInput(){

    }

    @Test
    @Title("Check if last name from ContactDetails step  is being validated correclty")
    @Pending
    public void checLastNameInContactDetailsForInvalidInput(){

    }

    @Test
    @Title("Check if first name from CertificateDetails step is being validated correctly")
    @Pending
    public void checFirstNameInCertificateDetailsForInvalidInput(){

    }

    @Test
    @Title("Check if last name from CertificateDetails step is being validated correctly")
    @Pending
    public void checLastNameInCertificateDetailsForInvalidInput(){

    }

    @Test
    @Title("Check if Zip Code from CertificateDetails step is being validated correctly")
    @Pending
    public void checZipCodeInCertificateDetailsForInvalidInput(){

    }

    @Test
    @Title("Check if City from CertificateDetails step is being validated correctly")
    @Pending
    public void checCityInCertificateDetailsForInvalidInput(){

    }

    @Test
    @Title("Check if Invoice Setting from CertificateDetails step is being validated correctly")
    @Pending
    public void checInvoiceInCertificateDetailsForInvalidInput(){

    }

    @Test
    @Title("Check if Legal Policy Setting from CertificateDetails step is being validated correctly")
    @Pending
    public void checIfLegalPolicySettingIsBeingValidated(){

    }

    @After
    public void deleteSession(){
        SessionRequest sessionRequest = new SessionRequest(credentialsHolder.getCookieStore());
        sessionRequest.deleteSession(addSessionSteps.getSession().getId().get());
    }
}
