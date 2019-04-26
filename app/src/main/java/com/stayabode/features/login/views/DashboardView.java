package com.stayabode.features.login.views;

import com.stayabode.net.response.getmodels.QuestionsResponse;

/**
 * Created by VarunBhalla on 16/11/16.
 */
public interface DashboardView {
    void onLoginSuccessful(QuestionsResponse adminLoginResponse);
    void onRequestFailed(int errorCode, String errorMsg);

}
