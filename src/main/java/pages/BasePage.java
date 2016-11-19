package pages;

import com.typesafe.config.ConfigFactory;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.WhenPageOpens;
import org.fest.assertions.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;

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
        Assertions.assertThat(element.isSelected()).as(element.getText() + "Element is selected").isTrue();
    }

    public void decorateElement(WebElement element) {
        if (element != null) {
            ((JavascriptExecutor) getDriver()).executeScript("var handle = arguments[0];\n" +
                    "handle.style.border='2px solid red';\n" +
                    "setTimeout(\n" +
                    "    function() {\n" +
                    "      handle.style.border='0px solid red';\n" +
                    "    }, 1000);", element);
        }
    }

    public void switchToOpenWindow() {
        Iterator var1 = this.getDriver().getWindowHandles().iterator();

        while(var1.hasNext()) {
            String newWinHandle = (String)var1.next();
            this.getDriver().switchTo().window(newWinHandle);
        }

        this.maximizePage();
    }

    public String switchToFirstWindow() {
        String newWinHandle = (String)this.getDriver().getWindowHandles().iterator().next();
        this.getDriver().switchTo().window(newWinHandle);
        this.maximizePage();
        return newWinHandle;
    }

    public void maximizePage() {
        this.getDriver().manage().window().maximize();
    }

    public void reload(){
        switchToFirstWindow();
        PageFactory.initElements(getDriver(), this);
        decorateElement(find(By.cssSelector("body")));
    }

}
