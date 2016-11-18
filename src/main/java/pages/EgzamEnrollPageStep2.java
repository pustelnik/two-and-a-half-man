package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebDriver;

/**
 * Created by LewarskiT on 2016-11-18.
 */
public class EgzamEnrollPageStep2 extends EgzamEnrollPageBase{
    @FindBy(css = "#PersonDataDto_Name")
    private FluentWebElement firstNameField;
    @FindBy(css = "span for['PersonDataDto_Name']")
    private FluentWebElement firstNameFieldValidation;
    @FindBy(css = "#PersonDataDto.Surname")
    private FluentWebElement lastNameField;
    @FindBy(css = "span for['PersonDataDto.Surname']")
    private FluentWebElement lastNameFieldValidation;
    @FindBy(css = "#PersonDataDto_Email")
    private FluentWebElement emailField;
    @FindBy(css = "span for['PersonDataDto_Email']")
    private FluentWebElement emailFieldValidation;
    @FindBy(css = "#PersonDataDto_Phone")
    private FluentWebElement phoneField;
    @FindBy(css = "span for['PersonDataDto_Phone']")
    private FluentWebElement phoneFieldValidation;

    @FindBy(css = ".Register-backBtn button")
    private FluentWebElement previousButton;

    @FindBy(css = ".Register-forwardBtn button")
    private FluentWebElement nextButton;

    public EgzamEnrollPageStep2(WebDriver driver) {
        super(driver);
    }

    public void setFirstName(String firstName) {
        firstNameField.text(firstName);
    }

    public String getfirstNameValidationMessage() {
        return getValidationMessage(firstNameFieldValidation);
    }

    public void setLasttName(String lastName) {
        lastNameField.text(lastName);
    }

    public String getLastNameValidationMessage() {
        return getValidationMessage(lastNameFieldValidation);
    }

    public void setEmail(String email) {
        emailField.text(email);
    }

    public String getEmailValidationMessage() {
        return getValidationMessage(emailFieldValidation);
    }

    public void setPhone(String phone) {
        phoneField.text(phone);
    }

    public String getPhoneValidationMessage() {
        return getValidationMessage(phoneFieldValidation);
    }

    public void goToStep2() {
        previousButton.click();
    }
    public void goToStep3() {
        nextButton.click();
    }
}






