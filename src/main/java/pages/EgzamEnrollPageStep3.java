package pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by LewarskiT on 2016-11-18.
 */
public class EgzamEnrollPageStep3 extends EgzamEnrollPageBase{
    public EgzamEnrollPageStep3(WebDriver driver) {
        super(driver);
    }


    public void goToStep3() {
        previousButton.click();
    }
    public void finishRegistration() {
        nextButton.click();
    }
}
