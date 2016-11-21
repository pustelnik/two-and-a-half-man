package pages;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import com.jayway.awaitility.core.ConditionTimeoutException;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.jayway.awaitility.Awaitility.waitAtMost;

/**
 * @author jakubp on 11.11.16.
 */
public class LoginPage extends BasePage {

    @FindBy(css = "#Email")
    private WebElement emailInput;
    @FindBy(css = "#PasswordPass")
    private WebElement passwordInput;
    @FindBy(css = "#RememberMe")
    private WebElementFacade rememberMe;
    @FindBy(css = ".btn.btn-light.btn-form.u-float-Left>a")
    private WebElement forgotPassBtn;
    @FindBy(css = ".btn.btn-dark.btn-form")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterCredentials(String email, String pass) {
        String loginPageUrl = BASE_URL.concat("/taahm/Account/Login");
        if(!isLoginPage(loginPageUrl)) {
            fluent().goTo(loginPageUrl);
        }
        emailInput.sendKeys(email);
        passwordInput.sendKeys(pass);
        return this;
    }

    public LoginPage pressLoginBtn() {
        loginBtn.click();
        return this;
    }

    public LoginPage pressForgottenPasswordBtn() {
        forgotPassBtn.click();
        return this;
    }

    public LoginPage withRememberMe() {
        if(!rememberMe.isSelected()) {
            rememberMe.click();
        }
        return this;
    }

    public <T extends WebElementFacade> T invalidCredentialsErrorMsg() {
        return this.find(By.cssSelector(".validation-summary-errors.text-danger>ul>li"));
    }

    public boolean isInvalidCredentialsErrorMsgVisible() {
        return invalidCredentialsErrorMsg().isVisible();
    }

    private boolean isLoginPage(String loginPageUrl) {
        return this.getDriver().getCurrentUrl().equals(loginPageUrl);
    }
}
