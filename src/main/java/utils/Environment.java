package utils;

/**
 * @author jakubp on 11.11.16.
 */
public enum Environment {

    TEST_USR("test.user.name"),
    TEST_PWD("test.user.pwd");

    public final String val;

    Environment(String val) {
        this.val = val;
    }

}
