package pages.session;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jakubp on 17.11.16.
 */
public class SessionDetailsPage extends SessionSidebar {

    private WebElement sessionDate;
    private WebElement sessionTime;
    private WebElement numberOfSeats;
    private WebElement examiner;
    private WebElement postalCode;
    private WebElement address;
    private WebElement additionalInfo;
    private WebElement city;

    protected SessionDetailsPage(WebDriver driver) {
        super(driver);
    }







}
