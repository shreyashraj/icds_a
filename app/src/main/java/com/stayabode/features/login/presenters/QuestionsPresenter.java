package com.stayabode.features.login.presenters;

import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;
import com.stayabode.features.login.views.QuestionsView;
import com.stayabode.net.RestAdapterFactory;
import com.stayabode.net.response.BaseResponse;
import com.stayabode.net.response.getmodels.AdminLoginResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;
import utils.Constants;
import utils.JsonKeys;
import utils.Status;
import utils.StringUtils;

/**
 * Created by VarunBhalla on 16/11/16.
 */
public class QuestionsPresenter {
    QuestionsView mQuestionsView;

    public void setView(QuestionsView questionsView) {
        mQuestionsView = questionsView;
    }


    public void verifyRegistration(String phone,String fbUserId) {

        RequestBody phoneRequestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addFormDataPart(JsonKeys.JSONKEY_PHONE, phone)
                .addFormDataPart(JsonKeys.JSONKEY_FB_USER_ID, fbUserId)
                .build();

        final Call<AdminLoginResponse> fetchIssuesCall =
                RestAdapterFactory.newInstance(RestAdapterFactory.RequestType.STAGING).verifyPhone(phoneRequestBody);

        Callback<AdminLoginResponse> callback = new Callback<AdminLoginResponse>() {
            @Override
            public void onResponse(final Response<AdminLoginResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    String requestQuery = response.raw().request().httpUrl().encodedQuery();
                    BaseResponse baseResponse = response.body();
                    if (StringUtils.equals(baseResponse.status, Status.STATUS_SUCCESS)) {
                        Timber.v("response" + response.body());
                        mQuestionsView.onRegistrationVerified();
                    }else {
                        Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(),  response.message());
                        mQuestionsView.onRequestFailed(response.code(),  Constants.GENERIC_ERROR);
                    }
                } else {
                    Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(),  response.message());
                    mQuestionsView.onRequestFailed(response.code(),  Constants.GENERIC_ERROR);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Timber.e("Network call failed: %s", t.getMessage());
                mQuestionsView.onRequestFailed(0,Constants.INTERNET_ERROR);
            }
        };

        fetchIssuesCall.enqueue(callback);
    }

}
