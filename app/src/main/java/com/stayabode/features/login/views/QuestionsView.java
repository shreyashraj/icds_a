package com.stayabode.features.login.views;

/**
 * Created by VarunBhalla on 16/11/16.
 */
public interface QuestionsView {
    void onRegistrationVerified();
    void onRequestFailed(int errorCode, String errorMsg);

}
