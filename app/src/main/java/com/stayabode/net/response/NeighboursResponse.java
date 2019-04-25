package com.stayabode.net.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VarunBhalla on 17/12/16.
 */
public class NeighboursResponse extends BaseResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        @SerializedName("neighbours")
        private Neighbours neighbours;

        public Neighbours getNeighbours() {
            return neighbours;
        }

        public void setNeighbours(Neighbours neighbours) {
            this.neighbours = neighbours;
        }
    }

    public class Neighbours {
        @SerializedName("floor")
        private List<NeighbourDetails> floorNeighbours;

        @SerializedName("all")
        private List<NeighbourDetails> allNeighbours;

        public List<NeighbourDetails> getFloorNeighbours() {
            return floorNeighbours;
        }

        public void setFloorNeighbours(List<NeighbourDetails> floorNeighbours) {
            this.floorNeighbours = floorNeighbours;
        }

        public List<NeighbourDetails> getAllNeighBours() {
            return allNeighbours;
        }

        public void setAllNeighBours(List<NeighbourDetails> allNeighbours) {
            this.allNeighbours = allNeighbours;
        }
    }

    public class NeighbourDetails {
        private String name;
        @SerializedName("room_number")
        private String roomNumber;
        private String floor;
        @SerializedName("profile_pic")
        private String profilePic;
        private String sectionName;
        private int viewType;

        public String getSectionName() {
            return sectionName;
        }

        public void setSectionName(String sectionName) {
            this.sectionName = sectionName;
        }

        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }

        public NeighbourDetails() {
        }

        public NeighbourDetails(String name, String roomNumber, String profilePic, int viewType) {
            this.name = name;
            this.roomNumber = roomNumber;
            this.profilePic = profilePic;
            this.viewType = viewType;
        }

        public NeighbourDetails(int viewType, String sectionName) {
            this.viewType = viewType;
            this.sectionName = sectionName;
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

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getProfilePic() {
            return profilePic;
        }

        public void setProfilePic(String profilePic) {
            this.profilePic = profilePic;
        }
    }




}
