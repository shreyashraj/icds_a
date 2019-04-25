package com.stayabode.features.login.views;

/**
 * Created by VarunBhalla on 13/12/16.
 */
public interface LoginAfterCheckoutView {

    void onRequestFailed(int code, String message);

    void onFeedbackSent(String message);
}
