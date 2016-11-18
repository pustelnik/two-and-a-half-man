package steps.session;

import pages.AddSessionPage;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static pages.AddSessionPage.Level.*;
import static pages.AddSessionPage.Level.EXPERT;
import static pages.AddSessionPage.Product.BASIC_ISTQB;

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
                LocalDateTime.now().plusWeeks(2),
                "95-100",
                "Lodz",
                "Fabryczna 17",
                "brak",
                AddSessionPage.ManagementMethod.SESSION,
                "10",
                Arrays.asList(BASIC, EXPERT),
                Collections.singletonList(BASIC_ISTQB),
                "Alan Harper");
    }

    public Session build() {
        return session;
    }
}
