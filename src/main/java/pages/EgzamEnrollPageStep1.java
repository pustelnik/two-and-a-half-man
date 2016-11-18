package pages;

import model.EnrollEnums;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebDriver;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class EgzamEnrollPageStep1 extends EgzamEnrollPageBase {
    ///STEP 1
    @FindBy(css = "#ProductSelectionDto_ProductLanguage25")
    private FluentWebElement polishLanguage;
    @FindBy(css = "#ProductSelectionDto_ProductLanguage26")
    private FluentWebElement englishLanguage;
    @FindBy(css = "span[for='ProductSelectionDto.ProductLanguage']")
    private FluentWebElement languageValidateion;

    @FindBy(css = "#ProductSelectionDto_ProductForm_papierowa")
    private FluentWebElement egzamTypePaper;
    @FindBy(css = "#ProductSelectionDto_ProductForm_elektroniczna")
    private FluentWebElement egzamTypeElectronic;
    @FindBy(css = "span[for='ProductSelectionDto.ProductForm']")
    private FluentWebElement egzamTypeValidation;


    @FindBy(css = "#ProductSelectionDto_CertificateNumber")
    private FluentWebElement ownedCertificateNumber;
    @FindBy(css = "span[data-val-date='ProductSelectionDto_CertificateNumber']")
    private FluentWebElement ownedCertificateNumberValidation;
    @FindBy(css = "#ProductSelectionDto_CertificateDateOfIssue")
    private FluentWebElement ownedCertificateIssueDate;
    @FindBy(css = "span[data-val-date='ProductSelectionDto_CertificateDateOfIssue']")
    private FluentWebElement ownedCertificateIssueDateValidation;
    @FindBy(css = "#ProductSelectionDto_CertificateProvider")
    private FluentWebElement ownedCertificateIssuedBy;
    @FindBy(css = "span[data-val-date='ProductSelectionDto_CertificateProvider']")
    private FluentWebElement ownedCertificateIssuedByValidation;

    @FindBy(css = ".Register-forwardBtn button")
    private FluentWebElement nextButton;

    public EgzamEnrollPageStep1(WebDriver driver) {
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

    public void selelectEgazmType(EnrollEnums.PREFFERED_EGZAM_TYPE type) {
        if (type.equals(EnrollEnums.PREFFERED_EGZAM_TYPE.PAPER)) {
            egzamTypePaper.click();
        } else {
            egzamTypeElectronic.click();
        }
    }

    public String getEgzamTypeValidationMessage() {
        return getValidationMessage(egzamTypeValidation);
    }

    public void setOwnedCertificateNumber(String certificateNumber) {
        ownedCertificateNumber.text(certificateNumber);
    }

    public String getOwnedCertificateNumberValidationMessage() {
        return getValidationMessage(ownedCertificateNumberValidation);
    }

    public void setOwnedCertificateIssueDate(String certificateIssueDate) {
        ownedCertificateIssueDate.text(certificateIssueDate);
    }

    public String getOwnedCertificateIssueDateValidationMessage() {
        return getValidationMessage(ownedCertificateIssueDateValidation);
    }

    public void setOwnedCertificateIssuedBy(String certificateIssuedBy) {
        ownedCertificateIssuedBy.text(certificateIssuedBy);
    }

    public String getOwnedCertificateIssuedByValidationMessage() {
        return getValidationMessage(ownedCertificateIssuedByValidation);
    }

    public void goToStep2() {
        nextButton.click();
    }
}
