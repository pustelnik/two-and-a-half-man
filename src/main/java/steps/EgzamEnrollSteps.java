package steps;

import model.EnrollEnums;
import net.thucydides.core.pages.Pages;
import pages.EgzamEnrollPage;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class EgzamEnrollSteps extends BaseScenarioSteps {
    private final EgzamEnrollPage egzamEnrollPage = getCurrentPage(EgzamEnrollPage.class);

    public EgzamEnrollSteps(Pages pages) {
        super(pages);
    }

    public void selectLanguage(){
        egzamEnrollPage.selelectLanguage(EnrollEnums.PREFFERED_LANGUAGE.POLISH);
    }

}
