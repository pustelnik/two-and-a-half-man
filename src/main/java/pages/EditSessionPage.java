package pages;

import model.EnrollEnums;
import model.Exam;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.String.format;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.fest.assertions.Assertions.assertThat;
import static pages.AddSessionPage.ManagementMethod.PRODUCT;

/**
 * Created by OtrembskiA on 2016-11-21.
 */
public class EditSessionPage  extends NavigationBar{
    @FindBy(css = "button[data-id=\"SessionDto_ExaminerId\"]")
    private WebElement examinerBtn;
    @FindBy(css = ".btn.btn-form.btn-dark")
    private WebElement saveSessionBtn;
    @FindBy(name = "SessionDto.SpaceForSession")
    private WebElementFacade numberOfSeats;

    public WebElementFacade getNumberOfSeats() {
        return numberOfSeats;
    }

    public WebElement getExaminerBtn() {
        return examinerBtn;
    }
    public WebElement getSaveSessionBtn() {
        return saveSessionBtn;
    }
    public WebElementFacade getExamProductInputBox(EnrollEnums.EGZAM_PRODUCT product) {
        return find(By.cssSelector("input[name=\"SessionDto.Products["+ product.deleteBtnId +"].CapacityForProductSession\"]"));
    }
    public WebElementFacade selectProductBtn() {
        return find(By.xpath("(//button[@type='button'])[5]"));
    }
    public WebElementFacade selectLevelBtn() {
        return find(By.xpath("(//button[@type='button'])[4]"));
    }
    public EditSessionPage selectLevel(List<EnrollEnums.EGZAM_LEVEL> levels) {
        levels.forEach(level -> clickOnDropDown(level.index, 0));
        assertThatSelectionNumberMatches(levels, selectLevelBtn().getAttribute("title"));
        return this;
    }
    public EditSessionPage(WebDriver driver) {
        super(driver);
    }


    public void goToEditSession(String sessionId){
        this.getDriver().get(BasePage.BASE_URL+"/taahm/Session/EditSession/"+sessionId);
    }
    /**
     * Selecting seats number is valid only for product selection.
     * @param method EGZAM_PRODUCT or Session
     */
    public EditSessionPage selectSeatManagementMethod(AddSessionPage.ManagementMethod method) {
        WebElementFacade radioBtn = seatManagementMethod(method);
        radioBtn.shouldBePresent();
        radioBtn.click();
        return this;
    }
    public EditSessionPage selectProduct(List<EnrollEnums.EGZAM_PRODUCT> products) {
        products.forEach(this::clickOnProductDropDown);
        assertThatSelectionNumberMatches(products, selectProductBtn().getAttribute("title"));
        return this;
    }

    public EditSessionPage selectProductByExams(List<Exam> exams) {
        Logger log = getLogger(AddSessionPage.class);
        for (Exam exam : exams) {
            // click on product from dropdown
            log.debug("Clicking on {} product", exam.getProduct().name);
            clickOnProductDropDown(exam.getProduct());
        }
        selectProductBtn().click();
        for (Exam exam : exams) {
            setProductSeatsNumber(exam.getNumberOfSeats(), exam.getProduct());
        }
        return this;
    }

    public WebElementFacade seatManagementMethod(AddSessionPage.ManagementMethod method) {
        if(method.equals(PRODUCT)) {
            return find(By.id("spacePerProduct"));
        }
        return find(By.id("spacePerSession"));
    }

    public EditSessionPage setProductSeatsNumber(String numberOfSeats, EnrollEnums.EGZAM_PRODUCT product) {
        WebElementFacade inputBox = getExamProductInputBox(product);
        shouldBeVisible(inputBox);
        inputBox.clear();
        inputBox.sendKeys(numberOfSeats);
        return this;
    }

    public EditSessionPage selectExaminer(String examiner) {
        fluent().$(".Session-row li[data-original-index]>a>span[class=\"text\"]").forEach(element -> {
            if(element.getText().equals(examiner)) {
                element.click();
            }
        });
        return this;
    }
    private void clickOnDropDown(int index, int elementNumber) {
        WebElementFacade btn = findAll(By.cssSelector(".Session-selectBox li[data-original-index=\"" + index + "\"]")).
                get(elementNumber);
        shouldBeVisible(btn);
        btn.click();
    }

    private void assertThatSelectionNumberMatches(List levels, String levelsTitle) {
        assertThat(levelsTitle).as(format("%s is not %s", levelsTitle, levels.size())).matches(".*\\w?"+levels.size());
    }

    private void clickOnProductDropDown(EnrollEnums.EGZAM_PRODUCT product) {
        List<WebElementFacade> elements = findAll(By.cssSelector("a[class^=\"opt  optionGroup\"]>span[class=\"text\"]"));
        for (WebElementFacade webElementFacade : elements) {
            if(webElementFacade.getTextValue().startsWith(product.name)) {
                webElementFacade.click();
                return;
            }
        }
    }
}
