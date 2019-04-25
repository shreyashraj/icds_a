package com.stayabode.net.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class RefreshTokenResponse extends BaseResponse {
    @SerializedName("accesstoken")
    private String accesssToken;
    @SerializedName("refreshtoken")
    private String refreshToken;

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

