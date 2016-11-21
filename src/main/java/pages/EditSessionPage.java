package pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by OtrembskiA on 2016-11-21.
 */
public class EditSessionPage  extends NavigationBar{
    public EditSessionPage(WebDriver driver) {
        super(driver);
    }


    public void goToEditSession(String sessionId){
        this.getDriver().get(BasePage.BASE_URL+"/taahm/Session/EditSession/"+sessionId);
    }
}
