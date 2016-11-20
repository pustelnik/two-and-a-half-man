package pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DashboardPage extends NavigationBar {

    // Navigation bar
    @FindBy(css = "#loginLink")
    private WebElement loginLink;
    @FindBy(css = "dropdownMenu-language")
    private WebElementFacade languageDropDown;
    @FindBy(css = ".fc-prev-button")
    private WebDriver previousMonth;
    @FindBy(css = ".fc-next-button")
    private WebDriver nextMonth;
    @FindBy(css = ".fc-left")
    private WebDriver currectMoth;

    private enum Language {
        POLISH("Polski"),
        ENGLISH("English");

        final String val;

        Language(String val) {
            this.val = val;
        }
    }

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void deleteSessionsAtDate(LocalDateTime date) {
        // TODO implement this
    }

    public void clickOnLoginLink() {
        waitFor(loginLink).waitUntilVisible();
        loginLink.click();
    }

    public void clickOnAddSessionBtn() {
        addSessionBtn().click();
    }

    private WebElement addSessionBtn() {
        return find(By.cssSelector("a[href=\"/taahm/Session/AddSession\"]"));
    }

    private WebElement addApplicationBtn() {
        return find(By.cssSelector("a[href=\"/taahm/Registration/List\"]"));
    }

    public void clickOnAddApplicationBtn() {
        addApplicationBtn().click();
    }

    public void languageSelection(Language language) {
        languageDropDown.click();
        languageDropDown.selectByValue(language.val);
        assertThat(languageDropDown.getSelectedVisibleTextValue(), is(language.val));
    }
}
