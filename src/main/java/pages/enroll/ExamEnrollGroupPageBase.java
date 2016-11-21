package pages.enroll;

import org.openqa.selenium.WebDriver;

/**
 * Created by work on 2016-11-21.
 */
public class ExamEnrollGroupPageBase extends ExamEnrollPageBase{
    private final String URL = "/taahm/RegisterProduct/GetAttendees";

    public ExamEnrollGroupPageBase(WebDriver driver) {
        super(driver);
    }
}
