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
import java.util.List;
import java.util.Locale;

import static model.EnrollEnums.EGZAM_LEVEL;
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







    public boolean isIndividualRegistrationAvailable(Session session, EGZAM_PRODUCT product, EGZAM_LEVEL level){
        return false;
    }

    public boolean isGroupRegistrationAvailable(Session session){

        return false;
    }



    public WebElement getIndividualRegisterButton(WebElement examSessionContainer, EGZAM_PRODUCT product){

        //session container - ma tylko egzaminy z konkretnej jednej godziny
        //musze wylistowac wszyskie wiersze tablicy z egzaminami ( jest tylko jedna table i potrzebuje jej <tr>
        //w tych <tr>  bede sprawdzal czy dany row jest szukanym egzaminem
        //i jezeli tak to zwracam buton z tego wiersza

        try {
            WebElement examsTable = examSessionContainer.findElement(By.cssSelector(".table.Agenda-table"));
            List<WebElement> examRow = examsTable.findElements(By.cssSelector("tr"));
            for(WebElement row : examRow){
                System.out.println(row.getText());
                WebElement productName = row.findElement(By.cssSelector(".Agenda-textColumn--levelName"));
            }
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : Couldn't find 'Rejestracja indywidualna' button for created exam on exam list",null);
        }
        return null;
    }

    public WebElement getIndividualRegisterButtonById(String examId){
        try {
            return getDriver().findElement(By.cssSelector("td[data-productsessionid='" + examId + "']"));
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : Couldn't find 'Rejestracja indywidualna' button for created exam on exam list",null);
            return null;
        }
    }


    public WebElement getGroupRegisterButton(WebElement examSessionContainer){
        try {
            WebElement registerGroupBtn = examSessionContainer.findElement(By.cssSelector(".Agenda-groupBtnContainer.btn"));
            return registerGroupBtn;
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : Couldn't find 'Rejestracja grupowa' button for created exam on exam list",null);
            return null;
        }
    }


    public WebElement getExamSession(Session session){
        WebElement examDayContainer = getExamDayContainer(session);
        assertNotNull("Created exam not found on LandingPage exam list",examDayContainer);

        List<WebElement> agendaSessions = examDayContainer.findElements(By.cssSelector(".Agenda-dateContentContainer.row"));
        for(WebElement element : agendaSessions){
            if(getAgendaHour(element).equals(session.getSessionDate().toLocalTime())){
                return element;
            }
        }
        return null;
    }







    private LocalTime getAgendaHour(WebElement agendaSession){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        try {
            WebElement hourSpan = agendaSession.findElement(By.cssSelector(".Agenda-hourSpan"));
            return LocalTime.parse(hourSpan.getText(), dtf);
        }catch(NoSuchElementException e){
            assertNotNull("Landing page : No agenda time information found on page.", null);
            return null;
        }
    }





    private WebElement getExamDayContainer(Session session){
        List<WebElement> examsDaysFound = getListOfDaysWithExams();
        for (WebElement element : examsDaysFound) {
            if( isAgendaWithDayAndPlaceOfExam(element, session.getCity(), session.getSessionDate().toLocalDate()) ){
                return element;
            }
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
        return LocalDate.parse(agendaDateRow.substring(0,agendaDateRow.lastIndexOf(",")), dtf);
    }






    private List<WebElement> getListOfDaysWithExams() {
        return getDriver().findElements(By.cssSelector(".Agenda-dateContainer.col-xs-30.col-sm-offset-1.col-sm-28"));
    }


}
