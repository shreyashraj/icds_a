package com.stayabode.base.views;

/**
 * Created by Arpit on 23-01-2017.
 */

public interface BaseView {

    void onTokenRefreshed(String accessToken,String refreshToken);

    void onTokenRequestFailed(int code, String message);
}
