package model;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import model.EnrollEnums.EGZAM_LEVEL;
import model.EnrollEnums.EGZAM_PRODUCT;
import pages.AddSessionPage.ManagementMethod;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static model.EnrollEnums.EGZAM_LEVEL.BASIC;
import static model.EnrollEnums.EGZAM_LEVEL.EXPERT;
import static model.EnrollEnums.EGZAM_PRODUCT.BASIC_ISTQB;
import static pages.AddSessionPage.ManagementMethod.PRODUCT;
import static pages.AddSessionPage.ManagementMethod.SESSION;

/**
 * @author jakubp on 16.11.16.
 */
public class SessionBuilder {

    private final Session session;

    public static SessionBuilder Instance() {
        return new SessionBuilder();
    }

    private SessionBuilder() {
        session = new Session(
                LocalDateTime.now().plusWeeks(1).plusMinutes(1).withSecond(0).withNano(0),
                "95-100",
                "Lodz"+ new Random().nextInt((100-0)+1)+1,
                "Fabryczna 17",
                "brak",
                SESSION,
                "10",
                Arrays.asList(BASIC, EXPERT),
                Collections.singletonList(BASIC_ISTQB),
                new ArrayList<>(),
                "Alan Harper");
    }

    public SessionBuilder loadSessionFromConfig(int session) {
        Config conf = ConfigFactory.load();
        List<? extends Config> configList = conf.getConfigList("test.sessions");
        if(configList.size() < session) {
            throw new NotExistingSessionConfig();
        }

        Config selectedSession = configList.get(session-1);
        if(ManagementMethod.valueOf(selectedSession.getString("managementMethod")).equals(SESSION)) {
            return withSessionDate(LocalDateTime.now().plusWeeks(selectedSession.getInt("sessionDate")).withSecond(0).withNano(0)).
                    withPostalCode(selectedSession.getString("postalCode")).
                    withCity(selectedSession.getString("city")).
                    withAddress(selectedSession.getString("address")).
                    withAdditionalInfo(selectedSession.getString("additionalInfo")).
                    withManagementMethod(SESSION).
                    withNumberOfSeats(selectedSession.getString("numberOfSeats")).
                    withLevels(createLevelsList(selectedSession.getStringList("levels"))).
                    withProducts(createProductList(selectedSession.getStringList("products"))).
                    withExaminer(selectedSession.getString("examiner"));
        }
        return withSessionDate(LocalDateTime.now().plusWeeks(selectedSession.getInt("sessionDate")).withSecond(0).withNano(0)).
                withPostalCode(selectedSession.getString("postalCode")).
                withCity(selectedSession.getString("city")).
                withAddress(selectedSession.getString("address")).
                withAdditionalInfo(selectedSession.getString("additionalInfo")).
                withManagementMethod(PRODUCT).
                withExams(createExamsList(selectedSession.getConfigList("products"))).
                withLevels(createLevelsList(selectedSession.getStringList("levels"))).
                withExaminer(selectedSession.getString("examiner"));
    }

    private List<Exam> createExamsList(List<? extends Config> products) {
        return products.stream().map(product -> new Exam(
                EGZAM_LEVEL.valueOf(product.getString("level")),
                EGZAM_PRODUCT.valueOf(product.getString("name")),
                product.getString("numberOfSeats")))
                .collect(Collectors.toList());
    }

    private List<EGZAM_PRODUCT> createProductList(List<String> products) {
        return products.stream().map(EGZAM_PRODUCT::valueOf).collect(Collectors.toList());
    }

    private List<EGZAM_LEVEL> createLevelsList(List<String> levels) {
        return levels.stream().map(EGZAM_LEVEL::valueOf).collect(Collectors.toList());
    }

    public SessionBuilder withSessionDate(LocalDateTime date) {
        this.session.setSessionDate(date);
        return this;
    }

    public SessionBuilder withPostalCode(String postalCode) {
        this.session.setPostalCode(postalCode);
        return this;
    }

    public SessionBuilder withCity(String city) {
        this.session.setCity(city);
        return this;
    }

    public SessionBuilder withAddress(String address) {
        this.session.setAddress(address);
        return this;
    }

    public SessionBuilder withAdditionalInfo(String additionalInfo) {
        this.session.setAdditionalInfo(additionalInfo);
        return this;
    }

    public SessionBuilder withManagementMethod(ManagementMethod managementMethod) {
        this.session.setManagementMethod(managementMethod);
        return this;
    }

    public SessionBuilder withNumberOfSeats(String numberOfSeats) {
        this.session.setNumberOfSeats(numberOfSeats);
        return this;
    }

    public SessionBuilder withLevels(List<EGZAM_LEVEL> levels) {
        this.session.setLevels(levels);
        return this;
    }

    public SessionBuilder withProducts(List<EGZAM_PRODUCT> products) {
        this.session.setProducts(products);
        return this;
    }

    public SessionBuilder withExams(List<Exam> exams) {
        this.session.setExams(exams);
        return this;
    }

    public SessionBuilder withExaminer(String examiner) {
        this.session.setExaminer(examiner);
        return this;
    }

    public Session build() {
        return session;
    }

    private class NotExistingSessionConfig extends RuntimeException {
    }
}
