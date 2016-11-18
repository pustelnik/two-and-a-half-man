package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import pages.EgzamEnrollPageStep1;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class EgzamEnrollSteps extends BaseScenarioSteps {
    private final EgzamEnrollPageStep1 egzamEnrollPageStep1 = getCurrentPage(EgzamEnrollPageStep1.class);

    public EgzamEnrollSteps(Pages pages) {
        super(pages);

    }


    @Step
    public void fillFieldsFromStep1(){
        //egzamEnrollPageStep1.selelectLanguage();

    }
    @Step
    public void fillFieldsFromStep2(){

    }
    @Step
    public void fillFieldsFromStep3(){

    }

}
