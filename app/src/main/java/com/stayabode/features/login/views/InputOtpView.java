package com.stayabode.features.login.views;

/**
 * Created by VarunBhalla on 15/12/16.
 */
public interface InputOtpView {

    void onOtpGenerated();

    void onOtpVerified(String accessToken,String refreshToken,String userId);

    void onGenerationRequestFailed(int code, String message);

    void onVerificationRequestFailed(int code, String message);
}
