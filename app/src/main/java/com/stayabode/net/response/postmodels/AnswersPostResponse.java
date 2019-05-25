package com.stayabode.net.response.postmodels;

import com.google.gson.annotations.SerializedName;
import com.stayabode.net.response.BaseResponse;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class AnswersPostResponse extends BaseResponse {
    @SerializedName("q_id")
    private Integer questionId;
    @SerializedName("q_name")
    private String questionName;
    @SerializedName("answer")
    private String answer;
    @SerializedName("is_mandatory")
    private String isMandatory;
    @SerializedName("next_question_id")
    private Integer nextQuestionId;
    @SerializedName("checkbox_answers")
    private HashSet<String> checkboxAnswers;
    @SerializedName("multipleInputAnswers")
    private ArrayList<String> multipleInputAnswers;
    @SerializedName("sub_question_resp")
    private SubAnswersPostBaseResponse subAnswersResponse;
    @SerializedName("validation")
    private String validation;

    @SerializedName("groupID")
    private String groupID;

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

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

    public SubAnswersPostBaseResponse getSubAnswersResponse() {
        return subAnswersResponse;
    }

    public void setSubAnswersResponse(SubAnswersPostBaseResponse subAnswersResponse) {
        this.subAnswersResponse = subAnswersResponse;
    }

    public HashSet<String> getCheckboxAnswers() {
        return checkboxAnswers;
    }

    public void setCheckboxAnswers(HashSet<String> checkboxAnswers) {
        this.checkboxAnswers = checkboxAnswers;
    }

    public ArrayList<String> getMultipleInputAnswers() {
        return multipleInputAnswers;
    }

    public void setMultipleInputAnswers(ArrayList<String> multipleInputAnswers) {
        this.multipleInputAnswers = multipleInputAnswers;
    }

    public String getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(String isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
}

