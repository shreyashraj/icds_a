package com.stayabode.features.login.presenters;

import com.stayabode.features.login.views.AdminLoginView;
import com.stayabode.net.RestAdapterFactory;
import com.stayabode.net.response.BaseResponse;
import com.stayabode.net.response.getmodels.AdminLoginResponse;
import com.stayabode.net.response.postmodels.AdminLoginPostResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;
import utils.Constants;
import utils.Status;
import utils.StringUtils;

/**
 * Created by VarunBhalla on 16/11/16.
 */
public class AdminLoginPresenter {
    AdminLoginView mAdminLoginView;

    public void setView(AdminLoginView adminLoginView) {
        mAdminLoginView = adminLoginView;
    }


    public void loginAdmin(String userName, String password, String selection) {




        AdminLoginPostResponse a = new AdminLoginPostResponse();

        a.setPassword(password);
        a.setUsername(userName);
        a.setSelection(selection);


        final Call<AdminLoginResponse> adminLoginCall =
                RestAdapterFactory.newInstance(RestAdapterFactory.RequestType.LOCAL).loginAdmin(a);

        Callback<AdminLoginResponse> callback = new Callback<AdminLoginResponse>() {
            @Override
            public void onResponse(final Response<AdminLoginResponse> response, Retrofit retrofit) {

                BaseResponse baseResponse = response.body();

                if (response.isSuccess()) {
4                    String requestQuery = response.raw().request().httpUrl().encodedQuery();

                    if (StringUtils.equals(baseResponse.status, Status.STATUS_SUCCESS)) {
                        Timber.v("response" + response.body());

                        AdminLoginResponse adminLoginResponse = response.body();
                        mAdminLoginView.onLoginSuccessful(adminLoginResponse);
                    } else {

                        Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(), response.message());
                        mAdminLoginView.onRequestFailed(response.code(),baseResponse.message);
                    }
                } else {
                    Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(), response.message());
                    mAdminLoginView.onRequestFailed(response.code(), Constants.GENERIC_ERROR);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Timber.e("Network call failed: %s", t.getMessage());
                mAdminLoginView.onRequestFailed(0, Constants.INTERNET_ERROR);
            }
        };

        adminLoginCall.enqueue(callback);
    }

}
