package model;

import model.EnrollEnums.EGZAM_LEVEL;
import model.EnrollEnums.EGZAM_PRODUCT;

import java.util.Optional;

/**
 * @author jakubp on 20.11.16.
 * Container class for Exam in a meaning of /taahm/Session/Exams/ page
 */
public class Exam {
    private final EGZAM_PRODUCT product;
    private final String numberOfSeats;
    private final EGZAM_LEVEL level;
    private boolean registrationEnabled;
    private Optional<Integer> id = Optional.empty();

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

    public Optional<Integer> getId() {
        return id;
    }

    public void setId(int id) {
        this.id = Optional.of(id);
    }
}
