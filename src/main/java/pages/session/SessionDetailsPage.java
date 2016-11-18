package pages.session;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.fest.assertions.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

/**
 * @author jakubp on 17.11.16.
 */
public class SessionDetailsPage extends SessionNavigation {

    // session details
    @FindBy(xpath = "//div[2]/div/div/div[3]/div/div[1]/div[1]/div[2]/div[1]")
    private WebElement sessionDate;
    @FindBy(xpath = "//div[2]/div/div/div[3]/div/div[1]/div[1]/div[2]/div[2]")
    private WebElement sessionTime;
    @FindBy(xpath = "//div[2]/div/div/div[3]/div/div[1]/div[2]/div[2]")
    private WebElement numberOfSeats;
    @FindBy(xpath = "//div[2]/div/div/div[3]/div/div[1]/div[3]/div[2]")
    private WebElement examiner;
    @FindBy(xpath = "//div[2]/div/div/div[3]/div/div[2]/div[1]/div[1]/div[2]")
    private WebElement postalCode;
    @FindBy(xpath = "//div[2]/div/div/div[3]/div/div[2]/div[2]/div[2]")
    private WebElement address;
    @FindBy(xpath = "//div[2]/div/div/div[3]/div/div[2]/div[3]/div[2]")
    private WebElement additionalInfo;
    @FindBy(xpath = "//div[2]/div/div/div[3]/div/div[2]/div[1]/div[2]/div[2]")
    private WebElement city;

    public SessionDetailsPage(WebDriver driver) {
        super(driver);
    }

    public SessionDetailsPage deleteSession(boolean acceptChange) {
        String currentUrl = getDriver().getCurrentUrl();
        clickOnDeleteBtn();
        waitFor(alertIsPresent());
        clickOnAlert(acceptChange);
        try {
            clickOnAlert(true);
        } catch (Exception e) {
            assertThatUrlHasChanged(currentUrl);
            return this;
        }
        assertThatUrlHasChanged(currentUrl);
        return this;
    }

    public WebElement getSessionDate() {
        return sessionDate;
    }

    public WebElement getSessionTime() {
        return sessionTime;
    }

    public WebElement getNumberOfSeats() {
        return numberOfSeats;
    }

    public WebElement getExaminer() {
        return examiner;
    }

    public WebElement getPostalCode() {
        return postalCode;
    }

    public WebElement getAddress() {
        return address;
    }

    public WebElement getAdditionalInfo() {
        return additionalInfo;
    }

    public WebElement getCity() {
        return city;
    }

    private void assertThatUrlHasChanged(String currentUrl) {
        assertThat(currentUrl).as("Session is not deleted!").
                isNotEqualTo(getDriver().getCurrentUrl());
    }
}
