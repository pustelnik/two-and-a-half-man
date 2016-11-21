package pages.session.closedRegistration;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Created by PustelnikJ on 2016-11-21.
 */
public class RegistrationAddressData extends BasePage {

    @FindBy(id = "AddressDto_Name")
    private WebElementFacade name;
    @FindBy(id = "AddressDto_Surname")
    private WebElementFacade surname;
    @FindBy(id = "AddressDto_PostalCode")
    private WebElementFacade postalCode;
    @FindBy(id = "AddressDto_City")
    private WebElementFacade city;
    @FindBy(id = "AddressDto_Comment")
    private WebElementFacade additionalInfo;

    // invoice
    @FindBy(id = "AddressDto_InvoiceTypesNone")
    private WebElementFacade no;
    @FindBy(id = "AddressDto_InvoiceTypesElectronic")
    private WebElementFacade electronic;
    @FindBy(id = "AddressDto_InvoiceTypesPaper")
    private WebElementFacade paper;

    // policy
    @FindBy(id = "AddressDto_AcceptedPrivacyPolicy")
    private WebElementFacade acceptPrivatePolicy;
    @FindBy(id = "AddressDto_AcceptedMarketingPolicy")
    private WebElementFacade acceptMarketingPolicy;

    @FindBy(id = "Register-forwardBtn")
    private WebElementFacade registerBtn;

    public RegistrationAddressData(WebDriver driver) {
        super(driver);
    }

    public WebElementFacade getName() {
        return name;
    }

    public WebElementFacade getSurname() {
        return surname;
    }

    public WebElementFacade getPostalCode() {
        return postalCode;
    }

    public WebElementFacade getCity() {
        return city;
    }

    public WebElementFacade getAdditionalInfo() {
        return additionalInfo;
    }

    public WebElementFacade getNo() {
        return no;
    }

    public WebElementFacade getElectronic() {
        return electronic;
    }

    public WebElementFacade getPaper() {
        return paper;
    }

    public WebElementFacade getAcceptPrivatePolicy() {
        return acceptPrivatePolicy;
    }

    public WebElementFacade getAcceptMarketingPolicy() {
        return acceptMarketingPolicy;
    }

    public WebElementFacade getRegisterBtn() {
        return registerBtn;
    }
}
