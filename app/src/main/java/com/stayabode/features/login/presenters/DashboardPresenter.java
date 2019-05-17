package com.stayabode.features.login.presenters;

import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;
import com.stayabode.features.login.views.DashboardView;
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
public class DashboardPresenter {
    DashboardView mdashboardView;

    public void setView(DashboardView AWCListView) {
        mdashboardView = AWCListView;
    }


    public void logout(String token) {


        AdminLoginPostResponse a = new AdminLoginPostResponse();

        a.setToken(token);


        final Call<AdminLoginResponse> fetchQuestionsCall =
                RestAdapterFactory.newInstance(RestAdapterFactory.RequestType.LOCAL).logoutAdmin(a);

        Callback<AdminLoginResponse> callback = new Callback<AdminLoginResponse>() {
            @Override
            public void onResponse(final Response<AdminLoginResponse> response, Retrofit retrofit) {

                BaseResponse baseResponse = response.body();

                if (response.isSuccess()) {
                    String requestQuery = response.raw().request().httpUrl().encodedQuery();

                    if (StringUtils.equals(baseResponse.status, Status.STATUS_SUCCESS)) {
                        Timber.v("response" + response.body());

                        mdashboardView.onLogoutSuccessful();
                    } else {

                        Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(), response.message());
                        mdashboardView.onRequestFailed(response.code(),baseResponse.message);
                    }
                } else {
                    Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(), response.message());
                    mdashboardView.onRequestFailed(response.code(), Constants.GENERIC_ERROR);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Timber.e("Network call failed: %s", t.getMessage());
                mdashboardView.onRequestFailed(0, Constants.INTERNET_ERROR);
            }
        };

        fetchQuestionsCall.enqueue(callback);
    }

}
