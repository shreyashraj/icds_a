package com.stayabode.net.response.postmodels;

import com.google.gson.annotations.SerializedName;
import com.stayabode.net.response.BaseResponse;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class AdminLoginPostResponse extends BaseResponse {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("selection")
    private String selection;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}

