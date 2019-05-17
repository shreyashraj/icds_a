package com.stayabode.net.response.postmodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by VarunBhalla on 16/12/16.
 */

public class HomeVisitObject implements Parcelable {

    private String uuid;
    private String benificiaryname;
    private String qid;
    private String qname;

    public HomeVisitObject(Parcel in) {
        uuid = in.readString();
        benificiaryname = in.readString();
        qid = in.readString();
        qname = in.readString();
    }

    public static final Creator<HomeVisitObject> CREATOR = new Creator<HomeVisitObject>() {
        @Override
        public HomeVisitObject createFromParcel(Parcel in) {
            return new HomeVisitObject(in);
        }

        @Override
        public HomeVisitObject[] newArray(int size) {
            return new HomeVisitObject[size];
        }
    };

    public HomeVisitObject() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBenificiaryname() {
        return benificiaryname;
    }

    public void setBenificiaryname(String benificiaryname) {
        this.benificiaryname = benificiaryname;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(benificiaryname);
        dest.writeString(qid);
        dest.writeString(qname);
    }
}

