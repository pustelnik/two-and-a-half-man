package tools;

import model.HttpResponseWrap;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;

import static pages.BasePage.BASE_URL;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class EnrollRequest extends RequestBase{

    public EnrollRequest() {
    }

    public EnrollRequest(CookieStore cookieStore) {
        super(cookieStore);
    }

    public void setEgzamSessionId(int sessionId){
        EntityBuilder mpEntity = EntityBuilder.create();
        URIBuilder parameters = new URIBuilder();
        parameters.addParameter("productSessionId", String.valueOf(sessionId));
        mpEntity.setParameters(parameters.getQueryParams());

        HttpPost httpPost = new HttpPost("/taahm/RegisterProduct/SetIndividual");
        httpPost.addHeader("Referer",HttpHost.create(BASE_URL)+"/taahm");
        httpPost.addHeader("Origin",HttpHost.create(BASE_URL)+"");
        httpPost.addHeader("Host",HttpHost.create(BASE_URL).getHostName());
        httpPost.addHeader("X-Requested-With","XMLHttpRequest");
        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");

        HttpResponseWrap res = sendPOSTFormRequest(HttpHost.create(BASE_URL),httpPost,mpEntity.build());
        System.out.println(res.getHtmlContent());
    }

    public void deleteUserEnrollment(int enrollId){
        sendDeleteRequest(HttpHost.create(BASE_URL),"/taahm/Registration/DeleteRegistration/"+enrollId);
    }
}
