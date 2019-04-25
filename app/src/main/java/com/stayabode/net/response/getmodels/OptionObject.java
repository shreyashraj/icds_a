package com.stayabode.net.response.getmodels;

import android.os.Parcel;
import android.os.Parcelable;

public class OptionObject implements Parcelable {
    private String option;
    private String nq_id;

    public OptionObject(String option, String nq_id) {
        super();
        this.option = option;
        this.nq_id = nq_id;
    }

    protected OptionObject(Parcel in) {
        option = in.readString();

        nq_id = in.readString();

    }

    public static final Creator<OptionObject> CREATOR = new Creator<OptionObject>() {
        @Override
        public OptionObject createFromParcel(Parcel in) {
            return new OptionObject(in);
        }

        @Override
        public OptionObject[] newArray(int size) {
            return new OptionObject[size];
        }
    };

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }


    public String getNq_id() {
        return nq_id;
    }

    public void setNq_id(String nq_id) {
        this.nq_id = nq_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(option);
        dest.writeString(nq_id);

    }
}
