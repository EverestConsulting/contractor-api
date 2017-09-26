package com.contractor.model.service.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FirebaseResponse {

    @JsonProperty("multicast_id")
    private long multicastId;
    @JsonProperty("success")
    private int success;
    @JsonProperty("failure")
    private int failure;
    @JsonProperty("canonical_ids")
    private int canonicalIds;
    @JsonProperty("results")
    private Result[] results;

    public long getMulticastId() {
        return multicastId;
    }

    public void setMulticastId(long multicastId) {
        this.multicastId = multicastId;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public int getCanonicalIds() {
        return canonicalIds;
    }

    public void setCanonicalIds(int canonicalIds) {
        this.canonicalIds = canonicalIds;
    }

    public Result[] getResult() {
        return results;
    }

    public void setResult(Result[] result) {
        this.results = result;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class Result {
        @JsonProperty("error")
        private String error;
        @JsonProperty("message_id")
        private String messageId;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }
    }
}
