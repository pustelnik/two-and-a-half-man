package pages;

import com.jayway.awaitility.Duration;
import model.EnrollEnums;
import model.EnrollEnums.EGZAM_PRODUCT;
import model.Exam;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.logging.log4j.Logger;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.jayway.awaitility.Awaitility.waitAtMost;
import static java.lang.String.format;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.fest.assertions.Assertions.assertThat;
import static pages.AddSessionPage.ManagementMethod.PRODUCT;

/**
 * @author jakubp
 */
public class AddSessionPage extends NavigationBar {

    @FindBy(id = "SessionDto_Location_PostalCode")
    private WebElement postalCode;
    @FindBy(id = "SessionDto_Location_City")
    private WebElement city;
    @FindBy(id = "SessionDto_Location_Address")
    private WebElement address;
    @FindBy(id = "SessionDto_AdditionalInformation")
    private WebElement additionalData;
    @FindBy(name = "SessionDto.SpaceForSession")
    private WebElementFacade numberOfSeats;
    @FindBy(css = "button[data-id=\"SessionDto_ExaminerId\"]")
    private WebElement examinerBtn;
    @FindBy(css = ".btn.btn-form.btn-light")
    private WebElement cancelBtn;
    @FindBy(css = ".btn.btn-form.btn-dark")
    private WebElement saveSessionBtn;

    public AddSessionPage(WebDriver driver) {
        super(driver);
    }

    public  enum ManagementMethod {
        PRODUCT, SESSION
    }

    public FluentWebElement sessionDateInput() {
        return fluent().$("#SessionDto_Date").first();
    }

    /**
     * Sets session date in format dd.MM.yyyy HH:mm
     * @param dateTime Session date
     */
    public AddSessionPage setSessionDateInput(LocalDateTime dateTime) {
        String sessionDate = dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        sessionDateInput().text(sessionDate);
        String actualSessionDate = sessionDateInput().getValue();
        waitAtMost(Duration.FIVE_SECONDS).until(() -> sessionDateInput().getValue().equals(sessionDate));
        assertThat(actualSessionDate).as("Session date is not " + dateTime).isEqualTo(sessionDate);
        return this;
    }

    /**
     * Selecting seats number is valid only for product selection.
     * @param method EGZAM_PRODUCT or Session
     */
    public AddSessionPage selectSeatManagementMethod(ManagementMethod method) {
        WebElementFacade radioBtn = seatManagementMethod(method);
        radioBtn.shouldBePresent();
        radioBtn.click();
//        shouldBeSelected(radioBtn);
        return this;
    }

    /**
     * Assumes that user has already clicked on selectLevelsBtn.
     * Asserts that displayed number of selected levels is equal to
     * size of levels list.
     * @param levels List of levels to click on.
     */
    public AddSessionPage selectLevel(List<EnrollEnums.EGZAM_LEVEL> levels) {
        levels.forEach(level -> clickOnDropDown(level.index, 0));
        assertThatSelectionNumberMatches(levels, selectLevelBtn().getAttribute("title"));
        return this;
    }

    /**
     * Assumes that user has already clicked on selectProductsBtn.
     * Asserts that displayed number of selected products is equal to
     * size of products list.
     * @param products List of products to click on.
     */
    public AddSessionPage selectProduct(List<EGZAM_PRODUCT> products) {
        products.forEach(this::clickOnProductDropDown);
        assertThatSelectionNumberMatches(products, selectProductBtn().getAttribute("title"));
        return this;
    }

    public AddSessionPage selectProductByExams(List<Exam> exams) {
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

    /**
     * Removed product from drop down list by clicking on remove button.
     * @param product EGZAM_PRODUCT to be removed
     */
    public AddSessionPage removeProduct(EGZAM_PRODUCT product) {
        WebElementFacade deleteBtn = find(By.id(product.deleteBtnId));
        shouldBeVisible(deleteBtn);
        deleteBtn.click();
        return this;
    }

    public AddSessionPage selectExaminer(String examiner) {
        fluent().$(".Session-row li[data-original-index]>a>span[class=\"text\"]").forEach(element -> {
            if(element.getText().equals(examiner)) {
                element.click();
            }
        });
        return this;
    }

    public boolean isCurrentPageAddSessionPage() {
        return getDriver().getCurrentUrl().endsWith("AddSession");
    }

    public boolean isCurrentPageSessionDetailsPage() {
        return getDriver().getCurrentUrl().matches(".*Session/Details/\\d*");
    }

    public WebElementFacade selectLevelBtn() {
        return find(By.xpath("(//button[@type='button'])[4]"));
    }

    public WebElementFacade selectProductBtn() {
        return find(By.xpath("(//button[@type='button'])[5]"));
    }

    public WebElement getPostalCode() {
        return postalCode;
    }

    public WebElement getCity() {
        return city;
    }

    public WebElement getAddress() {
        return address;
    }

    public WebElement getAdditionalData() {
        return additionalData;
    }

    public WebElementFacade getNumberOfSeats() {
        return numberOfSeats;
    }

    public WebElement getExaminerBtn() {
        return examinerBtn;
    }

    public WebElement getCancelBtn() {
        return cancelBtn;
    }

    public WebElement getSaveSessionBtn() {
        return saveSessionBtn;
    }

    public WebElementFacade seatManagementMethod(ManagementMethod method) {
        if(method.equals(PRODUCT)) {
            return find(By.id("spacePerProduct"));
        }
        return find(By.id("spacePerSession"));
    }

    private void assertThatSelectionNumberMatches(List levels, String levelsTitle) {
        assertThat(levelsTitle).as(format("%s is not %s", levelsTitle, levels.size())).matches(".*\\w?"+levels.size());
    }

    private void clickOnDropDown(int index, int elementNumber) {
        WebElementFacade btn = findAll(By.cssSelector(".Session-selectBox li[data-original-index=\"" + index + "\"]")).
                get(elementNumber);
        shouldBeVisible(btn);
        btn.click();
    }

    private void clickOnProductDropDown(EGZAM_PRODUCT product) {
        List<WebElementFacade> elements = findAll(By.cssSelector(".Session-selectBox li[data-original-index]"));
        for (WebElementFacade webElementFacade : elements) {
            if(webElementFacade.getTextValue().startsWith(product.name)) {
                webElementFacade.click();
                return;
            }
        }
    }

    public AddSessionPage setProductSeatsNumber(String numberOfSeats, EGZAM_PRODUCT product) {
        WebElementFacade inputBox = getExamProductInputBox(product);
        shouldBeVisible(inputBox);
        inputBox.clear();
        inputBox.sendKeys(numberOfSeats);
        return this;
    }

    public WebElementFacade getExamProductInputBox(EGZAM_PRODUCT product) {
        return find(By.cssSelector("input[name=\"SessionDto.Products["+ product.deleteBtnId +"].CapacityForProductSession\"]"));
    }

    public AddSessionPage deleteProductFromList(int productInputNumber) {
        WebElementFacade trashIcon = find(By.cssSelector("span[data-id=\""+ productInputNumber +"\"]"));
        shouldBeVisible(trashIcon);
        trashIcon.click();
        assertThat(trashIcon.isVisible()).as("Product deleted").isFalse();
        return this;
    }
}
