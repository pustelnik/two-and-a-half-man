package model;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by LewarskiT on 2016-11-17.
 */
public class HttpResponseWrap {
    private int statusCode;
    private String htmlContent;

    public HttpResponseWrap() {
        this.setHtmlContent("");
        this.setStatusCode(500);
    }

    public HttpResponseWrap(HttpResponse httpResponse) {
        this.setStatusCode(httpResponse.getStatusLine().getStatusCode());

        try {
            this.setHtmlContent(EntityUtils.toString(httpResponse.getEntity()));
        } catch (IOException var4) {
            this.setHtmlContent("");
        }

    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getHtmlContent() {
        return this.htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}
