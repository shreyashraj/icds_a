package com.stayabode.net.response.getmodels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.stayabode.net.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class AdminLoginResponse extends BaseResponse implements Parcelable {
    @SerializedName("response")
    public String response;

    @SerializedName("token")
    public String token;

    @SerializedName("data")
    public ArrayList<String> data;


    protected AdminLoginResponse(Parcel in) {
        response = in.readString();
        token = in.readString();
        data = in.createStringArrayList();
    }

    public static final Creator<AdminLoginResponse> CREATOR = new Creator<AdminLoginResponse>() {
        @Override
        public AdminLoginResponse createFromParcel(Parcel in) {
            return new AdminLoginResponse(in);
        }

        @Override
        public AdminLoginResponse[] newArray(int size) {
            return new AdminLoginResponse[size];
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

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
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
        dest.writeStringList(data);
    }
}

