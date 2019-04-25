package com.stayabode.features.login.presenters;

import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;
import com.stayabode.features.login.views.LoginAfterCheckoutView;
import com.stayabode.net.RestAdapterFactory;
import com.stayabode.net.response.BaseResponse;
import com.stayabode.net.response.GeneralFeedbackResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import utils.Constants;
import utils.JsonKeys;
import utils.Status;
import utils.StringUtils;

/**
 * Created by VarunBhalla on 13/12/16.
 */
public class LoginAfterCheckoutPresenter {
    LoginAfterCheckoutView mloginAfterCheckoutView;

    public void setView(LoginAfterCheckoutView loginAfterCheckoutView) {
        mloginAfterCheckoutView = loginAfterCheckoutView;
    }


    public void sendFeedback(String mGeneralFeedback) {


        RequestBody feedbackRequestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addFormDataPart(JsonKeys.JSONKEY_DESCRIPTION, mGeneralFeedback)
                .build();

        Call<GeneralFeedbackResponse> postFeedbackCall = RestAdapterFactory.newInstance(RestAdapterFactory.RequestType.STAGING)
                .sendGeneralFeedback(feedbackRequestBody);

        postFeedbackCall.enqueue(new Callback<GeneralFeedbackResponse>() {
            @Override
            public void onResponse(Response<GeneralFeedbackResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    BaseResponse baseResponse = response.body();
                    if (StringUtils.equals(baseResponse.status, Status.STATUS_SUCCESS)) {
                        mloginAfterCheckoutView.onFeedbackSent(response.message());
                    }else{
                        mloginAfterCheckoutView.onRequestFailed(response.code(), Constants.GENERIC_ERROR);
                    }
                }else{
                    mloginAfterCheckoutView.onRequestFailed(response.code(), Constants.GENERIC_ERROR);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mloginAfterCheckoutView.onRequestFailed(0, Constants.INTERNET_ERROR);

            }
        });

    }


}
