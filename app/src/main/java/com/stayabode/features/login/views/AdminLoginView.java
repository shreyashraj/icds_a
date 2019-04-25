package com.stayabode.features.login.views;

import com.stayabode.net.response.getmodels.AdminLoginResponse;

/**
 * Created by VarunBhalla on 16/11/16.
 */
public interface AdminLoginView {
    void onLoginSuccessful(AdminLoginResponse adminLoginResponse);
    void onRequestFailed(int errorCode, String errorMsg);

}
