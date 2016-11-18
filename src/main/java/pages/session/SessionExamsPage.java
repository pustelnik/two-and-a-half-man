package pages.session;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jakubp on 17.11.16.
 */
public class SessionExamsPage extends SessionNavigation {

    @FindBy(css = ".btn.btn-dark.btn-backofficeTop--small")
    private WebElement editBtn;
    protected SessionExamsPage(WebDriver driver) {
        super(driver);
    }

    public SessionExamsPage clickOnEditBtn() {
        editBtn.click();
        return this;
    }

    

}
