package model;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class EnrollEnums {
    public enum PREFFERED_LANGUAGE {
        ENGLISH(25), POLISH(26);
        final int index;

        PREFFERED_LANGUAGE(int index) {
            this.index = index;
        }
    }

    public enum PREFFERED_EGZAM_TYPE {
        PAPER("Paper"), ELECTRONIC("Electronic");
        final String type;

        PREFFERED_EGZAM_TYPE(String type) {
            this.type = type;
        }
    }

    public enum INVOICE_TYPE {
        NONE("None"), ELECTRONIC("Electronic"), PAPER("Paper");
        final String type;

        INVOICE_TYPE(String type) {
            this.type = type;
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
        BASIC_ISTQB(0, "52", "ISTQB Foundation Level"),
        BASIC_REQB(1, "55", "REQB Foundation Level"),
        EXPERT_TEST_PROCESS(2, "53","ISTQB Improving the Test Process"),
        EXPERT_TEST_MANAGEMENT(3, "54","ISTQB Test Management"),
        ADVANCED_TECHNICAL_TEST_ANALYST(4,"48","ISTQB Advanced Level Technical Test Analyst"),
        ADVANCED_TEST_MANAGER(5,"49","ISTQB Advanced Level Test Manager"),
        ADVANCED_TEST_ANALYST(6,"50","ISTQB Advancel Level Test Analyst"),
        OTHER_AGILE_TESTER_EXTENTION(7,"51","ISTQB Agile Tester Extension");

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

        EGZAM_PRODUCT(int index, String deleteBtnId, String name) {
            this.index = index;
            this.deleteBtnId = deleteBtnId;
            this.name = name;
        }
    }
}
