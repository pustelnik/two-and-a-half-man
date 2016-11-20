package model;

import model.EnrollEnums.EGZAM_LEVEL;
import model.EnrollEnums.EGZAM_PRODUCT;
import pages.AddSessionPage.ManagementMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
/**
 * @author jakubp on 16.11.16.
 */
public class Session {
    private Optional<Integer> id = Optional.empty();
    private LocalDateTime sessionDate;
    private String postalCode;
    private String city;
    private String address;
    private String additionalInfo;
    private ManagementMethod managementMethod;
    private String numberOfSeats;
    private List<EGZAM_LEVEL> levels;
    private List<EGZAM_PRODUCT> products;
    private List<Exam> exams;
    private String examiner;

    public Session(
            LocalDateTime sessionDate,
            String postalCode,
            String city,
            String address,
            String additionalInfo,
            ManagementMethod managementMethod,
            String numberOfSeats,
            List<EGZAM_LEVEL> levels,
            List<EGZAM_PRODUCT> products,
            List<Exam> exams,
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
        this.exams = exams;
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

    public List<EGZAM_LEVEL> getLevels() {
        return levels;
    }

    public List<EGZAM_PRODUCT> getProducts() {
        return products;
    }

    public String getExaminer() {
        return examiner;
    }

    public List<Exam> getExams() {
        return exams;
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

    public void setLevels(List<EGZAM_LEVEL> levels) {
        this.levels = levels;
    }

    public void setProducts(List<EGZAM_PRODUCT> products) {
        this.products = products;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public void setExaminer(String examiner) {
        this.examiner = examiner;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public void setId(int id) {
        this.id = Optional.of(id);
    }

    @Override
    public String toString() {
        return sessionDate + " " + city + " " + address + " " + postalCode;
    }
}
