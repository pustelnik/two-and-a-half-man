package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import steps.session.Session;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

/**
 * Created by OtrembskiA on 2016-11-16.
 */

@At("https://examplanner.pgs-soft.com/taahm")
public class LandingPage extends BasePage {

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

    public void clickOnLoginLink() {
        loginLink.click();
    }

    public void languageSelection(Language language) {
        languageDropDown.click();
        languageDropDown.selectByValue(language.val);
        Assert.assertThat(languageDropDown.getSelectedVisibleTextValue(), is(language.val));
    }







    public boolean isIndividualRegistrationAvailable(Session session, AddSessionPage.Product product, AddSessionPage.Level level){
        return false;
    }

    public boolean isGroupRegistrationAvailable(Session session){

        return false;
    }


    public void clickGroupRegisterForExam(Session session){
        WebElement examSessionContainer = getExamSession(session);
        assertNotNull("Created exam not found on LandingPage exam list",examSessionContainer);

        //click on group register in container
        WebElement registerGroupBtn = getGroupRegisterButton(examSessionContainer);
        assertNotNull("Couldn't find 'Rejestracja grupowa' button for created exam on Landing page exam list",examSessionContainer);

        registerGroupBtn.click();

    }


    public void clickIndividualRegisterForExamByID(String examId){
        WebElement registerIndividualBtn = getIndividualRegisterButtonById(examId);
        assertNotNull("Couldn't find 'Rejestracja indywidualna' button for created exam on Landing page exam list",registerIndividualBtn);

        registerIndividualBtn.click();
    }








    private WebElement getIndividualRegisterButtonById(String examId){
        return  getDriver().findElement(By.cssSelector("td[data-productsessionid='"+examId+"']"));
    }


    private WebElement getGroupRegisterButton(WebElement examSessionContainer){
        WebElement registerGroupBtn = examSessionContainer.findElement(By.cssSelector(".Agenda-groupBtnContainer.btn"));
        return registerGroupBtn;
    }


    private WebElement getExamSession(Session session){
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
        WebElement hourSpan = agendaSession.findElement(By.cssSelector(".Agenda-hourSpan"));
        return LocalTime.parse(hourSpan.getText(), dtf);
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
        String agendaDateRow = examAgenda.findElement(By.cssSelector(".row.Agenda-dateRow")).getText();

        if(!examCity.equals(extractExamPlaceFromAgendaText(agendaDateRow)) || !examDate.equals(extractExamDateFromAgendaText(agendaDateRow)) ){
            return false;
        }
        return true;
    }




    private String extractExamPlaceFromAgendaText(String agendaDateRow){
        return agendaDateRow.substring(agendaDateRow.indexOf(",")+2);
    }





    private LocalDate extractExamDateFromAgendaText(String agendaDateRow){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(new Locale("pl","PL"));
        return LocalDate.parse(agendaDateRow.substring(0,agendaDateRow.lastIndexOf(",")), dtf);
    }






    private List<WebElement> getListOfDaysWithExams() {
        return getDriver().findElements(By.cssSelector(".Agenda-dateContainer.col-xs-30.col-sm-offset-1.col-sm-28"));
    }


}
