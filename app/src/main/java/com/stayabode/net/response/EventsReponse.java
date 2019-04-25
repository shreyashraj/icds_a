package com.stayabode.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VarunBhalla on 16/12/16.
 */
public class EventsReponse extends BaseResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        @SerializedName("events")
        private List<EventDetail> eventsList;

        public List<EventDetail> getEventsList() {
            return eventsList;
        }

        public void setEventsList(List<EventDetail> eventsList) {
            this.eventsList = eventsList;
        }
    }

    public class ImageUrl {
        @SerializedName("image_url")
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }



    public static class EventDetail implements Parcelable{
        @SerializedName("user_rsvp_guests")
        private String userRsvpGuests;
        @SerializedName("max_allowed_people_per_person")
        private String maxPeopleAllowedPerPerson;
        @SerializedName("max_allowed_people")
        private String maxPeopleAllowedEvent;
        @SerializedName("event_id")
        private String eventId;
        @SerializedName("event_end_date_time")
        private String eventEndDateTime;
        @SerializedName("event_start_date_time")
        private String eventStartDateTime;
        @SerializedName("event_expiry_date_time")
        private String eventExpiryDateTime;
        @SerializedName("venue")
        private String eventVenue;
        @SerializedName("title")
        private String eventTitle;
        @SerializedName("user_rsvp_status")
        private String rsvpStatus;
        @SerializedName("total_attending")
        private String totalAttending;
        @SerializedName("is_rsvp_allowed")
        private String isRsvpAllowed;
        @SerializedName("image_urls")
        private  List<ImageUrl> imageUrls;
        @SerializedName("preferences_present")
        private String isPreferencesPresent;
        @SerializedName("is_expired")
        private String isExpired;
        @SerializedName("is_rsvp")
        private String isRsvp;
        @SerializedName("is_multi_day_event")
        private String isMultiDayEvent;




        public EventDetail(String eventStartDateTime, String eventEndDateTime, String eventExpiryDateTime, String eventVenue,
                           String eventTitle, String isRsvpAllowed, String rsvpStatus, String totalAttending, List<ImageUrl> imageUrls,
                           String eventId, String maxPeopleAllowedPerPerson, String userRsvpGuests, String maxPeopleAllowedEvent
                , String isPreferencesPresent,String isExpired,String isRsvp,String isMultiDayEvent){
            this.eventStartDateTime = eventStartDateTime;
            this.eventEndDateTime = eventEndDateTime;
            this.eventExpiryDateTime = eventExpiryDateTime;
            this.eventVenue = eventVenue;
            this.eventTitle = eventTitle;
            this.isRsvpAllowed = isRsvpAllowed;
            this.rsvpStatus = rsvpStatus;
            this.totalAttending = totalAttending;
            this.imageUrls = imageUrls;
            this.eventId = eventId;
            this.maxPeopleAllowedPerPerson = maxPeopleAllowedPerPerson;
            this.userRsvpGuests = userRsvpGuests;
            this.maxPeopleAllowedEvent=maxPeopleAllowedEvent;
            this.isPreferencesPresent=isPreferencesPresent;
            this.isExpired = isExpired;
            this.isRsvp = isRsvp;
            this.isMultiDayEvent = isMultiDayEvent;

        }

        public EventDetail() {

        }

        public String getIsMultiDayEvent() {
            return isMultiDayEvent;
        }

        public void setIsMultiDayEvent(String isMultiDayEvent) {
            this.isMultiDayEvent = isMultiDayEvent;
        }

        public String getIsRsvp() {
            return isRsvp;
        }

        public void setIsRsvp(String isRsvp) {
            this.isRsvp = isRsvp;
        }

        public String getIsExpired() {
            return isExpired;
        }

        public void setIsExpired(String isExpired) {
            this.isExpired = isExpired;
        }

        public String getIsRsvpAllowed() {
            return isRsvpAllowed;
        }

        public void setIsRsvpAllowed(String isRsvpAllowed) {
            this.isRsvpAllowed = isRsvpAllowed;
        }

        public String getIsPreferencesPresent() {
            return isPreferencesPresent;
        }

        public void setIsPreferencesPresent(String isPreferencesPresent) {
            this.isPreferencesPresent = isPreferencesPresent;
        }

        public String getMaxPeopleAllowedEvent() {
            return maxPeopleAllowedEvent;
        }

        public void setMaxPeopleAllowedEvent(String maxPeopleAllowedEvent) {
            this.maxPeopleAllowedEvent = maxPeopleAllowedEvent;
        }

        public String getUserRsvpGuests() {
            return userRsvpGuests;
        }

        public void setUserRsvpGuests(String userRsvpGuests) {
            this.userRsvpGuests = userRsvpGuests;
        }

        public String getMaxPeopleAllowedPerPerson() {
            return maxPeopleAllowedPerPerson;
        }

        public void setMaxPeopleAllowedPerPerson(String maxPeopleAllowedPerPerson) {
            this.maxPeopleAllowedPerPerson = maxPeopleAllowedPerPerson;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public List<ImageUrl> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<ImageUrl> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public String getEventStartDateTime() {
            return eventStartDateTime;
        }

        public void setEventStartDateTime(String eventStartDateTime) {
            this.eventStartDateTime = eventStartDateTime;
        }

        public String getEventEndDateTime() {
            return eventEndDateTime;
        }

        public void setEventEndDateTime(String eventEndDateTime) {
            this.eventEndDateTime = eventEndDateTime;
        }

        public String getEventExpiryDateTime() {
            return eventExpiryDateTime;
        }

        public void setEventExpiryDateTime(String eventExpiryDateTime) {
            this.eventExpiryDateTime = eventExpiryDateTime;
        }

        public String getEventVenue() {
            return eventVenue;
        }

        public void setEventVenue(String eventVenue) {
            this.eventVenue = eventVenue;
        }

        public String getEventTitle() {
            return eventTitle;
        }

        public void setEventTitle(String eventTitle) {
            this.eventTitle = eventTitle;
        }

        public String getRsvpStatus() {
            return rsvpStatus;
        }

        public void setRsvpStatus(String rsvpStatus) {
            this.rsvpStatus = rsvpStatus;
        }

        public String getTotalAttending() {
            return totalAttending;
        }

        public void setTotalAttending(String totalAttending) {
            this.totalAttending = totalAttending;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {

            parcel.writeString(eventStartDateTime);
            parcel.writeString(eventEndDateTime);
            parcel.writeString(eventExpiryDateTime);
            parcel.writeString(eventVenue);
            parcel.writeString(eventTitle);
            parcel.writeString(isRsvpAllowed);
            parcel.writeString(rsvpStatus);
            parcel.writeString(totalAttending);
            parcel.writeString(eventId);
            parcel.writeString(userRsvpGuests);
            parcel.writeString(isPreferencesPresent);
            parcel.writeString(maxPeopleAllowedPerPerson);
            parcel.writeString(maxPeopleAllowedEvent);
            parcel.writeString(isExpired);
            parcel.writeString(isRsvp);
            parcel.writeString(isMultiDayEvent);
        }

        private EventDetail(Parcel in){
            this.eventStartDateTime = in.readString();
            this.eventEndDateTime = in.readString();
            this.eventExpiryDateTime = in.readString();
            this.eventVenue = in.readString();
            this.eventTitle = in.readString();
            this.isRsvpAllowed = in.readString();
            this.rsvpStatus = in.readString();
            this.totalAttending = in.readString();
            this.eventId = in.readString();
            this.userRsvpGuests = in.readString();
            this.isPreferencesPresent = in.readString();
            this.maxPeopleAllowedPerPerson=in.readString();
            this.maxPeopleAllowedEvent=in.readString();
            this.isExpired = in.readString();
            this.isRsvp = in.readString();
            this.isMultiDayEvent = in.readString();
        }

        public static final Creator<EventDetail> CREATOR = new Creator<EventDetail>() {

            @Override
            public EventDetail createFromParcel(Parcel source) {
                return new EventDetail(source);
            }

            @Override
            public EventDetail[] newArray(int size) {
                return new EventDetail[size];
            }
        };
    }
}
