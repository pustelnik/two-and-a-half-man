package pages.enroll;

import model.EnrollEnums;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class ExamEnrollPageStep1 extends ExamEnrollPageBase {
    ///STEP 1
    @FindBy(xpath = "//div[@class='radio']/input[@id='ProductSelectionDto_ProductLanguage25']")
    private WebElement polishLanguage;
    @FindBy(xpath = "//div[@class='radio']/input[@id='ProductSelectionDto_ProductLanguage26']")
    private WebElement englishLanguage;
    @FindBy(css = "span[for='ProductSelectionDto.ProductLanguage']")
    private WebElement languageValidateion;

    @FindBy(xpath = "//div[@class='radio']/input[@id='ProductSelectionDto_ProductForm_papierowa']")
    private WebElement egzamTypePaper;
    @FindBy(xpath = "//div[@class='radio']/input[@id='ProductSelectionDto_ProductForm_elektroniczna']")
    private WebElement egzamTypeElectronic;
    @FindBy(css = "span[for='ProductSelectionDto.ProductForm']")
    private WebElement egzamTypeValidation;


    @FindBy(css = "#ProductSelectionDto_CertificateNumber")
    private WebElement ownedCertificateNumber;
    @FindBy(css = "span[data-val-date='ProductSelectionDto_CertificateNumber']")
    private WebElement ownedCertificateNumberValidation;
    @FindBy(css = "#ProductSelectionDto_CertificateDateOfIssue")
    private WebElement ownedCertificateIssueDate;
    @FindBy(css = "span[data-val-date='ProductSelectionDto_CertificateDateOfIssue']")
    private WebElement ownedCertificateIssueDateValidation;
    @FindBy(css = "#ProductSelectionDto_CertificateProvider")
    private WebElement ownedCertificateIssuedBy;
    @FindBy(css = "span[data-val-date='ProductSelectionDto_CertificateProvider']")
    private WebElement ownedCertificateIssuedByValidation;

    @FindBy(css = ".Register-forwardBtn button")
    private WebElement nextButton;

    public ExamEnrollPageStep1(WebDriver driver) {
        super(driver);
    }

    public void selelectLanguage(EnrollEnums.PREFFERED_LANGUAGE lang) {
        if (lang.equals(EnrollEnums.PREFFERED_LANGUAGE.POLISH)) {
            polishLanguage.click();
        } else {
            englishLanguage.click();
        }
    }

    public String getLanguageValidationMessage() {
        return getValidationMessage(languageValidateion);
    }

    public void selelectExamType(EnrollEnums.PREFFERED_EGZAM_TYPE type) {
        if (type.equals(EnrollEnums.PREFFERED_EGZAM_TYPE.PAPER)) {
            egzamTypePaper.click();
        } else {
            egzamTypeElectronic.click();
        }
    }

    public String getExamTypeValidationMessage() {
        return getValidationMessage(egzamTypeValidation);
    }

    public void setOwnedCertificateNumber(String certificateNumber) {
        ownedCertificateNumber.sendKeys(certificateNumber);
    }

    public String getOwnedCertificateNumberValidationMessage() {
        return getValidationMessage(ownedCertificateNumberValidation);
    }

    public void setOwnedCertificateIssueDate(String certificateIssueDate) {
        ownedCertificateIssueDate.sendKeys(certificateIssueDate);
    }

    public String getOwnedCertificateIssueDateValidationMessage() {
        return getValidationMessage(ownedCertificateIssueDateValidation);
    }

    public void setOwnedCertificateIssuedBy(String certificateIssuedBy) {
        ownedCertificateIssuedBy.sendKeys(certificateIssuedBy);
    }

    public String getOwnedCertificateIssuedByValidationMessage() {
        return getValidationMessage(ownedCertificateIssuedByValidation);
    }

    public boolean isOwnedCertificateRequired(){
        return ownedCertificateNumber.isDisplayed();
    }

    public void goToStep2() {
        nextButton.click();
    }
}
