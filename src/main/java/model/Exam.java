package model;

import model.EnrollEnums.EGZAM_LEVEL;
import model.EnrollEnums.EGZAM_PRODUCT;

/**
 * @author jakubp on 20.11.16.
 * Container class for Exam in a meaning of /taahm/Session/Exams/ page
 */
public class Exam {
    private final EGZAM_LEVEL level;
    private final EGZAM_PRODUCT product;
    private final String numberOfSeats;
    private boolean registrationEnabled;

    public Exam(
            EGZAM_LEVEL level,
            EGZAM_PRODUCT product,
            String numberOfSeats) {
        this.level = level;
        this.product = product;
        this.numberOfSeats = numberOfSeats;
    }

    public EGZAM_LEVEL getLevel() {
        return level;
    }

    public EGZAM_PRODUCT getProduct() {
        return product;
    }

    public String getNumberOfSeats() {
        return numberOfSeats;
    }

    public boolean isRegistrationEnabled() {
        return registrationEnabled;
    }

    public void setRegistrationEnabled(boolean registrationEnabled) {
        this.registrationEnabled = registrationEnabled;
    }
}
