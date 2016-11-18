package pages;

import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebDriver;

/**
 * Created by LewarskiT on 2016-11-18.
 */
public class EgzamEnrollPageBase extends BasePage {
    private final String URL = "/taahm/RegisterProduct/GetAttendee";
    public EgzamEnrollPageBase(WebDriver driver) {
        super(driver);
        fluent().goTo(BASE_URL + URL);
    }


    protected String getValidationMessage(FluentWebElement elementToValidate) {
        if (elementToValidate != null && elementToValidate.isDisplayed()) {
            return elementToValidate.getText();
        }
        return null;
    }
}
