package com.stayabode.net.response.getmodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.stayabode.net.response.BaseResponse;
import com.stayabode.net.response.ReportedIssuesResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class QuestionsResponse extends BaseResponse implements Parcelable {
    @SerializedName("response")
    public String response;

    @SerializedName("token")
    public String token;

    @SerializedName("data")
    private List<GroupObject> data;


    protected QuestionsResponse(Parcel in) {
        response = in.readString();
        token = in.readString();
        data=in.readArrayList(GroupObject.class.getClassLoader());

    }

    public static final Creator<QuestionsResponse> CREATOR = new Creator<QuestionsResponse>() {
        @Override
        public QuestionsResponse createFromParcel(Parcel in) {
            return new QuestionsResponse(in);
        }

        @Override
        public QuestionsResponse[] newArray(int size) {
            return new QuestionsResponse[size];
        }
    };

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<GroupObject> getData() {
        return data;
    }

    public void setData(List<GroupObject> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(response);
        dest.writeString(token);
        dest.writeList(data);
    }
}

