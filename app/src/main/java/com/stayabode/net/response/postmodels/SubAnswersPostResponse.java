package com.stayabode.net.response.postmodels;

import com.google.gson.annotations.SerializedName;
import com.stayabode.net.response.BaseResponse;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class SubAnswersPostResponse extends BaseResponse {
    @SerializedName("sub_q_id")
    private Integer questionId;
    @SerializedName("sub_answer")
    private String answer;
    @SerializedName("next_question_id")
    private Integer nextQuestionId;
    @SerializedName("name")
    private Integer name;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getNextQuestionId() {
        return nextQuestionId;
    }

    public void setNextQuestionId(Integer nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }
}

