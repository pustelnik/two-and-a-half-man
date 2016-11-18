package tools;

import model.HttpResponseWrap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class RequestBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestBase.class);
    protected CloseableHttpClient httpClient;
    protected HttpClientContext httpContext;
    protected CookieStore httpCookieStore;

    public RequestBase(){
        setupClientAndContext();
    }

    public RequestBase(CookieStore cookieStore){
        setupClientAndContext();
        setCookieStore(cookieStore);
    }

    public void setupClientAndContext(){
        httpContext = HttpClientContext.create();
        httpCookieStore = new BasicCookieStore();
        httpClient = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore).build();
    }

    public List<Cookie> getCookies(){
        return httpCookieStore.getCookies();
    }

    public CookieStore getCookieStore(){
        return httpCookieStore;
    }

    public void setCookieStore(CookieStore cookieStore){
        this.httpCookieStore = cookieStore;
    }

    public HttpResponseWrap performSimpleGet(HttpHost host,String path){
        HttpResponseWrap httpResponseWrap = new HttpResponseWrap();
        //HttpHost host = new HttpHost(fullUrl,80);
        HttpGet getRequest = new HttpGet(path);
        try {
            httpClient.execute(host, getRequest);
            CloseableHttpResponse httpResponse = httpClient.execute(host, getRequest, httpContext);
            httpResponseWrap = new HttpResponseWrap(httpResponse);
            EntityUtils.consume(httpResponse.getEntity());
        }
        catch (IOException ioex){
            System.out.println(ioex.getMessage());
        }
        return httpResponseWrap;
    }

    public HttpResponseWrap sendPOSTFormRequest(HttpHost host,HttpPost httpPost, HttpEntity formParameters) {
        HttpResponseWrap httpResponseWrap = new HttpResponseWrap();
        try {
            httpPost.setEntity(formParameters);
            CloseableHttpResponse httpResult = this.httpClient.execute(host,httpPost, this.httpContext);
            httpResponseWrap = new HttpResponseWrap(httpResult);
            EntityUtils.consume(httpResult.getEntity());
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());

        }

        return httpResponseWrap;
    }

    public HttpResponseWrap sendPOSTFormRequest(HttpHost host,String path, HttpEntity formParameters) {
        HttpResponseWrap httpResponseWrap = new HttpResponseWrap();
        HttpPost httpPost = new HttpPost(path);
        try {
            httpPost.setEntity(formParameters);
            CloseableHttpResponse httpResult = this.httpClient.execute(host, httpPost, this.httpContext);
            httpResponseWrap = new HttpResponseWrap(httpResult);
            EntityUtils.consume(httpResult.getEntity());
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());

        }

        return httpResponseWrap;
    }
}
