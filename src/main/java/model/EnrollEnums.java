package model;

import static model.EnrollEnums.EGZAM_LEVEL.*;

import java.util.NoSuchElementException;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class EnrollEnums {
    public enum PREFFERED_LANGUAGE {
        POLISH(25), ENGLISH(26);
        final int index;

        PREFFERED_LANGUAGE(int index) {
            this.index = index;
        }
        static PREFFERED_LANGUAGE getValue(int value) {
            for(PREFFERED_LANGUAGE e: PREFFERED_LANGUAGE.values()) {
                if(e.index == value) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum PREFFERED_EGZAM_TYPE {
        PAPER("Paper"), ELECTRONIC("Electronic");
        final String type;

        PREFFERED_EGZAM_TYPE(String type) {
            this.type = type;
        }
        static PREFFERED_EGZAM_TYPE getValue(String value) {
            for(PREFFERED_EGZAM_TYPE e: PREFFERED_EGZAM_TYPE.values()) {
                if(e.type.equals(value)) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum INVOICE_TYPE {
        NONE("None"), ELECTRONIC("Electronic"), PAPER("Paper");
        final String type;

        INVOICE_TYPE(String type) {
            this.type = type;
        }
        static INVOICE_TYPE getValue(String value) {
            for(INVOICE_TYPE e: INVOICE_TYPE.values()) {
                if(e.type.equals(value)) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum EGZAM_LEVEL {
        BASIC(0), ADVANCE(1), EXPERT(2), OTHER(3);

        /**
         * Session level data-original-index
         */
        public final int index;

        EGZAM_LEVEL(int index) {
            this.index = index;
        }
    }

    public enum EGZAM_PRODUCT {
        BASIC_ISTQB(0, "114", "ISTQB Foundation Level", BASIC),
        BASIC_REQB(1, "117", "REQB Foundation Level", BASIC),
        EXPERT_TEST_PROCESS(2, "115","ISTQB Improving the Test Process", EXPERT),
        EXPERT_TEST_MANAGEMENT(3, "116","ISTQB Test Management", EXPERT),
        ADVANCED_TECHNICAL_TEST_ANALYST(4,"110","ISTQB Advanced Level Technical Test Analyst", ADVANCE),
        ADVANCED_TEST_MANAGER(5,"112","ISTQB Advanced Level Test Manager", ADVANCE),
        ADVANCED_TEST_ANALYST(6,"111","ISTQB Advancel Level Test Analyst", ADVANCE),
        OTHER_AGILE_TESTER_EXTENTION(7,"113","ISTQB Agile Tester Extension", OTHER);

        /**
         * EGZAM_PRODUCT data-original-index
         */
        public final int index;
        /**
         * EGZAM_PRODUCT delete btn (trash icon) id
         */
        public final String deleteBtnId;

        /**
         * EGZAM_PRODUCT name
         */
        public final String name;

        /**
         * EGZAM_PRODUCT level
         */
        public final EGZAM_LEVEL egzam_level;

        EGZAM_PRODUCT(int index, String deleteBtnId, String name, EGZAM_LEVEL egzam_level) {
            this.index = index;
            this.deleteBtnId = deleteBtnId;
            this.name = name;
            this.egzam_level = egzam_level;
        }

        public static EGZAM_PRODUCT getProduct(String productName){
            for(EGZAM_PRODUCT value : EGZAM_PRODUCT.values()){
                if(value.name.equals(productName)){
                    return value;
                }
            }
            throw new NoSuchElementException("Unknown product");
        }
    }
}
