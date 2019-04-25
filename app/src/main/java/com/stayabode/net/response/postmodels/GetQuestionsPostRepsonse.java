package com.stayabode.net.response.postmodels;

import com.google.gson.annotations.SerializedName;
import com.stayabode.net.response.BaseResponse;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class GetQuestionsPostRepsonse extends BaseResponse {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

