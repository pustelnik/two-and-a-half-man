package pages.session;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static model.EnrollEnums.EGZAM_PRODUCT;

/**
 * @author jakubp on 17.11.16.
 */
public class SessionExamsPage extends SessionNavigation {

    @FindBy(css = ".btn.btn-dark.btn-backofficeTop--small")
    private WebElement editBtn;
    public SessionExamsPage(WebDriver driver) {
        super(driver);
    }

    public void openSessionExamsPage(int sessionID){
        fluent().goTo(BASE_URL + "/taahm/Session/Exams/"+sessionID);
    }

    public String getExamToSession(EGZAM_PRODUCT product){
        WebElement productElement = find(By.xpath("//div[contains(text(),'"+product.name+"')]/parent::*/a"));
        return productElement.getAttribute("data-productid");
    }

    public SessionExamsPage clickOnEditBtn() {
        editBtn.click();
        return this;
    }
}
