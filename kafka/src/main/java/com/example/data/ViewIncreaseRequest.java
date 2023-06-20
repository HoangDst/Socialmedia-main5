package com.example.data;

public class ViewIncreaseRequest {
    private String userId;
    private String contentId;
    public ViewIncreaseRequest(String userId, String contentId) {
        this.userId = userId;
        this.contentId = contentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
