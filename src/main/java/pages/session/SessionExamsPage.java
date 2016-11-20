package pages.session;

import model.Exam;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static model.EnrollEnums.EGZAM_PRODUCT;

/**
 * @author jakubp on 17.11.16.
 */
public class SessionExamsPage extends SessionNavigation {

    @FindBy(css = ".btn.btn-dark.btn-backofficeTop--small")
    private WebElement editBtn;
    public SessionExamsPage(WebDriver driver) {
        super(driver);
    }

    public void openSessionExamsPage(int sessionID){
        fluent().goTo(BASE_URL + "/taahm/Session/Exams/"+sessionID);
    }

    public String getExamToSession(EGZAM_PRODUCT product){
        WebElement productElement = find(By.xpath("//div[contains(text(),'"+product.name+"')]/parent::*/a"));
        return productElement.getAttribute("data-productid");
    }

    public SessionExamsPage clickOnEditBtn() {
        editBtn.click();
        return this;
    }

    // TODO add number of seats per exam and egzam level validation
    public List<Exam> getExams() {
        List<Exam> examsResult = new ArrayList<>();
        for (WebElementFacade webElementFacade : findAll(By.cssSelector(".row .Exam-examList>div"))) {
            for (EGZAM_PRODUCT product : EGZAM_PRODUCT.values()) {
                if(webElementFacade.getText().startsWith(product.name)) {
                    WebElementFacade link = webElementFacade.find(By.cssSelector("a"));
                    Exam exam = new Exam(product.egzam_level, product, "0");
                    exam.setId(link.getAttribute("data-productid"));
                    examsResult.add(exam);
                }
            }
        }
        return examsResult;
    }
}
