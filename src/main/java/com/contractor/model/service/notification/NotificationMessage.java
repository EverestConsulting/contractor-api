package com.contractor.model.service.notification;

import com.fasterxml.jackson.annotation.JsonProperty;


public class NotificationMessage {

    @JsonProperty("notification")
    private NotificationBody notificationBody;
    @JsonProperty("data")
    private NotificationBody dataBody;

    public NotificationMessage(NotificationBody notificationBody, NotificationBody dataBody) {
        this.notificationBody = notificationBody;
        this.dataBody = dataBody;
    }

    public NotificationBody getNotificationBody() {
        return notificationBody;
    }

    public void setNotificationBody(NotificationBody notificationBody) {
        this.notificationBody = notificationBody;
    }

    public NotificationBody getDataBody() {
        return dataBody;
    }

    public void setDataBody(NotificationBody dataBody) {
        this.dataBody = dataBody;
    }

    private class NotificationBody {
        @JsonProperty("title")
        String title;
        @JsonProperty("body")
        String messageBody;
        @JsonProperty("icon")
        String icon;
        @JsonProperty("message_code")
        Integer messageCode;
        @JsonProperty("click_action")
        String clickAction;
        @JsonProperty("sound")
        String sound = "default";

        public NotificationBody(String title, String messageBody, String icon, Integer messageCode, String clickAction) {
            this.title = title;
            this.messageBody = messageBody;
            this.icon = icon;
            this.messageCode = messageCode;
            this.clickAction = clickAction;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessageBody() {
            return messageBody;
        }

        public void setMessageBody(String messageBody) {
            this.messageBody = messageBody;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Integer getMessageCode() {
            return messageCode;
        }

        public void setMessageCode(Integer messageCode) {
            this.messageCode = messageCode;
        }

        public String getClickAction() {
            return clickAction;
        }

        public void setClickAction(String clickAction) {
            this.clickAction = clickAction;
        }

        public String getSound() {
            return sound;
        }

    }
}
