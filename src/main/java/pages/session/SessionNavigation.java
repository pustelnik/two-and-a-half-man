package pages.session;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.NavigationBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

/**
 * @author jakubp on 17.11.16.
 */
public class SessionNavigation extends NavigationBar {

    @FindBy(css = ".Backoffice-backButton>a")
    private WebElement dashBoardLink;
    @FindBy(css = ".js-session-delete")
    private WebElement deleteBtn;
    @FindBy(id = "sidebarItem-SessionExams")
    private WebElement examsLink;
    @FindBy(id = "sidebarItem-SessionNotes")
    private WebElement notesLink;
    @FindBy(id = "sidebarIte-SessionAttachments")
    private WebElement attachmentsLink;

    public SessionNavigation(WebDriver driver) {
        super(driver);
    }

    public enum SessionStateChange {
        ACTIVATE_SESSION(1),
        FORWARDED_ATTENDERS_INFO(2),
        EXAMINER_PROTOCOL(3),
        INVOICES_ISSUED(4),
        NO_ADDICTIONAL_ACTIONS_ALLOWED(5);

        final int index;

        SessionStateChange(int index) {
            this.index = index;
        }
    }

    /**
     * Reads session Id from current URL
     * @return exam session id
     */
    public int getSessionId() throws FailedToParseSessionUrlException {
        Pattern sessionIdPattern = Pattern.compile(".*[0-9]{3,}");
        Matcher matcher = sessionIdPattern.matcher(getDriver().getCurrentUrl());
        if(matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        throw new FailedToParseSessionUrlException();
    }

    public SessionNavigation clickOnExamsLink() {
        examsLink.click();
        return this;
    }

    public SessionNavigation clickOnNotesLink() {
        notesLink.click();
        return this;
    }

    public SessionNavigation clickOnAttachmentsLink() {
        attachmentsLink.click();
        return this;
    }

    public SessionNavigation clickOnDashBoardLink() {
        dashBoardLink.click();
        return this;
    }

    public SessionNavigation clickOnDeleteBtn() {
        deleteBtn.click();
        return this;
    }

    public SessionNavigation changeSessionState(SessionStateChange state, boolean acceptChange) {
        WebElementFacade checkBox = find(By.xpath(String.format("(//input[@type='checkbox'])[%d]", state.index)));
        shouldBeVisible(checkBox);
        checkBox.click();
        waitFor(alertIsPresent());
        clickOnAlert(acceptChange);
        shouldBeSelected(checkBox);
        return this;
    }

    protected void clickOnAlert(boolean acceptChange) {
        if (acceptChange) {
            getAlert().accept();
        } else {
            getAlert().dismiss();
        }
    }

    protected class FailedToParseSessionUrlException extends Exception {
    }
}
