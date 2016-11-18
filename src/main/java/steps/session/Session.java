package steps.session;

import pages.AddSessionPage.Level;
import pages.AddSessionPage.ManagementMethod;
import pages.AddSessionPage.Product;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jakubp on 16.11.16.
 */
public class Session {
    private LocalDateTime sessionDate;
    private String postalCode;
    private String city;
    private String address;
    private String additionalInfo;
    private ManagementMethod managementMethod;
    private String numberOfSeats;
    private List<Level> levels;
    private List<Product> products;
    private String examiner;

    public Session(
            LocalDateTime sessionDate,
            String postalCode,
            String city,
            String address,
            String additionalInfo,
            ManagementMethod managementMethod,
            String numberOfSeats,
            List<Level> levels,
            List<Product> products,
            String examiner) {
        this.sessionDate = sessionDate;
        this.postalCode = postalCode;
        this.city = city;
        this.address = address;
        this.additionalInfo = additionalInfo;
        this.managementMethod = managementMethod;
        this.numberOfSeats = numberOfSeats;
        this.levels = levels;
        this.products = products;
        this.examiner = examiner;
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public ManagementMethod getManagementMethod() {
        return managementMethod;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getExaminer() {
        return examiner;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void setManagementMethod(ManagementMethod managementMethod) {
        this.managementMethod = managementMethod;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setExaminer(String examiner) {
        this.examiner = examiner;
    }
}
