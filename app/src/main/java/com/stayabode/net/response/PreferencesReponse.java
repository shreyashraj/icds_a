package com.stayabode.net.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by VarunBhalla on 16/12/16.
 */
public class PreferencesReponse extends BaseResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class PreferencesDetail {
        @SerializedName("preference_id")
        private String preferenceId;
        @SerializedName("question")
        private String question;
        @SerializedName("answer")
        private String answer;

        public String getPreferenceId() {
            return preferenceId;
        }

        public void setPreferenceId(String preferenceId) {
            this.preferenceId = preferenceId;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }



    public static class Data {

        @SerializedName("preferences")
        private  List<PreferencesDetail> preferencesDetail;

        public List<PreferencesDetail> getPreferencesDetail() {
            return preferencesDetail;
        }

        public void setPreferencesDetail(List<PreferencesDetail> preferencesDetail) {
            this.preferencesDetail = preferencesDetail;
        }
    }
}
