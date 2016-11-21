package pages.session.closedRegistration;

import model.EnrollEnums.EGZAM_LEVEL;
import model.EnrollEnums.EGZAM_PRODUCT;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Created by PustelnikJ on 2016-11-21.
 */
public class RegistrationParticipantsPage extends BasePage{

    @FindBy(css = ".form-control registration__input--name")
    private WebElementFacade name;
    @FindBy(css = ".form-control registration__input--surname")
    private WebElementFacade surname;
    @FindBy(css = ".form-control registration__input--email")
    private WebElementFacade email;
    @FindBy(css = ".form-control registration__input--phone")
    private WebElementFacade phoneNumber;
    @FindBy(id = "Language43add")
    private WebElementFacade languagePl;
    @FindBy(id = "Language44add")
    private WebElementFacade languageEn;
    @FindBy(id = "Form1add")
    private WebElementFacade paperExamForm;
    @FindBy(id = "Form2add")
    private WebElementFacade electronicExamForm;
    @FindBy(css = ".btn-registrationAddAttendee")
    private WebElementFacade addAttendeBtn;
    @FindBy(css = ".button--accept btn-form")
    private WebElementFacade contactDataBtn;

    public RegistrationParticipantsPage(WebDriver driver) {
        super(driver);
    }

    public void selectExam(EGZAM_PRODUCT exam) {
        WebElementFacade webElementFacade = find(By.cssSelector("input[id=\"Product" + exam.deleteBtnId + "add\"]"));
        webElementFacade.click();
    }

    public void selectCertLevel(EGZAM_LEVEL level) {
        WebElementFacade webElementFacade;
        String id = "Certificate";
        switch (level) {
            case BASIC:
                id += "67";
                break;
            case ADVANCE:
                id += "68";
                break;
            case EXPERT:
                id += "69";
                break;
            case OTHER:
                id += "70";
                break;
        }
        id += "add";
        webElementFacade = find(By.id(id));
        shouldBeVisible(webElementFacade);
        webElementFacade.click();
    }

    public WebElementFacade getName() {
        return name;
    }

    public WebElementFacade getSurname() {
        return surname;
    }

    public WebElementFacade getEmail() {
        return email;
    }

    public WebElementFacade getPhoneNumber() {
        return phoneNumber;
    }

    public WebElementFacade getLanguagePl() {
        return languagePl;
    }

    public WebElementFacade getLanguageEn() {
        return languageEn;
    }

    public WebElementFacade getPaperExamForm() {
        return paperExamForm;
    }

    public WebElementFacade getElectronicExamForm() {
        return electronicExamForm;
    }

    public WebElementFacade getAddAttendeBtn() {
        return addAttendeBtn;
    }

    public WebElementFacade getContactDataBtn() {
        return contactDataBtn;
    }
}
