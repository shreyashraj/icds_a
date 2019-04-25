package com.stayabode.features.login.presenters;

import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.RequestBody;
import com.stayabode.features.login.views.InputOtpView;
import com.stayabode.net.RestAdapterFactory;
import com.stayabode.net.response.BaseResponse;
import com.stayabode.net.response.OtpResponse;
import com.stayabode.net.response.getmodels.AdminLoginResponse;

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
 * Created by VarunBhalla on 15/12/16.
 */
public class InputOtpPresenter {
    InputOtpView mInputOtpView;

    public void setView(InputOtpView inputOtpView) {
        mInputOtpView = inputOtpView;
    }


    public void generateOtp(String phoneNumber) {

            RequestBody phoneRequestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addFormDataPart(JsonKeys.JSONKEY_PHONE, phoneNumber)
                    .addFormDataPart(JsonKeys.JSONKEY_FB_USER_ID, SharedPrefManager.getInstance().getFbUserId())
                    .build();

            final Call<AdminLoginResponse> generateOtpCall =
                    RestAdapterFactory.newInstance(RestAdapterFactory.RequestType.STAGING).verifyPhone(phoneRequestBody);

            Callback<AdminLoginResponse> callback = new Callback<AdminLoginResponse>() {
                @Override
                public void onResponse(final Response<AdminLoginResponse> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        String requestQuery = response.raw().request().httpUrl().encodedQuery();
                        BaseResponse baseResponse = response.body();
                        if (StringUtils.equals(baseResponse.status, Status.STATUS_SUCCESS)) {
                            Timber.v("response" + response.body());
                            mInputOtpView.onOtpGenerated();
                        }else {
                            Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(),  response.message());
                            mInputOtpView.onGenerationRequestFailed(response.code(),  Constants.GENERIC_ERROR);
                        }
                    } else {
                        Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(),  response.message());
                        mInputOtpView.onGenerationRequestFailed(response.code(),  Constants.GENERIC_ERROR);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Timber.e("Network call failed: %s", t.getMessage());
                    mInputOtpView.onGenerationRequestFailed(0,Constants.INTERNET_ERROR);
                }
            };

        generateOtpCall.enqueue(callback);

    }

    public void verifyOtp(String otp, String phone) {

        String fbUserId = SharedPrefManager.getInstance().getFbUserId();
        RequestBody loginRequestBody = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addFormDataPart(JsonKeys.JSONKEY_OTP, otp)
                .addFormDataPart(JsonKeys.JSONKEY_PHONE, phone)
                .addFormDataPart(JsonKeys.JSONKEY_FB_USER_ID, fbUserId)
                .build();

        final Call<OtpResponse> otpVerificationCall =
                RestAdapterFactory.newInstance(RestAdapterFactory.RequestType.STAGING).verifyOtp(loginRequestBody);

        Callback<OtpResponse> callback = new Callback<OtpResponse>() {
            @Override
            public void onResponse(final Response<OtpResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    String requestQuery = response.raw().request().httpUrl().encodedQuery();
                    BaseResponse baseResponse = response.body();
                    if (StringUtils.equals(baseResponse.status, Status.STATUS_SUCCESS)) {
                        Timber.v("response" + response.body());

                        String accessToken = response.body().getAccesssToken();
                        String refreshToken = response.body().getRefreshToken();
                        String userId = response.body().getUserId();

                        mInputOtpView.onOtpVerified(accessToken, refreshToken,userId);
                    }else {
                        Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(), response.message());
                        mInputOtpView.onVerificationRequestFailed(response.code(),  Constants.GENERIC_ERROR);
                    }

                } else {
                    Timber.i("Response: Failure:: error code: %s, msg: %s", response.code(), response.message());
                    mInputOtpView.onVerificationRequestFailed(response.code(),  Constants.GENERIC_ERROR);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Timber.e("Network call failed: %s", t.getMessage());
                mInputOtpView.onVerificationRequestFailed(0, Constants.INTERNET_ERROR);
            }
        };

        otpVerificationCall.enqueue(callback);


    }
}

