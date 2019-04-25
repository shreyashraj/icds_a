package com.stayabode.net.response.postmodels;

import com.google.gson.annotations.SerializedName;
import com.stayabode.net.response.BaseResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class SubAnswersPostBaseResponse {
    private ArrayList<HashMap<Integer, SubAnswersPostResponse>> allUsersSubQuestionAnswersResponse;

    public ArrayList<HashMap<Integer, SubAnswersPostResponse>> getAllUsersSubQuestionAnswersResponse() {
        return allUsersSubQuestionAnswersResponse;
    }

    public void setAllUsersSubQuestionAnswersResponse(ArrayList<HashMap<Integer, SubAnswersPostResponse>> allUsersSubQuestionAnswersResponse) {
        this.allUsersSubQuestionAnswersResponse = allUsersSubQuestionAnswersResponse;
    }
}

