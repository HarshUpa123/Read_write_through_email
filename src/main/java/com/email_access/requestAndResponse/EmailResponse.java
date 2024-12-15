package com.email_access.requestAndResponse;

import lombok.*;

import java.util.List;

public class EmailResponse {

    private String from;
    private String content;
    private String subjects;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }
}
