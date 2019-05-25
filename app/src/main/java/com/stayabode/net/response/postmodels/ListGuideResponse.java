package com.stayabode.net.response.postmodels;

import com.google.gson.annotations.SerializedName;
import com.stayabode.net.response.BaseResponse;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class ListGuideResponse extends BaseResponse {
    @SerializedName("question_id")
    private String questionId;
    @SerializedName("title")
    private String title;
    @SerializedName("selection")
    private String selection;
    @SerializedName("groupID")
    private String groupId;


    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}

