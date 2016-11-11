import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.LoginSteps;

/**
 * @author jakubp on 11.11.16.
 */
@RunWith(SerenityRunner.class)
public class LoginTest {

    @Managed
    private WebDriver driver;

    @Steps
    private LoginSteps steps;

    @Test
    public void shouldNotLogin() {
        steps.enterInvalidCredentials();
    }
}
