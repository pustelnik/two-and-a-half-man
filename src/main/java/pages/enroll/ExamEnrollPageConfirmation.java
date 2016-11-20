package pages.enroll;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * Created by LewarskiT on 2016-11-18.
 */
//@At(urls="#HOST/taahm/RegisterProduct/Complete")
public class ExamEnrollPageConfirmation extends ExamEnrollPageBase{

    public ExamEnrollPageConfirmation(WebDriver driver) {
        super(driver);
    }
    
    @FindBy(css = "body > div.container.body-content > div > div > div > h3")
    private WebElement confirmationMessage;
    
    public boolean isConfirmationVisible(){
        return confirmationMessage.isDisplayed();
    }
    
    public String getConfirmationMessage(){
        return confirmationMessage.getText();
    }
    
}
