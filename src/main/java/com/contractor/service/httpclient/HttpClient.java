package com.contractor.service.httpclient;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;


public class HttpClient {

    private static final Logger LOG = LoggerFactory.getLogger(HttpClient.class.getSimpleName());
    private static HttpClient INSTANCE;

    private static PoolingHttpClientConnectionManager clientConnectionManager;
    private static CloseableHttpClient httpClient;

    private HttpClient() {
        int maxPerRouteConnections = 50;
        int maxConnections = 100;

        clientConnectionManager = new PoolingHttpClientConnectionManager();

        clientConnectionManager.setDefaultMaxPerRoute(maxPerRouteConnections);
        clientConnectionManager.setMaxTotal(maxConnections);

        httpClient = HttpClients.custom().setConnectionManager(clientConnectionManager).build();
    }

    public static void init() {

        if (null != INSTANCE) {
            LOG.warn(String.format("%s already instantiated", HttpClient.class.getSimpleName()));
            return;
        }

        INSTANCE = new HttpClient();
    }

    public static HttpClient instance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly instantiated", HttpClient.class.getSimpleName()));
        }

        return INSTANCE;
    }

    public static void shutdown() {
        try {
            httpClient.close();
        } catch (IOException e) {
            LOG.error("Client already closed !", e);
        }
        clientConnectionManager.close();
    }

    public String postJson(String URL, String jsonRequestBody, Map<String, String> headers) {

        HttpPost httpPost = new HttpPost(URL);
        httpPost.setEntity(new StringEntity(jsonRequestBody, ContentType.APPLICATION_JSON));

        headers.forEach((k, v) -> httpPost.setHeader(k, v));

        HttpResponse httpResponse;
        String jsonResponse = null;


        try {
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            jsonResponse = IOUtils.toString(httpEntity.getContent(), "UTF-8");
        } catch (IOException e) {
            LOG.error(String.format("Something went wrong communicating with %s", URL), e);
        }

        return jsonResponse;
    }

}
