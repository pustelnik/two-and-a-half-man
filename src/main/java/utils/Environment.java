package utils;

/**
 * @author jakubp on 11.11.16.
 */
public enum Environment {
    BROWSER("test.webdriver.browser"),
    WEBDRIVER_URL("test.webdriver.url");

    private String val;

    Environment(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
