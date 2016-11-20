package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import model.Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static model.EnrollEnums.EGZAM_PRODUCT;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

/**
 * Created by OtrembskiA on 2016-11-16.
 */

//@At(urls={"#HOST/taahm"})
public class LandingPage extends BasePage{
    private final String URL = "/taahm";

    @FindBy(css = ".text-center.row.Agenda-container.u-resetMargin")
    private WebElement agendaContainer;
    // Navigation bar
    @FindBy(css = "#loginLink")
    private WebElement loginLink;
    @FindBy(css = "dropdownMenu-language")
    private WebElementFacade languageDropDown;


    public enum Language {
        POLISH("Polski"),
        ENGLISH("English");

        final String val;

        Language(String val) {
            this.val = val;
        }
    }

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void goToLandingPage(){
        fluent().goTo(BASE_URL + URL);
    }

    public void clickOnLoginLink() {
        loginLink.click();
    }

    public void languageSelection(Language language) {
        languageDropDown.click();
        languageDropDown.selectByValue(language.val);
        Assert.assertThat(languageDropDown.getSelectedVisibleTextValue(), is(language.val));
    }







    public boolean isIndividualRegistrationAvailable(Session session, EGZAM_PRODUCT product){
        return false;
    }



    public WebElement getIndividualRegisterButton(WebElement examSessionContainer, EGZAM_PRODUCT product){

        for(WebElement row : getExamsTableRows(examSessionContainer)){
            if(row.getAttribute("class").contains("u-hideOnDesktop--tableRow")){
                continue;
            }
            if(product.equals(extractProductFromExamsTableRow(row))){
                return getProductRegisterBtnFromExamsTableRow(row);
            }
        }
        assertNotNull("Landing page : Couldn't find 'Rejestracja indywidualna' button for created exam on exam list",null);
        return null;
    }


    public WebElement getElementByID(String examId){
        try {
            return getDriver().findElement(By.cssSelector("td[data-productsessionid='" + examId + "']"));
        }catch(NoSuchElementException e){}
        assertNotNull("Landing page : Couldn't find element with specified ID="+examId,null);
        return null;
    }


    public WebElement getGroupRegisterButton(WebElement examSessionContainer){
        try {
            return examSessionContainer.findElement(By.cssSelector(".Agenda-groupBtnContainer.btn"));
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : Couldn't find 'Rejestracja grupowa' button for created exam on exam list",null);
            return null;
        }
    }

    public boolean isGroupRegistrationButtonAvailable(WebElement examSessionContainer){
        try {
            examSessionContainer.findElement(By.cssSelector(".Agenda-groupBtnContainer.btn"));
            return true;
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : Couldn't find 'Rejestracja grupowa' button for created exam on exam list",null);
            return false;
        }

    }


    public WebElement getExamSession(Session session){
        List<WebElement> agendaSessions = getExamDayContainer(session).findElements(By.cssSelector(".Agenda-dateContentContainer.row"));
        for(WebElement element : agendaSessions){
            if(extractAgendaHour(element).equals(session.getSessionDate().toLocalTime())){
                return element;
            }
        }
        assertNotNull("Landing page : Created exam not found on exam list",null);
        return null;
    }


    public int getSummaryFreeSeats(Session session){
        WebElement examsContainer = getExamSession(session);
        int count = extractSummarySeats(examsContainer);
        return count;
    }


    /**
     * Count all showed free seats of session exams
     * @param session - session to count free seats from
     * @return summary of free seats
     */
    public int getCountOfFreeSeats(Session session){
        WebElement examsContainer = getExamSession(session);
        int count=0;
        for(WebElement item : examsContainer.findElements(By.cssSelector(".Agenda-textColumn--freePlaces"))){
            count += Integer.parseInt(item.getText());
        }
        return count;
    }


    private int extractSummarySeats(WebElement examsContainer){
        try {
            WebElement element = examsContainer.findElement(By.cssSelector(".Agenda-groupFreePlaces"));
            String value = element.getText().substring(element.getText().lastIndexOf(" "));
            return Integer.parseInt(value);
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : No information found about summary free seats.", null);
        }
        return 0;
    }





    private WebElement getExamDayContainer(Session session){
        List<WebElement> examsDaysFound = getListOfDaysWithExams();
        for (WebElement element : examsDaysFound) {
            if( isAgendaWithDayAndPlaceOfExam(element, session.getCity(), session.getSessionDate().toLocalDate()) ){
                return element;
            }
        }
        assertNotNull("Created exam not found on LandingPage exam list",null);
        return null;
    }




    private List<WebElement> getExamsTableRows(WebElement examSessionContainer){
        try {
            return examSessionContainer.findElement(By.cssSelector(".table.Agenda-table")).findElements(By.cssSelector("tr"));
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : No exams table found.", null);
        }
        return new ArrayList<>();
    }


    private EGZAM_PRODUCT extractProductFromExamsTableRow(WebElement tableRow){
        try {
            WebElement we = tableRow.findElement(By.cssSelector(".Agenda-textColumn--levelName"));
            return EGZAM_PRODUCT.getProduct(we.getText());
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : Incorrect exams table structure. Product name not found.", null);
        }
        return null;
    }



    private WebElement getProductRegisterBtnFromExamsTableRow(WebElement tableRow){
        try {
            return tableRow.findElement(By.cssSelector(".btn.js-individual"));
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : Incorrect exams table structure. Register button not found", null);
        }
        return null;
    }



    private boolean isAgendaWithDayAndPlaceOfExam(WebElement examAgenda, String examCity, LocalDate examDate){
        try {
            String agendaDateRow = examAgenda.findElement(By.cssSelector(".row.Agenda-dateRow")).getText();
            if(!examCity.equals(extractExamPlaceFromAgendaText(agendaDateRow)) || !examDate.equals(extractExamDateFromAgendaText(agendaDateRow)) ){
                return false;
            }
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : No exam agenda found on page.", null);
        }
        return true;
    }




    private String extractExamPlaceFromAgendaText(String agendaDateRow){
        return agendaDateRow.substring(agendaDateRow.indexOf(",")+2);
    }





    private LocalDate extractExamDateFromAgendaText(String agendaDateRow){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMMM yyyy").withLocale(new Locale("pl","PL"));
        return LocalDate.parse(agendaDateRow.substring(0, agendaDateRow.lastIndexOf(",")), dtf);
    }


    private LocalTime extractAgendaHour(WebElement agendaSession){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        try {
            WebElement hourSpan = agendaSession.findElement(By.cssSelector(".Agenda-hourSpan"));
            return LocalTime.parse(hourSpan.getText(), dtf);
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : No agenda time information found on page.", null);
            return null;
        }
    }





    private List<WebElement> getListOfDaysWithExams() {
        return getDriver().findElements(By.cssSelector(".Agenda-dateContainer.col-xs-30.col-sm-offset-1.col-sm-28"));
    }


}
