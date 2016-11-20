package pages.enroll;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static model.EnrollEnums.INVOICE_TYPE;

/**
 * Created by LewarskiT on 2016-11-18.
 */
public class ExamEnrollPageStep3 extends ExamEnrollPageBase {
    public ExamEnrollPageStep3(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#AddressDto_Name")
    private WebElement certificateFirstName;
    @FindBy(css = "span[data-valmsg-for='AddressDto_Name']")
    private WebElement certificateFirstNameValidation;
    @FindBy(css = "#AddressDto_Surname")
    private WebElement certificateLastName;
    @FindBy(css = "span[data-valmsg-for='AddressDto_Surname']")
    private WebElement certificateLastNameValidation;
    @FindBy(css = "#AddressDto_PostalCode")
    private WebElement zipCode;
    @FindBy(css = "span[data-valmsg-for='AddressDto_PostalCode']")
    private WebElement zipCodeValidation;
    @FindBy(css = "#AddressDto_City")
    private WebElement city;
    @FindBy(css = "span[data-valmsg-for='AddressDto_City']")
    private WebElement cityValidation;
    @FindBy(css = "#AddressDto_Address")
    private WebElement address;
    @FindBy(css = "span[data-valmsg-for='AddressDto_Address']")
    private WebElement addressValidation;
    @FindBy(css = "#AddressDto_Comment")
    private WebElement additionalDetails;
    @FindBy(css = "span[data-valmsg-for='AddressDto_Comment']")
    private WebElement additionalDetailsValidation;

    @FindBy(xpath = "//div[@class='radio']/input[@id='AddressDto_InvoiceTypesNone']")
    private WebElement invoiceTypeNone;
    @FindBy(xpath = "//div[@class='radio']/input[@id='AddressDto_InvoiceTypesElectronic']")
    private WebElement invoiceTypeElectronic;
    @FindBy(xpath = "//div[@class='radio']/input[@id='AddressDto_InvoiceTypesPaper']")
    private WebElement invoiceTypePaper;
    @FindBy(css = "span for['AddressDto.InvoiceTypes']")
    private WebElement invoiceTypeValidation;

    @FindBy(css = "label[for='AddressDto_AcceptedPrivacyPolicy']")
    private WebElement legalPolicy;
    @FindBy(css = "span[for='AddressDto.AcceptedPrivacyPolicy']")
    private WebElement legalPolicyValidation;

    @FindBy(css = "label[for='AddressDto_AcceptedMarketingPolicy']")
    private WebElement marketingPolicy;

    public void setCertificateFirstName(String certificateFirstName) {
        this.certificateFirstName.sendKeys(certificateFirstName);
    }

    public void setCertificateLastName(String certificateLastName) {
        this.certificateLastName.sendKeys(certificateLastName);
    }

    public void setZipCode(String zipCode) {
        this.zipCode.sendKeys(zipCode);
    }

    public void setCity(String city) {
        this.city.sendKeys(city);
    }

    public void setAddress(String address) {
        this.address.sendKeys(address);
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails.sendKeys(additionalDetails);
    }

    public void setMarketingPolicy(boolean marketingPolicy) {
       if(marketingPolicy){
           this.marketingPolicy.click();
       }
    }

    public void setLegalPolicy(boolean legalPolicy) {
        if(legalPolicy){
            this.legalPolicy.click();
        }
    }

    public void setInvoiceType(INVOICE_TYPE invoiceType){
        if(INVOICE_TYPE.NONE.equals(invoiceType)){
            invoiceTypeNone.click();
        }else if(INVOICE_TYPE.PAPER.equals(invoiceType)){
            invoiceTypePaper.click();
        }else if(INVOICE_TYPE.ELECTRONIC.equals(invoiceType)){
            invoiceTypeElectronic.click();
        }
    }

    public void goToStep3() {
        previousButton.click();
    }
    public void finishRegistration() {
        nextButton.click();
    }
}
