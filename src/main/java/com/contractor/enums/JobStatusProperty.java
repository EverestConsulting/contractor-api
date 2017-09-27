package com.contractor.enums;

public enum JobStatusProperty {

    requested("Requested"),
    accepted("Accepted"),
    inProgres("In Progress"),
    review("Review"),
    completed("Completed");

    private String title;

    private JobStatusProperty(String title) {
        this.title = title;
    }
}
