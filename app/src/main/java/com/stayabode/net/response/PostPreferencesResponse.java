package com.stayabode.net.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Arpit on 2/23/17.
 */

public class PostPreferencesResponse {
    @SerializedName("status")
    String status;
    @SerializedName("no_of_guests")
    String numberOfGuests;
    @SerializedName("preferences")
    ArrayList<PreferencesDetail> preferencesdetail=new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(String numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public ArrayList<PreferencesDetail> getPreferences() {
        return preferencesdetail;
    }

    public void setPreferences(ArrayList<PreferencesDetail> preferencesdetail) {
        this.preferencesdetail = preferencesdetail;
    }

    public PostPreferencesResponse(String status, String numberOfGuests, ArrayList<PreferencesDetail> preferencesdetail) {
        this.status = status;
        this.numberOfGuests = numberOfGuests;
        this.preferencesdetail = preferencesdetail;
    }

    public static class PreferencesDetail{

        @SerializedName("preference_id")
        private String preferenceId;
        @SerializedName("answer")
        private String answer;

        public PreferencesDetail(String preferenceId, String answer) {
            this.preferenceId = preferenceId;
            this.answer = answer;
        }

        public String getPreferenceId() {
            return preferenceId;
        }

        public void setPreferenceId(String preferenceId) {
            this.preferenceId = preferenceId;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
