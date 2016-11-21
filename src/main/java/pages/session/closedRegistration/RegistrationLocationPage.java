package pages.session.closedRegistration;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

/**
 * Created by PustelnikJ on 2016-11-21.
 */
public class RegistrationLocationPage extends BasePage {

    @FindBy(id = "ClosedRegistrationDateAndPlaceDto_ProposedDateTime")
    private WebElementFacade proposedTime;
    @FindBy(id = "ClosedRegistrationDateAndPlaceDto_PostalCode")
    private WebElementFacade postalCode;
    @FindBy(id = "ClosedRegistrationDateAndPlaceDto_City")
    private WebElementFacade city;
    @FindBy(id = "ClosedRegistrationDateAndPlaceDto_Address")
    private WebElementFacade address;
    @FindBy(id = "ClosedRegistrationDateAndPlaceDto_AdditionalInformation")
    private WebElementFacade additionalData;
    @FindBy(id = "ClosedRegistrationDateAndPlaceDto_IsLocationSpecifiedTrue")
    private WebElementFacade declareLocationTrue;
    @FindBy(id = "ClosedRegistrationDateAndPlaceDto_IsLocationSpecifiedFalse")
    private WebElementFacade declareLocationFalse;

    @FindBy(className = ".Register-forwardBtn")
    private WebElement participantsBtn;

    public RegistrationLocationPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnDeclareExamLocation(boolean declareLocation) {
        if(declareLocation) {
            declareLocationTrue.click();
        } else {
            declareLocationFalse.click();
        }
    }
}
