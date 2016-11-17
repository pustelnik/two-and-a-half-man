package pages.session;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.NavigationBar;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

/**
 * @author jakubp on 17.11.16.
 */
public class SessionSidebar extends NavigationBar {

    @FindBy(id = "sidebarItem-SessionExams")
    private WebElement examsLink;
    @FindBy(id = "sidebarItem-SessionNotes")
    private WebElement notesLink;
    @FindBy(id = "sidebarIte-SessionAttachments")
    private WebElement attachmentsLink;

    protected SessionSidebar(WebDriver driver) {
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

    public SessionSidebar clickOnExamsLink() {
        examsLink.click();
        return this;
    }

    public SessionSidebar clickOnNotesLink() {
        notesLink.click();
        return this;
    }

    public SessionSidebar clickOnAttachmentsLink() {
        attachmentsLink.click();
        return this;
    }

    public SessionSidebar changeSessionState(SessionStateChange state, boolean acceptChange) {
        WebElementFacade checkBox = find(By.xpath(String.format("(//input[@type='checkbox'])[%d]\")", state.index)));
        shouldBeVisible(checkBox);
        checkBox.click();
        waitFor(alertIsPresent());
        clickOnAlert(acceptChange);
        shouldBeSelected(checkBox);
        return this;
    }

    private void clickOnAlert(boolean acceptChange) {
        if (acceptChange) {
            getAlert().accept();
        } else {
            getAlert().dismiss();
        }
    }
}
