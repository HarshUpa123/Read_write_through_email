package com.email_access.requestAndResponse;


public class EmailRequest {
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAppPassword() {
        return appPassword;
    }

    public void setAppPassword(String appPassword) {
        this.appPassword = appPassword;
    }

    public String getSubjectFilter() {
        return subjectFilter;
    }

    public void setSubjectFilter(String subjectFilter) {
        this.subjectFilter = subjectFilter;
    }

    private String userEmail;
    private String appPassword;
    private String subjectFilter;
}
