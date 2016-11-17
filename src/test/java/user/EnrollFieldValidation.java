package user;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.Pages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * Created by LewarskiT on 2016-11-17.
 */
@RunWith(SerenityRunner.class)
public class EnrollFieldValidation {
    @Managed
    public WebDriver driver;

    @ManagedPages
    public Pages pages;

    @Test
    public void validateEgzamDetails(){

    }

    @Test
    public void checkEmailFieldForInvalidInput(){

    }

    @Test
    public void checkPhoneNumberForInvalidInput(){

    }
}
