package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.core.Is.is;

@At("https://examplanner.pgs-soft.com/taahm")
public class DashboardPage extends BasePage {

    // Navigation bar
    @FindBy(css = "#loginLink")
    private WebElement loginLink;
    @FindBy(css = "dropdownMenu-language")
    private WebElementFacade languageDropDown;

    public enum Language {
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

    public void clickOnLoginLink() {
        loginLink.click();
    }

    public void languageSelection(Language language) {
        languageDropDown.click();
        languageDropDown.selectByValue(language.val);
        Assert.assertThat(languageDropDown.getSelectedVisibleTextValue(), is(language.val));
    }
}
