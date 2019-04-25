package com.stayabode.net.response.getmodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class GroupObject implements Parcelable {
    private String g_id;
    private String g_name;
    private List<QuestionObject> data;

    public GroupObject(String g_id, String g_name, List<QuestionObject> data) {
        super();
        this.g_id = g_id;
        this.g_name = g_name;
        this.data = data;
    }

    protected GroupObject(Parcel in) {
        g_id = in.readString();
        g_name = in.readString();
    }

    public static final Creator<GroupObject> CREATOR = new Creator<GroupObject>() {
        @Override
        public GroupObject createFromParcel(Parcel in) {
            return new GroupObject(in);
        }

        @Override
        public GroupObject[] newArray(int size) {
            return new GroupObject[size];
        }
    };


    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public List<QuestionObject> getData() {
        return data;
    }

    public void setData(List<QuestionObject> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(g_id);
        dest.writeString(g_name);
    }
}
