package tools;

import model.HttpResponseWrap;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.utils.URIBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pages.BasePage.BASE_URL;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class LoginRequest extends RequestBase{
    private static String LOGIN_URL = BASE_URL + "/taahm/Account/Login";

    public LoginRequest() {
    }

    public LoginRequest(CookieStore cookieStore) {
        super(cookieStore);
    }

    public boolean login(String username, String password){
        EntityBuilder mpEntity = EntityBuilder.create();
        URIBuilder parameters = new URIBuilder();
        parameters.addParameter("Email", username);
        parameters.addParameter("Password", password);
        parameters.addParameter("RememberMe", "false");
        parameters.addParameter("__RequestVerificationToken", obtainRequestVerificationCode());
        mpEntity.setParameters(parameters.getQueryParams());

        HttpResponseWrap responseWrap = sendPOSTFormRequest(HttpHost.create(BASE_URL),"/taahm/Account/Login",mpEntity.build());

        if (responseWrap.getStatusCode() == 302) {
            return true;
        }
        return false;
    }

    private String obtainRequestVerificationCode(){
        HttpResponseWrap pageConetent = performSimpleGet(HttpHost.create(BASE_URL),"/taahm/Account/Login");
        if(pageConetent.getStatusCode() == 200){
            Pattern pattern = Pattern.compile("<input name=\"__RequestVerificationToken\"\\s?(type=\"hidden\")?\\s?value=\"(.*?)\"\\s?\\/>");
            Matcher matcher = pattern.matcher(pageConetent.getHtmlContent());
            if(matcher.find()){
                return matcher.group(2);
            }
        }
        return "";
    }
}
