package com.stayabode.features.login.presenters;

import com.stayabode.features.login.views.DashboardView;
import com.stayabode.net.RestAdapterFactory;
import com.stayabode.net.response.BaseResponse;
import com.stayabode.net.response.getmodels.QuestionsResponse;
import com.stayabode.net.response.postmodels.GetQuestionsPostRepsonse;

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


    public void getQuestions(String token) {

        GetQuestionsPostRepsonse a = new GetQuestionsPostRepsonse();

        a.setToken(token);



        final Call<QuestionsResponse> fetchQuestionsCall =
                RestAdapterFactory.newInstance(RestAdapterFactory.RequestType.LOCAL).fetchQuestions(a);

        Callback<QuestionsResponse> callback = new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(final Response<QuestionsResponse> response, Retrofit retrofit) {

                BaseResponse baseResponse = response.body();

                if (response.isSuccess()) {
                    String requestQuery = response.raw().request().httpUrl().encodedQuery();

                    if (StringUtils.equals(baseResponse.status, Status.STATUS_SUCCESS)) {
                        Timber.v("response" + response.body());

                        QuestionsResponse questionsresponse = response.body();
                        mdashboardView.onLoginSuccessful(questionsresponse);
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
