package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jakubp on 11.11.16.
 */
@At("#HOST/taahm/Account/Login")
public class LoginPage extends BasePage {
    @FindBy(css = "#Email")
    private WebElement emailInput;
    @FindBy(css = "#PasswordPass")
    private WebElement passwordInput;
    @FindBy(css = "#RememberMe")
    private WebElementFacade rememberMe;
    @FindBy(css = ".btn.btn-light.btn-form.u-float-Left>a")
    private WebElement forgotPassBtn;
    @FindBy(css = ".btn.btn-dark.btn-form.u-floatRight")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @WhenPageOpens
    public void waitUntilVisible() {
        element(emailInput).waitUntilVisible();
    }

    public LoginPage enterCredentials(String email, String pass) {
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
}
