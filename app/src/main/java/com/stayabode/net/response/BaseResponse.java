package com.stayabode.net.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VarunBhalla on 13/10/16.
 */
public class BaseResponse {
    @SerializedName("message")
    public String message;

    @SerializedName("status")
    public String status;

    public int code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    @Override
    public String toString() {
        return "BaseResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

}
