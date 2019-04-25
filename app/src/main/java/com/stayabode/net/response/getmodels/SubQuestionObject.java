package com.stayabode.net.response.getmodels;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SubQuestionObject implements Parcelable {
    private String sub_id;
    private String q_id;
    private String g_name;

    private String sub_question;
    private String sub_type;
    private String sub_hint;
    private String sub_mandatory;
    private String sub_validation;
    private List<OptionObject> options;

    public SubQuestionObject(String sub_id, String g_name, String q_id, String sub_question, String sub_type, String sub_hint,
                             String sub_mandatory, String sub_validation, List<OptionObject> options) {
        super();
        this.sub_id = sub_id;
        this.g_name = g_name;
        this.q_id = q_id;
        this.sub_question = sub_question;
        this.sub_type = sub_type;
        this.sub_hint = sub_hint;
        this.sub_mandatory = sub_mandatory;
        this.sub_validation = sub_validation;
        this.options = options;
    }

    protected SubQuestionObject(Parcel in) {

        sub_id = in.readString();
        g_name = in.readString();
        q_id = in.readString();
        sub_question = in.readString();
        sub_type = in.readString();
        sub_hint = in.readString();
        sub_mandatory = in.readString();
        sub_validation = in.readString();
        options=in.readArrayList(OptionObject.class.getClassLoader());
    }

    public static final Creator<SubQuestionObject> CREATOR = new Creator<SubQuestionObject>() {
        @Override
        public SubQuestionObject createFromParcel(Parcel in) {
            return new SubQuestionObject(in);
        }

        @Override
        public SubQuestionObject[] newArray(int size) {
            return new SubQuestionObject[size];
        }
    };

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getQ_id() {
        return q_id;
    }

    public void setQ_id(String q_id) {
        this.q_id = q_id;
    }

    public String getSub_question() {
        return sub_question;
    }

    public void setSub_question(String sub_question) {
        this.sub_question = sub_question;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getSub_hint() {
        return sub_hint;
    }

    public void setSub_hint(String sub_hint) {
        this.sub_hint = sub_hint;
    }

    public String getSub_mandatory() {
        return sub_mandatory;
    }

    public void setSub_mandatory(String sub_mandatory) {
        this.sub_mandatory = sub_mandatory;
    }

    public String getSub_validation() {
        return sub_validation;
    }

    public void setSub_validation(String sub_validation) {
        this.sub_validation = sub_validation;
    }

    public List<OptionObject> getOptions() {
        return options;
    }

    public void setOptions(List<OptionObject> options) {
        this.options = options;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sub_id);
        dest.writeString(g_name);
        dest.writeString(q_id);
        dest.writeString(sub_question);
        dest.writeString(sub_type);
        dest.writeString(sub_hint);
        dest.writeString(sub_mandatory);
        dest.writeString(sub_validation);
        dest.writeList(options);
    }


}
