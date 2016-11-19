package pages.enroll;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by LewarskiT on 2016-11-18.
 */
public class ExamEnrollPageStep2 extends ExamEnrollPageBase {
    @FindBy(css = "#PersonDataDto_Name")
    private WebElement firstNameField;
    @FindBy(css = "span[for='PersonDataDto_Name']")
    private WebElement firstNameFieldValidation;
    @FindBy(css = "#PersonDataDto_Surname")
    private WebElement lastNameField;
    @FindBy(css = "span[for='PersonDataDto.Surname']")
    private WebElement lastNameFieldValidation;
    @FindBy(css = "#PersonDataDto_Email")
    private WebElement emailField;
    @FindBy(css = "span[for='PersonDataDto_Email']")
    private WebElement emailFieldValidation;
    @FindBy(css = "#PersonDataDto_Phone")
    private WebElement phoneField;
    @FindBy(css = "span[for='PersonDataDto_Phone']")
    private WebElement phoneFieldValidation;

    @FindBy(css = ".Register-backBtn button")
    private WebElement previousButton;

    @FindBy(css = ".Register-forwardBtn button")
    private WebElement nextButton;

    public ExamEnrollPageStep2(WebDriver driver) {
        super(driver);
    }

    public void setFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public String getfirstNameValidationMessage() {
        return getValidationMessage(firstNameFieldValidation);
    }

    public void setLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public String getLastNameValidationMessage() {
        return getValidationMessage(lastNameFieldValidation);
    }

    public void setEmail(String email) {
        emailField.sendKeys(email);
    }

    public String getEmailValidationMessage() {
        return getValidationMessage(emailFieldValidation);
    }

    public void setPhone(String phone) {
        phoneField.sendKeys(phone);
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






