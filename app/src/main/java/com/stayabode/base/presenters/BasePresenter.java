package com.stayabode.base.presenters;

import com.stayabode.base.views.BaseView;
import com.stayabode.net.RestAdapterFactory;
import com.stayabode.net.response.RefreshTokenResponse;

import java.util.HashMap;

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
 * Created by Arpit on 23-01-2017.
 */
public class BasePresenter{

    BaseView mBaseView;

    public void setView(BaseView baseView) {
        mBaseView = baseView;
    }
    public void refreshToken() {

        HashMap<String, String> params = new HashMap<>();
        params.put(JsonKeys.JSONKEY_REFRESH_TOKEN, SharedPrefManager.getInstance().getRefreshToken());
        params.put(JsonKeys.JSONKEY_USER_ID, SharedPrefManager.getInstance().getUserId());


        final Call<RefreshTokenResponse> refreshTokenCall =
                RestAdapterFactory.newInstance(RestAdapterFactory.RequestType.STAGING).refreshToken(params);

        Callback<RefreshTokenResponse> callback = new Callback<RefreshTokenResponse>() {
            @Override
            public void onResponse(final Response<RefreshTokenResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    RefreshTokenResponse refreshTokenResponse = response.body();
                    if (StringUtils.equals(refreshTokenResponse.status, Status.STATUS_SUCCESS)) {
                        mBaseView.onTokenRefreshed(refreshTokenResponse.getAccesssToken(),refreshTokenResponse.getRefreshToken());
                    }else{
                        mBaseView.onTokenRequestFailed(response.code(),  Constants.GENERIC_ERROR);
                    }

                } else {
                    Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(), response.message());
                    mBaseView.onTokenRequestFailed(response.code(),  Constants.GENERIC_ERROR);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Timber.e("Network call failed: %s", t.getMessage());
                mBaseView.onTokenRequestFailed(0, Constants.INTERNET_ERROR);

            }
        };

        refreshTokenCall.enqueue(callback);
    }
}
