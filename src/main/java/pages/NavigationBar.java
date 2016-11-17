package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jakubp on 17.11.16.
 *
 * Class contains WebElements from navigation bar
 */
public class NavigationBar extends BasePage {

    @FindBy(css = "a[href$=\"Registration/List\"]")
    private WebElement applicationsLink;
    @FindBy(css = "a[href$=\"AddSession\"]")
    private WebElement sessionLink;
    @FindBy(css = "a[href$=\"^Products/List\"]")
    private WebElement productsLink;

    protected NavigationBar(WebDriver driver) {
        super(driver);
    }

    public NavigationBar clickOnApplicationsLink() {
        shouldBeVisible(applicationsLink);
        applicationsLink.click();
        return this;
    }

    public NavigationBar clickOnSessionLink() {
        shouldBeVisible(sessionLink);
        sessionLink.click();
        return this;
    }

    public NavigationBar getProductsLink() {
        shouldBeVisible(productsLink);
        productsLink.click();
        return this;
    }
}
