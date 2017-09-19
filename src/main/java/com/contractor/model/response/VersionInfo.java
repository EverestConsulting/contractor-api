package com.contractor.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;

public class VersionInfo {

    @JsonProperty("applicationVersion")
    public Double applicationVersion = 0.1;

}
