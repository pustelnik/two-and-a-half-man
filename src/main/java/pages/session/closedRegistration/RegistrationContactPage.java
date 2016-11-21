package pages.session.closedRegistration;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Created by PustelnikJ on 2016-11-21.
 */
public class RegistrationContactPage extends BasePage {

    @FindBy(id = "PersonDataDto_Name")
    private WebElementFacade name;
    @FindBy(id = "PersonDataDto_Surname")
    private WebElementFacade surname;
    @FindBy(id = "PersonDataDto_Email")
    private WebElementFacade email;
    @FindBy(id = "PersonDataDto_Phone")
    private WebElementFacade phonNumber;
    @FindBy(css = ".Register-forwardBtn")
    private WebElementFacade dataToSendBtn;

    public RegistrationContactPage(WebDriver driver) {
        super(driver);
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

    public WebElementFacade getPhonNumber() {
        return phonNumber;
    }

    public WebElementFacade getDataToSendBtn() {
        return dataToSendBtn;
    }
}
