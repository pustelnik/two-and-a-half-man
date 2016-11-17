package pages;

import model.EnrollEnums;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebDriver;

/**
 * Created by LewarskiT on 2016-11-17.
 */
//@At(urls={"#HOST/taahm/RegisterProduct/GetAddress"})
public class EgzamEnrollPage extends BasePage{
    private final String URL = "/taahm/RegisterProduct/GetAttendee";

    @FindBy(css = "#ProductSelectionDto_ProductLanguage25")
    private FluentWebElement polishLanguage;
    @FindBy(css = "#ProductSelectionDto_ProductLanguage26")
    private FluentWebElement englishLanguage;

    public EgzamEnrollPage(WebDriver driver) {
        super(driver);
        fluent().goTo(BASE_URL+URL);
    }

    public void selelectLanguage(EnrollEnums.PREFFERED_LANGUAGE lang){
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(lang.equals(EnrollEnums.PREFFERED_LANGUAGE.POLISH)){
            polishLanguage.click();
        }else{
            englishLanguage.click();
        }
    }
}
