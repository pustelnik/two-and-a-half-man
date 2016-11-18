package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.stream.Collectors;

/**
 * Created by LewarskiT on 2016-11-18.
 */
//@At(urls={"#HOST/taahm/RegisterProduct/GetAttendee"})
public class ExamEnrollPageBase extends BasePage {
    private final String URL = "/taahm/RegisterProduct/GetAttendee";

    @FindBy(css = ".Register-backBtn button")
    protected WebElement previousButton;

    @FindBy(css = ".Register-forwardBtn button")
    protected WebElement nextButton;

    public ExamEnrollPageBase(WebDriver driver) {
        super(driver);
        reload();
    }

    protected String getValidationMessage(WebElement elementToValidate) {
        if (elementToValidate != null && elementToValidate.isDisplayed()) {
            return elementToValidate.getText();
        }
        return null;
    }

    public boolean checkIfValidationWasTriggered(){
        return findAll(By.cssSelector(".field-validation-error")).stream().filter(element -> element.isDisplayed()).collect(Collectors.toList()).size()>0;
    }
}
