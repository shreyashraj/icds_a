package com.stayabode.features.login.views;

/**
 * Created by VarunBhalla on 13/12/16.
 */
public interface LoginView {
    void onTokenFetched(String accessToken,String refreshToken,Boolean isPhoneVerified,String userId);

    void onRequestFailed(int errorCode,String errorMessage);
}
