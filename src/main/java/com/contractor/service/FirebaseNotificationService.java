package com.contractor.service;

import com.contractor.model.service.notification.FirebaseResponse;
import com.contractor.model.service.notification.NotificationMessage;
import com.contractor.service.httpclient.HttpClient;
import com.contractor.service.httpclient.HttpClientWorker;
import com.contractor.util.Config;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class FirebaseNotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(FirebaseNotificationService.class.getSimpleName());

    private static FirebaseNotificationService INSTANCE;

    private FirebaseNotificationService() {
        //singleton pattern
    }

    public static void init() {
        if (null != INSTANCE) {
            LOG.error(String.format("%s already initialized!!!", FirebaseNotificationService.class.getSimpleName()));
            return;
        }

        INSTANCE = new FirebaseNotificationService();
    }

    public static FirebaseNotificationService instance() {
        if (null == INSTANCE) {
            LOG.error(String.format("%s not properly initialized!!!", FirebaseNotificationService.class.getSimpleName()));
        }
        return INSTANCE;
    }

    public boolean sendNotificationRequest(NotificationMessage notificationMessage) {
        String jsonRequestBody;
        try {
            jsonRequestBody = new ObjectMapper().writeValueAsString(notificationMessage);
        } catch (JsonProcessingException e) {
            LOG.error("Couldn't parse notification message to json string", e);
            return false;
        }

        Map<String, String> headers = new HashMap<String, String>() {{
            put("Authorization", String.format("key=%s", Config.FIREBASE_NOTIFICATION_API_TOKEN));
            put("Content-Type", ContentType.APPLICATION_JSON.withCharset("UTF-8").toString());
        }};


        String jsonResponse = HttpClientWorker.instance().execute(() ->
                HttpClient.instance().postJson(Config.FIREBASE_NOTIFICATION_URL, jsonRequestBody, headers)
        );

        FirebaseResponse
                firebaseResponse = new ObjectMapper().convertValue(jsonResponse, FirebaseResponse.class);


        return null != firebaseResponse && firebaseResponse.getSuccess() == 1;
    }
}
