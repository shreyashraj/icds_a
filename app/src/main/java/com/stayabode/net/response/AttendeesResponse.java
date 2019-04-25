package com.stayabode.net.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VarunBhalla on 17/12/16.
 */
public class AttendeesResponse extends BaseResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("guest_list")
        private List<AttendeesDetails> attendees;

        public List<AttendeesDetails> getAttendees() {
            return attendees;
        }

        public void setAttendees(List<AttendeesDetails> attendees) {
            this.attendees = attendees;
        }
    }


    public class AttendeesDetails {
        @SerializedName("building")
        private String building;

        @SerializedName("image_url")
        private String imageUrl;

        @SerializedName("name")
        private String name;

        @SerializedName("room")
        private String roomNumber;

        @SerializedName("user_rsvp_guests")
        private int userRsvpGuests;


        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public int getUserRsvpGuests() {
            return userRsvpGuests;
        }

        public void setUserRsvpGuests(int userRsvpGuests) {
            this.userRsvpGuests = userRsvpGuests;
        }
    }




}
