package pages.applications;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

/**
 * Created by work on 2016-11-21.
 */
public class ApplicationsPage extends BasePage{
    private final String URL = "/taahm/Registration/List";
    public ApplicationsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".js-registration-delete")
    List<WebElement> deleteButtons;


    public void goToEnrollmentsPage(){
        fluent().goTo(BASE_URL + URL);
    }

    public void deleteAllApplications(){
        deleteButtons.stream().forEach(elem -> {elem.click();getDriver().switchTo().alert().accept();});
    }
}
