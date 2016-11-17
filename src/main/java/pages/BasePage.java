package pages;

import com.typesafe.config.ConfigFactory;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.WhenPageOpens;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jakubp on 11.11.16.
 */
public class BasePage extends PageObject {

    public static final String BASE_URL = ConfigFactory.load("serenity.properties").getString("webdriver.base.url");

    BasePage(WebDriver driver) {
        super(driver);
    }

    @WhenPageOpens
    public void waitUntilVisible() {
        fluent().await().untilPage().isLoaded();
    }

    public void shouldBeSelected(WebElement element) {
        Assertions.assertThat(element.isSelected()).as(element.getText() + "is not selected").isTrue();
    }
}
