package com.stayabode.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VarunBhalla on 15/12/16.
 */
public class DashboardResponse extends BaseResponse {
    private Data data;


    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        @SerializedName("broadcasts")
        private List<Broadcast> broadcastList;

        @SerializedName("community_manager_phone")
        private String communityManagerPhone;

        @SerializedName("issues")
        private List<Issues>  issues;

        @SerializedName("notifications")
        private List<Notifications>  notifications;

        @SerializedName("user")
        private UserDetails userDetails;

        @SerializedName("community_manager_json")
        private CommunityManagerDetails communityManagerDetails;

        @SerializedName("greeting")
        private String greeting;

        @SerializedName("greeting_message")
        private String greetingMessage;

        @SerializedName("day_time")
        private String dayTime;

        @SerializedName("total_non_expired_events_count")
        private String eventsCount;

        @SerializedName("contract_extension_status")
        private String contractExtensionstatus;

        @SerializedName("contract_extension_requested_date")
        private String contractExtensionRequestedDate;

        public String getContractExtensionRequestedDate() {
            return contractExtensionRequestedDate;
        }

        public void setContractExtensionRequestedDate(String contractExtensionRequestedDate) {
            this.contractExtensionRequestedDate = contractExtensionRequestedDate;
        }

        public CommunityManagerDetails getCommunityManagerDetails() {
            return communityManagerDetails;
        }

        public void setCommunityManagerDetails(CommunityManagerDetails communityManagerDetails) {
            this.communityManagerDetails = communityManagerDetails;
        }

        public String getContractExtensionstatus() {
            return contractExtensionstatus;
        }

        public void setContractExtensionstatus(String contractExtensionstatus) {
            this.contractExtensionstatus = contractExtensionstatus;
        }

        public String getEventsCount() {
            return eventsCount;
        }

        public void setEventsCount(String eventsCount) {
            this.eventsCount = eventsCount;
        }

        public String getGreetingMessage() {
            return greetingMessage;
        }

        public void setGreetingMessage(String greetingMessage) {
            this.greetingMessage = greetingMessage;
        }

        public String getDayTime() {
            return dayTime;
        }

        public void setDayTime(String dayTime) {
            this.dayTime = dayTime;
        }

        public String getGreeting() {
            return greeting;
        }

        public void setGreeting(String greeting) {
            this.greeting = greeting;
        }

        public List<Broadcast> getBroadcastList() {
            return broadcastList;
        }

        public void setBroadcastList(List<Broadcast> broadcastList) {
            this.broadcastList = broadcastList;
        }

        public UserDetails getUserDetails() {
            return userDetails;
        }

        public void setUserDetails(UserDetails userDetails) {
            this.userDetails = userDetails;
        }

        public List<Issues>  getIssues() {
            return issues;
        }

        public void setIssues(List<Issues>  issues) {
            this.issues = issues;
        }



        public List<Notifications>  getNotifications() {
            return notifications;
        }

        public void setNotifications(List<Notifications>  notifications) {
            this.notifications = notifications;
        }




        public String getCommunityManagerPhone() {
            return communityManagerPhone;
        }

        public void setCommunityManagerPhone(String communityManagerPhone) {
            this.communityManagerPhone = communityManagerPhone;
        }
    }


    public class CommunityManagerDetails{

        @SerializedName("phone_number")
        private String phoneNumber;
        @SerializedName("profile_pic")
        private String profilePic;
        @SerializedName("name")
        private String managerName;

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }

        public String getManagerName() {
            return managerName;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }
    }

    public class Issues{

        @SerializedName("issue_id")
        private String id;
        @SerializedName("category")
        private String category;
        @SerializedName("description")
        private String description;
        @SerializedName("reported_at")
        private String reported_at;
        @SerializedName("status")
        private String status;
        @SerializedName("sub_category")
        private String subCategory;


        public String getSubCategory() {
            return subCategory;
        }

        public void setSubCategory(String subCategory) {
            this.subCategory = subCategory;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getReported_at() {
            return reported_at;
        }

        public void setReported_at(String reported_at) {
            this.reported_at = reported_at;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public class Notifications{


        @SerializedName("title")
        private String title;
        @SerializedName("body")
        private String body;
        @SerializedName("click_action")
        private String clickAction;
        @SerializedName("persistent")
        private String persistent;
        @SerializedName("id")
        private String notificationId;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getClickAction() {
            return clickAction;
        }

        public void setClickAction(String clickAction) {
            this.clickAction = clickAction;
        }

        public String getPersistent() {
            return persistent;
        }

        public void setPersistent(String persistent) {
            this.persistent = persistent;
        }

        public String getNotificationId() {
            return notificationId;
        }

        public void setNotificationId(String notificationId) {
            this.notificationId = notificationId;
        }
    }


    public class UserDetails {
        @SerializedName("phone_number")
        private String phoneNo;
        @SerializedName("first_name")
        private String firstName;
        @SerializedName("last_name")
        private String lastName;
        @SerializedName("check_in")
        private String checkIn;
        @SerializedName("check_out")
        private String checkOut;
        @SerializedName("room_number")
        private String room_number;
        @SerializedName("user_id")
        private String userId;
        @SerializedName("gender")
        private String gender;
        @SerializedName("community")
        private String community;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }


        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCommunity() {
            return community;
        }

        public void setCommunity(String community) {
            this.community = community;
        }

        public String getRoom_number() {
            return room_number;
        }

        public void setRoom_number(String room_number) {
            this.room_number = room_number;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
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
    }


    public static class Broadcast implements Parcelable {

        @SerializedName("event_end_date_time")
        private String eventEndDateTime;
        @SerializedName("event_id")
        private String eventId;
        @SerializedName("expiry_date")
        private String expiryDate;
        @SerializedName("is_event")
        private String isEvent;
        @SerializedName("message")
        private String message;
        @SerializedName("sender")
        private String sender;
        @SerializedName("role")
        private String role;
        @SerializedName("broadcast_id")
        private String broadcastId;
        @SerializedName("venue")
        private String venue;
        @SerializedName("event_date_time")
        private String eventDateTime;
        @SerializedName("profile_pic")
        private String profileImageUrl;

        @SerializedName("is_multi_day_event")
        private String isMultiDayEvent;


        protected Broadcast(Parcel in) {
            message = in.readString();
            sender = in.readString();
            role = in.readString();
            broadcastId = in.readString();
            venue = in.readString();
            eventDateTime = in.readString();
            profileImageUrl=in.readString();

            eventEndDateTime = in.readString();
            eventId = in.readString();
            expiryDate = in.readString();
            isEvent=in.readString();

            isMultiDayEvent=in.readString();
        }

        public static final Creator<Broadcast> CREATOR = new Creator<Broadcast>() {
            @Override
            public Broadcast createFromParcel(Parcel in) {
                return new Broadcast(in);
            }

            @Override
            public Broadcast[] newArray(int size) {
                return new Broadcast[size];
            }
        };

        public String getIsMultiDayEvent() {
            return isMultiDayEvent;
        }

        public void setIsMultiDayEvent(String isMultiDayEvent) {
            this.isMultiDayEvent = isMultiDayEvent;
        }

        public String getEventEndDateTime() {
            return eventEndDateTime;
        }

        public void setEventEndDateTime(String eventEndDateTime) {
            this.eventEndDateTime = eventEndDateTime;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }

        public String getIsEvent() {
            return isEvent;
        }

        public void setIsEvent(String isEvent) {
            this.isEvent = isEvent;
        }

        public String getProfileImageUrl() {
            return profileImageUrl;
        }

        public void setProfileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
        }

        public String getBroadcastId() {
            return broadcastId;
        }

        public void setBroadcastId(String broadcastId) {
            this.broadcastId = broadcastId;
        }


        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public String getEventDateTime() {
            return eventDateTime;
        }

        public void setEventDateTime(String eventDateTime) {
            this.eventDateTime = eventDateTime;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(message);
            dest.writeString(sender);
            dest.writeString(role);
            dest.writeString(broadcastId);
            dest.writeString(venue);
            dest.writeString(eventDateTime);
            dest.writeString(profileImageUrl);
            dest.writeString(eventEndDateTime);
            dest.writeString(eventId);
            dest.writeString(expiryDate);
            dest.writeString(isEvent);
            dest.writeString(isMultiDayEvent);
        }
    }
}
