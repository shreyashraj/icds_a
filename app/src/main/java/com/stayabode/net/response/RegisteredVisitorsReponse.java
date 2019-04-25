package com.stayabode.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VarunBhalla on 16/12/16.
 */
public class RegisteredVisitorsReponse extends BaseResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        @SerializedName("visitors")
        private List<VisitorDetails> visitorDetailsList;

        public List<VisitorDetails> getVisitorDetailsList() {
            return visitorDetailsList;
        }

        public void setVisitorDetailsList(List<VisitorDetails> visitorDetailsList) {
            this.visitorDetailsList = visitorDetailsList;
        }
    }

    public static class VisitorDetails implements Parcelable{
        private String name;
        @SerializedName("check_in")
        private String checkIn;
        @SerializedName("check_out")
        private String checkOut;
        @SerializedName("phone")
        private String phoneNumber;
        private String gender;
        @SerializedName("is_editable")
        private Boolean isEditable;
        @SerializedName("visitor_id")
        private String visitorId;



        public VisitorDetails(String name, String checkIn, String checkOut, String phoneNumber, String gender,Boolean isEditable, String visitorId){
            this.name = name;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.phoneNumber = phoneNumber;
            this.gender = gender;
            this.isEditable = isEditable;
            this.visitorId = visitorId;
        }

        public String getVisitorId() {
            return visitorId;
        }

        public void setVisitorId(String visitorId) {
            this.visitorId = visitorId;
        }

        public Boolean getIsEditable() {
            return isEditable;
        }

        public void setIsEditable(Boolean isEditable) {
            this.isEditable = isEditable;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCheckIn() {
            return checkIn;
        }

        public void setCheckIn(String checkIn) {
            this.checkIn = checkIn;
        }

        public String getCheckOut() {
            return checkOut;
        }

        public void setCheckOut(String checkOut) {
            this.checkOut = checkOut;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name);
            parcel.writeString(checkIn);
            parcel.writeString(checkOut);
            parcel.writeString(phoneNumber);
            parcel.writeString(gender);
            parcel.writeString(isEditable+"");
            parcel.writeString(visitorId);
        }

        private VisitorDetails(Parcel in){
            this.name = in.readString();
            this.checkIn = in.readString();
            this.checkOut = in.readString();
            this.phoneNumber = in.readString();
            this.gender = in.readString();
            this.isEditable = Boolean.parseBoolean(in.readString());
            this.visitorId = in.readString();
        }

        public static final Parcelable.Creator<VisitorDetails> CREATOR = new Parcelable.Creator<VisitorDetails>() {

            @Override
            public VisitorDetails createFromParcel(Parcel source) {
                return new VisitorDetails(source);
            }

            @Override
            public VisitorDetails[] newArray(int size) {
                return new VisitorDetails[size];
            }
        };
    }
}
