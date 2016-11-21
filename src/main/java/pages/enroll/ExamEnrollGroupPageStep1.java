package pages.enroll;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertNotNull;

/**
 * Created by work on 2016-11-21.
 */
public class ExamEnrollGroupPageStep1 extends ExamEnrollGroupPageBase{
    public ExamEnrollGroupPageStep1(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[name='name']")
    private WebElement firstName;

    @FindBy(css = "input[name='surname']")
    private WebElement lastName;

    @FindBy(css = "input[name='email']")
    private WebElement email;

    @FindBy(css = "input[name='phone']")
    private WebElement phone;

    @FindBy(css = "input[name='certificateNumber']")
    private WebElement ownedCertificateNumber;

    @FindBy(css = "input[name='certificatePicker']")
    private WebElement ownedCertificateIssueDate;

    @FindBy(css = "input[name='certificateProvider']")
    private WebElement ownedCertificateIssuedBy;


    public void setFirstName(String firstNameString) {
        firstName.sendKeys(firstNameString);
    }

    public void setLastName(String lastNameString) {
        lastName.sendKeys(lastNameString);
    }

    public void setEmail(String emailString) {
        email.sendKeys(emailString);
    }

    public void setPhone(String phoneString) {
        phone.sendKeys(phoneString);
    }

    public void setOwnedCertificateNumber(String certificateNumber) {
        ownedCertificateNumber.sendKeys(certificateNumber);
    }

    public void setOwnedCertificateIssueDate(String certificateIssueDate) {
        ownedCertificateIssueDate.sendKeys(certificateIssueDate);
    }

    public void setOwnedCertificateIssuedBy(String certificateIssuedBy) {
        ownedCertificateIssuedBy.sendKeys(certificateIssuedBy);
    }

    public void selectExamType(String examType){
        try {
            getDriver().findElement(By.xpath("//input[@data-name='"+examType+"']/parent::*/label")).click();
        }catch(NoSuchElementException e){}
        assertNotNull("Group enrollment page : Couldn't find element with specified Type=" + examType, null);
    }

    public boolean isOwnedCertificateRequired(){
        try{
            return (ownedCertificateNumber != null && ownedCertificateNumber.isDisplayed());
        }catch(NoSuchElementException nsee){

        }
        return false;
    }

    public void goToStep2() {
        nextButton.click();
    }
}
