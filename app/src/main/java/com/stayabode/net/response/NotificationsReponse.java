package com.stayabode.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VarunBhalla on 16/12/16.
 */
public class NotificationsReponse extends BaseResponse {
    @SerializedName("data")
    private List<NotificationDetails> data;

    public List<NotificationDetails> getNotificationsDetails() {
        return data;
    }

    public void getNotificationsDetails(List<NotificationDetails> data) {
        this.data = data;
    }


    public static class NotificationDetails implements Parcelable{
        @SerializedName("id")
        private String notificationId;
        @SerializedName("title")
        private String notificationTitle;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("body")
        private String notificationBody;
        @SerializedName("click_action")
        private String clickAction;
        private String notificationStatus;


        public NotificationDetails(String notificationId, String notificationTitle, String createdAt, String notificationBody, String clickAction,  String notificationStatus){
            this.notificationId = notificationId;
            this.notificationTitle = notificationTitle;
            this.createdAt = createdAt;
            this.notificationBody = notificationBody;
            this.clickAction = clickAction;
            this.notificationStatus = notificationStatus;

        }

        public String getNotificationStatus() {
            return notificationStatus;
        }

        public void setNotificationStatus(String notificationStatus) {
            this.notificationStatus = notificationStatus;
        }

        public String getNotificationId() {
            return notificationId;
        }

        public void setNotificationId(String notificationId) {
            this.notificationId = notificationId;
        }

        public String getNotificationTitle() {
            return notificationTitle;
        }

        public void setNotificationTitle(String notificationTitle) {
            this.notificationTitle = notificationTitle;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getNotificationBody() {
            return notificationBody;
        }

        public void setNotificationBody(String notificationBody) {
            this.notificationBody = notificationBody;
        }

        public String getClickAction() {
            return clickAction;
        }

        public void setClickAction(String clickAction) {
            this.clickAction = clickAction;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(notificationId);
            parcel.writeString(notificationTitle);
            parcel.writeString(createdAt);
            parcel.writeString(notificationBody);
            parcel.writeString(clickAction);
            parcel.writeString(notificationStatus);

        }

        private NotificationDetails(Parcel in){
            this.notificationId = in.readString();
            this.notificationTitle = in.readString();
            this.createdAt = in.readString();
            this.notificationBody = in.readString();
            this.clickAction = in.readString();
            this.notificationStatus = in.readString();
        }

        public static final Creator<NotificationDetails> CREATOR = new Creator<NotificationDetails>() {

            @Override
            public NotificationDetails createFromParcel(Parcel source) {
                return new NotificationDetails(source);
            }

            @Override
            public NotificationDetails[] newArray(int size) {
                return new NotificationDetails[size];
            }
        };
    }
}
