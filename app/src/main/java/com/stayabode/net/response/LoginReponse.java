package com.stayabode.net.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class LoginReponse extends BaseResponse {
    @SerializedName("accesstoken")
    private String accesssToken;
    @SerializedName("refreshtoken")
    private String refreshToken;
    @SerializedName("fbaccesstoken")
    private String fbAccessToken;
    @SerializedName("phone_verified")
    private boolean isPhoneVerified;
    @SerializedName("user_id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFbAccessToken() {
        return fbAccessToken;
    }

    public void setFbAccessToken(String fbAccessToken) {
        this.fbAccessToken = fbAccessToken;
    }

    public boolean isPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccesssToken() {
        return accesssToken;
    }

    public void setAccesssToken(String accesssToken) {
        this.accesssToken = accesssToken;
    }
}

