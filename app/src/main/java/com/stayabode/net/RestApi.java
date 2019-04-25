package com.stayabode.net;

import com.squareup.okhttp.RequestBody;
import com.stayabode.net.response.AttendeesResponse;
import com.stayabode.net.response.BaseResponse;
import com.stayabode.net.response.DashboardResponse;
import com.stayabode.net.response.EventDetailReponse;
import com.stayabode.net.response.EventsReponse;
import com.stayabode.net.response.GeneralFeedbackResponse;
import com.stayabode.net.response.IssueFeedbackResponse;
import com.stayabode.net.response.LoginReponse;
import com.stayabode.net.response.LogoutResponse;
import com.stayabode.net.response.NeighboursResponse;
import com.stayabode.net.response.NotificationsReponse;
import com.stayabode.net.response.OtpResponse;
import com.stayabode.net.response.PaymentHistoryResponse;
import com.stayabode.net.response.getmodels.AdminLoginResponse;
import com.stayabode.net.response.PreferencesReponse;
import com.stayabode.net.response.RefreshTokenResponse;
import com.stayabode.net.response.RegisteredVisitorsReponse;
import com.stayabode.net.response.RentalDetailsReponse;
import com.stayabode.net.response.ReportedIssuesResponse;
import com.stayabode.net.response.RequestRentalReceiptResponse;
import com.stayabode.net.response.getmodels.QuestionsResponse;
import com.stayabode.net.response.postmodels.AdminLoginPostResponse;
import com.stayabode.net.response.postmodels.GetQuestionsPostRepsonse;

import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by VarunBhalla on 13/10/16.
 */
public interface RestApi {

    @GET("/v2/57ffb196130000e60dcce35d")
    Call<BaseResponse> makeMockApiCall();

    @POST("/v2/57ffb196130000e60dcce35d")
    Call<BaseResponse> makeMockPostApiCall(@Body RequestBody requestBody);

    //Home screen API call
    @GET("/api/v1/stayabode/users/dashboard/")
    Call<DashboardResponse> fetchDashboardDetails(@QueryMap HashMap<String, String> params);

    //Fetch reported issues
    @GET("/api/v2/stayabode/issues/issue/")
    Call<ReportedIssuesResponse> fetchReportedIssues(@QueryMap HashMap<String, String> params);

    //Fetch registered visitors
    @GET("/api/v1/stayabode/residents/visitor/")
    Call<RegisteredVisitorsReponse> fetchRegisteredVisitors();

    //Fetch neighbours
    @GET("/api/v1/stayabode/residents/neighbours/")
    Call<NeighboursResponse> fetchNeighbours();

    //Register a visitor
    @POST("/api/v1/stayabode/residents/visitor/")
    Call<RegisteredVisitorsReponse> registerAVisitors(@Body RequestBody requestBody);

    //Report an issue
    @POST("/api/v2/stayabode/issues/issue/")
    Call<ReportedIssuesResponse> reportAnIssue(@Body Object data);

    //Fetch rental details
    @GET("/api/v2/stayabode/guests/rentals/")
    Call<RentalDetailsReponse> fetchRentalDetails();

    //request rental receipts
    @POST("/api/v1/stayabode/guests/rentals/receipt/")
    Call<RequestRentalReceiptResponse> requestRentalReceipts(@Body List<String> months);

    //Fetch accesstoken
    @POST("/api/v1/stayabode/users/fblogin/")
    Call<LoginReponse> getAccessToken(@Body RequestBody requestBody);

    //Verify phone
    @POST("/api/v1/stayabode/users/enter_mobile/")
    Call<AdminLoginResponse>verifyPhone(@Body RequestBody requestBody);

    //generate otp
    @POST("/api/v1/stayabode/users/enter_mobile/")
    Call<OtpResponse>generateOtp(@Body RequestBody requestBody);

    //verify otp
    @POST("/api/v1/stayabode/users/verify_otp/")
    Call<OtpResponse>verifyOtp(@Body RequestBody requestBody);

    //cancel issue
    @DELETE("/api/v1/stayabode/issues/issue/{issue_id}/")
    Call<ReportedIssuesResponse>cancelIssue(@Path("issue_id") String issue_Id);

    //reopen issue
    @PUT("/api/v1/stayabode/issues/issue/{issue_id}/")
    Call<ReportedIssuesResponse>reopenIssue(@Path("issue_id") String issue_Id,@Body RequestBody requestBody);

    //delete visitor
    @DELETE("/api/v1/stayabode/residents/visitor/{visitor_id}/")
    Call<ReportedIssuesResponse> deleteVisitor(@Path("visitor_id")  String visitorId);

    //Update a visitor
    @PUT("/api/v1/stayabode/residents/visitor/{visitor_id}/")
    Call<RegisteredVisitorsReponse> updateAVisitor(@Path("visitor_id")  String visitorId,@Body RequestBody requestBody);

    //Submit issue feedback
    @PUT("/api/v1/stayabode/issues/issue/{issue_id}/")
    Call<IssueFeedbackResponse> sendIssueFeedback(@Path("issue_id")  String issueId, @Body RequestBody requestBody);

    //Submit general feedback
    @POST("/api/v1/stayabode/residents/general_feedback/")
    Call<GeneralFeedbackResponse>sendGeneralFeedback(@Body RequestBody requestBody);

    //Logout
    @POST("/api/v1/stayabode/users/logout/")
    Call<LogoutResponse>logoutUser(@Body RequestBody requestBody);

    //Refresh token
    @GET("/api/v1/stayabode/guests/refresh_token/")
    Call<RefreshTokenResponse> refreshToken(@QueryMap HashMap<String, String> params);


    //Fetch all notifications
    @GET("/api/v1/stayabode/residents/notifications/")
    Call<NotificationsReponse> fetchNotifications();

    //Apply for contract extension
    @POST("/api/v1/stayabode/guests/rentals/extend_contract/")
    Call<BaseResponse> submitContractExtension(@Body RequestBody requestBody);

    //cancel contract extension request
    @POST("api/v1/stayabode/guests/rentals/extend_contract/cancel/")
    Call<BaseResponse> cancelContract();

    //change rsvp
    @POST("/api/v1/stayabode/residents/events/{event_id}/rsvp/")
    Call<BaseResponse> setRsvp(@Path("event_id")  String eventId, @Body RequestBody requestBody);

    //change rsvp Preferences
    @POST("/api/v1/stayabode/residents/events/{event_id}/rsvp/")
    Call<BaseResponse> setRsvpWithPreferences(@Path("event_id")  String eventId,@Body Object data);

    //get event details
    @GET("/api/v1/stayabode/residents/events/{event_id}/")
    Call<EventDetailReponse> fetchEventDetails(@Path("event_id")  String eventId);

    //get list of events
    @GET("/api/v1/stayabode/residents/events/")
    Call<EventsReponse> fetchEvents();

    //get event preferences
    @GET("/api/v1/stayabode/residents/events/{event_id}/preferences/")
    Call<PreferencesReponse> getPreferences(@Path("event_id")  String eventId);

    //get event attendees
    @GET("/api/v1/stayabode/residents/events/{event_id}/attendees/")
    Call<AttendeesResponse> fetchAttendees(@Path("event_id")  String eventId);

    //get payment history
    @GET("/api/v1/stayabode/guests/rentals/history/")
    Call<PaymentHistoryResponse> requestPaymentHistory();

    //get payment history
    @POST("/api/v1/stayabode/guests/rentals/older_than_history/")
    Call<BaseResponse> requestOlderThanHistory();

    //

    @POST("login/")
    Call<AdminLoginResponse> loginAdmin(@Body AdminLoginPostResponse requestBody);

    @POST("getQuestionnaire/")
    Call<QuestionsResponse> fetchQuestions(@Body GetQuestionsPostRepsonse requestBody);



}


