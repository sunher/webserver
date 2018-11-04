package com.webserver.model;

import org.hibernate.validator.constraints.NotBlank;

public class SearchCriteria {

    @NotBlank(message = "message can't empty!")
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}