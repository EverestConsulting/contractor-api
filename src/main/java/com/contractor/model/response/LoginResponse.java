package com.contractor.model.response;

public class LoginResponse {
    private String token;
    private long created;
    private long validUntil;

    public LoginResponse() {
    }

    public LoginResponse(String token, long created, long validUntil) {
        this.token = token;
        this.created = created;
        this.validUntil = validUntil;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(long validUntil) {
        this.validUntil = validUntil;
    }
}
