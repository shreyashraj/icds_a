package com.stayabode.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VarunBhalla on 16/12/16.
 */
public class EventDetailReponse extends BaseResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
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



    public static class Data implements Parcelable{
        @SerializedName("user_rsvp_guests")
        private String userRsvpGuests;
        @SerializedName("max_allowed_people_per_person")
        private String maxPeopleAllowedPerPerson;
        @SerializedName("max_allowed_people")
        private String maxPeopleAllowedEvent;
        @SerializedName("event_id")
        private String eventId;
        @SerializedName("event_start_date_time")
        private String eventStartDateTime;
        @SerializedName("event_end_date_time")
        private String eventEndDateTime;
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
        private Boolean isRsvpAllowed;
        @SerializedName("description")
        private String description;
        @SerializedName("venue_latitude")
        private String venueLatitude;
        @SerializedName("venue_longitude")
        private String venueLongitude;
        @SerializedName("image_urls")
        private  List<ImageUrl> imageUrls;
        @SerializedName("host_name")
        private String hostName;
        @SerializedName("host_designation")
        private String hostDesignation;
        @SerializedName("host_image_url")
        private String hostImageUrl;
        @SerializedName("is_expired")
        private String isExpired;
        @SerializedName("is_rsvp")
        private String isRsvp;
        @SerializedName("preferences_present")
        private String isPreferencesPresent;
        @SerializedName("is_multi_day_event")
        private String isMultiDayEvent;



        public Data(String eventStartDateTime, String eventEndDateTime, String eventExpiryDateTime, String eventVenue,
                    String eventTitle, Boolean isRsvpAllowed, String rsvpStatus, String totalAttending, List<ImageUrl> imageUrls,
                    String eventId, String maxPeopleAllowedPerPerson, String userRsvpGuests,
                    String description, String venueLatitude, String venueLongitude,
                    String hostName, String hostDesignation, String hostImageUrl,String maxPeopleAllowedEvent,
                    String isExpired,String isRsvp,String isPreferencesPresent,String isMultiDayEvent){
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
            this.maxPeopleAllowedEvent = maxPeopleAllowedEvent;
            this.userRsvpGuests = userRsvpGuests;

            this.description = description;
            this.venueLatitude = venueLatitude;
            this.venueLongitude = venueLongitude;

            this.hostName = hostName;
            this.hostDesignation = hostDesignation;
            this.hostImageUrl = hostImageUrl;

            this.isExpired = isExpired;
            this.isRsvp = isRsvp;
            this.isPreferencesPresent=isPreferencesPresent;
            this.isMultiDayEvent=isMultiDayEvent;

        }

        public String getIsMultiDayEvent() {
            return isMultiDayEvent;
        }

        public void setIsMultiDayEvent(String isMultiDayEvent) {
            this.isMultiDayEvent = isMultiDayEvent;
        }

        public String getIsPreferencesPresent() {
            return isPreferencesPresent;
        }

        public void setIsPreferencesPresent(String isPreferencesPresent) {
            this.isPreferencesPresent = isPreferencesPresent;
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

        public String getMaxPeopleAllowedEvent() {
            return maxPeopleAllowedEvent;
        }

        public void setMaxPeopleAllowedEvent(String maxPeopleAllowedEvent) {
            this.maxPeopleAllowedEvent = maxPeopleAllowedEvent;
        }

        public String getHostName() {
            return hostName;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public String getHostDesignation() {
            return hostDesignation;
        }

        public void setHostDesignation(String hostDesignation) {
            this.hostDesignation = hostDesignation;
        }

        public String getHostImageUrl() {
            return hostImageUrl;
        }

        public void setHostImageUrl(String hostImageUrl) {
            this.hostImageUrl = hostImageUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVenueLatitude() {
            return venueLatitude;
        }

        public void setVenueLatitude(String venueLatitude) {
            this.venueLatitude = venueLatitude;
        }

        public String getVenueLongitude() {
            return venueLongitude;
        }

        public void setVenueLongitude(String venueLongitude) {
            this.venueLongitude = venueLongitude;
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

        public Boolean getRsvpAllowed() {
            return isRsvpAllowed;
        }

        public void setRsvpAllowed(Boolean rsvpAllowed) {
            isRsvpAllowed = rsvpAllowed;
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
            parcel.writeString(isRsvpAllowed+"");
            parcel.writeString(rsvpStatus);
            parcel.writeString(totalAttending);
            parcel.writeString(eventId);
            parcel.writeString(userRsvpGuests);

            parcel.writeString(description);
            parcel.writeString(venueLatitude);
            parcel.writeString(venueLongitude);

            parcel.writeString(hostName);
            parcel.writeString(hostDesignation);
            parcel.writeString(hostImageUrl);

            parcel.writeString(isExpired);
            parcel.writeString(isRsvp);
            parcel.writeString(isPreferencesPresent);

            parcel.writeString(isMultiDayEvent);
        }

        private Data(Parcel in){
            this.eventStartDateTime = in.readString();
            this.eventEndDateTime = in.readString();
            this.eventExpiryDateTime = in.readString();
            this.eventVenue = in.readString();
            this.eventTitle = in.readString();
            this.isRsvpAllowed = Boolean.parseBoolean(in.readString());
            this.rsvpStatus = in.readString();
            this.totalAttending = in.readString();
            this.eventId = in.readString();
            this.userRsvpGuests = in.readString();

            this.description = in.readString();
            this.venueLatitude = in.readString();
            this.venueLongitude = in.readString();

            this.hostName = in.readString();
            this.hostDesignation = in.readString();
            this.hostImageUrl = in.readString();

            this.isExpired = in.readString();
            this.isRsvp = in.readString();

            this.isPreferencesPresent = in.readString();

            this.isMultiDayEvent = in.readString();
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {

            @Override
            public Data createFromParcel(Parcel source) {
                return new Data(source);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };
    }
}
