package tools;

import model.HttpResponseWrap;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static pages.BasePage.BASE_URL;

/**
 * Created by work on 2016-11-19.
 */
public class SessionRequest extends RequestBase{

    public SessionRequest() {
    }

    public SessionRequest(CookieStore cookieStore) {
        super(cookieStore);
    }

    public void deleteSession(int sessionId){

        HttpDelete deleteRequest = new HttpDelete("/taahm/Session/RemoveSession/"+sessionId);
        deleteRequest.addHeader("Referer",HttpHost.create(BASE_URL)+"/taahm/Dashboard/Index");
        deleteRequest.addHeader("Origin",HttpHost.create(BASE_URL)+"");
        deleteRequest.addHeader("Host",HttpHost.create(BASE_URL).getHostName());
        deleteRequest.addHeader("X-Requested-With","XMLHttpRequest");
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(HttpHost.create(BASE_URL), deleteRequest, httpContext);
            HttpResponseWrap httpResponseWrap = new HttpResponseWrap(httpResponse);
            System.out.println(httpResponseWrap.getHtmlContent());
            EntityUtils.consume(httpResponse.getEntity());
        }
        catch (IOException ioex){
        }
    }
}
