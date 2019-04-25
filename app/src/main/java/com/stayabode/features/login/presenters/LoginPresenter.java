package com.stayabode.features.login.presenters;

import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;
import com.stayabode.features.login.views.LoginView;
import com.stayabode.net.RestAdapterFactory;
import com.stayabode.net.response.BaseResponse;
import com.stayabode.net.response.LoginReponse;


import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;
import utils.Constants;
import utils.JsonKeys;
import utils.SharedPrefManager;
import utils.Status;
import utils.StringUtils;

/**
 * Created by VarunBhalla on 13/12/16.
 */
public class LoginPresenter {
    LoginView mloginView;

    public void setView(LoginView loginView) {
        mloginView = loginView;
    }


    public void fetchToken(String name, String email, String imageUrl, String userId, String accessToken,String birthday) {

        RequestBody loginRequestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addFormDataPart(JsonKeys.JSONKEY_FCM_ID, SharedPrefManager.getInstance().getFCMToken())
                .addFormDataPart(JsonKeys.JSONKEY_NAME, name)
                .addFormDataPart(JsonKeys.JSONKEY_BIRTHDAY, birthday)
                .addFormDataPart(JsonKeys.JSONKEY_EMAIL, email)
                .addFormDataPart(JsonKeys.JSONKEY_IMAGE_URL, imageUrl)
                .addFormDataPart(JsonKeys.JSONKEY_FB_USER_ID, userId)
                .addFormDataPart(JsonKeys.JSONKEY_FB_ACCESS_TOKEN, accessToken)
                .addFormDataPart(JsonKeys.JSONKEY_FCM_ID, SharedPrefManager.getInstance().getFCMToken())
                .build();

        final Call<LoginReponse> fetchIssuesCall =
                RestAdapterFactory.newInstance(RestAdapterFactory.RequestType.STAGING).getAccessToken(loginRequestBody);

        Callback<LoginReponse> callback = new Callback<LoginReponse>() {
            @Override
            public void onResponse(final Response<LoginReponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    String requestQuery = response.raw().request().httpUrl().encodedQuery();
                    BaseResponse baseResponse = response.body();
                    if (StringUtils.equals(baseResponse.status, Status.STATUS_SUCCESS)) {
                        Timber.v("response" + response.body());

                        Boolean isPhoneVerified = response.body().isPhoneVerified();

                        String accessToken;
                        String refreshToken = "";
                        String userId = "";

                        if (isPhoneVerified) {
                            accessToken = response.body().getAccesssToken();
                            refreshToken = response.body().getRefreshToken();
                            userId = response.body().getUserId();
                        } else {
                            accessToken = response.body().getFbAccessToken();
                        }
                        mloginView.onTokenFetched(accessToken, refreshToken, isPhoneVerified,userId);
                    }else {
                        Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(), response.message());
                        mloginView.onRequestFailed(response.code(),  Constants.GENERIC_ERROR);
                    }

                } else {
                    Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(), response.message());
                    mloginView.onRequestFailed(response.code(),  Constants.GENERIC_ERROR);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Timber.e("Network call failed: %s", t.getMessage());
                mloginView.onRequestFailed(0, Constants.INTERNET_ERROR);
            }
        };

        fetchIssuesCall.enqueue(callback);
    }


}
